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
class ComptesRendus {
    
    private int num;
    private Jour date;
    private String bilan;
    private String motif;
    
    public ComptesRendus(){
        this.num = 0;
        this.date = new Jour(Calendar.getInstance().getTime().getTime());
        this.bilan = "";
        this.motif = "";       
        
    }

    public ComptesRendus(int num, Jour date, String bilan, String motif) {
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

    public Jour getDate() {
        return date;
    }

    public void setDate(Jour date) {
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
