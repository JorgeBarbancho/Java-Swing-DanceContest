package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Competicio;
import model.Jutge;
import principal.Component;
import principal.GestorCompeticioException;


public class GestorJDBC implements ProveedorPersistencia {

    private Competicio competicio;

    private Connection conn; //Connexió a la base de dades

    public Competicio getCompeticio() {
        return competicio;
    }

    public void setCompeticio(Competicio competicio) {
        this.competicio = competicio;
    }

    
    private static String edicioCompeticioSQL = "SELECT * FROM COMPETICIONS WHERE EDICIO=?";

    private PreparedStatement edicioCompeticioSt;

    
    private static String insereixCompeticioSQL = "INSERT INTO COMPETICIONS VALUES(?,?,?)";

    private PreparedStatement insereixCompeticioSt;

   
    private static String actualitzaCompeticioSQL = "UPDATE COMPETICIONS SET \"ANY\"=?, POBLACIO=? WHERE EDICIO=?";

    private PreparedStatement actualitzaCompeticioSt;

   
    private static String eliminaJutgeSQL = "DELETE FROM JUTGES WHERE EDICIOCOMPETICIO=?";

    private PreparedStatement eliminaJutgeSt;

    private static String insereixJutgeSQL = "INSERT INTO JUTGES VALUES(?,?,?)";

    private PreparedStatement insereixJutgeSt;

    
    private static String selJutgesSQL = "SELECT * FROM JUTGES WHERE EDICIOCOMPETICIO=?";

    private PreparedStatement selJutgesSt;

   
    public void estableixConnexio() throws SQLException {
        try{
            String urlBaseDades = "jdbc:derby://localhost:1527/EAC111920S2";
            String user = "root";
            String password = "root123";
            conn = DriverManager.getConnection(urlBaseDades, user, password);
            edicioCompeticioSt = conn.prepareStatement(edicioCompeticioSQL);
            insereixCompeticioSt = conn.prepareStatement(insereixCompeticioSQL);
            actualitzaCompeticioSt = conn.prepareStatement(actualitzaCompeticioSQL);
            eliminaJutgeSt = conn.prepareStatement(eliminaJutgeSQL);
            insereixJutgeSt = conn.prepareStatement(insereixJutgeSQL);
            selJutgesSt = conn.prepareStatement(selJutgesSQL);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void tancaConnexio() throws SQLException {
        try {
            conn.close();
        } finally {
            conn = null;
        }
    }

    @Override
    public void desarCompeticio(String nomFitxer, Competicio competicio) throws GestorCompeticioException {
        
        try{
            estableixConnexio();
            edicioCompeticioSt.setInt(1, Integer.parseInt(nomFitxer));
            if(edicioCompeticioSt.executeQuery().next()){
                actualitzaCompeticioSt.setInt(1, competicio.getAny());
                actualitzaCompeticioSt.setString(2, competicio.getPoblacio());
                actualitzaCompeticioSt.setInt(3, Integer.parseInt(nomFitxer));
                actualitzaCompeticioSt.executeUpdate();
                eliminaJutgeSt.setInt(1, Integer.parseInt(nomFitxer));
                eliminaJutgeSt.executeUpdate();
            }else{
                insereixCompeticioSt.setInt(1, Integer.parseInt(nomFitxer));
                insereixCompeticioSt.setInt(2, competicio.getAny());
                insereixCompeticioSt.setString(3, competicio.getPoblacio());
                insereixCompeticioSt.executeUpdate();                
            }
            for (Component component : competicio.getComponents()) {
                if (component instanceof Jutge){
                    insereixJutgeSt.setString(1, ((Jutge) component).getNif());
                    insereixJutgeSt.setString(2, ((Jutge) component).getNom());
                    insereixJutgeSt.setInt(3, Integer.parseInt(nomFitxer));
                    insereixJutgeSt.executeUpdate();
                }
            }
            tancaConnexio();
        } catch (SQLException ex){
            throw new GestorCompeticioException("GestorJDBC.desar");
        }
    }

   
    @Override
    public void carregarCompeticio(String nomFitxer) throws GestorCompeticioException {
        try{
            estableixConnexio();
            edicioCompeticioSt.setInt(1, Integer.parseInt(nomFitxer));
            ResultSet resC = edicioCompeticioSt.executeQuery();
            if(resC.next()){
                competicio = new Competicio(resC.getInt(1),resC.getInt(2),resC.getString(3));
                selJutgesSt.setInt(1, Integer.parseInt(nomFitxer));
                ResultSet resJ = selJutgesSt.executeQuery();
                while (resJ.next()){                    
                    competicio.addJutge(new Jutge(resJ.getString(1),resJ.getString(2)));
                }
            }else{
                throw new GestorCompeticioException("GestorJDBC.noexist");
            }
            tancaConnexio();
        } catch (SQLException ex){
            throw new GestorCompeticioException("GestorJDBC.carregar");
        }
    }
}
