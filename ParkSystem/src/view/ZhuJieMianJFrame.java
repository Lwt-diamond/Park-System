package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ZhuJieMianJFrame extends JFrame {

    private JPanel contentPane;
    public JPanel p3;
    public CardLayout cl;
    public JLabel yonghulabel;
    public JLabel jibielabel;
    public JLabel shijianlabel;



    public ZhuJieMianJFrame() {

        setTitle("停车管理系统");
        setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\logo_1.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(470, 230, 1000, 680);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);//绝对布局，空布局，想放那就放哪

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 982, 162);
        contentPane.add(p1);
        p1.setLayout(null);

        JButton yonghu = new JButton("");
        yonghu.setFont(new Font("宋体", Font.BOLD, 28));
        yonghu.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\conver.png"));
        yonghu.setBounds(10, 10, 130, 140);
        p1.add(yonghu);

        JButton likai = new JButton("");
        likai.setFont(new Font("宋体", Font.BOLD, 28));//F:\eclipse-workspace\LibraryManagerSystem_ZhouXi\image
        likai.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\locksys.png"));
        likai.setBounds(150, 10, 130, 140);
        p1.add(likai);

        JButton shezhi = new JButton("");
        shezhi.setFont(new Font("宋体", Font.BOLD, 28));
        shezhi.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\SysSet.png"));
        shezhi.setBounds(290, 10, 130, 140);
        p1.add(shezhi);

        JButton beifen = new JButton("");
        beifen.setFont(new Font("宋体", Font.BOLD, 28));
        beifen.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\DataBak.png"));
        beifen.setBounds(430, 10, 130, 140);
        p1.add(beifen);

        JButton huifu = new JButton("");
        huifu.setFont(new Font("宋体", Font.BOLD, 28));
        huifu.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\DataRenew.png"));
        huifu.setBounds(570, 10, 130, 140);
        p1.add(huifu);

        JButton bangzhu = new JButton("");
        bangzhu.setFont(new Font("宋体", Font.BOLD, 28));
        bangzhu.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\Help.png"));
        bangzhu.setBounds(710, 10, 130, 140);
        p1.add(bangzhu);

        JButton tuichu = new JButton("");
        tuichu.setFont(new Font("宋体", Font.BOLD, 28));
        tuichu.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\exit.png"));
        tuichu.setBounds(850, 10, 130, 140);
        p1.add(tuichu);



        JPanel p2 = new JPanel();
        p2.setBounds(10, 175, 216, 404);
        contentPane.add(p2);
        p2.setLayout(null);

        JButton tushubtn = new JButton("图书管理");
        tushubtn.setFont(new Font("宋体", Font.BOLD, 20));
        tushubtn.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\File.png"));
        tushubtn.setBounds(20, 44, 180, 50);
        p2.add(tushubtn);
       // tushubtn.addActionListener(new ZhuJieMianListener(this));

        JButton jieyuebtn = new JButton("\u501F\u9605\u7BA1\u7406");
        jieyuebtn.setFont(new Font("宋体", Font.BOLD, 20));
        jieyuebtn.setIcon(new ImageIcon("F:\\eclipse-workspace\\LibraryManagerSystem\\image\\Next.png"));
        jieyuebtn.setBounds(20, 124, 180, 50);
        p2.add(jieyuebtn);
        //jieyuebtn.addActionListener(new ZhuJieMianListener(this));

        JButton duzhebtn = new JButton("读者管理");
        duzhebtn.setFont(new Font("宋体", Font.BOLD, 20));
        duzhebtn.setIcon(new ImageIcon("image\\Pause.png"));
        duzhebtn.setBounds(20, 204, 180, 50);
        p2.add(duzhebtn);
        //duzhebtn.addActionListener(new ZhuJieMianListener(this));

        JButton xitongbtn = new JButton("系统管理");
        xitongbtn.setFont(new Font("宋体", Font.BOLD, 20));
        xitongbtn.setIcon(new ImageIcon("image\\Front.png"));
        xitongbtn.setBounds(20, 284, 180, 50);
        p2.add(xitongbtn);
       // xitongbtn.addActionListener(new ZhuJieMianListener(this));

        p3 = new JPanel();
        p3.setBounds(240, 175, 728, 396);
        contentPane.add(p3);
        cl = new CardLayout(0, 0);
        p3.setLayout(cl);

//        TuShuGuanLiJPanel tsgl = new TuShuGuanLiJPanel(this);
//        p3.add(tsgl, "tsgl");
//        DuZheGuanLiJPanel dzgl = new DuZheGuanLiJPanel();
//        p3.add(dzgl, "dzgl");
//        XiTongGuanLiJPanel xtgl = new XiTongGuanLiJPanel();
//        p3.add(xtgl, "xtgl");
//        JieYueGuanLiJPanel jygl = new JieYueGuanLiJPanel();
//        p3.add(jygl, "jygl");
//        //默认显示的TuShuGuanLiJPanel
//        cl.show(p3, "tsgl");

        JPanel p4 = new JPanel();
        p4.setBounds(10, 619, 958, 22);
        contentPane.add(p4);
        p4.setLayout(null);

        yonghulabel = new JLabel("当前用户：");
        yonghulabel.setBounds(0, 0, 117, 22);
        p4.add(yonghulabel);

        jibielabel = new JLabel("级别：");
        jibielabel.setBounds(120, 2, 128, 22);
        p4.add(jibielabel);

        shijianlabel = new JLabel();
        shijianlabel.setBounds(252, 2, 235, 22);
        p4.add(shijianlabel);
        //日期时间监听
        Timer timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();//获取当前时间
                DateFormat df =new SimpleDateFormat("yyyy年MM月dd日 hh小时mm分ss秒");
                shijianlabel.setText(df.format(date));
            }
        });
        timer.start();//启动时间监听
        JLabel banbenlabel = new JLabel("版本号:   图书管理系统 v1.0");
        banbenlabel.setBounds(492, 2, 235, 22);
        p4.add(banbenlabel);

        JLabel zuozhelabel = new JLabel("作者：  张三");
        zuozhelabel.setBounds(733, 2, 195, 22);
        p4.add(zuozhelabel);

    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //ZhuJieMianJFrame frame = new ZhuJieMianJFrame();
                //frame.setVisible(true);
                //frame.setVisible(true);
                DengLuJFrame dengLuJFrame = new DengLuJFrame(null);
                dengLuJFrame.setVisible(true);


            }
        });
    }

}
