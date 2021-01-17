package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import model.Competicio;
import principal.GestorCompeticioException;


public class GestorSerial implements ProveedorPersistencia {

    private Competicio competicio;

    public Competicio getCompeticio() {
        return competicio;
    }

    public void setCompeticio(Competicio competicio) {
        this.competicio = competicio;
    }

    @Override
    public void desarCompeticio(String nomFitxer, Competicio competicio) throws GestorCompeticioException {
       

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(nomFitxer + ".ser")))) {
            oos.writeObject(competicio);
        } catch (IOException ex) {
            throw new GestorCompeticioException("GestorSerial.desar");
        }

    }

    @Override
    public void carregarCompeticio(String nomFitxer) throws GestorCompeticioException {
      
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nomFitxer + ".ser")))) {
            competicio = (Competicio) ois.readObject();
        } catch (IOException ex) {
            throw new GestorCompeticioException("GestorSerial.carregar");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error de classe: " + ex.getMessage());
        }

    }
}
