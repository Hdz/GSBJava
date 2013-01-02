/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoOracle;
import vues.VueAccueil;

/**
 *
 * @author btssio
 */
public class CtrlAccueil extends Controleur {

    private VueAccueil vueAccueil = null;
    private DaoOracle dao = null;
    
    private CtrlVisiteur ctrlVisiteur;
    private CtrlRapport ctrlRapport;


    public CtrlAccueil(Controleur ctrl) {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoOracle("GSB", "GSB", "GSB");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueAccueil(this));
            this.afficherVue();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(vueAccueil, "CtrAccueil - instanciation - " + ex.getMessage(), "Accueil", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void afficherVisiteur() throws DaoException{
        if (ctrlVisiteur == null){
            ctrlVisiteur = new CtrlVisiteur(this);
        }else{
            ctrlVisiteur.afficherVue();
        }
        this.cacherVue();
    }
    
    public void afficherRapport() throws DaoException{
        if (ctrlRapport == null){
            ctrlRapport = new CtrlRapport(this);
        }else{
            ctrlRapport.afficherVue();
        }
        this.cacherVue();
    }
    
}
