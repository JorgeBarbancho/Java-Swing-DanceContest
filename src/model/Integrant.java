
package model;

import principal.Component;

public abstract class Integrant implements Component{
    private String nif;
    private String nom;


    public Integrant(String nif, String nom) {
        this.nif = nif;
        this.nom = nom;
    }

    
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Override
    public void updateComponent() {
        System.out.println("\nNIF de l'integrant: " + nif);
        System.out.println("\nEntra el nou nif:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de l'integrant: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
    }

    @Override
    public void showComponent() {
        System.out.println("\nLes dades de l'integrant amb nif " + nif + " són:");
        System.out.println("\nNom: " + nom);
    }
}
