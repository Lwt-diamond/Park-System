package view.panel;



import com.eltima.components.ui.DatePicker;

import Control.LookBillsJPanelListener;
import Control.LookBillsJPanelListener;
import dao.FindJPanelDB;
import dao.LookBillsJPanelDB;
import dao.StopJPanelDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class LookBillsJPanel extends JPanel{
    public JPanel leftPanel;
    public DefaultTableModel model;
    public JTextField carNojtf;
    public JTextField carOwnerjtf;
    public JTextField carFeejtf;
    public JComboBox carTypeComboBox;
    public JComboBox sourceNoComboBox;
    public DatePicker sDatePicker;
    public JTable dataJTabel;
    public String carType;
    public JButton refreshButton;
    public JButton findButton;
    public JButton clearButton;

    public JScrollPane dataJScrollPane;

    public JCheckBox carTypeCheckBox;
    public JCheckBox sourceNoCheckBox;
    public JCheckBox carFeeCheckBox;
    public JCheckBox carNoCheckBox;
    public JCheckBox carOwnerCheckBox;
    public JCheckBox sTimeCheckBox;
    public JCheckBox billAdminCheckBox;
    public JTextField billAdminjtf;
    private JButton deleteButton;
    private JButton showButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new LookBillsJPanel());
        frame.setVisible(true);

    }
    public LookBillsJPanel() {


        this.setSize(982, 435);
        this.setLayout(null);


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

        Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
        dataRows = LookBillsJPanelDB.getAllBillsData();
        System.out.println(dataCols);
        System.out.println(dataRows);
        model = new DefaultTableModel();
        model.setDataVector(dataRows, dataCols);
        dataJTabel= new JTable(model);
//        dataJTabel.setRowHeight(1000);


        dataJScrollPane = new JScrollPane() ;
        dataJScrollPane.setViewportView(dataJTabel);
        dataJScrollPane.setLocation(15, 49);
        dataJScrollPane.setSize(669, 341);
        dataJTabel.addMouseListener(new LookBillsJPanelListener(this));

        // leftPanel
        leftPanel = new JPanel();
        leftPanel.setBounds(15, 15, 699, 405);
        leftPanel.add(dataJScrollPane);
        this.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Information"));
        leftPanel.setLayout(null);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new LookBillsJPanelListener(this));
        refreshButton.setBounds(565, 15, 90, 29);
        leftPanel.add(refreshButton);




        // rightPanel

//        Image image = new ImageIcon("image/beijin88.jpg").getImage();
//        JPanel rightPanel = new BackGroundPanel(image);
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(721, 15, 250, 405);
        rightPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Find Car", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        this.add(rightPanel);
        rightPanel.setLayout(null);



        carTypeComboBox = new JComboBox();
        carTypeComboBox.setEnabled(false);
        Vector<String> sourceGroup = FindJPanelDB.getSourceGroup();
        carTypeComboBox.setModel(new DefaultComboBoxModel(sourceGroup));
        carTypeComboBox.setSelectedIndex(0);
        carTypeComboBox.setBounds(135, 31, 87, 27);
        rightPanel.add(carTypeComboBox);

        carTypeComboBox.addItemListener(new LookBillsJPanelListener(this));
        carType = (String) carTypeComboBox.getSelectedItem();

        sourceNoComboBox = new JComboBox();
        sourceNoComboBox.setEnabled(false);
        Vector<String> sourceNoString = FindJPanelDB.getSourceNoString();
        sourceNoComboBox.setModel(new DefaultComboBoxModel(sourceNoString));
        sourceNoComboBox.setSelectedIndex(0);
        sourceNoComboBox.setBounds(135, 75, 87, 27);
        rightPanel.add(sourceNoComboBox);

