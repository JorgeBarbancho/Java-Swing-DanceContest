package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CompeticioLlista {
    private JFrame frame;
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tCompeticio;

    private JButton bSortir;   
    

    public CompeticioLlista() {        
        
        //Definició de la finestra del menú
        frame = new JFrame("Llista de competicions");
        frame.setLayout(new GridLayout(0, 1));

        //Creació de la taula en base al model
        tCompeticio = new JTable(new CompeticioTableModel());
        

        //Creació dels botons del formulari
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(new JScrollPane(tCompeticio));  
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable gettCompeticio() {
        return tCompeticio;
    }

    public void settCompeticio(JTable tCompeticio) {
        this.tCompeticio = tCompeticio;
    }     
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
}
