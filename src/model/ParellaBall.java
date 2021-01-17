
package model;


public class ParellaBall {

    private int numInscripcio;
    private Ballari ballari;
    private Ballari ballarina;
    private int puntuacio;

    
    public ParellaBall(Ballari ballari, Ballari ballarina) {
        this.numInscripcio = 0;
        this.ballari = ballari;
        this.ballarina = ballarina;
        this.puntuacio = 0;
    }

    public int getNumInscripcio() {
        return numInscripcio;
    }

    public void setNumInscripcio(int numInscripcio) {
        this.numInscripcio = numInscripcio;
    }

    public Ballari getBallari() {
        return ballari;
    }

    public void setBallari(Ballari ballari) {
        this.ballari = ballari;
    }

    public Ballari getBallarina() {
        return ballarina;
    }

    public void setBallarina(Ballari ballarina) {
        this.ballarina = ballarina;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public void showParellaBall() {
        System.out.println("\nLes dades de la parella de ball amb número d'inscripció " + numInscripcio + " són:");

        if (ballari != null) {
            ballari.showComponent();
        } else {
            System.out.println("\nEncara no hi ha ballarí assignat");
        }

        if (ballarina != null) {
            ballarina.showComponent();
        } else {
            System.out.println("\nEncara no hi ha ballarina assignada");
        }

        System.out.println("\nPuntuació: " + puntuacio);
    }
}
