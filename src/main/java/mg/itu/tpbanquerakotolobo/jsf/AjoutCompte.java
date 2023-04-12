/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotolobo.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquerakotolobo.ejb.GestionnaireCompte;
import mg.itu.tpbanquerakotolobo.entities.CompteBancaire;
import mg.itu.tpbanquerakotolobo.jsf.util.Util;

/**
 *
 * @author Administrator
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    
    private String nom;
    @PositiveOrZero
    private int solde;

    @EJB
    private GestionnaireCompte compteManager;
    
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public String creer(){
        CompteBancaire c = new CompteBancaire(nom, solde);
        compteManager.creerCompte(c);
        Util.addFlashInfoMessage("Creation correctement effectué");
        return "listeComptes?faces-redirect=true";
    }
}
