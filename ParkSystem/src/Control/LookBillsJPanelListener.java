package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import dao.DBUtil;
import dao.FindJPanelDB;
import dao.LookBillsJPanelDB;
import entity.ParkBill;
import entity.ParkCar;
import entity.ParkPark;
import entity.ParkSource;
import view.panel.LookBillsJPanel;
import view.ShowBillsInfoJFrame;

public class LookBillsJPanelListener implements ActionListener, ItemListener, MouseListener {
    public LookBillsJPanel lookBillsJPanel;

    public LookBillsJPanelListener(LookBillsJPanel lookBillsJPanel) {
        this.lookBillsJPanel=lookBillsJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn= e.getActionCommand();
        if (btn.equals("Refresh")) {
            System.out.println("CLICK Refresh");
            Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
            dataRows = LookBillsJPanelDB.getAllBillsData();
            Vector<String> dataCols = new Vector<String>();
            dataCols.add("billNo");
            dataCols.add("carNo");
            dataCols.add("carType");
            dataCols.add("carOwner");
            dataCols.add("carTel");
            dataCols.add("sourceNo");
            dataCols.add("carStartDate");
            dataCols.add("carEndDate");
            dataCols.add("carStopHours");
            dataCols.add("parkPrice");
            dataCols.add("carFee");
            dataCols.add("BillAdminUserName");
            dataCols.add("BillAdminUserTel");
            lookBillsJPanel.model.setDataVector(dataRows, dataCols);



            //stopJPanel.dataJScrollPane.setViewportView(dataJTabel);
        }else if(btn.equals("Find")) {
            boolean cartype= lookBillsJPanel.carTypeCheckBox.isSelected();
            boolean sourceno =lookBillsJPanel.sourceNoCheckBox.isSelected();
            boolean feeover = lookBillsJPanel.carFeeCheckBox.isSelected();
            boolean carno = lookBillsJPanel.carNoCheckBox.isSelected();
            boolean carowner = lookBillsJPanel.carOwnerCheckBox.isSelected();
            boolean stime = lookBillsJPanel.sTimeCheckBox.isSelected();
            boolean admin = lookBillsJPanel.billAdminCheckBox.isSelected();
            Vector<Vector<String>> rows = new Vector<Vector<String>>();
            java.sql.Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            String sql = "select * from parkBill where billNo is not null";
            if(cartype) {
                System.out.println(lookBillsJPanel.carTypeComboBox.getSelectedItem()+"");
                sql+=" and carType='"+lookBillsJPanel.carTypeComboBox.getSelectedItem()+"'";
            }
            if(sourceno) {
                System.out.println(lookBillsJPanel.sourceNoComboBox.getSelectedItem()+"");
                sql+=" and sourceNo='"+lookBillsJPanel.sourceNoComboBox.getSelectedItem()+"'";

            }
            if(feeover) {
                sql+=" and carFee>"+lookBillsJPanel.carFeejtf.getText()+"";
            }
            if(carno) {
                sql+=" and carNo like '"+lookBillsJPanel.carNojtf.getText()+"%'";
            }
            if(carowner) {

                System.out.println(lookBillsJPanel.carOwnerjtf.getText().trim());
                sql+=" and carOwner like '"+lookBillsJPanel.carOwnerjtf.getText().trim()+"%'";
                System.out.println(sql);
            }
            if(stime) {
                String string = lookBillsJPanel.sDatePicker.getText();


                //datediff(day, parkPark.carStartDate,'2021-07-06')=0;
                sql+=" and datediff(day,carStartDate,'"+string+"')=0  ";
            }
            if(admin) {
                sql+=" and billAdminUserName like '"+lookBillsJPanel.billAdminjtf.getText()+"%'";
            }

            try {
                ps = conn.prepareStatement(sql);//"select * from parkSource"
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Vector<String> v = new Vector<String>();
                    v.add(rs.getString(1));//表示是获取第一个字段的值
                    v.add(rs.getString(2));
                    v.add(rs.getString(3));
                    v.add(rs.getString(4));
                    v.add(rs.getString(5));
                    v.add(rs.getString(6));
                    v.add(rs.getString(7));
                    v.add(rs.getString(8));
                    v.add(rs.getString(9));
                    v.add(rs.getString(10));
                    v.add(rs.getString(11));
                    v.add(rs.getString(12));
                    v.add(rs.getString(13));

                    rows.add(v);//将每一行的值添加到所有行中

                }
                DBUtil.release(conn, ps, rs);
            }catch (Exception e1) {
                e1.printStackTrace();
            }

//            if(rows.size()==0) {
//                JOptionPane.showMessageDialog(null, "没有找到该停车记录");
//                return;
//
//            }
            Vector<String> dataCols = new Vector<String>();
            dataCols.add("billNo");
            dataCols.add("carNo");
            dataCols.add("carType");
            dataCols.add("carOwner");
            dataCols.add("carTel");
            dataCols.add("sourceNo");
            dataCols.add("carStartDate");
            dataCols.add("carEndDate");
            dataCols.add("carStopHours");
            dataCols.add("parkPrice");
            dataCols.add("carFee");
            dataCols.add("BillAdminUserName");
            dataCols.add("BillAdminUserTel");
            lookBillsJPanel.model.setDataVector(rows, dataCols);
            //lookBillsJPanel.refreshButton.doClick();

        }else if(btn.equals("Clear")) {
            //JOptionPane.showMessageDialog(null, "update");
            lookBillsJPanel.refreshButton.doClick();
            lookBillsJPanel.carTypeCheckBox.setSelected(false);
            lookBillsJPanel.sourceNoCheckBox.setSelected(false);
            lookBillsJPanel.carFeeCheckBox.setSelected(false);
            lookBillsJPanel.carNoCheckBox.setSelected(false);
            lookBillsJPanel.carOwnerCheckBox.setSelected(false);
            lookBillsJPanel.sTimeCheckBox.setSelected(false);
            lookBillsJPanel.billAdminCheckBox.setSelected(false);
        }else if(btn.equals("Delete")) {
            if(lookBillsJPanel.dataJTabel.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(null, "请先选中一行");
                return;
            }
            int type =JOptionPane.showConfirmDialog(null, "确定删除该信息？");
            if(type == 0) {
                ParkBill parkBill =getSelectedParkBill();
                LookBillsJPanelDB.deleteParkBillInfo(parkBill);
                JOptionPane.showMessageDialog(null, "删除成功！");
            }



        }else if(btn.equals("Show")) {
            if(lookBillsJPanel.dataJTabel.getSelectedRow()<0) {
                JOptionPane.showMessageDialog(null, "请先选中一行");
                return;
            }

            ParkBill parkBill =getSelectedParkBill();
            ShowBillsInfoJFrame showBillsInfoJFrame =new ShowBillsInfoJFrame(parkBill);
            showBillsInfoJFrame.setVisible(true);



        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getSource());

