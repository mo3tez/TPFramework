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
import java.util.ArrayList;
import java.util.List;


public class Regle {
	
	private Condition conclusion;
	private List<Condition> premisse;
        private String NumRegle;
		
	public List<Condition> getPremisse() {
		return premisse;
	}
	public void setPremisse(List<Condition> premisse) {
		this.premisse = premisse;
	}
	public Condition getConclusion() {
		return conclusion;
	}
	public void setConclusion(Condition conclusion) {
		this.conclusion = conclusion;
	}
        	
	public String getNumRegle() {
            
		return NumRegle;
	}
	public void setNumRegle(String NumRegle) {
		this.NumRegle = NumRegle;
	}
	
	@Override
	public String toString() {
            String s= "";
            for( Condition p:premisse) 
            {s+=p.toString()+ " and ";}
            s=s.substring(0, s.length()-4);
            
		return NumRegle + ": IF " + s +"THEN "+ conclusion.toString() ;
	}
        

}