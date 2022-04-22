package view;



import Control.UserJFrameListener;
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
import java.awt.Font;

public class UserJFrame extends JFrame{
    public JPanel contentPanel;
    public DefaultTableModel model;
    public String carType;
    public JTextField userNamejtf;
    public JPasswordField passwordjtf;
    public JPasswordField rePasswordjtf;
    public JButton okbtn;
    public JButton clearBtn;
  
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setBounds(470, 230, 1000, 680);
//        frame.setLocationRelativeTo(null);
//        JPanel contentJpanel= new JPanel();
//        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        frame.setContentPane(contentJpanel);
//        contentJpanel.setLayout(null);
//        contentJpanel.add(new UserJFrame());
//        frame.setVisible(true);
    	new UserJFrame("123").setVisible(true);

    }
    public UserJFrame(String userName) {

    	this.setLocationRelativeTo(null);
        this.setBounds(470, 230,400, 392);
        getContentPane().setLayout(null);

    
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
        System.out.println(dataCols);
        System.out.println(dataRows);
        model = new DefaultTableModel();
        model.setDataVector(dataRows, dataCols);
        
        // leftPanel
        contentPanel = new JPanel();
        contentPanel.setBounds(15, 15, 357, 321);
        getContentPane().add(contentPanel);
        contentPanel.setBorder(BorderFactory.createTitledBorder("Information"));
        contentPanel.setLayout(null);
        
        userNamejtf = new JTextField();
        userNamejtf.setEditable(false);
        userNamejtf.setBounds(155, 86, 96, 27);
        contentPanel.add(userNamejtf);
        userNamejtf.setBounds(155, 86, 96, 27);
        contentPanel.add(userNamejtf);
        userNamejtf.setColumns(10);
        userNamejtf.setText(userName);
        
        passwordjtf = new JPasswordField();
        passwordjtf.setBounds(155, 147, 127, 27);
        contentPanel.add(passwordjtf);
        
        rePasswordjtf = new JPasswordField();
        rePasswordjtf.setBounds(155, 204, 127, 27);
        contentPanel.add(rePasswordjtf);
        
        JLabel userNamelabel = new JLabel("UserName:");
        userNamelabel.setBounds(34, 89, 81, 21);
        contentPanel.add(userNamelabel);
        
        JLabel Passwordlabel = new JLabel("Password:");
        Passwordlabel.setBounds(34, 150, 81, 21);
        contentPanel.add(Passwordlabel);
        
        JLabel rePasswordlabel = new JLabel("Repassword:");
        rePasswordlabel.setBounds(34, 207, 81, 21);
        contentPanel.add(rePasswordlabel);
        
        okbtn = new JButton("Submit");
        okbtn.setBounds(184, 260, 106, 29);
        contentPanel.add(okbtn);
        okbtn.addActionListener(new UserJFrameListener(this));
        
        JLabel titlelabel = new JLabel("Set Password");
        titlelabel.setBounds(113, 50, 138, 21);
        contentPanel.add(titlelabel);
        
        
        userNamejtf.setText(userName);
        
        clearBtn = new JButton("Clear");
        clearBtn.setBounds(48, 260, 106, 29);
        contentPanel.add(clearBtn);
        clearBtn.addActionListener(new UserJFrameListener(this));
        

    }
}
