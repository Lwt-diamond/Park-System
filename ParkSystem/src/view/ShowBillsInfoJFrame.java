package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;




import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.eltima.components.ui.r;

import Control.ShowBillsInfoListener;
import entity.ParkBill;
import entity.ParkCar;

import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;


public class ShowBillsInfoJFrame extends JFrame {
    public ParkBill parkBill;
    /**
     * @wbp.nonvisual location=-64,410
     */
    private final JTextPane textPane = new JTextPane();

    public static void main(String[] args) {
        ParkBill parkBill=new ParkBill("100", new ParkCar("car1", "a", "let", "191"), "123", "123", "123",1, 12, 13, "admin","123");
        new ShowBillsInfoJFrame(parkBill).setVisible(true);
    }

    public ShowBillsInfoJFrame(ParkBill parkBill)  {
        this.parkBill = parkBill;

        //关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口的大小和位置
        this.setBounds(100,100,760,576);

        //窗口居中
        this.setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 741, 452);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel zhucelabel = new JLabel("CarFee");
        zhucelabel.setHorizontalAlignment(SwingConstants.CENTER);
        zhucelabel.setFont(new Font("宋体", Font.PLAIN, 30));
        zhucelabel.setBounds(278, 37, 172, 51);
        panel.add(zhucelabel);




        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ShowBillsInfoListener(this));
        printButton.setBounds(134, 370, 107, 43);
        panel.add(printButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ShowBillsInfoListener(this));
        backButton.setBounds(434, 370, 107, 43);
        panel.add(backButton);

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createTitledBorder("Simple Bill Info"));
        leftPanel.setBounds(60, 103, 255, 229);
        panel.add(leftPanel);
        leftPanel.setLayout(null);
        JLabel lineLabel2 = new JLabel("<html>&nbsp;&nbsp;"+parkBill.getParkCar().getCarOwner()+" 's car: "+parkBill.getParkCar().getCarNo()+" \uFF0C<br>StopHours: 0.0 ,<br>PerHourShouldPay: "+parkBill.getParkPrice()+"\uFF0C<br>FreeOneHour,<br>So,Paid: "+parkBill.getCarFee());
        lineLabel2.setBounds(15, 44, 240, 113);
        leftPanel.add(lineLabel2);

        JTextArea textArea = new JTextArea();
        textArea.setRows(10);
        textArea.setColumns(28);
        JScrollPane rightScrollPane = new JScrollPane(textArea);
        rightScrollPane.setBorder(BorderFactory.createTitledBorder("Bill Info"));
        rightScrollPane.setBounds(387, 103, 274, 216);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //rightScrollPane.add(textArea);
        panel.add(rightScrollPane);
        textArea.setText(parkBill.toString());
        textArea.setEditable(false);




    }
}
