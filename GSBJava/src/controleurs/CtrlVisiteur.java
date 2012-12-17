/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoOracle;
import vues.VueAccueil;
import vues.VueVisiteur;

/**
 *
 * @author btssio
 */
public class CtrlVisiteur extends Controleur{


    public CtrlVisiteur(Controleur ctrl) {
        super(ctrl);
       // initialiser l'interface graphique
          setVue(new VueVisiteur(this));
          this.afficherVue();
    }
    
    public void visiteurAnnuler() {
        this.getCtrl().afficherVue();
        this.cacherVue();
        
    }

}
