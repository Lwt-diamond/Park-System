package view;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import Control.ZhuCeListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZhuCeJFrame extends JFrame{
    public DengLuJFrame dengLuJFrame;

    public JTextField userNamejtf;
    public JPasswordField passwordjtf;
    public JPasswordField repasswordjtf;
    public ButtonGroup buttonGroup;
    public JRadioButton commUserRadioButton;




    public static void main(String[] args) {
        new ZhuCeJFrame(new DengLuJFrame(new HomePageJFrame())).setVisible(true);
    }
    public ZhuCeJFrame(DengLuJFrame f) {
        this.dengLuJFrame =f;
        //关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小和位置
        this.setBounds(100,100,760,576);

        //窗口居中
        this.setLocationRelativeTo(null);

       setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 741, 452);
        this.add(panel);
        panel.setLayout(null);

        JLabel zhucelabel = new JLabel("\u6CE8\u518C");
        zhucelabel.setHorizontalAlignment(SwingConstants.CENTER);
        zhucelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        zhucelabel.setBounds(278, 37, 172, 51);
        panel.add(zhucelabel);

        userNamejtf = new JTextField();
        userNamejtf.setBounds(322, 116, 217, 43);
        panel.add(userNamejtf);
        userNamejtf.setColumns(10);

        JLabel userPasswordLabel = new JLabel("userPassword:");
        userPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userPasswordLabel.setBounds(156, 174, 138, 43);
        panel.add(userPasswordLabel);

        JRadioButton adminRadioButton = new JRadioButton("\u7BA1\u7406\u5458");
        adminRadioButton.setBounds(454, 307, 87, 29);
        panel.add(adminRadioButton);

        commUserRadioButton = new JRadioButton("\u666E\u901A\u7528\u6237");
        commUserRadioButton.setBounds(322, 307, 125, 29);
        panel.add(commUserRadioButton);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(commUserRadioButton);
        buttonGroup.add(adminRadioButton);
        commUserRadioButton.setSelected(true);


        JLabel userRePasswordLabel = new JLabel("userRePassword:");
        userRePasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userRePasswordLabel.setBounds(156, 232, 138, 43);
        panel.add(userRePasswordLabel);

        JLabel userNameLabel = new JLabel("userName:");
        userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userNameLabel.setBounds(156, 116, 138, 43);
        panel.add(userNameLabel);

        JLabel rightLabel = new JLabel("right:");
        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightLabel.setBounds(156, 300, 138, 43);
        panel.add(rightLabel);

        passwordjtf = new JPasswordField();
        passwordjtf.setColumns(10);
        passwordjtf.setBounds(322, 174, 217, 43);
//		passwordjtf.setEchoChar('*');

        panel.add(passwordjtf);

        repasswordjtf = new JPasswordField();
        repasswordjtf.setColumns(10);
        repasswordjtf.setBounds(322, 240, 217, 43);
        panel.add(repasswordjtf);

        JButton registerButton = new JButton("\u6CE8\u518C");
        registerButton.addActionListener(new ZhuCeListener(this));
        registerButton.setBounds(432, 370, 107, 43);
        panel.add(registerButton);

        JButton resetButton = new JButton("\u91CD\u7F6E");
        resetButton.addActionListener(new ZhuCeListener(this));
        resetButton.setBounds(242, 370, 107, 43);
        panel.add(resetButton);



        //this.setVisible(true);

    }
}
