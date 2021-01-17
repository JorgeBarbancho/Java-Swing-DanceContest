package principal;

import model.Modalitat;
import model.Competicio;
import model.Ballari;
import model.Jutge;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;

public class Application {

    private final static Scanner DADES = new Scanner(System.in);

    static private Competicio[] competicions = new Competicio[25];
    static private int posicioCompeticions = 0;
    static private Competicio competicioActual = null;
    private static final String FITXER = "competicio";
    private static final GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) throws GestorCompeticioException {
        try {
            menuPrincipal();
        } catch (GestorCompeticioException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorCompeticioException {
        int opcio = 0;

        do {

            try {
                System.out.println("\nSelecciona una opció");
                System.out.println("\n0. Sortir");
                System.out.println("\n1. Gestió de competicions");
                System.out.println("\n2. Gestió de modalitats");
                System.out.println("\n3. Gestió de ballarins o ballarines");
                System.out.println("\n4. Gestió de jutges");
                opcio = DADES.nextInt();

                switch (opcio) {
                    case 0:
                        break;
                    case 1:
                        menuCompeticions();
                        break;
                    case 2:
                        if (competicioActual != null) {
                            menuComponents(3);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la competició al menú Gestió de competicions");
                        }
                        break;
                    case 3:
                        if (competicioActual != null) {
                            menuComponents(1);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la competició al menú Gestió de competicions");
                        }
                        break;
                    case 4:
                        if (competicioActual != null) {
                            menuComponents(2);
                        } else {
                            System.out.println("\nPrimer s'ha de seleccionar la competició al menú Gestió de competicions");
                        }
                        break;
                    default:
                        System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                        break;
                }
            } catch (InputMismatchException e) {
                throw new GestorCompeticioException("1");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GestorCompeticioException("10");
            }
            
        } while (opcio != 0);
    }

    public static void menuCompeticions()throws GestorCompeticioException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio;

        do {
            int indexSel;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Crear nova competició");
            System.out.println("\n2. Seleccionar competició");
            System.out.println("\n3. Modificar competició");
            System.out.println("\n4. Llista de competicions");
            System.out.println("\n5. Carregar competició");
            System.out.println("\n6. Desar competició");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    competicions[posicioCompeticions] = Competicio.addCompeticio();
                    posicioCompeticions++;
                    break;
                case 2:
                    indexSel = selectCompeticio();
                    if (indexSel >= 0) {
                        competicioActual = competicions[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquesta competició");
                    }
                    break;
                case 3:
                    indexSel = selectCompeticio();
                    if (indexSel >= 0) {
                        competicions[indexSel].updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquesta competició");
                    }
                    break;
                case 4:
                    for (int i = 0; i < posicioCompeticions; i++) {
                        competicions[i].showComponent();
                    }
                    break;
                case 5: //Carregar competicio
                    posicioCompeticions = 0;
                    competicions = new Competicio[1]; //Només podem carregar una competició
                    gp.carregarCompeticio("XML", FITXER);
                    competicions[posicioCompeticions] = ((GestorXML)gp.getGestor()).getCompeticio();
                    posicioCompeticions++;
                    break;
                case 6: //Desar competicio
                    int pos = selectCompeticio();
                    if (pos >= 0) {
                        gp.desarCompeticio("XML", FITXER, competicions[pos]);
                    } else {
                        System.out.println("\nNo existeix aquesta competició");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuComponents(int tipus)throws GestorCompeticioException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistar");
            if (tipus == 3) {
                System.out.println("\n4. Assignar jutge");
                System.out.println("\n5. Assignar parella de ball");
                System.out.println("\n6. Assignar puntuació a les parelles de ball");
            }
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            competicioActual.addBallari(null);
                            break;
                        case 2:
                            competicioActual.addJutge(null);
                            break;
                        case 3:
                            competicioActual.addModalitat(null);
                            break;
                    }
                    break;
                case 2:
                    int indexSel = competicioActual.selectComponent(tipus, null);
                    if (indexSel >= 0) {
                        competicioActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest component");
                    }
                    break;
                case 3:
                    for (int i = 0; i < competicioActual.getComponents().size(); i++) {
                        if (competicioActual.getComponents().get(i) instanceof Ballari && tipus == 1) {
                            competicioActual.getComponents().get(i).showComponent();
                        } else if (competicioActual.getComponents().get(i) instanceof Jutge && tipus == 2) {
                            competicioActual.getComponents().get(i).showComponent();
                        } else if (competicioActual.getComponents().get(i) instanceof Modalitat && tipus == 3) {
                            competicioActual.getComponents().get(i).showComponent();
                        }
                    }
                    break;
                case 4:
                    competicioActual.addJutgeModalitat();
                    break;
                case 5:
                    competicioActual.addParellaBallModalitat();
                    break;
                case 6:
                    competicioActual.addPuntuacioParellesBall();
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectCompeticio() {

        System.out.println("\nEdició de la competició?:");
        int edicio = DADES.nextInt();

        for (int i = 0; i < posicioCompeticions; i++) {
            if (competicions[i].getEdicio() == edicio) {
                return i;
            }
        }
        return -1;
    }

}
