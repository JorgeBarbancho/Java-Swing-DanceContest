package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CompeticioForm {
    private JFrame frame;
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lEdicio;
    private JTextField tEdicio;
    private JLabel lAny;
    private JTextField tAny;
    private JLabel lPoblacio;
    private JTextField tPoblacio;

    private JButton bDesar;   
    private JButton bSortir;   

    public CompeticioForm() {
       
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Competició");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lEdicio = new JLabel("Edicio");
        tEdicio = new JTextField(20);
        lAny = new JLabel("Any");
        tAny = new JTextField(20);
        lPoblacio = new JLabel("Poblacio");
        tPoblacio = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lEdicio);
        frame.add(tEdicio);
        frame.add(lAny);
        frame.add(tAny);
        frame.add(lPoblacio);
        frame.add(tPoblacio);
        frame.add(bDesar);       
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public CompeticioForm(int edicio, int any, String poblacio){
        this();
        tEdicio.setText(String.valueOf(edicio));
        tAny.setText(String.valueOf(any));
        tPoblacio.setText(poblacio);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getlEdicio() {
        return lEdicio;
    }

    public void setlEdicio(JLabel lEdicio) {
        this.lEdicio = lEdicio;
    }

    public JTextField gettEdicio() {
        return tEdicio;
    }

    public void settEdicio(JTextField tEdicio) {
        this.tEdicio = tEdicio;
    }

    public JLabel getlAny() {
        return lAny;
    }

    public void setlAny(JLabel lAny) {
        this.lAny = lAny;
    }

    public JTextField gettAny() {
        return tAny;
    }

    public void settAny(JTextField tAny) {
        this.tAny = tAny;
    }

    public JLabel getlPoblacio() {
        return lPoblacio;
    }

    public void setlPoblacio(JLabel lPoblacio) {
        this.lPoblacio = lPoblacio;
    }

    public JTextField gettPoblacio() {
        return tPoblacio;
    }

    public void settPoblacio(JTextField tPoblacio) {
        this.tPoblacio = tPoblacio;
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