//        sourceNoComboBox.addItemListener(new StopJPanelListener(this));


        carNojtf = new JTextField();
        carNojtf.setEditable(false);
        carNojtf.setBounds(135, 158, 96, 27);
        rightPanel.add(carNojtf);
        carNojtf.setColumns(10);

        carOwnerjtf = new JTextField();
        carOwnerjtf.setEditable(false);
        carOwnerjtf.setColumns(10);
        carOwnerjtf.setBounds(135, 202, 96, 27);
        rightPanel.add(carOwnerjtf);

        sDatePicker = new DatePicker();
        sDatePicker.setEnabled(false);
        sDatePicker.getInnerTextField().setEditable(false);
//        datePicker.setTimePanleVisible(true);
        sDatePicker.setBounds(98, 249, 133, 27);
        rightPanel.add(sDatePicker);



        carFeejtf = new JTextField();
        carFeejtf.setEditable(false);
        carFeejtf.setColumns(10);
        carFeejtf.setBounds(135, 115, 96, 27);
        rightPanel.add(carFeejtf);

        //sourcePositionjtf.setText(FindJPanelDB.getSourcePositionByNo((String)sourceNoComboBox.getSelectedItem()));


        //some checkbox:
        carTypeCheckBox = new JCheckBox("carType:");
        carTypeCheckBox.setBounds(15, 34, 109, 29);
        rightPanel.add(carTypeCheckBox);
        carTypeCheckBox.addItemListener(new LookBillsJPanelListener(this));

        sourceNoCheckBox = new JCheckBox("sorceNo:");
        sourceNoCheckBox.setBounds(15, 74, 109, 29);
        rightPanel.add(sourceNoCheckBox);
        sourceNoCheckBox.addItemListener(new LookBillsJPanelListener(this));

        carFeeCheckBox = new JCheckBox("feeOver:");
        carFeeCheckBox.setBounds(15, 114, 109, 29);
        rightPanel.add(carFeeCheckBox);
        carFeeCheckBox.addItemListener(new LookBillsJPanelListener(this));

        carNoCheckBox = new JCheckBox("carNo:");
        carNoCheckBox.setBounds(15, 157, 109, 29);
        rightPanel.add(carNoCheckBox);
        carNoCheckBox.addItemListener(new LookBillsJPanelListener(this));

        carOwnerCheckBox = new JCheckBox("carOwn:");
        carOwnerCheckBox.setBounds(15, 203, 109, 29);
        rightPanel.add(carOwnerCheckBox);
        carOwnerCheckBox.addItemListener(new LookBillsJPanelListener(this));

        sTimeCheckBox = new JCheckBox("sTime:");
        sTimeCheckBox.setBounds(15, 249, 87, 29);
        rightPanel.add(sTimeCheckBox);
        sTimeCheckBox.addItemListener(new LookBillsJPanelListener(this));

        //two buttons:
        findButton = new JButton("Find");
        findButton.setBounds(135, 327, 87, 29);
        rightPanel.add(findButton);
        findButton.addActionListener(new LookBillsJPanelListener(this));

        clearButton = new JButton("Clear");
        clearButton.setBounds(15, 327, 87, 29);
        rightPanel.add(clearButton);
        clearButton.addActionListener(new LookBillsJPanelListener(this));;

        billAdminCheckBox = new JCheckBox("admin:");
        billAdminCheckBox.setBounds(15, 287, 87, 29);
        rightPanel.add(billAdminCheckBox);
        billAdminCheckBox.addItemListener(new LookBillsJPanelListener(this));

        billAdminjtf = new JTextField();
        billAdminjtf.setEditable(false);
        billAdminjtf.setColumns(10);
        billAdminjtf.setBounds(135, 291, 96, 27);
        rightPanel.add(billAdminjtf);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(15, 361, 87, 29);
        rightPanel.add(deleteButton);
        deleteButton.addActionListener(new LookBillsJPanelListener(this));

        showButton = new JButton("Show");
        showButton.setBounds(135, 361, 87, 29);
        rightPanel.add(showButton);
        showButton.addActionListener(new LookBillsJPanelListener(this));

    }

}
