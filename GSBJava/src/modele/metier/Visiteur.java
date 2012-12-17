/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 *
 * @author btssio
 */
public class Visiteur {
    
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private Jour dateEmbauche;
    private ListeComptesRendus lesComptesRendus;
    
    public Visiteur() {
        this.matricule = "";
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.ville = "";
        lesComptesRendus = new ListeComptesRendus();
    }

    public Visiteur(String matricule, String nom, String prenom, String adresse, String ville, Jour dateEmbauche, ListeComptesRendus lesComptesRendus) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;
        this.lesComptesRendus = lesComptesRendus;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Jour getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Jour dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public ListeComptesRendus getLesComptesRendus() {
        return lesComptesRendus;
    }

    public void setLesComptesRendus(ListeComptesRendus lesComptesRendus) {
        this.lesComptesRendus = lesComptesRendus;
    }

    
}
