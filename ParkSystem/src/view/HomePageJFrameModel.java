package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class HomePageJFrameModel extends JFrame{
    public JLabel userNameLabel;
    public JLabel userRightLabel;
    public JButton homePageButton;
    public JButton personInfoPageButton;
    public JButton sourceInfoPageButton;
    public JButton stopCarPageButton;
    public JButton cancelStopPageButton;
    public JButton helpPageButton;
    public JButton exitButton;
    public JPanel contentPane;
    public JPanel  headJPanel;
    public JPanel  bodyJPanel;
    public JPanel  footJPanel;

    //一些其他非重要
    public JLabel timeJLabel;

    public static void main(String [] args) {
        new HomePageJFrameModel().setVisible(true);;
    }
    public HomePageJFrameModel()  {

        setTitle("停车管理系统");
        setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\logo_1.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(470, 230, 1000, 680);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);//绝对布局，空布局，想放那就放哪

        //上面的panel
        headJPanel = new JPanel();
        headJPanel.setBounds(0, 0, 982, 162);
        contentPane.add(headJPanel);
        headJPanel.setLayout(null);

        homePageButton = new JButton("home");
        homePageButton.setFont(new Font("宋体", Font.BOLD, 28));
        homePageButton.setIcon(null);
        homePageButton.setBounds(10, 10, 130, 140);

        headJPanel.add(homePageButton);

        personInfoPageButton = new JButton("personalInfo");
        personInfoPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        personInfoPageButton.setIcon(null);
        personInfoPageButton.setBounds(150, 10, 130, 140);
        headJPanel.add(personInfoPageButton);

        sourceInfoPageButton = new JButton("SourceInfo");
        sourceInfoPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        sourceInfoPageButton.setIcon(null);
        sourceInfoPageButton.setBounds(290, 10, 130, 140);
        headJPanel.add(sourceInfoPageButton);

        stopCarPageButton = new JButton("StopCar");
        stopCarPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        stopCarPageButton.setIcon(null);
        stopCarPageButton.setBounds(430, 10, 130, 140);
        headJPanel.add(stopCarPageButton);

        cancelStopPageButton = new JButton("Cancel");
        cancelStopPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        cancelStopPageButton.setIcon(null);
        cancelStopPageButton.setBounds(570, 10, 130, 140);
        headJPanel.add(cancelStopPageButton);

        helpPageButton = new JButton("Help");
        helpPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        helpPageButton.setIcon(null);
        helpPageButton.setBounds(710, 10, 130, 140);
        headJPanel.add(helpPageButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("宋体", Font.BOLD, 28));
        exitButton.setIcon(null);
        exitButton.setBounds(850, 10, 130, 140);
        headJPanel.add(exitButton);


        //contentPane.add(bodyJPanel);

        footJPanel = new JPanel();
        footJPanel.setBounds(15, 600, 958, 22);
        contentPane.add(footJPanel);
        footJPanel.setLayout(null);

        userNameLabel = new JLabel("当前用户：");
        userNameLabel.setBounds(29, 0, 117, 22);
        footJPanel.add(userNameLabel);

        userRightLabel = new JLabel("级别：");
        userRightLabel.setBounds(149, 0, 128, 22);
        footJPanel.add(userRightLabel);

        timeJLabel = new JLabel();
        timeJLabel.setBounds(277, 0, 235, 22);
        footJPanel.add(timeJLabel);
        //日期时间监听
        Timer timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();//获取当前时间
                DateFormat df =new SimpleDateFormat("yyyy年MM月dd日 hh小时mm分ss秒");
                timeJLabel.setText(df.format(date));
            }
        });
        timer.start();//启动时间监听
        JLabel banbenlabel = new JLabel("版本号:   停车管理系统 v1.0");
        banbenlabel.setBounds(527, 0, 235, 22);
        footJPanel.add(banbenlabel);

        JLabel zuozhelabel = new JLabel("作者：  刘吴涛");
        zuozhelabel.setBounds(777, 0, 166, 22);
        footJPanel.add(zuozhelabel);


    }


}
