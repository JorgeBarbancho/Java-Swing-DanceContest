
package model;

import java.io.Serializable;
import model.Ballari;
import model.Jutge;
import model.ParellaBall;
import java.util.ArrayList;
import java.util.List;
import principal.Component;
import principal.GestorCompeticioException;

public class Competicio implements Component, Serializable {

    private int edicio;
    private static int proximaEdicio = 1; //La pròxima edició a assignar
    private int any;
    private String poblacio;
    private List<Component> components = new ArrayList();

    public Competicio(int any, String poblacio) {
        edicio = proximaEdicio;
        proximaEdicio++;
        this.any = any;
        this.poblacio = poblacio;
    }

   
    public Competicio(int edicio, int any, String poblacio) {
        this.edicio = edicio;
        this.any = any;
        this.poblacio = poblacio;
    }

    
    public int getEdicio() {
        return edicio;
    }

    public void setEdicio(int edicio) {
        this.edicio = edicio;
    }

    public static int getProximaEdicio() {
        return proximaEdicio;
    }

    public static void setProximaEdicio(int proximaEdicio) {
        Competicio.proximaEdicio = proximaEdicio;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public static Competicio addCompeticio() {

        int any;
        String poblacio;

        System.out.println("\nAny de la competició:");
        any = DADES.nextInt();
        DADES.nextLine();
        System.out.println("\nPoblació on es realitza la competició:");
        poblacio = DADES.nextLine();

        return new Competicio(any, poblacio);
    }

   
    public void updateComponent() {

        System.out.println("\nAny de la competició: " + any);
        System.out.println("\nEntra el nou any:");
        any = DADES.nextInt();
        DADES.nextLine();
        System.out.println("\nPoblació on es realitza la competició: " + poblacio);
        System.out.println("\nEntra la nova població:");
        poblacio = DADES.nextLine();
    }

    public void showComponent() {
        System.out.println("\nLes dades de la competició edició " + edicio + " són:");
        System.out.println("\nAny: " + any);
        System.out.println("\nPoblacio: " + poblacio);
    }

  
    public void addBallari(Ballari ballari) throws GestorCompeticioException {

        if (ballari == null) {
            ballari = Ballari.addBallari();
        }

        if (selectComponent(1, ballari.getNif()) == -1) {
            
            if (ballari.getModalitat() == null) {
                addCodiModalitatBallari(ballari);
            }
            
            components.add(ballari);
            
        } else {
            System.out.println("\nEl ballarí o ballarina ja existeix");
        }
    }

    public void addJutge(Jutge jutge) throws GestorCompeticioException {

        if (jutge == null) {
            jutge = Jutge.addJutge();
        }

        if (selectComponent(2, jutge.getNif()) == -1) {
            components.add(jutge);
        } else {
            System.out.println("\nEl o la jutge ja existeix");
        }
    }

    
    public void addModalitat(Modalitat modalitat) throws GestorCompeticioException {

        if (modalitat == null) {
            modalitat = Modalitat.addModalitat();
        }

        if (selectComponent(3, modalitat.getCodi()) == -1) {
            components.add(modalitat);
        } else {
            System.out.println("\nLa modalitat ja existeix");
        }
    }

    public int selectComponent(int tipusComponent, String id) {

        if (id == null) {

            //Demanem quin tipus de component vol seleccionar i el seu codi
            switch (tipusComponent) {
                case 1:
                    System.out.println("NIF del ballarí o ballarina?:");
                    break;
                case 2:
                    System.out.println("NIF del o de la jutge?:");
                    break;

                case 3:
                    System.out.println("Codi de la modalitat?:");
                    break;
            }

            id = DADES.next();
        }

        int pos = -1; //Posició que ocupa el component seleccionat dins el vector de components de la competició

        //Seleccionem la posició que ocupa el component dins el vector de components de la competició
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof Ballari && tipusComponent == 1) {
                if (((Ballari) components.get(i)).getNif().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Jutge && tipusComponent == 2) {
                if (((Jutge) components.get(i)).getNif().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Modalitat && tipusComponent == 3) {
                if (((Modalitat) components.get(i)).getCodi().equals(id)) {
                    return i;
                }
            }
        }

        return pos;
    }

    public void addJutgeModalitat() throws GestorCompeticioException {

        int posModalitat = selectComponent(3, null);

        if (posModalitat >= 0) {

            int posJutge = selectComponent(2, null);

            if (posJutge >= 0) {

                ((Modalitat) getComponents().get(posModalitat)).addJutge((Jutge) getComponents().get(posJutge));

            } else {
                throw new GestorCompeticioException("3");
            }

        } else {
            throw new GestorCompeticioException("4");
        }

    }

    public void addCodiModalitatBallari(Ballari ballari) throws GestorCompeticioException {

        int pos = selectComponent(3, null);

        if (pos >= 0) {

            ballari.setModalitat(((Modalitat) getComponents().get(pos)).getCodi());

        } else {
            throw new GestorCompeticioException("4");
        }

    }

    public void addParellaBallModalitat() throws GestorCompeticioException {

        int pos = selectComponent(3, null);

        if (pos >= 0) {

            Ballari nouBallari = null;
            Ballari novaBallarina = null;
            Boolean parellaCreada = false;

            for (int i = 0; i < components.size() && !parellaCreada; i++) {

                if (getComponents().get(i) instanceof Ballari && ((Ballari) getComponents().get(i)).getModalitat().equals(((Modalitat) getComponents().get(pos)).getCodi()) && !((Ballari) getComponents().get(i)).isEmparellat()) {

                    if (((Ballari) getComponents().get(i)).getSexe().equals("H")) {
                        nouBallari = (Ballari) getComponents().get(i);
                    } else {
                        novaBallarina = (Ballari) getComponents().get(i);
                    }

                    if (nouBallari != null && novaBallarina != null) {
                        nouBallari.setEmparellat(true);
                        novaBallarina.setEmparellat(true);
                        (((Modalitat) getComponents().get(pos))).addParella(new ParellaBall(nouBallari, novaBallarina));
                        parellaCreada = true;
                    }
                }
            }

            if (!parellaCreada) {
                throw new GestorCompeticioException("5");
            }

        } else {
            throw new GestorCompeticioException("4");
        }

    }

    public void addPuntuacioParellesBall() throws GestorCompeticioException {

        int pos = selectComponent(3, null);

        if (pos >= 0) {

            if (!((Modalitat) getComponents().get(pos)).getComponents().get("parellesBall").isEmpty()) {

                for (int i = 0; i < ((Modalitat) getComponents().get(pos)).getComponents().get("parellesBall").size(); i++) {

                    System.out.println("\nParella amb número d'inscripció " + ((ParellaBall) ((Modalitat) getComponents().get(pos)).getComponents().get("parellesBall").get(i)).getNumInscripcio());

                    System.out.println("\nPuntuació de la parella:");

                    ((ParellaBall) ((Modalitat) getComponents().get(pos)).getComponents().get("parellesBall").get(i)).setPuntuacio(DADES.nextInt());

                    DADES.nextLine();
                }

            } else {
                throw new GestorCompeticioException("6");
            }

        } else {
            throw new GestorCompeticioException("4");
        }

    }
}
