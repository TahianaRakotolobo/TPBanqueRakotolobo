/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquerakotolobo.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Startup;
import mg.itu.tpbanquerakotolobo.entities.CompteBancaire;

/**
 *
 * @author Administrator
 */
@Startup
@jakarta.ejb.Singleton
public class SingletonInit {
    @EJB
    GestionnaireCompte gc;
    
    @PostConstruct
    public void createAccount(){
        if(gc.nbComptes()==0){
            CompteBancaire c1 = new CompteBancaire("John Lennon", 150000);
            gc.creerCompte(c1);
            CompteBancaire c2 = new CompteBancaire("Paul McCartney", 950000);
            gc.creerCompte(c2);
            CompteBancaire c3 = new CompteBancaire("Ringo Starr", 20000);
            gc.creerCompte(c3);
            CompteBancaire c4 = new CompteBancaire("Georges Harrisson", 100000);
            gc.creerCompte(c4);
        }
    }
}
