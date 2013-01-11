/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.Calendar;

/**
 *
 * @author btssio
 */
public class Rapport {
    
    private String matricule;
    private String visiteur;
    private String num;
    private String date;
    private String bilan;
    private String motif;
    
    public Rapport(){
        
        this.matricule = "";
        this.num = "";
        this.date = "";
        this.bilan = "";
        this.motif = "";       
        
    }

    public Rapport(String matricule, String visiteur, String num, String date, String bilan, String motif) {
        this.visiteur = visiteur;
        this.matricule = matricule;
        this.num = num;
        this.date = date;
        this.bilan = bilan;
        this.motif = motif;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(String visiteur) {
        this.visiteur = visiteur;
    }
    
    
}
