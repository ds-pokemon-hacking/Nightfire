package ctrmap.editor.gui.editors.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class HeaderCellRenderer implements TableCellRenderer {

    TableCellRenderer renderer;

    public HeaderCellRenderer(JTable table) {
        renderer = table.getTableHeader().getDefaultRenderer();
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, col);
    }
}