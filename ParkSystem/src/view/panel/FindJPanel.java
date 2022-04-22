package view.panel;


import com.eltima.components.ui.DatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;




import com.eltima.components.ui.DatePicker;

import Control.FindJPanelListener;
import dao.FindJPanelDB;
import dao.StopJPanelDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class FindJPanel extends JPanel{
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

    public JCheckBox carTypeCheckBox;
    public JCheckBox sourceNoCheckBox;
    public JCheckBox sourcePoCheckBox;
    public JCheckBox carNoCheckBox;
    public JCheckBox carOwnerCheckBox;
    public JCheckBox carTelCheckBox;
    public JCheckBox sTimeCheckBox;

    public JTextField carTypejtf2;
    public JTextField sourceNojtf2;
    public JTextField carOwnerjtf2;
    public JTextField carTeljtf2;
    public JTextField startTimejtf2;
    public JTextField sourcePositionjtf2;
    public JTextField carNojtf2;

    private JPanel panel;
    private JLabel carTypeLabel_1;

    private JLabel carTelLabel_1;

    private JLabel lblStarttime_1;

    private JLabel carNoLabel_1;

    private JLabel sourcePositionLabel_1;


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new FindJPanel());
        frame.setVisible(true);

    }
    public FindJPanel() {


        this.setSize(982, 435);
        this.setLayout(null);


        Vector<String> dataCols = new Vector<String>();
        dataCols.add("SourceNo");
        dataCols.add("sourcePosition");
        dataCols.add("carNo");
        dataCols.add("carOwner");
        dataCols.add("carTel");
        dataCols.add("carType");
        dataCols.add("carStartDate");
        Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
        dataRows = FindJPanelDB.getAllData();
        //System.out.println(dataCols);
        //System.out.println(dataRows);
        model = new DefaultTableModel();
        model.setDataVector(dataRows, dataCols);
        dataJTabel= new JTable(model);

        dataJScrollPane = new JScrollPane() ;
        dataJScrollPane.setViewportView(dataJTabel);
        dataJScrollPane.setLocation(15, 49);
        dataJScrollPane.setSize(669, 284);
        dataJTabel.addMouseListener(new FindJPanelListener(this));

        // leftPanel
        leftPanel = new JPanel();
        leftPanel.setBounds(15, 15, 699, 348);
        leftPanel.add(dataJScrollPane);
        this.add(leftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Information"));
        leftPanel.setLayout(null);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new FindJPanelListener(this));
        refreshButton.setBounds(565, 15, 90, 29);
        leftPanel.add(refreshButton);



        // rightPanel

//        Image image = new ImageIcon("image/beijin88.jpg").getImage();
//        JPanel rightPanel = new BackGroundPanel(image);
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(721, 15, 250, 348);
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

//        carTypeComboBox.addItemListener(new StopJPanelListener(this));
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


        carTeljtf = new JTextField();
        carTeljtf.setEditable(false);
        carTeljtf.setColumns(10);
        carTeljtf.setBounds(135, 244, 96, 27);
        rightPanel.add(carTeljtf);

        datePicker = new DatePicker();
        datePicker.setEnabled(false);
        datePicker.getInnerTextField().setEditable(false);
        datePicker.setTimePanleVisible(true);
        datePicker.setBounds(100, 280, 133, 27);
        rightPanel.add(datePicker);



        sourcePositionjtf = new JTextField();
        sourcePositionjtf.setEditable(false);
        sourcePositionjtf.setColumns(10);
        sourcePositionjtf.setBounds(135, 115, 96, 27);
        rightPanel.add(sourcePositionjtf);

        //sourcePositionjtf.setText(FindJPanelDB.getSourcePositionByNo((String)sourceNoComboBox.getSelectedItem()));


        //some checkbox:
        carTypeCheckBox = new JCheckBox("carType:");
        carTypeCheckBox.setBounds(15, 34, 109, 29);
        rightPanel.add(carTypeCheckBox);
        carTypeCheckBox.addItemListener(new FindJPanelListener(this));

        sourceNoCheckBox = new JCheckBox("sorceNo:");
        sourceNoCheckBox.setBounds(15, 74, 109, 29);
        rightPanel.add(sourceNoCheckBox);
        sourceNoCheckBox.addItemListener(new FindJPanelListener(this));

        sourcePoCheckBox = new JCheckBox("sorcePo:");
        sourcePoCheckBox.setBounds(15, 114, 109, 29);
        rightPanel.add(sourcePoCheckBox);
        sourcePoCheckBox.addItemListener(new FindJPanelListener(this));

        carNoCheckBox = new JCheckBox("carNo:");
        carNoCheckBox.setBounds(15, 157, 109, 29);
        rightPanel.add(carNoCheckBox);
        carNoCheckBox.addItemListener(new FindJPanelListener(this));

        carOwnerCheckBox = new JCheckBox("carOwn:");
        carOwnerCheckBox.setBounds(15, 203, 109, 29);
        rightPanel.add(carOwnerCheckBox);
        carOwnerCheckBox.addItemListener(new FindJPanelListener(this));

        carTelCheckBox = new JCheckBox("carTel:");
        carTelCheckBox.setBounds(15, 238, 109, 29);
        rightPanel.add(carTelCheckBox);
        carTelCheckBox.addItemListener(new FindJPanelListener(this));

        sTimeCheckBox = new JCheckBox("sTime:");
        sTimeCheckBox.setBounds(15, 280, 87, 29);
        rightPanel.add(sTimeCheckBox);
        sTimeCheckBox.addItemListener(new FindJPanelListener(this));

        //two buttons:
        JButton findButton = new JButton("Find");
        findButton.setBounds(141, 310, 87, 29);
        rightPanel.add(findButton);
        findButton.addActionListener(new FindJPanelListener(this));

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(15, 310, 87, 29);
        rightPanel.add(updateButton);
        updateButton.addActionListener(new FindJPanelListener(this));

        //car info panel: just for showing info
        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Car Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(15, 359, 952, 76);
        add(panel);
        panel.setLayout(null);

        carTypeLabel_1 = new JLabel("carType:");
        carTypeLabel_1.setBounds(15, 18, 72, 21);
        panel.add(carTypeLabel_1);

        carTypejtf2 = new JTextField();
        carTypejtf2.setEditable(false);
        carTypejtf2.setColumns(10);
        carTypejtf2.setBounds(97, 15, 96, 27);
        panel.add(carTypejtf2);

        JLabel sourceNoLabel_1 = new JLabel("sorceNo:");
        sourceNoLabel_1.setBounds(15, 43, 72, 29);
        panel.add(sourceNoLabel_1);

        sourceNojtf2 = new JTextField();
        sourceNojtf2.setEditable(false);
        sourceNojtf2.setColumns(10);
        sourceNojtf2.setBounds(97, 44, 96, 27);
        panel.add(sourceNojtf2);

        JLabel carOwnerLabel_1 = new JLabel("carOwnr:");
        carOwnerLabel_1.setBounds(487, 18, 78, 29);
        panel.add(carOwnerLabel_1);

        carOwnerjtf2 = new JTextField();
        carOwnerjtf2.setEditable(false);
        carOwnerjtf2.setColumns(10);
        carOwnerjtf2.setBounds(580, 15, 96, 27);
        panel.add(carOwnerjtf2);

        carTelLabel_1 = new JLabel(" carTel:");
        carTelLabel_1.setBounds(727, 14, 78, 29);
        panel.add(carTelLabel_1);

        carTeljtf2 = new JTextField();
        carTeljtf2.setEditable(false);
        carTeljtf2.setColumns(10);
        carTeljtf2.setBounds(820, 15, 117, 27);
        panel.add(carTeljtf2);

        lblStarttime_1 = new JLabel("  sTime:");
        lblStarttime_1.setBounds(487, 43, 78, 29);
        panel.add(lblStarttime_1);

        startTimejtf2 = new JTextField();
        startTimejtf2.setEditable(false);
        startTimejtf2.setColumns(10);
        startTimejtf2.setBounds(580, 44, 185, 27);
        panel.add(startTimejtf2);

        carNoLabel_1 = new JLabel("  carNo:");
        carNoLabel_1.setBounds(254, 14, 78, 29);
        panel.add(carNoLabel_1);

        carNojtf2 = new JTextField();
        carNojtf2.setEditable(false);
        carNojtf2.setColumns(10);
        carNojtf2.setBounds(333, 15, 96, 27);
        panel.add(carNojtf2);

        sourcePositionLabel_1 = new JLabel("sorcePo:");
        sourcePositionLabel_1.setBounds(254, 43, 78, 29);
        panel.add(sourcePositionLabel_1);

        sourcePositionjtf2 = new JTextField();
        sourcePositionjtf2.setEditable(false);
        sourcePositionjtf2.setColumns(10);
        sourcePositionjtf2.setBounds(333, 44, 96, 27);
        panel.add(sourcePositionjtf2);

    }
}
