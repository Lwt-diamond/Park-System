package Control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import dao.DBUtil;
import dao.FindJPanelDB;

import entity.ParkCar;
import entity.ParkPark;
import entity.ParkSource;

import view.panel.FindJPanel;

public class FindJPanelListener implements MouseListener, ActionListener, ItemListener {
    FindJPanel findJPanel;
    private ParkCar parkcar;
    private ParkPark parkpark;
    private ParkSource parksource;
    public FindJPanelListener(FindJPanel findJPanel) {
        // TODO Auto-generated constructor stub
        this.findJPanel = findJPanel;
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
            findJPanel.model.setDataVector(dataRows, dataCols);

            findJPanel.sourceNojtf2.setText("");
            findJPanel.sourcePositionjtf2.setText("");
            findJPanel.startTimejtf2.setText("");
            findJPanel.carOwnerjtf2.setText("");
            findJPanel.carTeljtf2.setText("");
            findJPanel.carTypejtf2.setText("");
            findJPanel.carNojtf2.setText("");

            //stopJPanel.dataJScrollPane.setViewportView(dataJTabel);
        }else if(btn.equals("Find")) {
            boolean cartype= findJPanel.carTypeCheckBox.isSelected();
            boolean sourceno =findJPanel.sourceNoCheckBox.isSelected();
            boolean sourcepo = findJPanel.sourcePoCheckBox.isSelected();
            boolean carno = findJPanel.carNoCheckBox.isSelected();
            boolean carowner = findJPanel.carOwnerCheckBox.isSelected();
            boolean cartel = findJPanel.carOwnerCheckBox.isSelected();
            boolean stime = findJPanel.sTimeCheckBox.isSelected();
            Vector<Vector<String>> rows = new Vector<Vector<String>>();
            Connection conn = DBUtil.getDBConnection();
            PreparedStatement ps;
            String sql = "select parkSource.SourceNo,parkSource.sourcePosition,parkPark.carNo,carOwner,carTel,carType,parkPark.carStartDate"
                    + " from parkSource left join parkPark on parkSource.sourceNo =parkPark.sourceNo left join parkCar on parkPark.carNo = parkCar.carNo "
                    + " where sourceIsUsed= 'true' ";
            if(cartype) {
                System.out.println(findJPanel.carTypeComboBox.getSelectedItem()+"");
                sql+=" and parkCar.carType='"+findJPanel.carTypeComboBox.getSelectedItem()+"'";
            }
            if(sourceno) {
                System.out.println(findJPanel.sourceNoComboBox.getSelectedItem()+"");
                sql+=" and parkSource.sourceNo='"+findJPanel.sourceNoComboBox.getSelectedItem()+"'";

            }
            if(sourcepo) {
                sql+=" and parkSource.sourcePosition like'"+findJPanel.sourcePositionjtf.getText()+"%'";
            }
            if(carno) {
                sql+=" and parkPark.carNo='"+findJPanel.carNojtf.getText()+"'";
            }
            if(carowner) {

                System.out.println(findJPanel.carOwnerjtf.getText().trim());
                sql+=" and carOwner like '"+findJPanel.carOwnerjtf.getText().trim()+"%'";
                System.out.println(sql);
            }
            if(cartel) {
                sql+=" and parkCar.carNo like'"+findJPanel.carTeljtf.getText()+"'";
            }

            if(stime){
                sql+=" and datediff(day, parkPark.carStartDate,'"+ findJPanel.datePicker.getText()+"')=0";
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
            findJPanel.model.setDataVector(rows, dataCols);

        }else if(btn.equals("Update")) {
            //JOptionPane.showMessageDialog(null, "update");
            int type =JOptionPane.showConfirmDialog(null,"OK?");

            System.out.println(type);
            if(type ==0){
                //先找到old
                String sNo =findJPanel.sourceNojtf2.getText();
                String sPo =findJPanel.sourcePositionjtf2.getText();
                String sTime =findJPanel.startTimejtf2.getText();
                String cOwner =findJPanel.carOwnerjtf2.getText();
                String cTel =findJPanel.carTeljtf2.getText();
                String cType = findJPanel.carTypejtf2.getText();
                String cNo=findJPanel.carNojtf2.getText();
                parkcar = new ParkCar(cNo, cType, cOwner, cTel);
                parksource = new ParkSource(sNo, sPo, "true");
                parkpark = new ParkPark(cNo, sNo, sTime);
                //yes
                String carType = (String) findJPanel.carTypeComboBox.getSelectedItem();
                String sourceNo = (String) findJPanel.sourceNoComboBox.getSelectedItem();
                String sourcePosition = findJPanel.sourcePositionjtf.getText();
                String carNo = findJPanel.carNojtf.getText();
                String carOwner = findJPanel.carOwnerjtf.getText();
                String carTel = findJPanel.carTeljtf.getText();
                //String carStartDate = findJPanel.datePicker.getText();
                if(!carType.equals("")&& !sourceNo.equals("")&& !sourcePosition.equals("")&&
                        !carNo.equals("")&&!carOwner.equals("")&&!carTel.equals("")) {
                    System.out.println("数据没问题");

                    ParkCar parkCar = new ParkCar(carNo, carType, carOwner, carTel);
                    if (!parkcar.equals(parkCar)) {
                        if(parkCar.getCarOwner()!=parkcar.getCarOwner()){
                            String sql ="update parkCar set carOwner='"+parkCar.getCarOwner()+"'where carOwner='"+parkcar.getCarOwner()+"'";
                            System.out.println("更新count: "+FindJPanelDB.updateDB(sql));
                        }
                        System.out.println(1);
                        //StopJPanelDB.addParkCarInfo(parkCar);
                    }

                    ParkSource parkSource = new ParkSource(sourceNo, sourcePosition, "true");
                    if (!parksource.equals(parkSource)) {
                        //StopJPanelDB.setParkSourceInfo(parkSource);
                        System.out.println(1);
                    }

                    ParkPark parkPark = new ParkPark(carNo, sourceNo, parkpark.getCarStartDate());
                    if (!parkpark.equals(parkPark)) {
                        System.out.println(1);
                        //StopJPanelDB.addParkParkInfo(parkPark);
                    }
//                    System.out.println("--------------");
//                    System.out.println(parkcar);
//                    System.out.println(parkCar);
//                    System.out.println(parksource);
//                    System.out.println(parkSource);
//                    System.out.println(parkpark);
//                    System.out.println(parkPark);
                    findJPanel.refreshButton.doClick();
                    findJPanel.carNoCheckBox.setSelected(false);
                    findJPanel.carTypeCheckBox.setSelected(false);
                    findJPanel.carTelCheckBox.setSelected(false);
                    findJPanel.carOwnerCheckBox.setSelected(false);
                    findJPanel.sourceNoCheckBox.setSelected(false);
                    findJPanel.sourcePoCheckBox.setSelected(false);
                    JOptionPane.showMessageDialog(null, "update sucessfully");
                }
            }else if(type==1){

            } else if(type ==2||type==-1){
                //cancel
                findJPanel.refreshButton.doClick();
                findJPanel.carNoCheckBox.setSelected(false);
                findJPanel.carTypeCheckBox.setSelected(false);
                findJPanel.carTelCheckBox.setSelected(false);
                findJPanel.carOwnerCheckBox.setSelected(false);
                findJPanel.sourceNoCheckBox.setSelected(false);
                findJPanel.sourcePoCheckBox.setSelected(false);
            }




        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getSource());

        findJPanel.carNoCheckBox.setSelected(true);
        findJPanel.carTypeCheckBox.setSelected(true);
        findJPanel.carTelCheckBox.setSelected(true);
        findJPanel.carOwnerCheckBox.setSelected(true);
        findJPanel.sourceNoCheckBox.setSelected(true);
        findJPanel.sourcePoCheckBox.setSelected(true);

        int row = findJPanel.dataJTabel.getSelectedRow();
        String sourceNo= findJPanel.dataJTabel.getValueAt(row, 0).toString();
        String sorcePosition= findJPanel.dataJTabel.getValueAt(row, 1).toString();
        String carNo= findJPanel.dataJTabel.getValueAt(row, 2).toString();
        String carOwner= findJPanel.dataJTabel.getValueAt(row, 3).toString();
        String carTel= findJPanel.dataJTabel.getValueAt(row, 4).toString();
        String carType= findJPanel.dataJTabel.getValueAt(row, 5).toString();
        String carStartDate= findJPanel.dataJTabel.getValueAt(row, 6).toString();
//		System.out.println(row);
//		System.out.println(sourceNo+"******");
        findJPanel.sourceNojtf2.setText(sourceNo);
        findJPanel.sourcePositionjtf2.setText(sorcePosition);
        findJPanel.startTimejtf2.setText(carStartDate);
        findJPanel.carOwnerjtf2.setText(carOwner);
        findJPanel.carTeljtf2.setText(carTel);
        findJPanel.carTypejtf2.setText(carType);
        findJPanel.carNojtf2.setText(carNo);
        findJPanel.carTypeComboBox.setEnabled(true);
        findJPanel.carTypeComboBox.setSelectedItem(carType);
        findJPanel.sourceNoComboBox.setEnabled(true);
        findJPanel.sourceNoComboBox.setSelectedItem(sourceNo);
        findJPanel.sourcePositionjtf.setText(sorcePosition);
        findJPanel.carNojtf.setText(carNo);
        findJPanel.carOwnerjtf.setText(carOwner);
        findJPanel.carTeljtf.setText(carTel);
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
                findJPanel.carTypeComboBox.setEnabled(true);
            }else {
                findJPanel.carTypeComboBox.setEnabled(false);
            }

        }else if(fxk.equals("sorceNo:")) {
            if(cb.isSelected()) {
                findJPanel.sourceNoComboBox.setEnabled(true);
            }else {
                findJPanel.sourceNoComboBox.setEnabled(false);
            }

        }else if(fxk.equals("sorcePo:")) {
            if(cb.isSelected()) {
                findJPanel.sourcePositionjtf.setEditable(true);
            }else {
                findJPanel.sourcePositionjtf.setText("");
                findJPanel.sourcePositionjtf.setEditable(false);
            }

        }else if(fxk.equals("carNo:")) {
            if(cb.isSelected()) {
                findJPanel.carNojtf.setEditable(true);
            }else {
                findJPanel.carNojtf.setText("");
                findJPanel.carNojtf.setEditable(false);
            }

        }else if(fxk.equals("carOwn:")) {
            if(cb.isSelected()) {
                findJPanel.carOwnerjtf.setEditable(true);
            }else {
                findJPanel.carOwnerjtf.setText("");
                findJPanel.carOwnerjtf.setEditable(false);
            }
        }else if(fxk.equals("carTel:")) {
            if(cb.isSelected()) {

                findJPanel.carTeljtf.setEditable(true);
            }else {
                findJPanel.carTeljtf.setText("");
                findJPanel.carTeljtf.setEditable(false);
            }
        }else if(fxk.equals("sourcePo:")) {
            if(cb.isSelected()) {
                findJPanel.sourcePositionjtf.setEditable(true);
            }else {
                findJPanel.sourcePositionjtf.setText("");
                findJPanel.sourcePositionjtf.setEditable(false);
            }

        }else if(fxk.equals("sTime:")) {

            if(cb.isSelected()) {
                findJPanel.datePicker.setEnabled(true);
            }else {

                findJPanel.datePicker.setEnabled(false);
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
