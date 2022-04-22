package Test;

import java.io.*;
import java.util.Scanner;

public class TestFileRW {
    public static void main(String[] args) {
        RWBuffer();
    }
    public  static  void  RWBuffer()  {
        File file = new File("d://ATest//RWBuffer.txt");
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
            String s = "import java.awt.BorderLayout;\n" +
                    "import java.awt.EventQueue;\n" +
                    "\n" +
                    "import javax.swing.BorderFactory;\n" +
                    "import javax.swing.JFrame;\n" +
                    "import javax.swing.JPanel;\n" +
                    "import javax.swing.border.EmptyBorder;\n" +
                    "\n" +
                    "import com.eltima.components.ui.DatePicker;\n" +
                    "import dao.DengLuDB;\n" +
                    "\n" +
                    "import javax.swing.JLabel;\n" +
                    "import javax.swing.ImageIcon;\n" +
                    "import java.awt.Color;\n" +
                    "import javax.swing.SwingConstants;\n" +
                    "import javax.swing.JTextField;\n" +
                    "import javax.swing.JComboBox;\n" +
                    "import javax.swing.JButton;\n" +
                    "import java.awt.Font;\n" +
                    "import java.util.Vector;";
            bw.write(s);
            bw.flush();
            System.out.println("----------write end-------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("123");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            System.out.println("1234");
            //System.out.println(br.readLine());
            while((s = br.readLine())!=null){
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
