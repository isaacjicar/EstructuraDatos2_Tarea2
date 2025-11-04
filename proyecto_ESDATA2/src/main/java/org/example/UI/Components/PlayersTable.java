package org.example.UI.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayersTable extends JPanel {
    private final DefaultTableModel model =
            new DefaultTableModel(new Object[]{"Tenista", "País", "Edad", "Puntaje"}, 0);
    private final JTable table = new JTable(model);

    public PlayersTable() {
        super(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setRows(Object[][] matrix) {
        model.setRowCount(0);
        if (matrix == null) return;
        for (Object[] row : matrix) model.addRow(row);
    }

    public JTable getTable() { return table; }
    public DefaultTableModel getModel() { return model; }
}
