package view.panel;


import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.Image;

public class InformationJPanel extends JPanel{

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(470, 230, 1000, 680);
        frame.setLocationRelativeTo(null);
        JPanel contentJpanel= new JPanel();
        contentJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentJpanel);
        contentJpanel.setLayout(null);
        contentJpanel.add(new InformationJPanel());
        frame.setVisible(true);

    }
    public InformationJPanel() {

        this.setSize(982, 435);
        this.setLayout(null);

        Image image = new ImageIcon("image/beijin88.png").getImage();
        JPanel rightPanel = new BackGroundPanel(image);
        //JPanel rightPanel = new JPanel();
        rightPanel.setBounds(114, 15, 806, 415);
        this.add(rightPanel);
        rightPanel.setLayout(null);
      

    }
}
