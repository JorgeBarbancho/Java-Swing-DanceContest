package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Competicio;
import persistencia.GestorDB4O;
import persistencia.GestorJDBC;
import persistencia.GestorPersistencia;
import persistencia.GestorSerial;
import persistencia.GestorXML;
import principal.GestorCompeticioException;
import vista.CompeticioForm;
import vista.CompeticioLlista;
import vista.MenuCompeticio;


public class ControladorCompeticio implements ActionListener {
    
    private MenuCompeticio menuCompeticio;
    private CompeticioForm competicioForm = null;
    private CompeticioLlista competicioLlista = null;
    private int opcioSelec = 0;

    public ControladorCompeticio() {

        
        menuCompeticio = new MenuCompeticio();
        afegirListenersMenu();

    }

    private void afegirListenersMenu() {
      
        
        for (JButton unBoto : menuCompeticio.getMenuButtons()) {
            unBoto.addActionListener(this);
        }

    }

    private void afegirListenersForm() {
       
        
        competicioForm.getbDesar().addActionListener(this);
        competicioForm.getbSortir().addActionListener(this);

    }

    private void afegirListenersLlista() {
      
        
        competicioLlista.getbSortir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        JButton[] botons = menuCompeticio.getMenuButtons();

        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                menuCompeticio.getFrame().setVisible(false);
                opcioSelec = i;
                seleccionarOpcio(i);
            }
        }

        if (competicioForm != null) {

            if (e.getSource() == competicioForm.getbDesar()) {

                if (opcioSelec == 1) {//Nova competició

                        Competicio competicio = new Competicio(Integer.parseInt(competicioForm.gettAny().getText()), competicioForm.gettPoblacio().getText());
                        ControladorPrincipal.getCompeticions()[ControladorPrincipal.getPosicioCompeticions()] = competicio;
                        ControladorPrincipal.setPosicioCompeticions();
                        competicioForm.gettEdicio().setText(String.valueOf(competicio.getEdicio()));
                        competicioForm.gettEdicio().setEnabled(false);
                        ControladorPrincipal.setCompeticioActual(competicio);
                        opcioSelec = 2;

                } else if (opcioSelec == 3) {//Modificar competició

                    ControladorPrincipal.getCompeticioActual().setAny(Integer.parseInt(competicioForm.gettAny().getText()));
                    ControladorPrincipal.getCompeticioActual().setPoblacio(competicioForm.gettPoblacio().getText());

                }

            } else if (e.getSource() == competicioForm.getbSortir()) { //Sortir

                competicioForm.getFrame().setVisible(false);
                menuCompeticio.getFrame().setVisible(true);

            }

        }

        if (competicioLlista != null) {

            if (e.getSource() == competicioLlista.getbSortir()) {

                competicioLlista.getFrame().setVisible(false);
                menuCompeticio.getFrame().setVisible(true);

            }

        }

    }

    private void seleccionarOpcio(Integer opcio) {

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipal().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getPosicioCompeticions()< ControladorPrincipal.getMAXCOMPETICIONS()) {
                    competicioForm = new CompeticioForm();
                    competicioForm.gettEdicio().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuCompeticio.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Màxim nombre de competicions assolit.");
                }
                break;

            case 2: //seleccionar
                menuCompeticio.getFrame().setVisible(true);
                if (ControladorPrincipal.getCompeticions()[0] != null) {
                    seleccionarCompeticio();
                } else {
                    JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Abans s'ha de crear al menys una competició");
                }
                break;

            case 3: //modificar
                if (ControladorPrincipal.getCompeticions()[0] != null) {
                    seleccionarCompeticio();
                    competicioForm = new CompeticioForm(ControladorPrincipal.getCompeticioActual().getEdicio(), ControladorPrincipal.getCompeticioActual().getAny(), ControladorPrincipal.getCompeticioActual().getPoblacio());
                    competicioForm.gettEdicio().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuCompeticio.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Abans s'ha de crear al menys una competició");
                }
                break;

            case 4: // llistar
                if (ControladorPrincipal.getCompeticions()[0] != null) {
                    competicioLlista = new CompeticioLlista();
                    afegirListenersLlista();
                } else {
                    menuCompeticio.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Abans s'ha de crear al menys una competició");
                }
                break;

            case 5: //carregar
           
                
                menuCompeticio.getFrame().setVisible(true);
                
                int codi = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Carregar competició", 0, JOptionPane.QUESTION_MESSAGE, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                
                if (codi != JOptionPane.CLOSED_OPTION) {
                    
                    GestorPersistencia gestor = new GestorPersistencia();
                    
                    Competicio competicio;
                    
                    try {
                        
                        String edicio = JOptionPane.showInputDialog("Quina és l'edició de la competició que vols carregar?");
                        
                        gestor.carregarCompeticio(ControladorPrincipal.getMETODESPERSISTENCIA()[codi], edicio);
                        
                        if(ControladorPrincipal.getMETODESPERSISTENCIA()[codi].equals("XML")){
                            competicio=((GestorXML)gestor.getGestor()).getCompeticio();
                        }else if(ControladorPrincipal.getMETODESPERSISTENCIA()[codi].equals("Serial")){
                            competicio=((GestorSerial)gestor.getGestor()).getCompeticio();
                        }else if(ControladorPrincipal.getMETODESPERSISTENCIA()[codi].equals("JDBC")){
                            competicio=((GestorJDBC)gestor.getGestor()).getCompeticio();
                        }else {
                            competicio=((GestorDB4O)gestor.getGestor()).getCompeticio();
                        }
                        
                        int pos = comprovarCompeticio(competicio.getEdicio());
                        
                        if (pos >= 0) {
                            
                            Object[] options = {"OK", "Cancel·lar"};                            
                            int i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-lo.", "Competició ja existent",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, options, options[0]);
                            
                            if (i == 0) {
                                ControladorPrincipal.getCompeticions()[pos] = competicio;
                            }
                            
                        } else {
                            
                            ControladorPrincipal.getCompeticions()[ControladorPrincipal.getPosicioCompeticions()] = competicio;
                            ControladorPrincipal.setPosicioCompeticions();
                            JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Competició afegida correctament");
                        
                        }

                    } catch (GestorCompeticioException e) {
                        JOptionPane.showMessageDialog(menuCompeticio.getFrame(), e.getMessage());
                    }
                }
                
                break;

            case 6:
                
                menuCompeticio.getFrame().setVisible(true);
                
                if (ControladorPrincipal.getCompeticioActual() != null) {
                    
                    int messageTyped = JOptionPane.QUESTION_MESSAGE;
                    int code = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Desar competició", 0, messageTyped, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                    
                    if (code != JOptionPane.CLOSED_OPTION) {
                        
                        GestorPersistencia gestor = new GestorPersistencia();
                        
                        try {
                            gestor.desarCompeticio(ControladorPrincipal.getMETODESPERSISTENCIA()[code], String.valueOf(ControladorPrincipal.getCompeticioActual().getEdicio()), ControladorPrincipal.getCompeticioActual());
                        } catch (GestorCompeticioException e) {
                            JOptionPane.showMessageDialog(menuCompeticio.getFrame(), e.getMessage());                           
                        }
                        
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(menuCompeticio.getFrame(), "Abans s'ha de seleccionar una competició");
                }

                break;

        }

    }

    private void seleccionarCompeticio() {

        String[] edicioCompeticio = new String[ControladorPrincipal.getPosicioCompeticions()];

        int i = 0;

        for (Competicio competicio : ControladorPrincipal.getCompeticions()) {

            if (competicio != null) {
                edicioCompeticio[i] = String.valueOf(competicio.getEdicio());
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int codi = JOptionPane.showOptionDialog(null, "Selecciona una competició", "Selecció de competicions", 0, messageType, null, edicioCompeticio, "A");
        
        if (codi != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setCompeticioActual(ControladorPrincipal.getCompeticions()[codi]);
        }

    }

    private Integer comprovarCompeticio(int codi) {

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getCompeticions().length; i++) {

            if (ControladorPrincipal.getCompeticions()[i] != null) {
                if (ControladorPrincipal.getCompeticions()[i].getEdicio() == codi) {
                    return pos=i;
                }
            }
        }

        return pos;
    }
    
}
