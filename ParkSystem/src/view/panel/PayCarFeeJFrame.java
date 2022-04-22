package view.panel;

import Control.CancelJPanelListener;
import Control.PayCarFeeJFrameListener;
import entity.ParkBill;
import entity.ParkCar;

import javax.swing.*;
import java.awt.*;

public class PayCarFeeJFrame extends JFrame{
    public JTextField carFeejtf;
    public ParkBill parkBill;
    public  CancelJPanelListener cancelJPanelListener;
    /**
     * @wbp.nonvisual location=-64,410
     */
    private final JTextPane textPane = new JTextPane();





    public static void main(String[] args) {
        ParkBill parkBill=new ParkBill("100", new ParkCar("car1", "a", "let", "191"), "123", "123", "123", 12, 11,13, "admin","123");
        new PayCarFeeJFrame(parkBill, new CancelJPanelListener(new CancelJPanel())).setVisible(true);
    }

    public PayCarFeeJFrame(ParkBill parkBill, CancelJPanelListener cancelJPanelListener)  {
        this.parkBill = parkBill;
        this.cancelJPanelListener =cancelJPanelListener;
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

        JLabel payCarFeeLable = new JLabel("PayCarFee");
        payCarFeeLable.setHorizontalAlignment(SwingConstants.CENTER);
        payCarFeeLable.setFont(new Font("宋体", Font.PLAIN, 30));
        payCarFeeLable.setBounds(278, 37, 172, 51);
        panel.add(payCarFeeLable);




        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new PayCarFeeJFrameListener(this));
        payButton.setBounds(134, 370, 107, 43);
        panel.add(payButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new PayCarFeeJFrameListener(this));
        backButton.setBounds(364, 370, 107, 43);
        panel.add(backButton);

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createTitledBorder("Pay Info"));
        leftPanel.setBounds(60, 103, 255, 229);
        panel.add(leftPanel);
        leftPanel.setLayout(null);

        JLabel lineLabel1 = new JLabel("Dear user:");
        lineLabel1.setBounds(15, 25, 103, 21);
        leftPanel.add(lineLabel1);

        JLabel lineLabel2 = new JLabel("<html>&nbsp;&nbsp;Your car: "+parkBill.getParkCar().getCarNo()+" \uFF0CStopHours: "+parkBill.getCarStopHours()+" ,PerHourShouldPay: "+parkBill.getParkPrice()+"\uFF0CFreeOneHour,So,YouNeedPay: "+parkBill.getCarFee());
        lineLabel2.setBounds(15, 44, 240, 113);
        leftPanel.add(lineLabel2);

        carFeejtf = new JTextField();
        carFeejtf.setBounds(92, 187, 96, 27);
        leftPanel.add(carFeejtf);
        carFeejtf.setColumns(10);

        JLabel feeLabel = new JLabel("Fee($):");
        feeLabel.setBounds(15, 190, 81, 21);
        leftPanel.add(feeLabel);

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
