package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class HuanYingJFrame extends JFrame{
	public static void main(String[] args) {
		new HuanYingJFrame().setVisible(true);
	}
	public HuanYingJFrame() {
		getContentPane().setLayout(null);
		getContentPane().setBounds(100,100,800,600);
		JLabel huanyinglable = new JLabel("欢迎进入停车管理系统");
		huanyinglable.setFont(new Font("宋体", Font.PLAIN, 30));
		huanyinglable.setHorizontalAlignment(SwingConstants.CENTER);
		huanyinglable.setBounds(171, 56, 397, 52);
		getContentPane().add(huanyinglable);
	
		
		JButton denglu = new JButton("登录");
		denglu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "登录");
			}
		});
		denglu.setBounds(258, 147, 162, 52);
		getContentPane().add(denglu);
		
		JButton zhuce = new JButton("注册");
		zhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "注册");
			}
		});
		zhuce.setBounds(258, 232, 162, 52);
		getContentPane().add(zhuce);
		
		
		JButton suibiankankan = new JButton("随便看看");
		suibiankankan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "随便看看");
			}
		});
		suibiankankan.setBounds(258, 320, 162, 52);
		getContentPane().add(suibiankankan);

	}
}
