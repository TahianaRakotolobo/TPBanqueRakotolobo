/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotolobo.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tpbanquerakotolobo.ejb.GestionnaireCompte;
import mg.itu.tpbanquerakotolobo.entities.CompteBancaire;

/**
 *
 * @author Administrator
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    @EJB
    private GestionnaireCompte compteManager;

    private Long id;
    private CompteBancaire compte;
    
    /**
     * Creates a new instance of Operations
     */
    public Operations() {
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
}
