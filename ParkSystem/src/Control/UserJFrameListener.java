package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.UserJFrameDB;
import view.UserJFrame;


public class UserJFrameListener implements ActionListener{
	public UserJFrame userJFrame;
	public UserJFrameListener(UserJFrame userJFrame) {
		super();
		this.userJFrame = userJFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String btn=e.getActionCommand();
		if(btn.equals("Clear")) {
			userJFrame.passwordjtf.setText("");
			userJFrame.rePasswordjtf.setText("");
		}else if(btn.equals("Submit")) {
			String password=userJFrame.passwordjtf.getText();
			String rePassword=userJFrame.rePasswordjtf.getText();
			if(!password.equals(rePassword) ){
				JOptionPane.showMessageDialog(null, "两次密码不一致");
				return;
			}
			UserJFrameDB.updateUserInfo(userJFrame.userNamejtf.getText(),password);
			int type=JOptionPane.showConfirmDialog(null,"修改成功，是否退出");
			if(type==0){
				userJFrame.dispose();
			}
		}

	}
}
