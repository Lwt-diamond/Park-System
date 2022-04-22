package view.panel;

import dao.HomePageDB;
import view.panel.BackGroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class HomeJPanel extends JPanel{
    public JPanel leftPanel;
    public JScrollPane sourceInfoJScrollPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new HomeJPanel());
        frame.setVisible(true);

    }
    public HomeJPanel() {


        this.setSize(982, 435);
        this.setLayout(null);

        Vector<String> priceCols = new Vector<String>();
        priceCols.add("carType");
        priceCols.add("price/perHour");
        priceCols.add("carComments");
        Vector<Vector<String>> priceRows= HomePageDB.getPriceInfo();
        this.setLayout(null);
        JTable priceTable = new JTable(priceRows,priceCols);

        // leftPanel
        leftPanel = new JPanel();
        leftPanel.setBounds(15, 15, 279, 415);
        this.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Info Tips！"));
        leftPanel.setLayout(null);

        JScrollPane priceInfoJScrollPane = new JScrollPane(priceTable);
        priceInfoJScrollPane.setBounds(15, 40, 250, 164);
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
        sourceInfoJScrollPane.setBounds(15, 210, 250, 198);
        sourceInfoJScrollPane.setBorder(BorderFactory.createTitledBorder("Spare Position Info"));
        leftPanel.add(sourceInfoJScrollPane);



        // rightPanel
        //设置窗口内容面板的背景图片
        Image image = new ImageIcon("image//carc.png").getImage();
        JPanel rightPanel = new BackGroundPanel(image);
        //JPanel rightPanel = new JPanel();
        rightPanel.setBounds(308, 15, 659, 415);
        this.add(rightPanel);
        rightPanel.setLayout(null);
        //rightPanel.setBorder(BorderFactory.createTitledBorder("Spare Position Info"));

    }
}
