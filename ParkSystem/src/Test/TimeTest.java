package Test;

import com.eltima.components.ui.DatePicker;

import javax.management.timer.Timer;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest extends JFrame {
    public TimeTest()  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(470, 230, 1000, 680);
        setLocationRelativeTo(null);
        DatePicker datePicker = new DatePicker(new Date(),
                "yyyy-MM-dd HH:mm:ss",
                new Font("宋体",Font.BOLD,20),
                new Dimension(177, 24));
        datePicker.setBounds(531, 171, 126, 29);
        datePicker.setTimePanleVisible(true);
        this.add(datePicker);
        this.setVisible(true);

    }

    public static void main(String[] args) {

        TimeTest timeTest = new TimeTest();

    }
}
