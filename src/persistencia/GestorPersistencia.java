package persistencia;

import model.Competicio;
import principal.GestorCompeticioException;


public class GestorPersistencia {

    private ProveedorPersistencia gestor;

    public ProveedorPersistencia getGestor() {
        return gestor;
    }

    public void setGestor(ProveedorPersistencia pGestor) {
        gestor = pGestor;
    }

    public void desarCompeticio(String tipusPersistencia, String nomFitxer, Competicio competicio) throws GestorCompeticioException {
        switch(tipusPersistencia){
            
            case "XML":
                gestor = new GestorXML();
                break;
            case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            case "DB4O":
                gestor = new GestorDB4O();
                break;
            
        }

        gestor.desarCompeticio(nomFitxer, competicio);
    }

    public void carregarCompeticio(String tipusPersistencia, String nomFitxer) throws GestorCompeticioException {

        switch(tipusPersistencia){
            
            case "XML":
                gestor = new GestorXML();
                break;                
             case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            case "DB4O":
                gestor = new GestorDB4O();
                break;
            
        }
        gestor.carregarCompeticio(nomFitxer);
    }
}
