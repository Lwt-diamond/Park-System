package view.panel;


import dao.StopJPanelDB;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.StopJPanelListener;

import java.util.Vector;

import com.eltima.components.ui.DatePicker;

public class StopJPanel extends JPanel{
    public JPanel leftPanel;
    public DefaultTableModel model;
    public JTextField carNojtf;
    public JTextField carOwnerjtf;
    public JTextField carTeljtf;
    public JTextField sourcePositionjtf;
    public JComboBox carTypeComboBox;
    public JComboBox sourceNoComboBox;
    public DatePicker datePicker;
    public JTable dataJTabel;
    public String carType;
    public JButton refreshButton;
    public JScrollPane dataJScrollPane;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new StopJPanel());
        frame.setVisible(true);

    }
    public StopJPanel() {


        this.setSize(982, 435);
        this.setLayout(null);

        //数据部分
        Vector<String> dataCols = new Vector<String>();
        dataCols.add("SourceNo");
        dataCols.add("sourcePosition");
        dataCols.add("carNo");
        dataCols.add("carOwner");
        dataCols.add("carTel");
        dataCols.add("carType");
        dataCols.add("carStartDate");
        Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
        dataRows = StopJPanelDB.getAllData();
//        System.out.println(dataCols);
//        System.out.println(dataRows);
        model = new DefaultTableModel();
        model.setDataVector(dataRows, dataCols);
        dataJTabel= new JTable(model);
        dataJTabel.addMouseListener(new StopJPanelListener(this));
        dataJScrollPane = new JScrollPane() ;
        dataJScrollPane.setViewportView(dataJTabel);
        dataJScrollPane.setLocation(15, 45);
        dataJScrollPane.setSize(669, 357);

        // leftPanel
        leftPanel = new JPanel();
        leftPanel.setBounds(15, 15, 699, 415);
        leftPanel.add(dataJScrollPane);
        this.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Information"));
        leftPanel.setLayout(null);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new StopJPanelListener(this));
        refreshButton.setBounds(565, 15, 90, 29);
        leftPanel.add(refreshButton);



        // rightPanel
        //设置窗口内容面板的背景图片
//        Image image = new ImageIcon("image/beijin88.jpg").getImage();
//        JPanel rightPanel = new BackGroundPanel(image);
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(721, 15, 246, 415);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Stop Car"));
        this.add(rightPanel);
        rightPanel.setLayout(null);
        //一堆Label
        JLabel carTypeLabel = new JLabel("carType:");
        carTypeLabel.setBounds(15, 30, 78, 29);
        rightPanel.add(carTypeLabel);

        JLabel sourceNoLabel = new JLabel("sorceNo:");
        sourceNoLabel.setBounds(15, 74, 78, 29);
        rightPanel.add(sourceNoLabel);

        JLabel carNoLabel = new JLabel("  carNo:");
        carNoLabel.setBounds(15, 157, 78, 29);
        rightPanel.add(carNoLabel);

        JLabel carOwnerLabel = new JLabel("carOwnr:");
        carOwnerLabel.setBounds(15, 201, 78, 29);
        rightPanel.add(carOwnerLabel);


        JLabel carTelLabel = new JLabel(" carTel:");
        carTelLabel.setBounds(15, 243, 78, 29);
        rightPanel.add(carTelLabel);
        JLabel lblStarttime = new JLabel("  sTime:");
        lblStarttime.setBounds(15, 278, 78, 29);
        rightPanel.add(lblStarttime);

        JLabel sourcePositionLabel = new JLabel("sorcePo:");
        sourcePositionLabel.setBounds(15, 114, 78, 29);
        rightPanel.add(sourcePositionLabel);


        //这是一堆要获取里面内容的东西，也是可以编辑内容的东西
        carTypeComboBox = new JComboBox();
        Vector<String> sourceGroup = StopJPanelDB.getSourceGroup();
        carTypeComboBox.setModel(new DefaultComboBoxModel(sourceGroup));
        carTypeComboBox.setSelectedIndex(0);
        carTypeComboBox.setBounds(95, 31, 87, 27);
        rightPanel.add(carTypeComboBox);
        //添加事件
        carTypeComboBox.addItemListener(new StopJPanelListener(this));
        carType = (String) carTypeComboBox.getSelectedItem();

        sourceNoComboBox = new JComboBox();
        Vector<String> sourceNoString = StopJPanelDB.getSourceNoStringByType(carType);
        sourceNoComboBox.setModel(new DefaultComboBoxModel(sourceNoString));
        sourceNoComboBox.setSelectedIndex(0);//问题
        sourceNoComboBox.setBounds(95, 75, 87, 27);
        rightPanel.add(sourceNoComboBox);
        //添加事件
        sourceNoComboBox.addItemListener(new StopJPanelListener(this));


        carNojtf = new JTextField();
        carNojtf.setBounds(95, 158, 96, 27);
        rightPanel.add(carNojtf);
        carNojtf.setColumns(10);

        carOwnerjtf = new JTextField();
        carOwnerjtf.setColumns(10);
        carOwnerjtf.setBounds(95, 202, 96, 27);
        rightPanel.add(carOwnerjtf);


        carTeljtf = new JTextField();
        carTeljtf.setColumns(10);
        carTeljtf.setBounds(95, 244, 96, 27);
        rightPanel.add(carTeljtf);

        datePicker = new DatePicker();
        datePicker.setTimePanleVisible(true);
        datePicker.setBounds(95, 280, 133, 27);
        rightPanel.add(datePicker);



        sourcePositionjtf = new JTextField();
        sourcePositionjtf.setColumns(10);
        sourcePositionjtf.setBounds(95, 118, 96, 27);
        rightPanel.add(sourcePositionjtf);
        //初始化啦
        sourcePositionjtf.setText(StopJPanelDB.getSourcePositionByNo(
                (String)sourceNoComboBox.getSelectedItem()));
        //两个按钮
        JButton addButton = new JButton("Add");
        addButton.setBounds(145, 371, 78, 29);
        rightPanel.add(addButton);
        addButton.addActionListener(new StopJPanelListener(this));

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new StopJPanelListener(this));
        clearButton.setBounds(34, 371, 78, 29);
        rightPanel.add(clearButton);

    }
}
