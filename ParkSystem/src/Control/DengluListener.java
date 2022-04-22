package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import dao.*;
import entity.ParkUser;
import view.DengLuJFrame;
import view.ZhuCeJFrame;
import view.panel.SourceInfoJPanel;

public class DengluListener implements ActionListener{
	//因为要用到LibraryJFrame 中的两个按钮，（登录和取消按钮），必须将这个引进来作为成员属性
	DengLuJFrame dengLuJFrame;

	public DengluListener(DengLuJFrame dengLuJFrame) {
		super();
		this.dengLuJFrame = dengLuJFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//判断点击的是登录还是取消按钮
		JButton b = (JButton) e.getSource();//获取按钮点击事件源
		if(b.getActionCommand().equals("注册")) {
			//JOptionPane.showMessageDialog(null,"注册按钮");
			ZhuCeJFrame zhuCeJFrame = new ZhuCeJFrame(dengLuJFrame);
			zhuCeJFrame.setVisible(true);
			//关闭窗口
			dengLuJFrame.dispose();
		}else {
			//获取帐号密码框中的数据
			String userName = dengLuJFrame.userjtf.getText();
			String userPassword =String.valueOf(dengLuJFrame.passwordField.getPassword()) ;
			// 判断输入的帐号密码和数据库中的帐号密码是否一致
			boolean isOK = DengLuDB.Login(userName, userPassword);
			if(isOK == true) {
				//表示帐号密码在数据库中存在，就要跳转至主界面
				System.out.println("登录成功！");
				ParkUser parkUser = new ParkUser(userName,userPassword,DengLuDB.getUserRight(userName));
				dengLuJFrame.homePageJFrame.parkUser = parkUser;
				int type =0;//权限，管理员权限type=0。
				if(parkUser.getUserRight().equals("管理员"))
					type=0;
				else
					type=1;
				dengLuJFrame.homePageJFrame.sourceInfoJPanel = new SourceInfoJPanel(type);
				dengLuJFrame.homePageJFrame.bodyJPanel.add(dengLuJFrame.homePageJFrame.sourceInfoJPanel,"source");
				dengLuJFrame.homePageJFrame.userNameLabel.setText(dengLuJFrame.homePageJFrame.userNameLabel.getText()+parkUser.getUserName());
				dengLuJFrame.homePageJFrame.userRightLabel.setText(dengLuJFrame.homePageJFrame.userRightLabel.getText()+parkUser.getUserRight());
				dengLuJFrame.homePageJFrame.setVisible(true);
				dengLuJFrame.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "登录失败，帐号密码错误");
				System.out.println("登录失败，帐号密码错误");
				dengLuJFrame.userjtf.setText("");//清空输入框中的帐号密码
				dengLuJFrame.passwordField.setText("");
			}
		}

	}



}
