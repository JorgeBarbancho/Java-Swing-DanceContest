
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Jutge;
import principal.Component;
import vista.JutgeForm;
import vista.JutgeLlista;
import vista.MenuJutge;


public class ControladorJutge implements ActionListener {
    private MenuJutge menuJutge;
    private Jutge jutge = null;
    private JutgeForm jutgeForm = null;
    private JutgeLlista jutgeLlista = null;
    private int opcioSelec = 0;

    public ControladorJutge() {

        menuJutge = new MenuJutge();
        afegirListenersMenu();

    }

    private void afegirListenersMenu() {

        for (JButton unBoto : menuJutge.getMenuButtons()) {
            unBoto.addActionListener(this);
        }

    }

    private void afegirListenersForm() {

        jutgeForm.getbDesar().addActionListener(this);
        jutgeForm.getbSortir().addActionListener(this);

    }

    private void afegirListenersLlista() {

        jutgeLlista.getbSortir().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Accions per al menú
        JButton[] botons = menuJutge.getMenuButtons();

        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                menuJutge.getFrame().setVisible(false);
                opcioSelec = i;
                seleccionarOpcio(i);
            }
        }

        //Accions per al formulari de jutges
        if (jutgeForm != null) {

            if (e.getSource() == jutgeForm.getbDesar()) {

                if (opcioSelec == 1) {//Nou jutge
                    
                    ControladorPrincipal.getCompeticioActual().getComponents().add(new Jutge(jutgeForm.gettNif().getText(), jutgeForm.gettNom().getText()));

                } else if (opcioSelec == 2) {//Modificar Jutge

                    jutge.setNif(jutgeForm.gettNif().getText());
                    jutge.setNom(jutgeForm.gettNom().getText());
                    
                }

            } else if (e.getSource() == jutgeForm.getbSortir()) { //Sortir

                jutgeForm.getFrame().setVisible(false);
                menuJutge.getFrame().setVisible(true);

            }

        }

        if (jutgeLlista != null) {

            if (e.getSource() == jutgeLlista.getbSortir()) {

                jutgeLlista.getFrame().setVisible(false);
                menuJutge.getFrame().setVisible(true);

            }

        }

    }

    private void seleccionarOpcio(Integer opcio) {
        
        switch (opcio) {
            case 0: //sortir
                ControladorPrincipal.getMenuPrincipal().getFrame().setVisible(true);
                break;
            case 1: // alta
                jutgeForm = new JutgeForm();
                afegirListenersForm();
                break;
            case 2: // modificar
                int pos = ControladorPrincipal.getCompeticioActual().selectComponent(1, seleccionarJutge());
                jutge = (Jutge) ControladorPrincipal.getCompeticioActual().getComponents().get(pos);
                jutgeForm = new JutgeForm(jutge.getNif(), jutge.getNom());
                afegirListenersForm();
                break;
            case 3: // llista
                jutgeLlista = new JutgeLlista();
                afegirListenersLlista();
                break;
        }

    }

    private String seleccionarJutge() {
        
        int i = 0;
        
        for (Component component : ControladorPrincipal.getCompeticioActual().getComponents()) {

            if (component instanceof Jutge) {
               i++;
            }

        }

        String[] nifsJutges = new String[i];
        
        i=0;

        for (Component component : ControladorPrincipal.getCompeticioActual().getComponents()) {

            if (component instanceof Jutge) {
                nifsJutges[i] = ((Jutge) component).getNif();
            }
            
            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un jutge", "Selecció de jutges", 0, messageType, null, nifsJutges, null);

        if (code != JOptionPane.CLOSED_OPTION) {
            return nifsJutges[code];
        }

        return null;
    }
}
