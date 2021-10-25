package swing;

import java.util.Scanner;
import javax.swing.JFrame  ;
import javax.swing.JButton ;
import java.awt.event.*;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class P4 {

	public static void main(String[] args) {
		//DEBUT DU PROGRAMME
		
		Scanner scanner = new Scanner(System.in);
		
		
		//Le plateau vertical de jeu:
		//alignement requis :
		int N = 4;
		//colonnes et lignes :
		int C = 7;
		int L = 6;
		//tableau du plateau ('.' = emplacement vide  / 'X' = joueur 1  /  'O'  = joueur 2)
		char[][] plateau = new char[C][L];
		//remplissage des cases avec du vide, oui c'est paradoxal !
		for(int x = 0 ; x < C ; x++)
			for(int y = 0 ; y < L ; y++)
				plateau[x][y] = '.';		
		int gagnant = 0;
		
		
		for(int i = 1 ; i <= C*L ; i++){
			
			//affichage du plateau:
			System.out.println("Tour " + i + ", Etat du plateau :");
			
			for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
			System.out.println();
			
			for(int y = 0 ; y < L ; y++){
				System.out.print('|');
				for(int x = 0 ; x < C ; x++){
					System.out.print(" " + plateau[x][y] + " ");
				}
				System.out.print('|');
				System.out.println();
			}
			
			for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
			System.out.println();
			
			//Placements du jeton:
			System.out.println("Tour du joueur " + (i%2==1 ? 'X' : 'O') );
			System.out.println("Entrez le numero de la colonne entre 1 et " + C + " ...");
			boolean placement = false;
			int colonne = -1;
			while(!placement){
				colonne = -1;
				String ligne = scanner.nextLine();
				//verification que la ligne est un entier entre 1 et C:
				try{
					colonne = Integer.valueOf(ligne);
					
					if(colonne >= 1 && colonne <= C){
						if(plateau[colonne - 1][0] != '.'){
							System.out.println("Colonne pleine, reiterez");
						} else {
							placement = true;
						}
					} else {
						System.out.println("Nombre incorrect, reiterez");
					}
					
				}catch(Exception e){System.out.println("Nombre incorrect, reiterez");}
				
			}
			//placement du jeton:
			int rang = L-1;
			while(plateau[colonne - 1][rang] != '.'){
				rang--;
			}
			plateau[colonne - 1][rang] = (i%2==1 ? 'X' : 'O');
			
			
			
			//Detection de victoire:
			
			//symbole en cours:
			char symbole = (i%2==1 ? 'X' : 'O');
			//nombre alignes maximal: 
			int max = 0;
			int x; int y;
			int somme;
			
			//-->  diagonale HG-BD
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && x >= 0 && plateau[x][y] == symbole){ y--; x--; somme++;}
			x = colonne-1; y = rang;
			while(y < L && x < C && plateau[x][y] == symbole){ y++; x++; somme++;}
			if(somme > max) max= somme;
			
			//-->  diagonale HD-BG
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && x < C && plateau[x][y] == symbole){ y--; x++; somme++;}
			x = colonne-1; y = rang;
			while(y < L && x >= 0 && plateau[x][y] == symbole){ y++; x--; somme++;}
			if(somme > max) max= somme;
			
			//-->  verticale:
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && plateau[x][y] == symbole){ y--; somme++;}
			y = rang;
			while(y < L && plateau[x][y] == symbole){ y++; somme++;}
			if(somme > max) max= somme;
			
			//-->  horizontale:
			x = colonne-1; y = rang; somme=-1;
			while(x >= 0 && plateau[x][y] == symbole){ x--; somme++;}
			x = colonne-1;
			while(x < C && plateau[x][y] == symbole){ x++; somme++;}
			if(somme > max) max= somme;
			
			
			if(max >= N){
				gagnant = (i%2==1 ? 1 : 2);
				i = C*L+1;
			}
			
			
			
			
			System.out.println("********************************");
		}
		
		
		//affichage des resultats:
		// si gagnant == 0 c'est que tout le plateau s'est remplis sans gagnant, il y a donc egalite
		System.out.println();
		System.out.println("*********************");
		System.out.println("****FIN DE PARTIE****");
		System.out.println("*********************");
		if(gagnant == 0)
			System.out.println("*******EGALITE*******");
		if(gagnant == 1)
			System.out.println("****VICTOIRE DE X****");
		if(gagnant == 2)
			System.out.println("****VICTOIRE DE O****");
		System.out.println("*********************");
		
		
		for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
		System.out.println();
		
		for(int y = 0 ; y < L ; y++){
			System.out.print('|');
			for(int x = 0 ; x < C ; x++){
				System.out.print(" " + plateau[x][y] + " ");
			}
			System.out.print('|');
			System.out.println();
		}
		
		for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
		System.out.println();
		
		
		
	}

}