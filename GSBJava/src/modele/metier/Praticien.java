/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

/**
 *
 * @author btssio
 */
public class Praticien{
    
    private String numero;
    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String coeffNotoriete;
    private String type;
    
    public Praticien() {
        this.numero = "";
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.ville = "";
        this.coeffNotoriete ="";
        this.type = "";
    }

    public Praticien(String numero, String nom, String prenom, String adresse, String cp, String ville, String coeffNotoriete, String type) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.coeffNotoriete = coeffNotoriete;
        this.type = type;
        
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCoeffNotoriete() {
        return coeffNotoriete;
    }

    public void setCoeffNotoriete(String coeffNotoriete) {
        this.coeffNotoriete = coeffNotoriete;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return numero + " | " + nom + "  " +  prenom ;
    }

    

    
}
