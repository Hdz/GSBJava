/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoOracle;
import modele.dao.Dao;
import modele.metier.Visiteur;
import vues.VueAccueil;
import vues.VueRapport;
import vues.VueVisiteur;

/**
 *
 * @author btssio
 */
public class CtrlRapport extends Controleur{
    
    DaoOracle dao = null;
    
    public CtrlRapport(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoOracle("GSB", "GSB", "GSB");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueRapport(this));
            this.afficherVue();
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlVisiteur - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void rapportAnnuler() {
        
        this.getCtrl().afficherVue();
        this.cacherVue();
        
    } 
}