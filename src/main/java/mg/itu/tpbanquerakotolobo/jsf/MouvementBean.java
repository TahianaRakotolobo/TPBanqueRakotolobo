/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerakotolobo.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
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
@Named(value = "mouvementBean")
@ViewScoped
public class MouvementBean implements Serializable {

    @EJB
    private GestionnaireCompte compteManager;

    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    private int montant;

    /**
     * Creates a new instance of MouvementBean
     */
    public MouvementBean() {
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

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void loadCompte() {
        compte = compteManager.findById(id);
    }

    // La méthode doit avoir cette signature.
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");
        // Sans entrer dans les détails, il faut parfois utiliser
        // getSubmittedValue() à la place de getLocalValue.
        // typeMouvement n'est pas encore mis tant que la validation n'est pas finie.
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement == null) {
            // Pour le cas où l'utilisateur a soumis le formulaire sans indiquer le type du mouvement
            return;
        }
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            if (compte.getSolde() < retrait) {
                FacesMessage message
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Le retrait doit être inférieur au solde du compte",
                                "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    public String enregistrerMouvement() {
        if (typeMouvement.equals("ajout")) {
            compteManager.deposer(compte, montant);
        } else {
            compteManager.retirer(compte, montant);
        }
        Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
