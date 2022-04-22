package Test;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TestJTable {

    public static void fitTableColumns(JTable myTable) {
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int columnCount = myTable.getColumnCount();
        int rowCount = myTable.getRowCount();

        int totalWidth = 0;
        for (int col = 0; col < columnCount; col++) {
            int width = myTable.getColumnModel().getColumn(col).getPreferredWidth();
            for (int row = 0; row < rowCount; row++) {

                Object value = myTable.getValueAt(row, col);

                Component c = myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable, value, false, false, row, col);
                int preferedWidth = (int) c.getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            TableColumn column = myTable.getColumnModel().getColumn(col);
            myTable.getTableHeader().setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width * 2);
            totalWidth += width + myTable.getIntercellSpacing().width * 2;
        }
        if (myTable.getParent() == null) {
            return;
        }
        //如果表格实际宽度小于父容器的宽度，则让表格自适应
        if (totalWidth < myTable.getParent().getPreferredSize().width) {
            myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
        return;
    }
}