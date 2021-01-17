package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Competicio;

public class CompeticioTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Edició", "Any", "Població"};

    String[][] dades = new String[ControladorPrincipal.getMAXCOMPETICIONS()][3];

    public CompeticioTableModel() {
        int i = 0;
        for (Competicio competicio : ControladorPrincipal.getCompeticions()) {
            if (competicio != null) {
                dades[i][0] = String.valueOf(competicio.getEdicio());
                dades[i][1] = String.valueOf(competicio.getAny());
                dades[i][2] = competicio.getPoblacio();
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
