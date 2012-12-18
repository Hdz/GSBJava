/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import vues.VueAbstraite;

/**
 *
 * @author btssio
 */
public class Controleur {
    
    private Controleur ctrl;
    protected VueAbstraite vue;

    public Controleur(Controleur ctrl) {
        this.ctrl = ctrl;
    }
    
    public void afficherVue(){
        vue.afficher();
    }
    
    public void cacherVue(){
        vue.cacher();
    }

    public Controleur getCtrl() {
        return ctrl;
    }

    public void setCtrl(Controleur ctrl) {
        this.ctrl = ctrl;
    }

    public VueAbstraite getVue() {
        return vue;
    }

    public void setVue(VueAbstraite vue) {
        this.vue = vue;
    }
    
    
}
