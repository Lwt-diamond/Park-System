package Control;

import dao.AdminInfoAddDB;
import dao.ZhuCeDB;
import entity.ParkAdmin;
import view.AdminInfoAddJFrame;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

public class AdminInfoAddListener implements ActionListener {
    AdminInfoAddJFrame adminInfoAddJFrame;


    public AdminInfoAddListener(AdminInfoAddJFrame g) {
        this.adminInfoAddJFrame = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e);
        String btn = e.getActionCommand();
        if(btn.equals("返回")){
            //点击取消按钮，返回注册页面
            adminInfoAddJFrame.zhuCeJFrame.setVisible(true);
            adminInfoAddJFrame.dispose();
            System.out.println("取消");
        }else if(btn.equals("确定")){
            //点击确认，跳转到登录页面
            System.out.println("确定");
            //判断数据
            String address= adminInfoAddJFrame.adminAdressjtf.getText().trim();
            String name = adminInfoAddJFrame.adminNamejtf.getText().trim();
            String id = adminInfoAddJFrame.adminIDjtf.getText().trim();
            String sex ="";
            Enumeration<AbstractButton> radioBtns= adminInfoAddJFrame.buttonGroup.getElements();
            while (radioBtns.hasMoreElements()) {
                AbstractButton abstractButton = radioBtns.nextElement();
                if(abstractButton.isSelected()){
                    sex=abstractButton.getText();
                    break;
                }
            }
            String tel = adminInfoAddJFrame.adminTeljtf.getText().trim();
            String birthday = adminInfoAddJFrame.dp.getText();
            System.out.println(adminInfoAddJFrame.parkUser.getUserName());
            System.out.println(name);
            System.out.println(id);
            System.out.println(birthday);
            System.out.println(sex);
            System.out.println(address);
            System.out.println(tel);
            File file = adminInfoAddJFrame.file;
            System.out.println(file);
            //简单判断信息是否正确
            if(file!=null&&!address.equals("")&&!name.equals("")&&!id.equals("")&&
                    !birthday.equals("")&&!sex.equals("")&&!tel.equals("")){
                ParkAdmin parkAdmin = new ParkAdmin(adminInfoAddJFrame.parkUser.getUserName(),name,id,birthday,sex,address,tel,file.getAbsolutePath());
                AdminInfoAddDB.addAdminInfo(parkAdmin);
                ZhuCeDB.createUser(adminInfoAddJFrame.parkUser);
                adminInfoAddJFrame.zhuCeJFrame.dengLuJFrame.setVisible(true);
                adminInfoAddJFrame.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"信息有误，请认真填写！");
            }

        }else if(btn.equals("选择照片")){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Open JPEG file");
            //设置文件过滤
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if(f.getName().contains(".jpg") ||f.getName().contains(".jpeg")|| f.isDirectory()){
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return "jpg文件";
                }
            });
            int result = jFileChooser.showOpenDialog(adminInfoAddJFrame); // 打开”打开文件”对话框
            // int result = jFileChooser.showSaveDialog(this); // 打”开保存文件”对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                adminInfoAddJFrame.photoLabel.setIcon(new ImageIcon(String.valueOf(file)));
                System.out.println(file.getPath());
                adminInfoAddJFrame.file =file;
            }

        }
    }
}
