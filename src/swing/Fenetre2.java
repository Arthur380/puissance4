package swing;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.dnd.DropTargetContext;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.*;


import java.awt.BorderLayout;



//- Definition de la classe fenetre -------------------------------------
class Fenetre2 implements ActionListener // L'implementation de la fenetre est ActionListener car la fenetre doit savoir
											// gerer l'action du bouton
{
	private JFrame frame1; // Jframe correspondant au corps dela fenetre
	private int[][] tab;
	int colonnejoue;
	private JButton[] colonnes;
	private Jeu jeu;
	int joueur = 1; // creation variable joueur
	// tentative tableau
	private ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet();
	private JTable tableau;

	Fenetre2() // Constructeur de la classe fenetre : remarque un constructeur est toujours
				// public car il doit etre vu depuis l'exterieur de la classe
	{
		frame1 = new JFrame("Fenetre 2"); // Creation de la JFrame par appel du constructeur
		frame1.setSize(1800, 800);

		// DesignFenetre2(); // Fonction mettant en place le comptenu de la fenetre

		frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE); // Gestion de la fermutre de la fenetre
		frame1.setVisible(true); // Affichage de la fenetre a l'ecran
		frame1.setLayout(null);

		tab = new int[7][1];
		colonnes = new JButton[7];
		jeu = new Jeu(7, 6, 4);		

		for (int x = 0; x < 7; x++) {
			JButton colonne = new JButton("Clique " + x); // Creation du JButton par appel du constructeur
			colonne.setBounds(25 + (250 * x), 50, 200, 50); // Le bouton est place aux coordonnees 100 en abscisse et
															// 150
															// en ordonnee dans le repere de la fenetre.
			frame1.add(colonne);
			colonnes[x] = colonne;
			colonne.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions
												// associees a celui-ci.
		}
        tableau = new JTable(modele);
        
        frame1.add(new JScrollPane(tableau), BorderLayout.CENTER); 
 
        tableau = new JTable(modele);
        
 
        frame1.add(new JScrollPane(tableau), BorderLayout.CENTER);
        tableau.setRowHeight(30);
        JPanel boutons = new JPanel();
        for(int i =0; i<tableau.getColumnCount();i++) {
        	tableau.getColumnModel().getColumn(i).setMaxWidth(30);        	
        }
 
        pack();
        setTitle("JTable avec modèle dynamique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        tableau = new JTable(modele);
      //  tableau.setDefaultRenderer(Color.class, new ColorCellRenderer());
       
        tableau.setValueAt(icon, 0, 1);
        //tableau.;
     
        
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        tableau.setRowHeight(30);
        JPanel boutons = new JPanel();
        for(int i =0; i<tableau.getColumnCount();i++) {
        	tableau.getColumnModel().getColumn(i).setMaxWidth(30);        	
        }
         
      //  boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(icon));
        boutons.add(new JButton(new RemoveAction()));
 
        getContentPane().add(boutons, BorderLayout.SOUTH);
     //   getContentPane().add(boutons, icon, BorderLayout.Center);
        pack();
		
	}

	public void actionPerformed(ActionEvent evt) // Controle des actions gerees par ActionListener
	{
		Object source = evt.getSource();
		char SymboleAPlace;
		if (jeu.getNbTour() % 2 == 0)
			SymboleAPlace = 'O';
		else
			SymboleAPlace = 'X';
		for (int i = 0; i < colonnes.length; i++) {

			JButton colonne = colonnes[i];

			if (source == colonne) {
				System.out.println("i " + i);
				colonnejoue = jeu.placementJeton(i, SymboleAPlace);
				System.out.println("avant fonction gagnant " + SymboleAPlace);
				jeu.gagnant(i, colonnejoue, SymboleAPlace);
				jeu.affichetabJeu();
			}

		}
	}
}