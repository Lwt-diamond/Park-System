package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.DengluListener;
import view.panel.BackGroundPanel;

public class DengLuJFrame extends JFrame{
    public HomePageJFrame homePageJFrame;
    private JPanel contentPanel;
    public	JTextField userjtf;
    public 	JPasswordField passwordField;
    public static void main(String []args) {
        EventQueue.invokeLater(new  Runnable() {
            public void run() {
                DengLuJFrame frame = new DengLuJFrame(new HomePageJFrame());
                frame.setVisible(true);
            }
        });
    }


    public DengLuJFrame(HomePageJFrame homePageJFrame) {
		this.homePageJFrame = homePageJFrame;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\LibraryMangerSystem\\image\\logo.png"));
        this.setTitle("停车场信息系统登录界面");
        //关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小和位置
        this.setBounds(100,100,760,576);
        //窗口居中
        this.setLocationRelativeTo(null);
        //往窗口中添加一个JPanel
        contentPanel = new JPanel();

        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPanel);
        contentPanel.setLayout(null);
        //设置窗口内容面板的背景图片
        Image image = new ImageIcon("image/beijin88_l.png").getImage();
        contentPanel.setLayout(null);
        JPanel bj1 = new BackGroundPanel(image);
        bj1.setBounds(0,-3,742,539);
        contentPanel.add(bj1);
        bj1.setLayout(null);

        //JLabel l = new JLabel("停车场信息系统登录");

        JButton titleBtn = new JButton("停车场信息系统登录");
        titleBtn.setEnabled(false);
        titleBtn.setForeground(new Color(255, 215, 0));
        titleBtn.setFont(new Font("宋体", Font.BOLD, 38));
        titleBtn.setBounds(183,83,411,74);

        bj1.add(titleBtn);

        Image image1 = new ImageIcon("F:\\eclipse-workspace\\LibraryMangerSystem\\image\\staff.gif").getImage();
        JPanel bj2 = new BackGroundPanel(image1);
        bj2.setBounds(239,208,280,110);
        bj1.add(bj2);

        JLabel userLabel = new JLabel("用户名：");
        userLabel.setForeground(new Color(255,204,51));
        userLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        userLabel.setBounds(14,51,72,18);
        bj2.add(userLabel);
        bj2.setLayout(null);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setForeground(new Color(255,204,51));
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        passwordLabel.setBounds(14,81,72,18);
        bj2.add(passwordLabel);

        userjtf = new JTextField();
        userjtf.setBounds(96, 49, 135, 24);
        bj2.add(userjtf);
        userjtf.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setBounds(96,78,135,25);
        bj2.add(passwordField);

        JPanel bj3 = new JPanel();
        bj3.setBackground(new Color(0, 204, 204));
        bj3.setBounds(173, 457, 569, 82);
        bj1.add(bj3);
        bj3.setLayout(null);

        //往 jp3中添加登录和取消按钮
        JButton dengluButton = new JButton("登录");
        dengluButton.setForeground(new Color(0,51,255));
        dengluButton.setFont(new Font("宋体",Font.BOLD,20));
        dengluButton.setBounds(92, 23,132, 34);
        bj3.add(dengluButton);
        //点击登录按钮：
        //1.要获取账号密码框中的数据和数据库中的帐号比较，如果相同则跳转至主界面，如果相同，则清空帐号密码中的数据
        dengluButton.addActionListener(new DengluListener(this));

        JButton zhuceButton = new JButton("注册");
        zhuceButton.setForeground(new Color(0,51,255));
        zhuceButton.setFont(new Font("宋体",Font.BOLD,20));
        zhuceButton.setBounds(303, 23,132, 34);
        bj3.add(zhuceButton);

        zhuceButton.addActionListener(new DengluListener(this));
        this.setVisible(true);
    }
}

