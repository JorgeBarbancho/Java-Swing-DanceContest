package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.Competicio;
import persistencia.GestorPersistencia;
import vista.MenuPrincipal;

public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipal menuPrincipal;
    static private final int MAXCOMPETICIONS = 4;
    static private Competicio[] competicions = new Competicio[MAXCOMPETICIONS];
    static private int posicioCompeticions = 0;
    static private Competicio competicioActual = null;
    static private int tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia(); 
    static private final String[] METODESPERSISTENCIA = {"XML", "Serial", "JDBC", "DB4O"};

    public ControladorPrincipal() {
        

        menuPrincipal = new MenuPrincipal();

        for (JButton unBoto : menuPrincipal.getMenuButtons()) {
            unBoto.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      

        JButton[] botons = menuPrincipal.getMenuButtons();
        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                seleccionarOpcio(i);
            }
        }

    }

    private void seleccionarOpcio(int opcio) {

        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipal.getFrame().setVisible(false);
                ControladorCompeticio controladorCompeticio = new ControladorCompeticio();
                break;
            case 2:
                menuPrincipal.getFrame().setVisible(false);
                ControladorJutge controladorJutge = new ControladorJutge();
                break;
        }

    }

    public static MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public static void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        ControladorPrincipal.menuPrincipal = menuPrincipal;
    }

    public static Competicio[] getCompeticions() {
        return competicions;
    }

    public static void setCompeticions(Competicio[] competicions) {
        ControladorPrincipal.competicions = competicions;
    }

    public static int getPosicioCompeticions() {
        return posicioCompeticions;
    }

    public static void setPosicioCompeticions() {
        ControladorPrincipal.posicioCompeticions ++;
    }

    public static Competicio getCompeticioActual() {
        return competicioActual;
    }

    public static void setCompeticioActual(Competicio competicioActual) {
        ControladorPrincipal.competicioActual = competicioActual;
    }

    public static int getTipusElement() {
        return tipusElement;
    }

    public static void setTipusElement(int tipusElement) {
        ControladorPrincipal.tipusElement = tipusElement;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static int getMAXCOMPETICIONS() {
        return MAXCOMPETICIONS;
    }

    public static String[] getMETODESPERSISTENCIA() {
        return METODESPERSISTENCIA;
    }

}
