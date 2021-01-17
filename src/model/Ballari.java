
package model;


public class Ballari extends Integrant {

    private String sexe;
    private String modalitat;
    private boolean emparellat;

    
    public Ballari(String nif, String nom, String sexe) {
        super(nif,nom);
        this.sexe = sexe;
        this.modalitat = null;
        this.emparellat = false;
    }

  
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getModalitat() {
        return modalitat;
    }

    public void setModalitat(String modalitat) {
        this.modalitat = modalitat;
    }

    public boolean isEmparellat() {
        return emparellat;
    }

    public void setEmparellat(boolean emparellat) {
        this.emparellat = emparellat;
    }


    public static Ballari addBallari() {

        String nif;
        String nom;
        String sexe;
        Boolean emparellat=false; 

        System.out.println("NIF del ballarí o ballarina:");
        nif = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("Nom del ballarí o ballarina:");
        nom = DADES.nextLine();
        System.out.println("Sexe del ballarí o ballarina: Dona (D) - Home (H)");
        sexe = DADES.next();
        
        return new Ballari(nif, nom, sexe);
    }

    
    @Override
    public void updateComponent() {
        super.updateComponent();

        if (sexe.equals("H")) {
            System.out.println("\nÉs un ballarí");
        } else {
            System.out.println("\nÉs una ballarina");
        }

        System.out.println("\nEntra el sexe del ballarí o ballarina: Dona (D) - Home (H):");

        sexe = DADES.next();
        
        if (emparellat) {
            System.out.println("\nTé parella");
        } else {
            System.out.println("\nNo té parella");
        }

        System.out.println("Està emparellat o emparellada?: Si (1) - No (0)");

        emparellat = DADES.next().equals("1");
    }

    @Override
    public void showComponent() {
        super.showComponent();

        if (sexe.equals("H")) {
            System.out.println("\nÉs un ballarí");
        } else {
            System.out.println("\nÉs una ballarina");
        }
        
        if (emparellat) {
            System.out.println("\nTé parella");
        } else {
            System.out.println("\nNo té parella");
        }
        
        System.out.println("\nEl codi de la seva modalitat és: " + modalitat);
    }

}
