package PresentationLayer;

import BusinessLayer.MenuItem;
import Utils.DisplayableObjectTableModel;
import Utils.JTableUtil;
import Utils.ObjectTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductsTable extends JPanel {

    public List<MenuItem> productsList;
    public ObjectTableModel<MenuItem> tableModel;
    public JTable table;

    public ProductsTable() {

        this.setLayout(new GridLayout());
        tableModel = new DisplayableObjectTableModel<>(MenuItem.class);
        tableModel.setObjectRows(productsList);
        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY);
        table.setRowHeight(40);
        table.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        JTableUtil.setJTableColumnsWidth(table,700,40,10,10,10,10,10,10);
        JScrollPane pane = new JScrollPane(table);
        this.add(pane);

    }
    public void  refresh() {
        tableModel.setObjectRows(tableModel.getObjectRows());
        table.setModel(tableModel);
        table.revalidate();
        table.repaint();
    }
}
