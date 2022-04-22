package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Control.CancelJPanelListener;
import entity.ParkUser;
import view.panel.*;


public class HomePageJFrame extends JFrame{
    public ParkUser parkUser;
    public JLabel userNameLabel;
    public JLabel userRightLabel;
    public JButton homePageButton;
    public JButton personInfoPageButton;
    public JButton sourceInfoPageButton;
    public JButton stopCarPageButton;
    public JButton cancelStopPageButton;
    public JButton usePageButton;
    public JButton exitButton;
    public JPanel contentPane;
    public JPanel  headJPanel;
    public JPanel  bodyJPanel;
    public JPanel  footJPanel;
    public CardLayout cardLayout;
    public SourceInfoJPanel sourceInfoJPanel;
    public JLabel timeJLabel;
    private JPanel leftPanel;
    private JScrollPane sourceInfoJScrollPane;

    public HomePageJFrame()  {
        HomePageJFrame h = this;
        parkUser = new ParkUser("","","");
        setTitle("停车管理系统");
        setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\logo_1.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(470, 230, 1000, 680);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);//绝对布局，空布局，想放那就放哪


        HomeJPanel homeJPanel = new HomeJPanel();
        CancelJPanel cancelJPanel = new CancelJPanel();
        int type=0;
        if(parkUser.getUserRight().equals("管理员"))
            type=0;
        else
            type=1;
        //sourceInfoJPanel = new SourceInfoJPanel(type);
        LookBillsJPanel lookBillsJPanel = new LookBillsJPanel();
        AdminJPanel adminJPanel = new AdminJPanel();
        StopJPanel stopJPanel = new StopJPanel();
        InformationJPanel informationJPanel = new InformationJPanel();

        //上面的panel
        headJPanel = new JPanel();
        headJPanel.setBounds(0, 0, 982, 162);
        contentPane.add(headJPanel);
        headJPanel.setLayout(null);
        ImageIcon icon=new ImageIcon("image\\home.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        homePageButton = new JButton();
        //homePageButton.setFont(new Font("宋体", Font.BOLD, 28));
        homePageButton.setIcon(icon);
        homePageButton.setBounds(10, 10, 130, 140);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show( bodyJPanel,"home");
            }
        });
        headJPanel.add(homePageButton);
        ImageIcon icon1=new ImageIcon("image\\person.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        personInfoPageButton = new JButton();
        //personInfoPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        personInfoPageButton.setIcon(icon1);
        personInfoPageButton.setBounds(150, 10, 130, 140);
        headJPanel.add(personInfoPageButton);
        personInfoPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( parkUser.getUserRight().equals("管理员"))
                cardLayout.show( bodyJPanel,"admin");
                else {
                    new UserJFrame(parkUser.getUserName()).setVisible(true);
                }
            }
        });

        ImageIcon icon2=new ImageIcon("image\\source.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        sourceInfoPageButton = new JButton();
        sourceInfoPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        sourceInfoPageButton.setIcon(icon2);
        sourceInfoPageButton.setBounds(290, 10, 130, 140);
        headJPanel.add(sourceInfoPageButton);
        sourceInfoPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show( bodyJPanel,"source");
            }
        });

        ImageIcon icon3=new ImageIcon("image\\stop.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        stopCarPageButton = new JButton();
        stopCarPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        stopCarPageButton.setIcon(icon3);
        stopCarPageButton.setBounds(430, 10, 130, 140);
        headJPanel.add(stopCarPageButton);
        stopCarPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( parkUser.getUserRight().equals("管理员"))
                    cardLayout.show( bodyJPanel,"stop");
                else {
                    JOptionPane.showMessageDialog(null,"你不是管理员，没有权限！");
                }
            }
        });

        ImageIcon icon4=new ImageIcon("image\\cancel.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        cancelStopPageButton = new JButton();
        cancelStopPageButton.setFont(new Font("宋体", Font.BOLD, 28));
        cancelStopPageButton.setIcon(icon4);
        cancelStopPageButton.setBounds(570, 10, 130, 140);
        headJPanel.add(cancelStopPageButton);
        cancelStopPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if( parkUser.getUserRight().equals("管理员")){
                    cardLayout.show( bodyJPanel,"cancel");
                    cancelJPanel.updateButton.addActionListener(new CancelJPanelListener(cancelJPanel));
                }

                else {
                    cardLayout.show( bodyJPanel,"cancel");
                    cancelJPanel.updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null,"你不是管理员，没有权限！");
                        }
                    });

                }
            }
        });

        ImageIcon icon5=new ImageIcon("image\\info.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        usePageButton = new JButton();
        usePageButton.setFont(new Font("宋体", Font.BOLD, 28));
        usePageButton.setIcon(icon5);
        usePageButton.setBounds(710, 10, 130, 140);
        headJPanel.add(usePageButton);
        usePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show( bodyJPanel,"information");
            }
        });

        ImageIcon icon6=new ImageIcon("image\\exit.png");
        icon.setImage(icon.getImage().getScaledInstance(130,140,0));
        exitButton = new JButton();
        exitButton.setFont(new Font("宋体", Font.BOLD, 28));
        exitButton.setIcon(icon6);
       exitButton.setBounds(850, 10, 130, 140);
        headJPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               h.dispose();
            }
        });




        //bodyJPanel = new CancelJPanel();
        bodyJPanel = new JPanel();
        bodyJPanel.setBounds(0, 161, 982, 435);
        contentPane.add(bodyJPanel);
        cardLayout =new CardLayout(0,0);
        bodyJPanel.setLayout(cardLayout);

        // TODO:add Jpanel


        bodyJPanel.add(adminJPanel,"admin");
        bodyJPanel.add(lookBillsJPanel,"look");

        bodyJPanel.add(cancelJPanel,"cancel");
        bodyJPanel.add(homeJPanel,"home");
        bodyJPanel.add(stopJPanel,"stop");
        bodyJPanel.add(informationJPanel,"information");
        cardLayout.show( bodyJPanel,"home");






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

        JLabel zuozhelabel = new JLabel("作者：  刘吴涛");
        zuozhelabel.setBounds(777, 0, 166, 22);
        footJPanel.add(zuozhelabel);


    }
}
