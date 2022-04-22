package Test;

import javax.swing.*;
import java.awt.*;

public class JTextAreaTest extends JFrame {
    public static void main(String[] args) {
        new JTextAreaTest().setVisible(true);
    }

    public JTextAreaTest() throws HeadlessException {

        JTextArea txaDisplay = new JTextArea();
        JScrollPane scroll = new JScrollPane(txaDisplay);
        scroll.setBounds(100,1001,600,600);
        this.setContentPane(scroll);
        //把定义的JTextArea放到JScrollPane里面去
        txaDisplay.setText("import java.awt.BorderLayout;\r\n" +
                "import java.awt.EventQueue;\r\n" +
                "\r\n" +
                "import javax.swing.BorderFactory;\r\n" +
                "import javax.swing.JFrame;\r\n" +
                "import javax.swing.JPanel;\r\n" +
                "import javax.swing.border.EmptyBorder;\r\n" +
                "\r\n" +
                "import com.eltima.components.ui.DatePicker;\r\n" +
                "import dao.DengLuDB;\r\n" +
                "\r\n" +
                "import javax.swing.JLabel;\r\n" +
                "import javax.swing.ImageIcon;\r\n" +
                "import java.awt.Color;\r\n" +
                "import javax.swing.SwingConstants;\r\n" +
                "import javax.swing.JTextField;\r\n" +
                "import javax.swing.JComboBox;\r\n" +
                "import javax.swing.JButton;\r\n" +
                "import java.awt.Font;\r\n" +
                "import java.util.Vector;");
      //分别设置水平和垂直滚动条自动出现
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setVisible(true);
    }
}
