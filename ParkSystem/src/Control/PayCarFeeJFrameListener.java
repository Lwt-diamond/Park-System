package Control;

import dao.CancelJPanelDB;
import view.panel.FindJPanel;
import view.panel.PayCarFeeJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PayCarFeeJFrameListener implements ActionListener {
    public PayCarFeeJFrame payCarFeeJFrame;

    public PayCarFeeJFrameListener(PayCarFeeJFrame payCarFeeJFrame) {
        this.payCarFeeJFrame = payCarFeeJFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        if(btn.equals("Back")){
            payCarFeeJFrame.dispose();
        }else if(btn.equals("Pay")){
            try {
                // 超期处理窗口中的缴费文本框上获取的数据
                Float payFee = Float.valueOf(payCarFeeJFrame.carFeejtf.getText().trim());
                if (payFee  >= payCarFeeJFrame.parkBill.getCarFee()) {
                    String message = "您已缴费成功!找您:"+Float.valueOf(new DecimalFormat("#.00").format(payFee-payCarFeeJFrame.parkBill.getCarFee()))+"元";
                    //缴费信息写进DB，删除停车信息
                    CancelJPanelDB.addParkBillInfo(payCarFeeJFrame.parkBill);
                    //不要慌。还没调用
                    JOptionPane.showMessageDialog(null, message);
                    int type =JOptionPane.showConfirmDialog(null, "PayBillInfo is needed printed?");
                    if(type==0){
                        String  str =payCarFeeJFrame.parkBill.getBillNo().replace(':','-');
                        System.out.println(str);
                        File file = new File("D://Bills/"+str+".txt");
                        WriteBuffer(file,payCarFeeJFrame.parkBill.toString());
                        JOptionPane.showMessageDialog(null, "PayBillInfo:"+file.getPath());
                    }
                    payCarFeeJFrame.dispose();
                }else
                {
                    JOptionPane.showMessageDialog(null, "不支持分期付款");
                }

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "输入格式不正确！");
            }
        }
    }

    //把字符串写进文件
    //缓存字符输入流 BufferedReader 可以一次读取一行数据
    //PrintWriter 缓存字符输出流， 可以一次写出一行数据
    public  static  void  WriteBuffer(File file,String s)  {
        try {
            if(!file.exists())
                file.createNewFile();
            else{
                System.out.println("文件已存在："+file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(s);
            bw.flush();
            System.out.println("----------write end-------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("123");

    }
}
