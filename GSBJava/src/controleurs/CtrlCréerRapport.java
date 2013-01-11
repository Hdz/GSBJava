/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoOracle;
import modele.dao.Dao;
import modele.metier.Praticien;
import modele.metier.Rapport;
import modele.metier.Visiteur;
import vues.VueAccueil;
import vues.VueCréerRapport;
import vues.VueRapport;
import vues.VueVisiteur;

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
            chargerListeVisiteurs();
            chargerListePraticien();
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlCréerRapport - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void rapportAnnuler() {
        
        this.getCtrl().afficherVue();
        this.cacherVue();
        
    } 
    
    public void chargerListeVisiteurs() throws DaoException {
        List<Visiteur> desVisiteurs = dao.lireTousLesVisiteurs();
        for (Visiteur unVisiteur : desVisiteurs) {
            ((VueCréerRapport)vue).getModeleJComboBoxVisiteur().addElement(unVisiteur);
        }
    }
    
    public void chargerListePraticien() throws DaoException {
        List<Praticien> desPraticiens = dao.lireTousLesPraticiens();
        for (Praticien unPraticien : desPraticiens) {
            ((VueCréerRapport)vue).getModeleJComboBoxPraticien().addElement(unPraticien);
        }
    }
    
    public void enregistrerRapport(String matricule, Integer numRap, Integer numPra, java.sql.Date RapDate, String RapBilan, String RapMotif) throws DaoException {
        // Déclarations de variables locales
        dao = new DaoOracle("GSB", "GSB", "GSB");
         try {
            dao.connecter();
            dao.ajouterUnRapport(matricule, numRap, numPra, RapDate, RapBilan, RapMotif);
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlCréerRapport - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    public Integer ajouterNumRap() throws DaoException{
        dao = new DaoOracle("GSB", "GSB", "GSB");
         
            dao.connecter();
            String dernierNum = dao.recupererDernierNumRap();
            Integer nouveauNum = Integer.parseInt(dernierNum) + 1;
            
            return nouveauNum;
            
        
    }
}