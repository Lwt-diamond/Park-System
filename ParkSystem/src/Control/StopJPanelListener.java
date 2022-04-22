package Control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;




import dao.StopJPanelDB;
import entity.ParkCar;
import entity.ParkPark;
import entity.ParkSource;
import view.panel.StopJPanel;

public class StopJPanelListener implements ActionListener, ItemListener, MouseListener {
    public StopJPanel stopJPanel;

    public StopJPanelListener(StopJPanel stopJPanel) {
        this.stopJPanel = stopJPanel;
    }

    // 按钮事件
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        if (btn.equals("Add")) {
            String carType = (String) stopJPanel.carTypeComboBox.getSelectedItem();
            String sourceNo = (String) stopJPanel.sourceNoComboBox.getSelectedItem();
            String sourcePosition = stopJPanel.sourcePositionjtf.getText();
            String carNo = stopJPanel.carNojtf.getText();
            String carOwner = stopJPanel.carOwnerjtf.getText();
            String carTel = stopJPanel.carTeljtf.getText();
            String carStartDate = stopJPanel.datePicker
                    .getText();
            if(!carType.equals("")&& !sourceNo.equals("")&& !sourcePosition.equals("")&&
                    !carNo.equals("")&&!carOwner.equals("")&&!carTel.equals("")&&!carStartDate.equals("")) {
                System.out.println("数据没问题");
                //你懂的，要插入数据了
                //这次插入的可不少
                //添加所停车的信息
                ParkCar parkCar = new ParkCar(carNo, carType, carOwner, carTel);
                StopJPanelDB.addParkCarInfo(parkCar);
                ParkSource parkSource = new ParkSource(sourceNo, sourcePosition, "true");
                StopJPanelDB.setParkSourceInfo(parkSource);
                ParkPark parkPark = new ParkPark(carNo, sourceNo, carStartDate);
                StopJPanelDB.addParkParkInfo(parkPark);
                stopJPanel.refreshButton.doClick();

                stopJPanel.carTypeComboBox.setSelectedIndex(0);
                stopJPanel.sourcePositionjtf.setText("");
                stopJPanel.carNojtf.setText("");
                stopJPanel.carOwnerjtf.setText("");
                stopJPanel.carTeljtf.setText("");
                Vector<String> sourceNoString = StopJPanelDB.getSourceNoStringByType(carType);
                stopJPanel.sourceNoComboBox.setModel(new DefaultComboBoxModel(sourceNoString));
                stopJPanel.sourceNoComboBox.setSelectedIndex(0);
                stopJPanel.sourcePositionjtf.setText(StopJPanelDB.getSourcePositionByNo(
                        (String) stopJPanel.sourceNoComboBox.getSelectedItem()));
            }else {
                JOptionPane.showMessageDialog(null, "给我点正确的数据好吗，亲！");
            }
        }
        if (btn.equals("Clear")) {
            stopJPanel.carTypeComboBox.setSelectedIndex(0);
            stopJPanel.sourcePositionjtf.setText("");
            stopJPanel.carNojtf.setText("");
            stopJPanel.carOwnerjtf.setText("");
            stopJPanel.carTeljtf.setText("");
        }
        if (btn.equals("Refresh")) {

            System.out.println("CLICK  REFRESH");
            Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
            dataRows = StopJPanelDB.getAllData();
            Vector<String> dataCols = new Vector<String>();
            dataCols.add("SourceNo");
            dataCols.add("sourcePosition");
            dataCols.add("carNo");
            dataCols.add("carOwner");
            dataCols.add("carTel");
            dataCols.add("carType");
            dataCols.add("carStartDate");
            stopJPanel.model.setDataVector(dataRows, dataCols);
            //stopJPanel.dataJScrollPane.setViewportView(dataJTabel);
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

//        System.out.println(e.getSource());
        if(e.getSource()==stopJPanel.carTypeComboBox) {
            String carType = (String) e.getItem();
            System.out.println(carType);
            stopJPanel.carType = carType;
            Vector<String> sourceNoString = StopJPanelDB.getSourceNoStringByType(carType);
            stopJPanel.sourceNoComboBox.setModel(new DefaultComboBoxModel(sourceNoString));
            stopJPanel.sourceNoComboBox.setSelectedIndex(0);
            stopJPanel.sourcePositionjtf.setText(StopJPanelDB.getSourcePositionByNo(
                    (String) stopJPanel.sourceNoComboBox.getSelectedItem()));
        }else if(e.getSource()==stopJPanel.sourceNoComboBox) {
            stopJPanel.sourcePositionjtf.setText(StopJPanelDB.getSourcePositionByNo(
                    (String) stopJPanel.sourceNoComboBox.getSelectedItem()));
        }



    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
