package Test;

import view.panel.BackGroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestBGPanel extends JFrame {
    public static void main(String[] args) {
        new TestBGPanel().setVisible(true);
    }
    public TestBGPanel() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\LibraryMangerSystem\\image\\logo.png"));
        this.setTitle("停车场信息系统登录界面");

        //关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小和位置
        this.setBounds(100,100,760,576);
        //窗口居中
        this.setLocationRelativeTo(null);
        //往窗口中添加一个JPanel
        JPanel contentPanel = new JPanel();

        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPanel);
        contentPanel.setLayout(null);
        //设置窗口内容面板的背景图片
        Image image = new ImageIcon("image/beijin88_l.png").getImage();

        JPanel bj1 = new BackGroundPanel(image);
        bj1.setBounds(0,-3,742,539);
        contentPanel.add(bj1);
        bj1.setLayout(null);

    }
}
