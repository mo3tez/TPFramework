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

public class Fait {
	
	
		private Condition fait ;
                private int Exp  ;

		public Condition getFait() {
			return fait;
		}
		
		
		public void setFait(Condition fait) {
			this.fait = fait;
		}

		public Fait(Condition fait , int Num) {
			
			this.fait = fait;
                        Exp = Num ;
		}
                
		public Fait() {
                   Exp = -1;
			
		}
                
                @Override
                public String toString(){
                    return (Exp + " :" + fait.toString());
                }
                
	


}
