package main;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Database extends JPanel {
    static JTextArea sql = new JTextArea();
    JLabel label = new JLabel("Insert your sql-query below: ");
    JButton exe = new JButton("Execute");
    JButton reset = new JButton("Reset");
    static DbConnector dc;
    static JTable table = new JTable();
    static DefaultTableModel tableModel = (DefaultTableModel) table.getModel();


    public Database(DbConnector connector) {
        dc = connector;
        add(label);
        JScrollPane pane = new JScrollPane(sql);
        pane.setPreferredSize(new Dimension(750, 100));
        setListeners();

        add(pane);
        add(exe);
        add(reset);
        JScrollPane jTable = new JScrollPane(table);
        jTable.setPreferredSize(new Dimension(750, 390));
        add(jTable);
    }


    private void setListeners() {
        exe.addActionListener(e -> execute());
        reset.addActionListener(e -> reset());
    }

    private void reset() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
    }

    private void execute() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        String s = sql.getText();

        try {

            if (s.trim().substring(0, 6).toLowerCase().equals("select")) {


                ResultSet resultSet = dc.executeQuery(s);
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    tableModel.addColumn(metaData.getColumnName(i));
                }
                while (resultSet.next()) {
                    String[] data = new String[metaData.getColumnCount()];
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        data[i - 1] = resultSet.getString(i);
                    }
                    tableModel.addRow(data);
                }
            } else {
                dc.executeUpdate(s);
            }
        } catch (SQLException throwable) {
            System.out.println("Error: " + throwable);
        }


    }
}
