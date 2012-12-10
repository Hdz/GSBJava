/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoMySql;
import vues.VueAccueil;

/**
 *
 * @author btssio
 */
public class CtrlAccueil implements IControleur {

    VueAccueil VueAccueil = null;
    DaoMySql dao = null;

    public CtrlAccueil() {
        // Ouvrir une connexion JDBC vers la base de données visée
        dao = new DaoMySql("GSB", "GSB", "GSB");
        try {
            dao.connecter();
            // initialiser l'interface graphique
            VueAccueil = new VueAccueil(this);
            chargerListeEquipiers();
            chargerLesCodesEtat();
            VueAccueil.setVisible(true);
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(VueAccueil, "CtrlPresence - instanciation - " + ex.getMessage(), "Saisie des présences", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * presenceEnregistrer
     * réaction au clic sur le bouton VALIDER de la vue VuePresences
     */
    public void presenceEnregistrer() {
        // Déclarations de variables locales
        int nb = 0; // valeur de retour de l'opération de mise à jour
        String msg = ""; // message à afficher à l'issue de la mise à jour
        int typeMsg = 0;
        // Lire et contrôler les données du formulaire
        // récupérer l'Equipier sélectionné
        Equipier equipierSelect = (Equipier) vuePresence.getjComboBoxEquipier().getSelectedItem();
        Jour dateSelect = new Jour(vuePresence.getjDateChooserDatePresence().getDate().getTime());
        CodeEtat etatPresenceSelect = (CodeEtat) vuePresence.getjComboBoxEtatPresence().getSelectedItem();
        if (equipierSelect == null || dateSelect == null || etatPresenceSelect == null) {
            //Saisie incomplète
            msg = "Saisie incomplète";
            typeMsg = JOptionPane.WARNING_MESSAGE;
        } else {
            try {
                // n° d'identification de cet équipier
                int id = equipierSelect.getId();
                // récupérer la liste des présences actuelles pour cet équipier
                equipierSelect.setLesPresences(dao.lireLesPresencesUnEquipier(id));
                // rechercher la date saisie dans cette liste
                Presence presenceRecherchee = equipierSelect.rechercherUnePresence(dateSelect);
                if (presenceRecherchee != null) {
                    // si elle est déjà présente, la mettre à jour
                    presenceRecherchee.setEtatPresence(etatPresenceSelect);
                    nb = dao.modifierUnePresence(id, presenceRecherchee);
                    msg = "Présence mise à jour";
                    typeMsg = JOptionPane.INFORMATION_MESSAGE;
                } else {
                    // sinon, l'ajouter
                    presenceRecherchee = new Presence(dateSelect, etatPresenceSelect);
                    nb = dao.ajouterUnePresence(id, presenceRecherchee);
                    msg = "Présence ajoutée";
                    typeMsg = JOptionPane.INFORMATION_MESSAGE;
                }
                // On vérifie qu'au moins un enregistrement a été ajouté ou modifié
                if (nb == 0) {
                    msg = "La mise à jour a échoué";
                    typeMsg = JOptionPane.WARNING_MESSAGE;
                }

            } catch (DaoException ex) {
                msg = "CtrlPresence - presenceEnregistrer() - " + ex.getMessage();
                typeMsg = JOptionPane.ERROR_MESSAGE;
            }
            JOptionPane.showMessageDialog(vuePresence, msg, "Saisie des présences", typeMsg);
        }
    }

    /**
     * presenceAnnuler
     * réaction au clic sur le bouton ANNULER de la vue VuePresences
     */
    public void presenceAnnuler() {
        // Saluer
        int rep = JOptionPane.showConfirmDialog(vuePresence, "Quitter l'application\nEtes-vous sûr(e) ?", "Saisie des présences", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // fermer la vue
            vuePresence.setVisible(false);
            // mettre fin à l'application
            System.exit(0);
        }
    }

    /**
     * chargerListeEquipiers
     * renseigner le modèle du composant jComboBoxEquipier à partir de la base de données
     * @throws DaoException 
     */
    private void chargerListeEquipiers() throws DaoException {
        List<Equipier> desEquipiers = dao.lireTousLesEquipiers();
        for (Equipier unEquipier : desEquipiers) {
            vuePresence.getModeleJComboBoxEquipier().addElement(unEquipier);
        }
    }

    /**
     * chargerLesCodesEtat
     * renseigner le modèle du composant jComboBoxEtatPresence à partir de la base de données
     * @throws DaoException 
     */
    private void chargerLesCodesEtat() throws DaoException {
        List<CodeEtat> desCodesEtat = dao.lireTousLesCodesEtat();
        for (CodeEtat unCodeEtat : desCodesEtat) {
            vuePresence.getModeleJComboBoxEtatPresence().addElement(unCodeEtat);
        }
    }
}
