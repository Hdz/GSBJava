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
import vues.VueVisiteur;

/**
 *
 * @author btssio
 */
public class CtrlVisiteur extends Controleur{
    
    DaoOracle dao = null;
    
    public CtrlVisiteur(Controleur ctrl) throws DaoException {
        super(ctrl);
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoOracle("GSB", "GSB", "GSB");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            setVue(new VueVisiteur(this));
            this.afficherVue();
            chargerListeVisiteurs();
            
        } catch (DaoException ex) {
           JOptionPane.showMessageDialog(vue, "CtrlVisiteur - instanciation - " + ex.getMessage(), "Visiteurs", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void visiteurAnnuler() {
        this.getCtrl().afficherVue();
        this.cacherVue();
        
    }
    
    public void chargerListeVisiteurs() throws DaoException {
        List<Visiteur> desVisiteurs = dao.lireTousLesVisiteurs();
        for (Visiteur unVisiteur : desVisiteurs) {
            ((VueVisiteur)vue).getModeleJComboBoxRechercherVisiteur().addElement(unVisiteur);
        }
    }
    
    public void chargerVisiteurSuivant() throws DaoException{
        Visiteur unVisiteur = dao.lireVisiteurSuivant();
        
        ((VueVisiteur)vue).getjTextFieldAdresse().setText(unVisiteur.getAdresse());
        ((VueVisiteur)vue).getjTextFieldCP().setText(unVisiteur.getCp());
        ((VueVisiteur)vue).getjTextFieldNom().setText(unVisiteur.getNom());
        ((VueVisiteur)vue).getjTextFieldPrenom().setText(unVisiteur.getPrenom());
        ((VueVisiteur)vue).getjTextFieldVille().setText(unVisiteur.getVille());
        ((VueVisiteur)vue).getjTextFieldLabo().setText(unVisiteur.getCodeLabo());
        ((VueVisiteur)vue).getjTextFieldSecteur().setText(unVisiteur.getCodeSecteur());
    }
    
    public void chargerDonneesVisiteur(String matricule) throws DaoException {
        
        Visiteur unVisiteur = dao.lireUnVisiteur(matricule);
        
        ((VueVisiteur)vue).getjTextFieldAdresse().setText(unVisiteur.getAdresse());
        ((VueVisiteur)vue).getjTextFieldCP().setText(unVisiteur.getCp());
        ((VueVisiteur)vue).getjTextFieldNom().setText(unVisiteur.getNom());
        ((VueVisiteur)vue).getjTextFieldPrenom().setText(unVisiteur.getPrenom());
        ((VueVisiteur)vue).getjTextFieldVille().setText(unVisiteur.getVille());
        ((VueVisiteur)vue).getjTextFieldLabo().setText(unVisiteur.getCodeLabo());
        ((VueVisiteur)vue).getjTextFieldSecteur().setText(unVisiteur.getCodeSecteur());

    }
}