        int row = lookBillsJPanel.dataJTabel.getSelectedRow();
        String billNo= lookBillsJPanel.dataJTabel.getValueAt(row, 0).toString();
        String carNo= lookBillsJPanel.dataJTabel.getValueAt(row, 1).toString();
        String carType= lookBillsJPanel.dataJTabel.getValueAt(row, 2).toString();
        String carOwner= lookBillsJPanel.dataJTabel.getValueAt(row, 3).toString();
        String carTel= lookBillsJPanel.dataJTabel.getValueAt(row, 4).toString();
        String sourceNo= lookBillsJPanel.dataJTabel.getValueAt(row, 5).toString();
        String carStartDate= lookBillsJPanel.dataJTabel.getValueAt(row, 6).toString();
        String carEndDate= lookBillsJPanel.dataJTabel.getValueAt(row, 7).toString();
        String carStopHours= lookBillsJPanel.dataJTabel.getValueAt(row, 8).toString();
        String parkPrice= lookBillsJPanel.dataJTabel.getValueAt(row, 9).toString();
        String carFee= lookBillsJPanel.dataJTabel.getValueAt(row, 10).toString();
        String billAdminUserName= lookBillsJPanel.dataJTabel.getValueAt(row, 11).toString();
        String billAdminUserTel= lookBillsJPanel.dataJTabel.getValueAt(row, 12).toString();


