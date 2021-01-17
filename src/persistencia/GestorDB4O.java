package persistencia;

import com.db4o.Db4oEmbedded;
import model.Competicio;
import principal.GestorCompeticioException;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;


public class GestorDB4O implements ProveedorPersistencia {

    private ObjectContainer db;
    private Competicio competicio;

    public Competicio getCompeticio() {
        return competicio;
    }

    public void setCompeticio(Competicio competicio) {
        this.competicio = competicio;
    }
    

    public void estableixConnexio() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Competicio.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(config, "EAC111920S2.db4o");
    }

    public void tancaConnexio() {
        db.close();
    }

   
    @Override
    public void desarCompeticio(String nomFitxer, Competicio pCompeticio){
        estableixConnexio();
        try{
            Predicate p = new Predicate<Competicio>() {
                @Override
                public boolean match(Competicio c) {
                    return nomFitxer.equals(Integer.toString(c.getEdicio()));
                }
            };
            ObjectSet<Competicio> result = db.query(p);
            if (result.size() != 1){
                db.store(pCompeticio);
            }else{
                Competicio c = result.next();
                c.setAny(pCompeticio.getAny());
                c.setPoblacio(pCompeticio.getPoblacio());
                c.setComponents(pCompeticio.getComponents());
                db.store(c);
            }
        }finally{       
            tancaConnexio();
        }
    }

    
    @Override
    public void carregarCompeticio(String nomFitxer) throws GestorCompeticioException {
        estableixConnexio();
        try{
            Predicate p = new Predicate<Competicio>() {
                @Override
                public boolean match(Competicio c) {
                    return nomFitxer.equals(Integer.toString(c.getEdicio()));
                }
            };
            ObjectSet<Competicio> result = db.query(p);
            if (result.size() == 1){
                competicio = result.next();
            }else{
                throw new GestorCompeticioException ("GestorDB4O.noExisteix");
            }
        }finally{       
            tancaConnexio();
        }
      
    }
}
