package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Jutge;
import principal.Component;

public class JutgeTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"NIF", "Nom"};

    String[][] dades = new String[ControladorPrincipal.getMAXCOMPETICIONS()][2];

    public JutgeTableModel() {
        int i = 0;
        for (Component component : ControladorPrincipal.getCompeticioActual().getComponents()) {
            if (component != null && component instanceof Jutge) {
                dades[i][0] = ((Jutge)component).getNif();
                dades[i][1] = ((Jutge)component).getNom();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return dades.length;
    }

    @Override
    public int getColumnCount() {
        return dades[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return dades[row][column];
    }
}
