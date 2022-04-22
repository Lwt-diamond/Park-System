package view.panel;



import dao.AdminJPanelUserInfoDB;
import dao.HomePageDB;
import dao.LookBillsJPanelDB;
import dao.SourceInfoJPanelDB;
import view.panel.BackGroundPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.AdminJPanelListener;
import Control.ControlListener;
import Control.SourceInfoJPanelListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import com.eltima.components.ui.DatePicker;

public class AdminJPanel extends JPanel{
    public CardLayout cardLayout;

    public DefaultTableModel adminModel;
    public DatePicker datePicker;


    public JPanel userPanel;
    public JButton updateBtn;
    public JTable userTable;
    public DefaultTableModel userModel;
    public JLabel photoLabel;
    private JLabel userNamelabel;
    private JLabel Passwordlabel;
    private JLabel Rightlabel;
    private JLabel Passwordlabel_1;
    private JLabel rightlabel_2;
    public JTextField namejtf_2;
    public JTextField passwordjtf_2;
    public JTextField rightjtf_2;
    public JButton userbtnNewButton;
    public JButton adminbtnNewButton;
    public JTextField userNamejtf;
    public JButton refreshBtn;
    public JButton addBtn;
    public JComboBox comboBox;
    public JTextField passwordjtf;
    public JScrollPane scrollPane;
    public JTable adminTable;
    public JTextField adminNamejtf;
    public JTextField adminuserNamejtf;
    public JTextField adminIDjtf;
    public JTextField adminSexjtf;
    public JTextField adminAddressjtf;
    public JTextField adminTeljtf;
    public JButton aFindbtn;
    public JButton btnAupdate;
    public JButton btnAdelete;
    public JButton btnArefresh;
    public JButton btnAadd;

