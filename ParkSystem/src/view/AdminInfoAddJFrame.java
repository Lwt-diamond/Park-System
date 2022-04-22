package view;


import java.awt.Font;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Control.AdminInfoAddListener;

import javax.swing.ImageIcon;
import com.eltima.components.ui.DatePicker;
import entity.ParkUser;


public class AdminInfoAddJFrame extends JFrame{
    public  ZhuCeJFrame zhuCeJFrame;
    public ParkUser parkUser;
    public  JLabel userNameLabel;
    public File file;
    public JTextField adminNamejtf;
    public ButtonGroup buttonGroup;
    public JRadioButton maleRadioButton;
    public JTextField adminAdressjtf;
    public JTextField adminIDjtf;
    public JTextField adminTeljtf;
    public JLabel photoLabel;
    public DatePicker dp;

    public static void main(String[] args) {
        new AdminInfoAddJFrame(null,null);
    }

    public AdminInfoAddJFrame(ZhuCeJFrame zhuCeJFrame, ParkUser parkUser) {
        this.parkUser = parkUser;
        this.zhuCeJFrame = zhuCeJFrame;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小和位置
        this.setBounds(100,100,760,576);

        //窗口居中
        this.setLocationRelativeTo(null);

        getContentPane().setLayout(null);
        getContentPane().setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 741, 520);
        this.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel zhucelabel = new JLabel("Personal  Information");
        zhucelabel.setHorizontalAlignment(SwingConstants.CENTER);
        zhucelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        zhucelabel.setBounds(213, 34, 336, 51);
        panel.add(zhucelabel);

        adminNamejtf = new JTextField();
        adminNamejtf.setBounds(428, 100, 217, 43);
        panel.add(adminNamejtf);
        adminNamejtf.setColumns(10);

        JLabel adminAddressLabel = new JLabel("adminAddress:");
        adminAddressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adminAddressLabel.setBounds(262, 319, 138, 43);
        panel.add(adminAddressLabel);

        JRadioButton femaleRadioButton = new JRadioButton("female");
        femaleRadioButton.setBounds(543, 275, 87, 29);
        panel.add(femaleRadioButton);

        maleRadioButton = new JRadioButton("male");
        maleRadioButton.setBounds(428, 275, 95, 29);
        panel.add(maleRadioButton);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);


        JLabel adminTelLabel = new JLabel("adminTel:");
        adminTelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adminTelLabel.setBounds(262, 380, 138, 43);
        panel.add(adminTelLabel);

        JLabel userRealNameLabel = new JLabel("adminName:");
        userRealNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userRealNameLabel.setBounds(262, 100, 138, 43);
        panel.add(userRealNameLabel);

        JLabel adminSexLabel = new JLabel("sex:");
        adminSexLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adminSexLabel.setBounds(262, 268, 138, 43);
        panel.add(adminSexLabel);


        JButton okButton = new JButton("确定");
        //okButton.addActionListener(new ZhuCeListener());
        okButton.setBounds(454, 438, 107, 43);
        panel.add(okButton);
        okButton.addActionListener(new AdminInfoAddListener(this));

        JButton backButton = new JButton("返回");
        //backButton.addActionListener(new ZhuCeListener());
        backButton.setBounds(213, 438, 107, 43);
        panel.add(backButton);
        backButton.addActionListener(new AdminInfoAddListener(this));

        userNameLabel = new JLabel("userName:");
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setBounds(58, 100, 176, 43);
        panel.add(userNameLabel);

        photoLabel = new JLabel("New label");
        file = new File("image\\white.png");
        photoLabel.setIcon(new ImageIcon(String.valueOf(file)));
        photoLabel.setBounds(68, 163, 150, 190);
        panel.add(photoLabel);

        JLabel adminbirthdaylabel = new JLabel("adminBirthday:");
        adminbirthdaylabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adminbirthdaylabel.setBounds(262, 221, 138, 43);
        panel.add(adminbirthdaylabel);

        dp = new DatePicker();
//        dp.getInnerButton().addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            }
//        });
        dp.setBounds(428, 221, 217, 43);
        panel.add(dp);

        adminAdressjtf = new JTextField();
        adminAdressjtf.setColumns(10);
        adminAdressjtf.setBounds(428, 319, 217, 43);
        panel.add(adminAdressjtf);

        JLabel adminIDlabel = new JLabel("adminID:");
        adminIDlabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adminIDlabel.setBounds(262, 163, 138, 43);
        panel.add(adminIDlabel);

        adminIDjtf = new JTextField();
        adminIDjtf.setColumns(10);
        adminIDjtf.setBounds(428, 163, 217, 43);
        panel.add(adminIDjtf);

        JButton btnChoosephoto = new JButton("\u9009\u62E9\u7167\u7247");
        btnChoosephoto.setBounds(98, 368, 107, 43);
        panel.add(btnChoosephoto);
        btnChoosephoto.addActionListener(new AdminInfoAddListener(this));

        adminTeljtf = new JTextField();
        adminTeljtf.setColumns(10);
        adminTeljtf.setBounds(428, 380, 217, 43);
        panel.add(adminTeljtf);


//		addressBox = new JComboBox<String>();
//		addressBox .setBounds(445, 316, 72, 35);
//
//		addressBox.addItem("北京大学出版社");
//		addressBox.addItem("陕西人民出版社");
//		addressBox.addItem("当代世界出版社");
//		addressBox.addItem("湖南大学出版社");
        this.setVisible(true);
    }
}
