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
import modele.metier.Rapport;
import vues.VueAccueil;
import vues.VueCréerRapport;
import vues.VueRapport;

/**
 *
 * @author btssio
 */
public class CtrlCréerRapport extends Controleur{
    
    DaoOracle dao = null;
    private VueCréerRapport vueCréerRapport = null;
    
    public CtrlCréerRapport(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoOracle("GSB", "GSB", "GSB");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueCréerRapport(this));
            this.afficherVue();
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlCréerRapport - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void rapportAnnuler() {
        
        this.getCtrl().afficherVue();
        this.cacherVue();
        
    } 
    
    public void enregistrerRapport() throws DaoException {
        // Déclarations de variables locales
        dao = new DaoOracle("GSB", "GSB", "GSB");
         try {
            dao.connecter();
            int nb = 0; // valeur de retour de l'opération de mise à jour
            
             nb = dao.ajouterUnRapport("vueCréerRapport", 18, 4, "aa", "aa", "aa");
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlCréerRapport - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
       
                    
}
}