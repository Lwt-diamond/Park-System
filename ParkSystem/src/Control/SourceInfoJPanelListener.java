package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import dao.HomePageDB;
import dao.LookBillsJPanelDB;
import dao.SourceInfoJPanelDB;
import entity.ParkPrice;
import view.panel.SourceInfoJPanel;

public class SourceInfoJPanelListener implements ActionListener,MouseListener{
	public SourceInfoJPanel sourceInfoJPanel;

	public SourceInfoJPanelListener(SourceInfoJPanel sourceInfoJPanel) {
		this.sourceInfoJPanel = sourceInfoJPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn =e.getActionCommand();
		if(btn.equals("SetPrice")) {
			System.out.println(btn);
			int row = sourceInfoJPanel.priceTable.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "请先选中一行");
				return ;
			}
			String carType=  sourceInfoJPanel.priceTable.getValueAt(row, 0).toString();
			String price=  sourceInfoJPanel.priceTable.getValueAt(row, 1).toString();
			String carComments=  sourceInfoJPanel.priceTable.getValueAt(row, 2).toString();
			String parkPrice = sourceInfoJPanel.pricejtf.getText();
			if( parkPrice.equals("")) {
				JOptionPane.showMessageDialog(null, "请先写入新的价格");
				return ;
			}
			SourceInfoJPanelDB.UpdateParkPrice(new ParkPrice(carType, Float.valueOf(parkPrice)));
			Vector<String> priceCols = new Vector<String>();
			priceCols.add("carType");
			priceCols.add("price/perHour");
			priceCols.add("carComments");
			Vector<Vector<String>> priceRows= SourceInfoJPanelDB.getPriceInfo();

			sourceInfoJPanel.priceModel.setDataVector(priceRows, priceCols);
			//sourceInfoJPanel.priceTable = new JTable(sourceInfoJPanel.priceModel);
			// sourceInfoJPanel.priceTable.add(new JScrollBar());
			ControlListener.fitTableColumns(sourceInfoJPanel.priceTable);
		}else if(btn.equals("ShowBills")) {

			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getAllBillsData();
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
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);

			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);
			//sourceInfoJPanel.dataJTable.
			//JTable priceTable = new JTable(priceRows,priceCols);
		}else if(btn.equals("CountByMonth")) {
			String sql="select CONVERT(varchar(7) , carStartDate, 120 ) as 'carStartMonth',count(*) as 'carNum',carFee from parkBill group by CONVERT(varchar(7) , carStartDate, 120 ),carFee order by carStartMonth desc";
			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getCountBy(sql);
			Vector<String> dataCols = new Vector<String>();
			dataCols.add("Month");
			dataCols.add("CarNum");
			dataCols.add("AllFee/￥");
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);

			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);

			//System.out.println(btn);
		}else if(btn.equals("CountByDay")) {
			String sql="select CONVERT(varchar(10) , carStartDate, 120 ) as 'carStartDay',count(*) as 'carNum' ,carFee from parkBill group by CONVERT(varchar(10) , carStartDate, 120 ),carFee order by carStartDay desc";
			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getCountBy(sql);
			Vector<String> dataCols = new Vector<String>();
			dataCols.add("Day");
			dataCols.add("CarNum");
			dataCols.add("AllFee/￥");
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);

			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);

			//System.out.println(btn);
		}else if(btn.equals("CountByCType")) {
			String sql = "select carType,count(*) as'carNum' from parkBill group by carType";
			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getCountBy(sql);
			Vector<String> dataCols = new Vector<String>();
			dataCols.add("carType/SourceType");
			dataCols.add("CarNum");
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);

			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);
			System.out.println(btn);
		}else if(btn.equals("CountBySTime")) {
			String sql="select CONVERT(varchar(2) , carStartDate, 114 ) as 'carStartTime/h',count(*) as 'carNum' from parkBill group by CONVERT(varchar(2) , carStartDate, 114 )";
			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getCountBy(sql);
			Vector<String> dataCols = new Vector<String>();
			dataCols.add("carEnterTime");
			dataCols.add("CarNum");
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);

			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);
			System.out.println(btn);
			System.out.println(btn);
		}else if(btn.equals("CountByETime")) {
			String sql="select CONVERT(varchar(2) , carEndDate, 114 ) as 'carStartTime/h',count(*) as 'carNum' from parkBill group by CONVERT(varchar(2) , carEndDate, 114 )";
			Vector<Vector<String>> dataRows= new Vector<Vector<String>>();
			dataRows = SourceInfoJPanelDB.getCountBy(sql);
			Vector<String> dataCols = new Vector<String>();
			dataCols.add("carOutTime");
			dataCols.add("CarNum");
			sourceInfoJPanel.dataModel.setDataVector(dataRows,dataCols);
			ControlListener.fitTableColumns(sourceInfoJPanel.dataJTable);
			System.out.println(btn);
		}

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = sourceInfoJPanel.priceTable.getSelectedRow();
		String sourceNo=  sourceInfoJPanel.priceTable.getValueAt(row, 0).toString();
		String sorcePosition=  sourceInfoJPanel.priceTable.getValueAt(row, 1).toString();
		String carNo=  sourceInfoJPanel.priceTable.getValueAt(row, 2).toString();

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
/**
 System.out.println(btn);
 Vector<String> priceCols = new Vector<String>();
 priceCols.add("carType");
 priceCols.add("price/perHour");
 priceCols.add("carComments");
 Vector<Vector<String>> priceRows= HomePageDB.getPriceInfo();
 sourceInfoJPanel.dataModel.setDataVector(priceRows, priceCols);
 */
