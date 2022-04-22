package Control;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;


import dao.AdminJPanelUserInfoDB;
import entity.ParkAdmin;
import entity.ParkUser;
import view.panel.AdminJPanel;

public class AdminJPanelListener implements ActionListener, MouseListener {
    public AdminJPanel adminJPanel;
    public AdminJPanelListener(AdminJPanel adminJPanel) {
        this.adminJPanel =adminJPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn=e.getActionCommand();
        if(btn.equals("Update")) {
            int row = adminJPanel.userTable.getSelectedRow();
            if(row<0) {
                JOptionPane.showMessageDialog(null, "选中一行");
                return;
            }
            String username= adminJPanel.userTable.getValueAt(row, 0).toString();
            String userpassword=adminJPanel.userTable.getValueAt(row, 1).toString();
            String userright= adminJPanel.userTable.getValueAt(row, 2).toString();
            String userName=adminJPanel.userNamejtf.getText();
            String userPassword = adminJPanel.passwordjtf.getText();
            String userRight =(String)adminJPanel.comboBox.getSelectedItem();
            if(userName.equals("")||userPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "信息不完整");
                return;
            }
            String sql="update parkUser set ";
            if(!username.equals(userName)) {
                sql=sql+" userName='"+userName+"'";
            }
            if(!userpassword.equals(userPassword)) {
                sql=sql+" userPassword='"+userPassword+"'";
            }
            if(!userright.equals(userRight)) {
                sql=sql+" userRight='"+userRight+"'";
            }
            sql+=" where userName='"+username+"'";
            System.out.println(sql);
            AdminJPanelUserInfoDB.deleteUserInfo(sql);
            adminJPanel.refreshBtn.doClick();

        }else if(btn.equals("Delete")) {
            int row = adminJPanel.userTable.getSelectedRow();
            if(row<0) {
                JOptionPane.showMessageDialog(null, "选中一行");
                return;
            }
            String userName= adminJPanel.userTable.getValueAt(row, 0).toString();
            String userPassword=adminJPanel.userTable.getValueAt(row, 1).toString();
            String userRight= adminJPanel.userTable.getValueAt(row, 2).toString();
            int type= JOptionPane.showConfirmDialog(null, "Delete?");
            if(type==0) {
                String sql="delete from parkUser where userName='"+userName+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
                adminJPanel.refreshBtn.doClick();
            }else {
                return;
            }

        }else if(btn.equals("Find")) {
            String userName=adminJPanel.userNamejtf.getText();
            String userPassword = adminJPanel.passwordjtf.getText();
            String userRight =(String)adminJPanel.comboBox.getSelectedItem();
            String sql="select * from parkUser where userName is not null";
            if(userName.equals("")&&userPassword.equals("")&& userRight.equals("")) {
                JOptionPane.showMessageDialog(null, "请至少输入一个信息");
                return;
            }else if(!userName.equals("")) {
                sql+=" and userName  like '"+userName+"'";
            }else if(!userPassword.equals("")) {
                sql+=" and userPassword  like '"+userPassword+"%'";
            }else if(!userRight.equals("")) {
                sql+=" and userRight='"+userRight+"'";
            }
            System.out.println(sql);
            Vector<Vector<String>> userRows=AdminJPanelUserInfoDB.findUserInfo(sql);
            Vector<String> userCols = new Vector<String>();
            userCols.add("userName");
            userCols.add("userPassword");
            userCols.add("userRight");
            adminJPanel.userModel.setDataVector(userRows, userCols);

            ControlListener.fitTableColumns(adminJPanel.userTable);
        }else if(btn.equals("Add")) {

            String userName=adminJPanel.userNamejtf.getText();
            String userPassword = adminJPanel.passwordjtf.getText();
            String userRight =(String)adminJPanel.comboBox.getSelectedItem();

            if(userName.equals("")&&userPassword.equals("")&& userRight.equals("")) {
                JOptionPane.showMessageDialog(null, "输入完整信息");
                return;
            }
            ParkUser parkUser = new ParkUser(userName,userPassword,userRight);
            AdminJPanelUserInfoDB.InsertUserInfo(parkUser);
            Vector<Vector<String>> userRows=AdminJPanelUserInfoDB.getUserInfo();
            Vector<String> userCols = new Vector<String>();
            userCols.add("userName");
            userCols.add("userPassword");
            userCols.add("userRight");
            adminJPanel.userModel.setDataVector(userRows, userCols);

            ControlListener.fitTableColumns(adminJPanel.userTable);
        }else if(btn.equals("RefreshTable")) {
            Vector<String> userCols = new Vector<String>();
            userCols.add("userName");
            userCols.add("userPassword");
            userCols.add("userRight");
            Vector<Vector<String>> userRows= AdminJPanelUserInfoDB.getUserInfo();

            adminJPanel.userModel.setDataVector(userRows, userCols);
        }else if(btn.equals("Choose")) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Open JPEG file");
            //设置文件过滤
//	            jFileChooser.setFileFilter(new FileFilter() {
//	                @Override
//	                public boolean accept(File f) {
//	                    if(f.getName().contains(".jpg") ||f.getName().contains(".jpeg")|| f.isDirectory()){
//	                        return true;
//	                    }
//	                    return false;
//	                }
//
//	                @Override
//	                public String getDescription() {
//	                    return "jpg文件";
//	                }
//	            });
            int result = jFileChooser.showOpenDialog(adminJPanel); // 打开”打开文件”对话框
            // int result = jFileChooser.showSaveDialog(this); // 打”开保存文件”对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                adminJPanel.photoLabel.setIcon(new ImageIcon(String.valueOf(file)));
                System.out.println(file.getPath());
                adminJPanel.file =file;
            }
        }else if(btn.equals("AAdd")) {
            System.out.println("AAdd");
            String userName=adminJPanel.adminuserNamejtf.getText();
            String adminName=adminJPanel.adminNamejtf.getText();
            String adminID= adminJPanel.adminIDjtf.getText();
            String adminBirthday= adminJPanel.datePicker.getText();
            String adminSex=adminJPanel.adminSexjtf.getText();
            String adminAddress=adminJPanel.adminAddressjtf.getText();
            String adminTel=adminJPanel.adminTeljtf.getText();
            String adminPhoto=adminJPanel.file.getAbsolutePath();

            if(userName.equals("")||adminName.equals("")||adminID.equals("")||adminBirthday.equals("")||adminSex.equals("")
                    ||adminAddress.equals("")||adminTel.equals("")||adminPhoto.equals("")) {
                JOptionPane.showMessageDialog(null, "请添加完整信息");
                return;

            }
            ParkAdmin parkAdmin = new ParkAdmin(userName, adminName, adminID, adminBirthday, adminSex, adminAddress, adminTel, adminPhoto);
            AdminJPanelUserInfoDB.addAdminInfo(parkAdmin);
            System.out.println(parkAdmin);
            adminJPanel.btnArefresh.doClick();
        }else if(btn.equals("ADelete")) {
            int row = adminJPanel.adminTable.getSelectedRow();
            if(row<0) {
                JOptionPane.showMessageDialog(null, "选中一行");
                return;
            }
            String userName= adminJPanel.adminTable.getValueAt(row, 0).toString();
            String adminName=adminJPanel.adminTable.getValueAt(row, 1).toString();
            String adminID= adminJPanel.adminTable.getValueAt(row, 2).toString();
            String adminBirthday= adminJPanel.adminTable.getValueAt(row, 3).toString();
            String adminSex=adminJPanel.adminTable.getValueAt(row, 4).toString();
            String adminAddress= adminJPanel.adminTable.getValueAt(row, 5).toString();
            String adminTel= adminJPanel.adminTable.getValueAt(row,6).toString();
            String adminPhoto=adminJPanel.adminTable.getValueAt(row, 7).toString();
            adminJPanel.adminuserNamejtf.setText(userName);
            adminJPanel.adminNamejtf.setText(adminName);
            adminJPanel.adminIDjtf.setText(adminID);

            adminJPanel.adminSexjtf.setText(adminSex);
            adminJPanel.adminAddressjtf.setText(adminAddress);
            adminJPanel.adminTeljtf.setText(adminTel);
            adminJPanel.photoLabel.setIcon(new ImageIcon(adminPhoto));
            int type =JOptionPane.showConfirmDialog(null,"Delete？");

            System.out.println(type);
            if(type ==0){
                String sql= " use park delete from parkAdmin where userName='"+userName+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
            }
            adminJPanel.btnArefresh.doClick();

        }else if(btn.equals("AUpdate")) {
            int row = adminJPanel.adminTable.getSelectedRow();
            if(row<0) {
                JOptionPane.showMessageDialog(null, "选中一行");
                return;
            }
            String username= adminJPanel.adminTable.getValueAt(row, 0).toString();
            String adminname=adminJPanel.adminTable.getValueAt(row, 1).toString();
            String adminid= adminJPanel.adminTable.getValueAt(row, 2).toString();
            String adminbirthday= adminJPanel.adminTable.getValueAt(row, 3).toString();
            String adminsex=adminJPanel.adminTable.getValueAt(row, 4).toString();
            String adminaddress= adminJPanel.adminTable.getValueAt(row, 5).toString();
            String admintel= adminJPanel.adminTable.getValueAt(row,6).toString();
            String adminphoto=adminJPanel.adminTable.getValueAt(row, 7).toString();
            if(username.equals("")||adminname.equals("")||adminid.equals("")||adminbirthday.equals("")||adminsex.equals("")
                    ||adminaddress.equals("")||admintel.equals("")||adminphoto.equals("")) {
                JOptionPane.showMessageDialog(null, "请添加完整信息");
                return;

            }
            String userName=adminJPanel.adminuserNamejtf.getText();
            String adminName=adminJPanel.adminNamejtf.getText();
            String adminID= adminJPanel.adminIDjtf.getText();
            String adminBirthday= adminJPanel.datePicker.getText();
            String adminSex=adminJPanel.adminSexjtf.getText();
            String adminAddress=adminJPanel.adminAddressjtf.getText();
            String adminTel=adminJPanel.adminTeljtf.getText();
            String adminPhoto=adminJPanel.file.getAbsolutePath();
            String sql="update parkAdmin set ";
            if(!adminname.equals(adminName)) {
                sql+=" adminName='"+adminName+"'";
                sql+=" where adminID='"+adminid+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
            }
            if(!adminid.equals(adminID)) {
                sql+=" adminID='"+adminID+"'";
                sql+=" where adminID='"+adminid+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
            }
            if(!adminaddress.equals(adminAddress)) {
                sql+=" adminAddress='"+adminAddress+"'";
                sql+=" where adminAddress='"+adminaddress+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
            }
            if(!admintel.equals(adminTel)) {
                sql+=" adminTel='"+adminTel+"'";
                sql+=" where adminID='"+adminid+"'";
                AdminJPanelUserInfoDB.deleteUserInfo(sql);
            }
            adminJPanel.btnArefresh.doClick();

        }else if(btn.equals("AFind")){

            String userName=adminJPanel.adminuserNamejtf.getText();
            String adminName=adminJPanel.adminNamejtf.getText();
            String adminID= adminJPanel.adminIDjtf.getText();
            String adminBirthday= adminJPanel.datePicker.getText();
            String adminSex=adminJPanel.adminSexjtf.getText();
            String adminAddress=adminJPanel.adminAddressjtf.getText();
            String adminTel=adminJPanel.adminTeljtf.getText();
            String adminPhoto=adminJPanel.file.getAbsolutePath();
            String sql="select * from parkAdmin where userName=userName ";
            if(!adminName.equals("")) {
                sql+=" and adminName='"+adminName+"' ";

            }
            if(!adminID.equals("")) {
                sql+=" and adminID='"+adminID+"'";

            }
            if(!adminTel.equals("")) {
                sql+=" and adminTel='"+adminTel+"'";
            }
            Vector<String> adminCols = new Vector<String>();
            adminCols.add("userName");
            adminCols.add("adminName");
            adminCols.add("adminID");
            adminCols.add("adminBirthday");
            adminCols.add("adminSex");
            adminCols.add("adminAddress");
            adminCols.add("adminTel");
            adminCols.add("adminPhoto");
            Vector<Vector<String>> adminRows= AdminJPanelUserInfoDB.findAdminInfo(sql);
            adminJPanel.adminModel.setDataVector(adminRows, adminCols);
        }if(btn.equals("ARefresh")) {
            Vector<String> adminCols = new Vector<String>();
            adminCols.add("userName");
            adminCols.add("adminName");
            adminCols.add("adminID");
            adminCols.add("adminBirthday");
            adminCols.add("adminSex");
            adminCols.add("adminAddress");
            adminCols.add("adminTel");
            adminCols.add("adminPhoto");
            Vector<Vector<String>> adminRows= AdminJPanelUserInfoDB.getAdminInfo();
            adminJPanel.adminModel.setDataVector(adminRows, adminCols);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(adminJPanel.userTable.getSelectedRow()>=0) {
            int row = adminJPanel.userTable.getSelectedRow();
            String userName= adminJPanel.userTable.getValueAt(row, 0).toString();
            String userPassword=adminJPanel.userTable.getValueAt(row, 1).toString();
            String userRight= adminJPanel.userTable.getValueAt(row, 2).toString();

            adminJPanel.namejtf_2.setText(userName);
            adminJPanel.passwordjtf_2.setText(userPassword);
            adminJPanel.rightjtf_2.setText(userRight);
            adminJPanel.userNamejtf.setText(userName);
            adminJPanel.passwordjtf.setText(userPassword);
        }
        if(adminJPanel.adminTable.getSelectedRow()>=0) {
            int row = adminJPanel.adminTable.getSelectedRow();
            String userName= adminJPanel.adminTable.getValueAt(row, 0).toString();
            String adminName=adminJPanel.adminTable.getValueAt(row, 1).toString();
            String adminID= adminJPanel.adminTable.getValueAt(row, 2).toString();
            String adminBirthday= adminJPanel.adminTable.getValueAt(row, 3).toString();
            String adminSex=adminJPanel.adminTable.getValueAt(row, 4).toString();
            String adminAddress= adminJPanel.adminTable.getValueAt(row, 5).toString();
            String adminTel= adminJPanel.adminTable.getValueAt(row,6).toString();
            String adminPhoto=adminJPanel.adminTable.getValueAt(row, 7).toString();
            adminJPanel.adminuserNamejtf.setText(userName);
            adminJPanel.adminNamejtf.setText(adminName);
            adminJPanel.adminIDjtf.setText(adminID);

            adminJPanel.adminSexjtf.setText(adminSex);
            adminJPanel.adminAddressjtf.setText(adminAddress);
            adminJPanel.adminTeljtf.setText(adminTel);
            adminJPanel.photoLabel.setIcon(new ImageIcon(adminPhoto));
        }

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


}
