package Control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import view.ShowBillsInfoJFrame;

public class ShowBillsInfoListener implements ActionListener{
    private ShowBillsInfoJFrame showBillsInfoJFrame;
    public ShowBillsInfoListener(ShowBillsInfoJFrame showBillsInfoJFrame) {
        super();
        this.showBillsInfoJFrame = showBillsInfoJFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn=e.getActionCommand();
        System.out.println(e);
        if(btn.equals("Print")) {
            String  str =showBillsInfoJFrame.parkBill.getBillNo().replace(':','-');
            System.out.println(str);
            File file = new File("D://Admin/"+str+".txt");
            WriteBuffer(file,showBillsInfoJFrame.parkBill.toString());
            JOptionPane.showMessageDialog(null, "PayBillInfo:"+file.getPath());
            showBillsInfoJFrame.dispose();
        }else if(btn.equals("Back")) {
            showBillsInfoJFrame.dispose();
            return;
        }
    }
    public  static  void  WriteBuffer(File file,String s)  {
        try {

            file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(s);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
