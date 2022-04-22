package view.panel;



import dao.HomePageDB;
import dao.LookBillsJPanelDB;
import dao.SourceInfoJPanelDB;
import view.panel.BackGroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.ControlListener;
import Control.SourceInfoJPanelListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SourceInfoJPanel extends JPanel{
    public JPanel leftDownPanel;
    public JTextField pricejtf;
    public JButton setPricebtn;
    public JButton showBillsBtn ;
    public JButton groupByDayBtn;
    public JButton groupByHoursBtn;
    public JButton groupByMonthBtn;
    public JButton groupByETimeBtn;
    public JButton groupBySTimeBtn;
    public JTable dataJTable;
    public JTable priceTable;
    public DefaultTableModel priceModel;
    public DefaultTableModel dataModel;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new SourceInfoJPanel(0));
        frame.setVisible(true);

    }
    public SourceInfoJPanel(int type) {

        this.setSize(982, 435);
        this.setLayout(null);
        priceModel = new DefaultTableModel();
        dataModel = new DefaultTableModel();



        Vector<String> priceCols = new Vector<String>();
        priceCols.add("carType");
        priceCols.add("price/perHour");
        priceCols.add("carComments");
        Vector<Vector<String>> priceRows= SourceInfoJPanelDB.getPriceInfo();
        this.setLayout(null);
        priceModel.setDataVector(priceRows, priceCols);
        priceTable = new JTable(priceModel);
        ControlListener.fitTableColumns(priceTable);


        // leftDownPanel
        if(type==0){
            leftDownPanel = new JPanel();
            leftDownPanel.setBounds(15, 182, 437, 248);
            this.add(leftDownPanel);
            leftDownPanel.setBorder(BorderFactory.createTitledBorder("Info Tips！"));
            leftDownPanel.setLayout(null);

            JScrollPane priceInfoJScrollPane = new JScrollPane(priceTable);
            priceInfoJScrollPane.setBounds(25, 69, 250, 164);
            leftDownPanel.add(priceInfoJScrollPane);
            priceInfoJScrollPane.setBorder(BorderFactory.createTitledBorder("Price Info"));

            JLabel priceLabel = new JLabel("NewPrice:");
            priceLabel.setBounds(280, 118, 81, 21);
            leftDownPanel.add(priceLabel);

            pricejtf = new JTextField();
            pricejtf.setBounds(353, 115, 69, 27);
            leftDownPanel.add(pricejtf);
            pricejtf.setColumns(10);

            setPricebtn = new JButton("SetPrice");
            setPricebtn.setBounds(290, 171, 112, 29);
            leftDownPanel.add(setPricebtn);
            setPricebtn.addActionListener(new SourceInfoJPanelListener(this));

            JLabel tip1 = new JLabel("Tip: Before update ParkPrice please click a row from priceTable!\r\n\r\n");
            tip1.setBounds(15, 27, 407, 21);
            leftDownPanel.add(tip1);
        }else{
            Image image2 = new ImageIcon("image/sourceinfo2.png").getImage();
            leftDownPanel = new BackGroundPanel(image2);
            leftDownPanel.setBounds(15, 182, 437, 248);
            this.add(leftDownPanel);
            leftDownPanel.setBorder(BorderFactory.createTitledBorder("Info Tips！"));
            leftDownPanel.setLayout(null);
        }







        // rightPanel
        //设置窗口内容面板的背景图片
        Image image = new ImageIcon("image/beijin88.jpg").getImage();
        JPanel rightPanel = new BackGroundPanel(image);
        //JPanel rightPanel = new JPanel();
        rightPanel.setBounds(467, 15, 477, 415);
        this.add(rightPanel);
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Statistics Info！"));

        showBillsBtn = new JButton("ShowBills");
        showBillsBtn.setBounds(25, 319, 123, 29);
        rightPanel.add(showBillsBtn);
        showBillsBtn.addActionListener(new SourceInfoJPanelListener(this));



        Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
        dataRows = SourceInfoJPanelDB.getAllBillsData();
        Vector<String> dataCols = new Vector<String>();
        dataCols.add("billNo");
        dataCols.add("carNo");
        dataCols.add("carType");
        dataCols.add("carOwner");
        dataCols.add("carTel");
        dataCols.add("sourceNo");
        dataCols.add("carStartDate");
        dataCols.add("carEndDate");
        dataCols.add("carStopHours");
        dataCols.add("parkPrice");
        dataCols.add("carFee");
        dataCols.add("BillAdminUserName");
        dataCols.add("BillAdminUserTel");
        dataModel.setDataVector(dataRows,dataCols);
        dataJTable = new JTable(dataModel);
        ControlListener.fitTableColumns(dataJTable);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 15, 447, 289);
        scrollPane.setViewportView(dataJTable);
        rightPanel.add(scrollPane);






        groupByDayBtn = new JButton("CountByDay");
        groupByDayBtn.setBounds(339, 319, 123, 29);
        rightPanel.add(groupByDayBtn);
        groupByDayBtn.addActionListener(new SourceInfoJPanelListener(this));

        groupByHoursBtn = new JButton("CountByCType");
        groupByHoursBtn.setBounds(25, 363, 123, 29);
        rightPanel.add(groupByHoursBtn);
        groupByHoursBtn.addActionListener(new SourceInfoJPanelListener(this));

        groupByMonthBtn = new JButton("CountByMonth");
        groupByMonthBtn.setBounds(188, 319, 123, 29);
        rightPanel.add(groupByMonthBtn);
        groupByMonthBtn.addActionListener(new SourceInfoJPanelListener(this));

        groupByETimeBtn = new JButton("CountByETime");
        groupByETimeBtn.setBounds(339, 363, 123, 29);
        rightPanel.add(groupByETimeBtn);
        groupByETimeBtn.addActionListener(new SourceInfoJPanelListener(this));

        groupBySTimeBtn = new JButton("CountBySTime");
        groupBySTimeBtn.setBounds(188, 363, 123, 29);
        rightPanel.add(groupBySTimeBtn);
        groupBySTimeBtn.addActionListener(new SourceInfoJPanelListener(this));

        Image image1 = new ImageIcon("image/sourceinfo.jpeg").getImage();

        JPanel leftUpPane =new BackGroundPanel(image1);
        leftUpPane.setLayout(null);
        leftUpPane.setBorder(BorderFactory.createTitledBorder("Picture"));
        leftUpPane.setBounds(15, 26, 437, 141);
        add(leftUpPane);


    }
}
