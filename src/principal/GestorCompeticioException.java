package principal;

public class GestorCompeticioException extends Exception {

    private String codiCausa = "Desconegut";
    private String missatge;

    public GestorCompeticioException(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                this.missatge = "L'opció introduïda no és numèrica";
                break;
            case "2":
                this.missatge = "La modalitat ja té tots els o les jutges necessaris/es";
                break;
            case "3":
                this.missatge = "No existeix aquest o aquesta jutge";
                break;
            case "4":
                this.missatge = "No existeix aquesta modalitat";
                break;
            case "5":
                this.missatge = "No s'ha pogut crear una parella per aquesta modalitat";
                break;
            case "6":
                this.missatge = "Aquesta modalitat no té parelles";
                break;
            case "7":
                this.missatge = "Ja no hi caben més components";
                break;
            case "GestorXML.model":
                this.missatge = "No s'ha pogut crear el model XML per desar la competició";
                break;
            case "GestorXML.desar":
            case "GestorSerial.desar":
            case "GestorJDBC.desar":
                this.missatge = "No s'ha pogut desar la competició a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
            case "GestorSerial.carrega":
            case "GestorJDBC.carregar":
                this.missatge = "No s'ha pogut carregar la competició a causa d'error d'entrada/sortida";
                break;
            case "GestorJDBC.noExisteix":
            case "GestorDB4O.noExisteix":
                this.missatge = "El fitxer no existeix";
                break;
            default:
                this.missatge = "Error desconegut";
                break;
        }
    }

    @Override
    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }
}
