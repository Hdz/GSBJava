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
    
    private int num;
    private String date;
    private String bilan;
    private String motif;
    
    public Rapport(){
        this.num = 0;
        this.date = "";
        this.bilan = "";
        this.motif = "";       
        
    }

    public Rapport(int num, String date, String bilan, String motif) {
        this.num = num;
        this.date = date;
        this.bilan = bilan;
        this.motif = motif;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
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
    
    
}
