package Control;


import dao.CancelJPanelDB;
import dao.DBUtil;
import dao.FindJPanelDB;
import entity.*;
import view.panel.CancelJPanel;
import view.panel.PayCarFeeJFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class CancelJPanelListener implements MouseListener, ActionListener, ItemListener {
    CancelJPanel cancelJPanel;
    private ParkCar parkcar;
    private ParkPark parkpark;
    private ParkSource parksource;
    public CancelJPanelListener(CancelJPanel cancelJPanel) {

        this.cancelJPanel = cancelJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn= e.getActionCommand();
        if (btn.equals("Refresh")) {
            //System.out.println("CLICK");
            Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
            dataRows = FindJPanelDB.getAllData();
            Vector<String> dataCols = new Vector<String>();
            dataCols.add("SourceNo");
            dataCols.add("sourcePosition");
            dataCols.add("carNo");
            dataCols.add("carOwner");
            dataCols.add("carTel");
            dataCols.add("carType");
            dataCols.add("carStartDate");
            cancelJPanel.model.setDataVector(dataRows, dataCols);

            cancelJPanel.sourceNojtf2.setText("");
            cancelJPanel.sourcePositionjtf2.setText("");
            cancelJPanel.startTimejtf2.setText("");
            cancelJPanel.carOwnerjtf2.setText("");
            cancelJPanel.carTeljtf2.setText("");
            cancelJPanel.carTypejtf2.setText("");
            cancelJPanel.carNojtf2.setText("");

            //stopJPanel.dataJScrollPane.setViewportView(dataJTabel);
        }else if(btn.equals("Find")) {
            boolean cartype= cancelJPanel.carTypeCheckBox.isSelected();
            boolean sourceno = cancelJPanel.sourceNoCheckBox.isSelected();
            boolean sourcepo = cancelJPanel.sourcePoCheckBox.isSelected();
            boolean carno = cancelJPanel.carNoCheckBox.isSelected();
            boolean carowner = cancelJPanel.carOwnerCheckBox.isSelected();
            boolean cartel = cancelJPanel.carOwnerCheckBox.isSelected();
            boolean stime = cancelJPanel.carOwnerCheckBox.isSelected();
            Vector<Vector<String>> rows = new Vector<Vector<String>>();
            Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            String sql = "select parkSource.SourceNo,parkSource.sourcePosition,parkPark.carNo,carOwner,carTel,carType,parkPark.carStartDate"
                    + " from parkSource left join parkPark on parkSource.sourceNo =parkPark.sourceNo left join parkCar on parkPark.carNo = parkCar.carNo "
                    + " where sourceIsUsed= 'true' ";
            if(cartype) {
                System.out.println(cancelJPanel.carTypeComboBox.getSelectedItem()+"");
                sql+=" and parkCar.carType='"+ cancelJPanel.carTypeComboBox.getSelectedItem()+"'";
            }
            if(sourceno) {
                System.out.println(cancelJPanel.sourceNoComboBox.getSelectedItem()+"");
                sql+=" and parkSource.sourceNo='"+ cancelJPanel.sourceNoComboBox.getSelectedItem()+"'";

            }
            if(sourcepo) {
                sql+=" and parkSource.sourcePosition like'"+ cancelJPanel.sourcePositionjtf.getText()+"%'";
            }
            if(carno) {
                sql+=" and parkPark.carNo='"+ cancelJPanel.carNojtf.getText()+"'";
            }
            if(carowner) {
                //bug，暂未修复
                System.out.println(cancelJPanel.carOwnerjtf.getText().trim());
                sql+=" and carOwner like '"+ cancelJPanel.carOwnerjtf.getText().trim()+"'";
               System.out.println(sql);
            }
            if(cartel) {
                sql+=" and parkCar.carNo like'"+ cancelJPanel.carTeljtf.getText()+"'";
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
                    rows.add(v);//将每一行的值添加到所有行中

                }
                DBUtil.release(conn, ps, rs);
            }catch (Exception e1) {
                e1.printStackTrace();
            }

            if(rows.size()==0) {
                JOptionPane.showMessageDialog(null, "没有找到该停车记录");
                return;

            }
            Vector<String> dataCols = new Vector<String>();
            dataCols.add("SourceNo");
            dataCols.add("sourcePosition");
            dataCols.add("carNo");
            dataCols.add("carOwner");
            dataCols.add("carTel");
            dataCols.add("carType");
            dataCols.add("carStartDate");
            //System.out.println(rows);
            cancelJPanel.model.setDataVector(rows, dataCols);
        }else if(btn.equals("Cancel")) {
            //先来个小提示
            if(cancelJPanel.dataJTabel.getSelectedColumn()<0){
                JOptionPane.showMessageDialog(null, "请选中一行");
                return;
            }
            if(cancelJPanel.endTimeCheckBox.isSelected()==false||cancelJPanel.datePicker.getText().equals("")){
                JOptionPane.showMessageDialog(null, "请选中日期并添加日期");
                return;
            }
            //JOptionPane.showMessageDialog(null, "cancel");
            int type =JOptionPane.showConfirmDialog(null,"是否确定结束停车？");
            System.out.println(type);
            if(type ==0){
                //先找到old
                String sNo = cancelJPanel.sourceNojtf2.getText();
                String sPo = cancelJPanel.sourcePositionjtf2.getText();
                String sTime = cancelJPanel.startTimejtf2.getText();
                String cOwner = cancelJPanel.carOwnerjtf2.getText();
                String cTel = cancelJPanel.carTeljtf2.getText();
                String cType = cancelJPanel.carTypejtf2.getText();
                String cNo= cancelJPanel.carNojtf2.getText();
                parkcar = new ParkCar(cNo, cType, cOwner, cTel);
                parksource = new ParkSource(sNo, sPo, "true");
                parkpark = new ParkPark(cNo, sNo, sTime);

                //找到停车结束时间
                String eTime = cancelJPanel.datePicker.getText();
                //找到单价
                float perHourPrice = CancelJPanelDB.getParkPriceByCarType(cType);
                //计算时间
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date start = null,end=null;
                try {
                    start = df.parse(sTime);
                    end =  df.parse(eTime);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                Calendar cStart= Calendar.getInstance();
                Calendar cEnd= Calendar.getInstance();
                cStart.setTime(start);
                cEnd.setTime(end);
                long time=cEnd.getTimeInMillis()-cStart.getTimeInMillis();
                float hours=(float) (time*1.0/(1000*60*60));

                hours = Float.valueOf(new DecimalFormat("#.00").format(hours));
                System.out.println("停车时长："+hours);
                float carFee = (hours- ParkPrice.freeHour)*perHourPrice;
                carFee = Float.valueOf(new DecimalFormat("#.00").format(carFee));

                System.out.println("停车费用："+carFee);
                ParkBill parkBill = new ParkBill(eTime+parkcar.getCarNo(),
                        parkcar,parksource.sourceNo,parkpark.getCarStartDate(),
                        eTime,hours,perHourPrice,carFee,"admin","19198001037");

                PayCarFeeJFrame payCarFeeJFrame = new PayCarFeeJFrame(parkBill,this);
                payCarFeeJFrame.setVisible(true);
                //账单加入
                //CancelJPanelDB.addParkBillInfo(parkBill);


                cancelJPanel.refreshButton.doClick();
                cancelJPanel.carNoCheckBox.setSelected(false);
                cancelJPanel.carTypeCheckBox.setSelected(false);
                cancelJPanel.carTelCheckBox.setSelected(false);
                cancelJPanel.carOwnerCheckBox.setSelected(false);
                cancelJPanel.sourceNoCheckBox.setSelected(false);
                cancelJPanel.sourcePoCheckBox.setSelected(false);

                cancelJPanel.endTimeCheckBox.setSelected(false);
                //JOptionPane.showMessageDialog(null, "delete sucessfully");




            }else if(type==1){
                return;

            } else if(type ==2||type==-1){
                //cancel
                cancelJPanel.refreshButton.doClick();
                cancelJPanel.carNoCheckBox.setSelected(false);
                cancelJPanel.carTypeCheckBox.setSelected(false);
                cancelJPanel.carTelCheckBox.setSelected(false);
                cancelJPanel.carOwnerCheckBox.setSelected(false);
                cancelJPanel.sourceNoCheckBox.setSelected(false);
                cancelJPanel.sourcePoCheckBox.setSelected(false);
                cancelJPanel.endTimeCheckBox.setSelected(false);
            }




        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getSource());

        cancelJPanel.carNoCheckBox.setSelected(true);
        cancelJPanel.carTypeCheckBox.setSelected(true);
        cancelJPanel.carTelCheckBox.setSelected(true);
        cancelJPanel.carOwnerCheckBox.setSelected(true);
        cancelJPanel.sourceNoCheckBox.setSelected(true);
        cancelJPanel.sourcePoCheckBox.setSelected(true);

        int row = cancelJPanel.dataJTabel.getSelectedRow();
        String sourceNo= cancelJPanel.dataJTabel.getValueAt(row, 0).toString();
        String sorcePosition= cancelJPanel.dataJTabel.getValueAt(row, 1).toString();
        String carNo= cancelJPanel.dataJTabel.getValueAt(row, 2).toString();
        String carOwner= cancelJPanel.dataJTabel.getValueAt(row, 3).toString();
        String carTel= cancelJPanel.dataJTabel.getValueAt(row, 4).toString();
        String carType= cancelJPanel.dataJTabel.getValueAt(row, 5).toString();
        String carStartDate= cancelJPanel.dataJTabel.getValueAt(row, 6).toString();
//		System.out.println(row);
//		System.out.println(sourceNo+"******");
        cancelJPanel.sourceNojtf2.setText(sourceNo);
        cancelJPanel.sourcePositionjtf2.setText(sorcePosition);
        cancelJPanel.startTimejtf2.setText(carStartDate);
        cancelJPanel.carOwnerjtf2.setText(carOwner);
        cancelJPanel.carTeljtf2.setText(carTel);
        cancelJPanel.carTypejtf2.setText(carType);
        cancelJPanel.carNojtf2.setText(carNo);
        cancelJPanel.carTypeComboBox.setEnabled(true);
        cancelJPanel.carTypeComboBox.setSelectedItem(carType);
        cancelJPanel.sourceNoComboBox.setEnabled(true);
        cancelJPanel.sourceNoComboBox.setSelectedItem(sourceNo);
        cancelJPanel.sourcePositionjtf.setText(sorcePosition);
        cancelJPanel.carNojtf.setText(carNo);
        cancelJPanel.carOwnerjtf.setText(carOwner);
        cancelJPanel.carTeljtf.setText(carTel);
        //创建对象为以后打算

        parkcar = new ParkCar(carNo, carType, carOwner, carTel);
        parksource = new ParkSource(sourceNo, sorcePosition, "true");
        parkpark = new ParkPark(carNo, sourceNo, carStartDate);

    }




    @Override
    public void itemStateChanged(ItemEvent e) {
        // 对复选框进行操作
        JCheckBox cb = (JCheckBox) e.getItem();
        // 获取复选框上的字符串
        String fxk = cb.getActionCommand();
        if(fxk.equals("carType:")) {
            if(cb.isSelected()) {
                cancelJPanel.carTypeComboBox.setEnabled(true);
            }else {
                cancelJPanel.carTypeComboBox.setEnabled(false);
            }

        }else if(fxk.equals("sorceNo:")) {
            if(cb.isSelected()) {
                cancelJPanel.sourceNoComboBox.setEnabled(true);
            }else {
                cancelJPanel.sourceNoComboBox.setEnabled(false);
            }

        }else if(fxk.equals("sorcePo:")) {
            if(cb.isSelected()) {
                cancelJPanel.sourcePositionjtf.setEditable(true);
            }else {
                cancelJPanel.sourcePositionjtf.setText("");
                cancelJPanel.sourcePositionjtf.setEditable(false);
            }

        }else if(fxk.equals("carNo:")) {
            if(cb.isSelected()) {
                cancelJPanel.carNojtf.setEditable(true);
            }else {
                cancelJPanel.carNojtf.setText("");
                cancelJPanel.carNojtf.setEditable(false);
            }

        }else if(fxk.equals("carOwn:")) {
            if(cb.isSelected()) {
                cancelJPanel.carOwnerjtf.setEditable(true);
            }else {
                cancelJPanel.carOwnerjtf.setText("");
                cancelJPanel.carOwnerjtf.setEditable(false);
            }
        }else if(fxk.equals("carTel:")) {
            if(cb.isSelected()) {

                cancelJPanel.carTeljtf.setEditable(true);
            }else {
                cancelJPanel.carTeljtf.setText("");
                cancelJPanel.carTeljtf.setEditable(false);
            }
        }else if(fxk.equals("sourcePo:")) {
            if(cb.isSelected()) {
                cancelJPanel.sourcePositionjtf.setEditable(true);
            }else {
                cancelJPanel.sourcePositionjtf.setText("");
                cancelJPanel.sourcePositionjtf.setEditable(false);
            }

        }else if(fxk.equals("eTime:")) {

            if(cb.isSelected()) {
                cancelJPanel.datePicker.setEnabled(true);
            }else {

                cancelJPanel.datePicker.setEnabled(false);
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
