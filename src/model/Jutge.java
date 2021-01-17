
package model;


public class Jutge extends Integrant {

    
    public Jutge(String nif, String nom) {
        super(nif,nom);
    }
   

   
    public static Jutge addJutge() {

        String nif;
        String nom;

        System.out.println("NIF del o de la jutge:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("Nom del o de la jutge:");
        nom = DADES.nextLine();

        return new Jutge(nif, nom);
    }

}