        ParkCar parkCar = new ParkCar(carNo, carType, carOwner, carTel);
        ParkBill parkBill = new ParkBill(billNo, parkCar, sourceNo, carStartDate, carEndDate, Float.valueOf(carStopHours),Float.valueOf(parkPrice) , Float.valueOf(carFee), billAdminUserName, billAdminUserTel);

        //创建对象为以后打算
    }

    public  ParkBill getSelectedParkBill() {
        int row = lookBillsJPanel.dataJTabel.getSelectedRow();
        String billNo= lookBillsJPanel.dataJTabel.getValueAt(row, 0).toString();
        String carNo= lookBillsJPanel.dataJTabel.getValueAt(row, 1).toString();
        String carType= lookBillsJPanel.dataJTabel.getValueAt(row, 2).toString();
        String carOwner= lookBillsJPanel.dataJTabel.getValueAt(row, 3).toString();
        String carTel= lookBillsJPanel.dataJTabel.getValueAt(row, 4).toString();
        String sourceNo= lookBillsJPanel.dataJTabel.getValueAt(row, 5).toString();
        String carStartDate= lookBillsJPanel.dataJTabel.getValueAt(row, 6).toString();
        String carEndDate= lookBillsJPanel.dataJTabel.getValueAt(row, 7).toString();
        String carStopHours= lookBillsJPanel.dataJTabel.getValueAt(row, 8).toString();
        String parkPrice= lookBillsJPanel.dataJTabel.getValueAt(row, 9).toString();
        String carFee= lookBillsJPanel.dataJTabel.getValueAt(row, 10).toString();
        String billAdminUserName= lookBillsJPanel.dataJTabel.getValueAt(row, 11).toString();
        String billAdminUserTel= lookBillsJPanel.dataJTabel.getValueAt(row, 12).toString();
        ParkCar parkCar = new ParkCar(carNo, carType, carOwner, carTel);
        ParkBill parkBill = new ParkBill(billNo, parkCar, sourceNo, carStartDate, carEndDate, Float.valueOf(carStopHours),Float.valueOf(parkPrice) , Float.valueOf(carFee), billAdminUserName, billAdminUserTel);

        return parkBill;
    }



    @Override
    public void itemStateChanged(ItemEvent e) {
        // 对复选框进行操作
        JCheckBox cb = (JCheckBox) e.getItem();
        // 获取复选框上的字符串
        String fxk = cb.getActionCommand();
        if(fxk.equals("carType:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.carTypeComboBox.setEnabled(true);
            }else {
                lookBillsJPanel.carTypeComboBox.setEnabled(false);
            }

        }else if(fxk.equals("sorceNo:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.sourceNoComboBox.setEnabled(true);
            }else {
                lookBillsJPanel.sourceNoComboBox.setEnabled(false);
            }

        }else if(fxk.equals("feeOver:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.carFeejtf.setEditable(true);
            }else {
                lookBillsJPanel.carFeejtf.setText("");
                lookBillsJPanel.carFeejtf.setEditable(false);
            }

        }else if(fxk.equals("carNo:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.carNojtf.setEditable(true);
            }else {
                lookBillsJPanel.carNojtf.setText("");
                lookBillsJPanel.carNojtf.setEditable(false);
            }

        }else if(fxk.equals("carOwn:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.carOwnerjtf.setEditable(true);
            }else {
                lookBillsJPanel.carOwnerjtf.setText("");
                lookBillsJPanel.carOwnerjtf.setEditable(false);
            }
        }else if(fxk.equals("admin:")) {
            if(cb.isSelected()) {
                lookBillsJPanel.billAdminjtf.setEditable(true);
            }else {
                lookBillsJPanel.billAdminjtf.setText("");
                lookBillsJPanel.billAdminjtf.setEditable(false);
            }

        }else if(fxk.equals("sTime:")) {

            if(cb.isSelected()) {
                lookBillsJPanel.sDatePicker.setEnabled(true);
            }else {

                lookBillsJPanel.sDatePicker.setEnabled(false);
            }
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
}
