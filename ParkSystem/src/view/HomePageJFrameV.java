package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;



import dao.HomePageDB;
import entity.ParkUser;
import view.panel.BackGroundPanel;



public class HomePageJFrameV extends JFrame{
    public ParkUser parkUser;
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

    public JPanel  footJPanel;

    //一些其他非重要
    public JLabel timeJLabel;
    private JPanel leftPanel;
    private JScrollPane sourceInfoJScrollPane;

    public static void main(String [] args) {
        new HomePageJFrameV().setVisible(true);;
    }
    public HomePageJFrameV()  {

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



        //所有列
//		Vector<String> cols = new Vector<String>();
//		cols.add("SourceID");//第一列
//		cols.add("SourcePosition");//第二列
//		cols.add("SourceIsUsed");
//		cols.add("123");
//		cols.add("SourceIsUsed");
//		cols.add("SourceIsUsed");
//		cols.add("SourceIsUsed");
//		Vector<Vector<String>> rows;//所有行
//		rows = HomePageDB.getAllData();
//		System.out.println(rows);
//		System.out.println(rows.toString());


        Vector<String> priceCols = new Vector<String>();
        priceCols.add("carType");
        priceCols.add("price/perHour");
        priceCols.add("carComments");
        Vector<Vector<String>> priceRows=HomePageDB.getPriceInfo();
        JTable priceTable = new JTable(priceRows,priceCols);

        // leftPanel
        leftPanel = new JPanel();
        leftPanel.setBounds(15, 164, 280, 423);
        contentPane.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Info Tips！"));
        leftPanel.setLayout(null);

        JScrollPane priceInfoJScrollPane = new JScrollPane(priceTable);
        priceInfoJScrollPane.setBounds(15, 40, 250, 184);
        leftPanel.add(priceInfoJScrollPane);
        priceInfoJScrollPane.setBorder(BorderFactory.createTitledBorder("Price Info"));



        Vector<String> sourceCols = new Vector<String>();
        sourceCols.add("sourceNo");
        sourceCols.add("sourcePosiion");


        Timer sourceTimer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


            }
        });

        Vector<Vector<String>> sourceRows = HomePageDB.getSpareSourceInfo();
        JTable sourceInfoTable = new JTable(sourceRows,sourceCols);
        sourceInfoJScrollPane = new JScrollPane(sourceInfoTable);
        sourceInfoJScrollPane.setBounds(15, 239, 250, 184);
        sourceInfoJScrollPane.setBorder(BorderFactory.createTitledBorder("Spare Position Info"));
        leftPanel.add(sourceInfoJScrollPane);



        // rightPanel
        //设置窗口内容面板的背景图片
        Image image = new ImageIcon("image/beijin88.jpg").getImage();
        JPanel rightPanel = new BackGroundPanel(image);
        //JPanel rightPanel = new JPanel();
        rightPanel.setBounds(310, 172, 659, 415);
        contentPane.add(rightPanel);
        rightPanel.setLayout(null);

//		rightPanel.setBackground(Color.red);
        //rightPanel.setBorder(BorderFactory.createTitledBorder("给你的爱车一个家"));



        footJPanel = new JPanel();
        footJPanel.setBounds(10, 602, 958, 22);
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

        JLabel zuozhelabel = new JLabel("作者：  张三");
        zuozhelabel.setBounds(777, 0, 166, 22);
        footJPanel.add(zuozhelabel);


    }
}
