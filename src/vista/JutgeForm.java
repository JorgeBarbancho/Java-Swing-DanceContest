package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JutgeForm {
    private JFrame frame;
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lNif;
    private JTextField tNif;
    private JLabel lNom;
    private JTextField tNom;

    private JButton bDesar;   
    private JButton bSortir;   

    public JutgeForm() {
                
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Jutge");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lNif = new JLabel("NIF");
        tNif = new JTextField(20);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lNif);
        frame.add(tNif);
        frame.add(lNom);
        frame.add(tNom);
        frame.add(bDesar);       
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public JutgeForm(String nif, String nom){
        this();
        tNif.setText(nif);
        tNom.setText(nom);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getlNif() {
        return lNif;
    }

    public void setlNif(JLabel lNif) {
        this.lNif = lNif;
    }

    public JTextField gettNif() {
        return tNif;
    }

    public void settNif(JTextField tNif) {
        this.tNif = tNif;
    }

    public JLabel getlNom() {
        return lNom;
    }

    public void setlNom(JLabel lNom) {
        this.lNom = lNom;
    }

    public JTextField gettNom() {
        return tNom;
    }

    public void settNom(JTextField tNom) {
        this.tNom = tNom;
    }
    
    public JButton getbDesar() {
        return bDesar;
    }

    public void setbDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
}
