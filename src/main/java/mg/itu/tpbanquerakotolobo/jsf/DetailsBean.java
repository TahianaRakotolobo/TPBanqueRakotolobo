/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotolobo.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.tpbanquerakotolobo.ejb.GestionnaireCompte;
import mg.itu.tpbanquerakotolobo.entities.CompteBancaire;
import mg.itu.tpbanquerakotolobo.jsf.util.Util;

/**
 *
 * @author Administrator
 */
@Named(value = "detailsBean")
@ViewScoped
public class DetailsBean implements Serializable {

    @EJB
    private GestionnaireCompte compteManager;

    private Long id;
    private CompteBancaire compte;
    
    /**
     * Creates a new instance of DetailsBean
     */
    public DetailsBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
    
    public void loadCompte() {
        compte = compteManager.findById(id);
    }
    
    public String update(){
        compteManager.update(compte);
        Util.addFlashInfoMessage("Mise a jour enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
    
}
