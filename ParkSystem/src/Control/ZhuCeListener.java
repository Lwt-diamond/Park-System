package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import dao.ZhuCeDB;
import entity.ParkUser;
import view.AdminInfoAddJFrame;
import view.ZhuCeJFrame;

public class ZhuCeListener implements ActionListener{
    ZhuCeJFrame zhuCeJFrame;
    public ZhuCeListener(ZhuCeJFrame zhuCeJFrame) {
        this.zhuCeJFrame = zhuCeJFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn=e.getActionCommand();
        if(btn.equals("重置")) {
            this.zhuCeJFrame.userNamejtf.setText("");
            this.zhuCeJFrame.passwordjtf.setText("");
            this.zhuCeJFrame.repasswordjtf.setText("");
            String right="";
            Enumeration<AbstractButton> radioBtns=zhuCeJFrame.buttonGroup.getElements();
            while (radioBtns.hasMoreElements()) {
                AbstractButton abstractButton = radioBtns.nextElement();
                abstractButton.setSelected(true);
                break;
//				if(abstractButton.isSelected()){
//					abstractButton.setSelected(false);
//					right=abstractButton.getText();
//					System.out.println(right);
//					break;
//				}
            }
        }else if(btn.equals("注册")){
            String userName =this.zhuCeJFrame.userNamejtf.getText();
            String password =this.zhuCeJFrame.passwordjtf.getText();
            System.out.println(password);
            String repassword = this.zhuCeJFrame.repasswordjtf.getText();
            System.out.println(repassword);
            String right="";
            Enumeration<AbstractButton> radioBtns=zhuCeJFrame.buttonGroup.getElements();
            while (radioBtns.hasMoreElements()) {
                AbstractButton abstractButton = radioBtns.nextElement();
                if(abstractButton.isSelected()){
                    right=abstractButton.getText();
                    break;
                }
            }
            if(userName.equals("")||repassword.equals("")||repassword.equals("")||right.equals("")) {
                JOptionPane.showMessageDialog(null, "信息不能为空!");
            }else if(!password.equals(repassword)) {
                JOptionPane.showMessageDialog(null, "两次密码不一致!");
            }else {
                if(right.equals("管理员")) {
                    ParkUser parkUser =new ParkUser(userName,password,right);
                    AdminInfoAddJFrame adminInfoAddJFrame
                            = new AdminInfoAddJFrame(zhuCeJFrame,parkUser);
                    adminInfoAddJFrame.userNameLabel.setText(adminInfoAddJFrame.userNameLabel.getText()+userName);
                    adminInfoAddJFrame.setVisible(true);
                    zhuCeJFrame.dispose();
                }else if(right.equals("普通用户")) {
                    
                    ParkUser parkUser =new ParkUser(userName,password,right);
                    ZhuCeDB.createUser(parkUser);
                    zhuCeJFrame.dengLuJFrame.setVisible(true);
                    zhuCeJFrame.dispose();
                }
              
            }
        }

    }

}
