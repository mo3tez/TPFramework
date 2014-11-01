/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TP1;

/**
 *
 * @author Motez
 */
public class Condition {
    private String nom ;
    private String operateur ;
    private String valeur ;
    
    public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
        public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
        public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
         
        public Condition (String nom , String operateur , String valeur)
        {
            this.nom = nom;
            this.operateur=operateur;
            this.valeur=valeur;
        }
        @Override
        public String toString(){
            return (nom + " " + operateur + " " + valeur);
        }
        
        @Override 
        public boolean equals(Object o){
            Condition c = (Condition) o;
             if ((c.getNom().toUpperCase().trim().equals(this.getNom().toUpperCase().trim())) 
                                 && (c.getOperateur().trim().equals(this.getOperateur().trim())) 
                                 && (c.getValeur().toUpperCase().trim().equals(this.getValeur().toUpperCase().trim())))
                 return true;
             return false;
        }
}