    public File file;
    private JLabel lblAdminbirth;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new AdminJPanel());
        frame.setVisible(true);

    }
    public AdminJPanel() {

        this.setSize(982, 435);
        this.setLayout(null);
        userModel = new DefaultTableModel();

        cardLayout = new CardLayout(0,0);

        JPanel contentJPanel = new JPanel();
        contentJPanel.setBounds(179, 15, 793, 415);
        this.add(contentJPanel);
        contentJPanel.setLayout(cardLayout);

        JPanel adminPanel=new JPanel();
        adminPanel.setBounds(0, 0, 672, 415);
        adminPanel.setBorder(BorderFactory.createTitledBorder("Admin Info"));
        adminPanel.setLayout(null);
        contentJPanel.add(adminPanel,"admin");

        scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 33, 351, 367);
        adminPanel.add(scrollPane);

        adminModel= new DefaultTableModel();
        Vector<String> adminCols = new Vector<String>();
        adminCols.add("userName");
        adminCols.add("adminName");
        adminCols.add("adminID");
        adminCols.add("adminBirthday");
        adminCols.add("adminSex");
        adminCols.add("adminAddress");
        adminCols.add("adminTel");
        adminCols.add("adminPhoto");

        Vector<Vector<String>> adminRows= AdminJPanelUserInfoDB.getAdminInfo();
        this.setLayout(null);
        adminModel.setDataVector(adminRows, adminCols);
        adminTable = new JTable(adminModel);
        adminTable.addMouseListener(new AdminJPanelListener(this));

        ControlListener.fitTableColumns(adminTable);
        scrollPane.setViewportView(adminTable);

        JPanel panelOperate = new JPanel();
        panelOperate.setBounds(400, 32, 378, 368);
        adminPanel.add(panelOperate);
        panelOperate.setLayout(null);

        photoLabel = new JLabel("Photo");
        file = new File("image\\white.png");
        photoLabel.setIcon(new ImageIcon(String.valueOf(file)));
        photoLabel.setBounds(29, 54, 90, 103);
        panelOperate.add(photoLabel);

        JLabel lblUsername = new JLabel("UserName:");
        lblUsername.setBounds(159, 26, 81, 21);
        panelOperate.add(lblUsername);

        adminuserNamejtf = new JTextField();
        adminuserNamejtf.setBounds(249, 23, 96, 27);
        panelOperate.add(adminuserNamejtf);
        adminuserNamejtf.setColumns(10);

        JLabel lblNewLabel = new JLabel("adminName:");
        lblNewLabel.setBounds(146, 71, 94, 21);
        panelOperate.add(lblNewLabel);

        adminNamejtf = new JTextField();
        adminNamejtf.setBounds(249, 68, 96, 27);
        panelOperate.add(adminNamejtf);
        adminNamejtf.setColumns(10);

        JLabel lblAdminid = new JLabel("adminID:");
        lblAdminid.setBounds(159, 113, 94, 21);
        panelOperate.add(lblAdminid);

        adminIDjtf = new JTextField();
        adminIDjtf.setBounds(249, 110, 96, 27);
        panelOperate.add(adminIDjtf);
        adminIDjtf.setColumns(10);

        adminSexjtf = new JTextField();
        adminSexjtf.setBounds(249, 178, 96, 27);
        panelOperate.add(adminSexjtf);
        adminSexjtf.setColumns(10);

        JLabel lblAdminsex = new JLabel("adminSex:");
        lblAdminsex.setBounds(159, 181, 81, 21);
        panelOperate.add(lblAdminsex);

        adminAddressjtf = new JTextField();
        adminAddressjtf.setBounds(249, 215, 96, 27);
        panelOperate.add(adminAddressjtf);
        adminAddressjtf.setColumns(10);

        JLabel lblAdminadd = new JLabel("adminAdd:");
        lblAdminadd.setBounds(153, 218, 93, 21);
        panelOperate.add(lblAdminadd);

        adminTeljtf = new JTextField();
        adminTeljtf.setBounds(249, 251, 96, 27);
        panelOperate.add(adminTeljtf);
        adminTeljtf.setColumns(10);

        JLabel lblAdminadd_1 = new JLabel("adminTel:");
        lblAdminadd_1.setBounds(153, 254, 93, 21);
        panelOperate.add(lblAdminadd_1);

        JButton adminChosebtn = new JButton("Choose");
        adminChosebtn.setBounds(29, 190, 96, 29);
        panelOperate.add(adminChosebtn);
        adminChosebtn.addActionListener(new AdminJPanelListener(this));

        aFindbtn = new JButton("AFind");
        aFindbtn.setBounds(261, 290, 92, 29);
        panelOperate.add(aFindbtn);
        aFindbtn.addActionListener(new AdminJPanelListener(this));

        btnAupdate = new JButton("AUpdate");
        btnAupdate.setBounds(74, 329, 92, 29);
        panelOperate.add(btnAupdate);
        btnAupdate.addActionListener(new AdminJPanelListener(this));

        btnAdelete = new JButton("ADelete");
        btnAdelete.setBounds(151, 290, 92, 29);
        panelOperate.add(btnAdelete);
        btnAdelete.addActionListener(new AdminJPanelListener(this));

        btnArefresh = new JButton("ARefresh");
        btnArefresh.setBounds(215, 329, 91, 29);
        panelOperate.add(btnArefresh);
        btnArefresh.addActionListener(new AdminJPanelListener(this));

        btnAadd = new JButton("AAdd");
        btnAadd.setBounds(31, 293, 91, 29);
        panelOperate.add(btnAadd);
        btnAadd.addActionListener(new AdminJPanelListener(this));

        lblAdminbirth = new JLabel("Birthday:");
        lblAdminbirth.setBounds(153, 149, 94, 21);
        panelOperate.add(lblAdminbirth);

        datePicker = new DatePicker();
        datePicker.setBounds(249, 149, 114, 21);
        panelOperate.add(datePicker);

        cardLayout.show(contentJPanel, "admin");

        userPanel = new JPanel();
        userPanel.setBounds(0, 0, 672, 415);
        contentJPanel.add(userPanel,"user");
        userPanel.setBorder(BorderFactory.createTitledBorder("User Info"));
        userPanel.setLayout(null);

        cardLayout.show(contentJPanel, "user");

        Vector<String> userCols = new Vector<String>();
        userCols.add("userName");
        userCols.add("userPassword");
        userCols.add("userRight");
        Vector<Vector<String>> userRows= AdminJPanelUserInfoDB.getUserInfo();
        this.setLayout(null);
        userModel.setDataVector(userRows, userCols);
        userTable = new JTable(userModel);
        ControlListener.fitTableColumns(userTable);
        userTable.addMouseListener(new AdminJPanelListener(this));


        JScrollPane UserInfoJScrollPane = new JScrollPane(userTable);
        UserInfoJScrollPane.setBounds(68, 63, 249, 173);
        userPanel.add(UserInfoJScrollPane);
        UserInfoJScrollPane.setBorder(BorderFactory.createTitledBorder("login table"));

        updateBtn = new JButton("Update");
        updateBtn.setBounds(368, 250, 96, 29);
        userPanel.add(updateBtn);
        updateBtn.addActionListener(new AdminJPanelListener(this));

        JLabel tip1 = new JLabel("Tip: Before update login please click a row from Table!\r\n\r\n");
        tip1.setBounds(15, 27, 542, 21);
        userPanel.add(tip1);

        userNamelabel = new JLabel("UserName:");
        userNamelabel.setBounds(368, 69, 81, 21);
        userPanel.add(userNamelabel);

        Passwordlabel = new JLabel("Password:");
        Passwordlabel.setBounds(368, 127, 81, 21);
        userPanel.add(Passwordlabel);

        Rightlabel = new JLabel("right:");
        Rightlabel.setBounds(393, 187, 81, 21);
        userPanel.add(Rightlabel);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"管理员","普通用户"}));
        comboBox.setBounds(473, 184, 96, 27);
        userPanel.add(comboBox);

        JButton findbtn = new JButton("Find");
        findbtn.setBounds(492, 250, 96, 29);
        userPanel.add(findbtn);
        findbtn.addActionListener(new AdminJPanelListener(this));

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new AdminJPanelListener(this));
        deleteBtn.setBounds(492, 306, 96, 29);
        userPanel.add(deleteBtn);

        JPanel panel = new JPanel();
        panel.setBounds(68, 251, 249, 152);
        userPanel.add(panel);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("Info:"));

        JLabel userNamelabel_1 = new JLabel("UserName:");
        userNamelabel_1.setBounds(46, 28, 81, 21);
        panel.add(userNamelabel_1);

        Passwordlabel_1 = new JLabel("Password:");
        Passwordlabel_1.setBounds(46, 64, 81, 21);
        panel.add(Passwordlabel_1);

        rightlabel_2 = new JLabel("   right:");
        rightlabel_2.setBounds(46, 100, 81, 21);
        panel.add(rightlabel_2);

        namejtf_2 = new JTextField();
        namejtf_2.setEditable(false);
        namejtf_2.setBounds(133, 25, 101, 27);
        panel.add(namejtf_2);
        namejtf_2.setColumns(10);

        passwordjtf_2 = new JTextField();
        passwordjtf_2.setEditable(false);
        passwordjtf_2.setColumns(10);
        passwordjtf_2.setBounds(133, 61, 101, 27);
        panel.add(passwordjtf_2);

        rightjtf_2 = new JTextField();
        rightjtf_2.setEditable(false);
        rightjtf_2.setColumns(10);
        rightjtf_2.setBounds(133, 97, 101, 27);
        panel.add(rightjtf_2);

        userNamejtf = new JTextField();
        userNamejtf.setColumns(10);
        userNamejtf.setBounds(473, 66, 101, 27);
        userPanel.add(userNamejtf);

        refreshBtn = new JButton("RefreshTable");
        refreshBtn.setBounds(412, 350, 145, 29);
        userPanel.add(refreshBtn);
        refreshBtn.addActionListener(new AdminJPanelListener(this));
        addBtn = new JButton("Add");
        addBtn.setBounds(368, 306, 96, 29);
        userPanel.add(addBtn);
        addBtn.addActionListener(new AdminJPanelListener(this));

        passwordjtf = new JTextField();
        passwordjtf.setColumns(10);
        passwordjtf.setBounds(473, 124, 101, 27);
        userPanel.add(passwordjtf);

        userbtnNewButton = new JButton("UserInfo");
        userbtnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentJPanel, "user");
            }
        });
        userbtnNewButton.setBounds(34, 121, 130, 48);
        add(userbtnNewButton);

        adminbtnNewButton = new JButton("AdminInfo");
        adminbtnNewButton.setBounds(34, 229, 130, 48);
        add(adminbtnNewButton);
        adminbtnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentJPanel, "admin");
            }
        });




    }
}
