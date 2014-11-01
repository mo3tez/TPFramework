package TP1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.swing.text.AbstractDocument.BranchElement;


public class IA {

static List<Regle> liste_regle = new ArrayList<Regle>();
static List<Fait> liste_fait = new ArrayList<Fait>();
    
public static void WriteToBaseFait() throws IOException {
        BufferedReader Keyboard = new BufferedReader(new InputStreamReader(System.in));
         String response = "O";

        while (response.equals("O")) {
        System.out.println("Veuillez exprimer les faits sous forme des chaines");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();


        File fichier = new File("C:\\TP1\\BaseFait.txt");
        if (!fichier.exists()) {
            fichier.createNewFile();
        }
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier , true));
        bufferedWriter.write(line+"  ");
        bufferedWriter.close();
            
         System.out.println("Voulez vous ajouter un autre fait? [O/N]");
            Scanner sc2 = new Scanner(System.in);
            response = sc2.nextLine().toUpperCase();

    }
    }
    public static void WriteToBasesRegle() throws IOException {

        BufferedReader Keyboard = new BufferedReader(new InputStreamReader(System.in));

        String response = "O";
        int i=1;

        while (response.equals("O")) {

            System.out.println(" Veillez ecrire la regle n°"+ i +" sous la forme IF [] THEN []");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line.toLowerCase().indexOf("if") + line.toLowerCase().indexOf("then") < 0) {
                System.out.println("entrer la bonne forme du regle");
            }

          
            File fichier = new File("C:\\TP1\\BaseRegle.txt");
            if (!fichier.exists()) {
                fichier.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier , true));
            bufferedWriter.write("R"+i+" : " +line);
            bufferedWriter.write("\r\n");
            bufferedWriter.close();
            i++;
            System.out.println("Voulez vous ajouter une autre regle? [O/N]");
            Scanner sc2 = new Scanner(System.in);
            response = sc2.nextLine().toUpperCase();
        } }
    
    
    public  Condition DecomposerCondition (String line){
                    String nom = null; 
                    String operateur = null; 
                    String valeur = null;
                    if (line.indexOf("=")!= -1)
                    {nom = line.substring(0,line.indexOf("="));
                     operateur ="=";
                    valeur=line.substring(line.indexOf("=") + 1, line.length()); }
                      if (line.indexOf("<")!= -1)
                  { nom = line.substring(0,line.indexOf("<"));
                   operateur ="<";
                     valeur=line.substring(line.indexOf("<") + 1, line.length()); }
                     if (line.indexOf(">")!= -1)
                    { nom = line.substring(0,line.indexOf(">"));
                    operateur =">";
                    valeur=line.substring(line.indexOf(">") + 1, line.length()); }
                     Condition c = new Condition(nom , operateur , valeur);
                    return c;
        
    }

    public void ReadFromBaseFait() {
        String filePath ;
//"C:\\TP1\\BaseFait.txt"
        try {
            Scanner sc = new Scanner(System.in);
            filePath = sc.nextLine();
            BufferedReader buff = new BufferedReader(new FileReader(filePath));
            try {
                String line;
                while ((line = buff.readLine()) != null) {
                    Fait fait = new Fait();
                    fait.setFait(DecomposerCondition(line));
                    System.out.println(fait.toString());
                    liste_fait.add(fait);
                   
                }
            } finally {
                buff.close();
            }
        } catch (IOException ioe) {
            System.out.println("Erreur --" + ioe.toString());
        }
       
 
    }
    
    

    public void ReadFromBaseRegle() {
        String filePath ;
        

        try {
            Scanner sc = new Scanner(System.in);
            filePath = sc.nextLine();
            

            BufferedReader buff = new BufferedReader(new FileReader(filePath));

            try {   
//C:\\TP1\\BaseRegle.txt
                String line;
                while ((line = buff.readLine()) != null) {
                String NumRegle = line.substring(1,line.indexOf(":")).trim();    
                String premisse = line.substring(line.indexOf("IF") + 2,line.indexOf("THEN")).trim();
                String conclusion = line.substring(line.indexOf("THEN") + 4, line.length()).trim();
                      List<Condition> ListePremisse = new ArrayList<Condition>();
                         while (premisse.indexOf("and")!=-1){
                          ListePremisse.add(DecomposerCondition(premisse.substring(0,premisse.indexOf("and"))));
                         premisse = premisse.substring(premisse.indexOf("and") + 4, premisse.length());
                     }
                       if (premisse.indexOf("and")==-1 )
                     {ListePremisse.add(DecomposerCondition(premisse));}    
                    Regle regle = new Regle();
                    regle.setNumRegle(NumRegle);
                    regle.setPremisse(ListePremisse);
                    regle.setConclusion(DecomposerCondition(conclusion));
                    System.out.println(regle.toString());
                    liste_regle.add(regle);
                }
            } finally {
                buff.close();
            }
        } catch (IOException ioe) {
            System.out.println("Erreur --" + ioe.toString());
        }

    }
    
    public boolean TrouverButLF (List<Fait> LF , Condition c){
      for (Fait f : LF){
         if (f.getFait().equals(c))
            return true;     
         String ch=c.getValeur();
         while(ch.indexOf("or")!=-1){
             //System.out.println(ch);
            if(f.getFait().getValeur().toUpperCase().trim().equals(ch.substring(0,ch.indexOf("or")).trim().toUpperCase()))return true;
             //System.out.println(ch);
               ch=ch.substring(ch.indexOf("or") +2 ,ch.length());
            // System.out.println(ch);
            
        }
      if(f.getFait().getValeur().toUpperCase().trim().equals(ch.substring(0,ch.length()).trim().toUpperCase()))return true;
      }
        return false;
        
    }
    
        /* Moteur d'inférence à chainage arrière */
    public boolean Moteur_Inference(Condition but , List<Fait> LFI , List<Regle> LR ) {
		
                /* Cas ou le but existe déja dans la base des fait */
                if(TrouverButLF(LFI , but))
                    {System.out.println("Le but existe déja dans la base des faits");
                    return true;
                    }
 
                /* Tester si le but donné n'est pas déclenchable par toutes regles */
                boolean test=false;
                List<Fait> LF;
                for (Regle regle : LR){
                    if(regle.getConclusion().equals(but))
                    { test=true;
                        LF=LFI;
                        List<Condition> LP = regle.getPremisse();
                        for(Condition cond : LP){
                            
                            if (test == false) break;
                            if (!TrouverButLF(LF , cond)){
                                test=Moteur_Inference(cond , LF , LR);
                                if (test == true)
                                {  // System.out.println(cond.toString());
                                    LF.add(new Fait(cond , Integer.parseInt(regle.getNumRegle())));}
                                else
                                    test=false;
                            
                            }      
                       }
                         
                    }
               if (test == true)return true;
                }
                   
   return false;
 }

	
	
    public static void main(String[] args) throws IOException {
         IA lecture = new IA();
         System.out.println("--------------   Bonjour  ----------------");
         System.out.println("Veuillez tester votre base de connaisance ");
         System.out.println("---- Veuillez choisir votre choix --------");
         System.out.println("[1]  :Pour remplir la base de fait ------ ");
         System.out.println("[2]  :Pour remplir la base de regle ----- ");
         System.out.println("[3]  :Pour lister la base de fait ------- ");
         System.out.println("[4]  :Pour lister la base de regle ------ ");
         System.out.println("[5]  :Pour tester un but -----------------");
         System.out.println("[6]  :Pour Sortir  -----------------------");
         Scanner sc = new Scanner(System.in);
         String c = sc.nextLine();
         int i =Integer.parseInt(c);
         while (i<6) {
             
         switch (i) {
             
         case 1 : WriteToBaseFait();break;
         case 2 : WriteToBasesRegle(); break;
         case 3 :
         {System.out.println("La Base des faits :");
         lecture.ReadFromBaseFait();break;}
         case 4 :
         {System.out.println("La Base des règles :");
         lecture.ReadFromBaseRegle();break;}
         case 5 : 
         {System.out.println("Le But ??");
          System.out.println(lecture.liste_fait.toString());
         String but = sc.nextLine();
         but=but.toUpperCase();
         Condition cond = lecture.DecomposerCondition(but);
                
         if (lecture.Moteur_Inference(cond , liste_fait , liste_regle))
                 System.out.println("Le but est vérifié");
         else
                 System.out.println("Le but n'est pas vérifié");
         }
         System.out.println("Liste des faits après l'execution" + lecture.liste_fait.toString());
         
         }
         System.out.println("Veuiller saisir votre choix");
         i = Integer.parseInt(sc.nextLine());
}
    }}