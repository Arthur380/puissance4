package swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class JTableBasiqueAvecModeleDynamiqueObjet extends JFrame {
	private ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet();
	private JTable tableau;
	private int[][] tab;
	int colonnejoue;
	private Jeu jeu;
	int joueur = 1; // creation variable joueur
	private JButton[] colonnes;

	//////////
	private JFrame frame;
	private JLabel[][] slots;
	private JButton[] buttons;
	// variables used in grid
	private int xsize = 7;
	private int ysize = 6;
	Grille grille = new Grille();
	private int nbTour = 1;

	public JTableBasiqueAvecModeleDynamiqueObjet() {
		super();

		jeu = new Jeu(7, 6, 4);

		JoueurAbstrait joueurA = new Humain('X', 4);
		joueurA.setNom("1");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("2");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		/*
		 * setTitle("JTable avec modèle dynamique");
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * 
		 * 
		 * 
		 * tab = new int[7][1]; colonnes = new JButton[7];
		 * 
		 * for (int x = 0; x < 7; x++) { JButton colonne = new JButton("Col " + x); //
		 * Creation du JButton par appel du constructeur colonne.setBounds(0 + (70 * x),
		 * 50, 70, 50); // Le bouton est place aux coordonnees 100 en abscisse et // 150
		 * // en ordonnee dans le repere de la fenetre. getContentPane().add(colonne);
		 * colonnes[x] = colonne; colonne.addActionListener(new AddAction()); // On
		 * precise que le bouton doit etre ecoute afin de gerer les actions // associees
		 * a celui-ci. }
		 * 
		 * tableau = new JTable(modele); // boutons.add(new JButton(new AddAction()));
		 * 
		 * getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		 * tableau.setRowHeight(30);
		 * 
		 * pack();
		 */

		frame = new JFrame("connect four");
		frame.setTitle("C'est au tour du joueur rouge de jouer");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(new GridLayout(xsize, ysize + 1));

		slots = new JLabel[xsize][ysize];
		buttons = new JButton[xsize];

		for (int i = 0; i < xsize; i++) {
			buttons[i] = new JButton("" + (i + 1));
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(new AddAction());
			panel.add(buttons[i]);
		}
		for (int column = 0; column < ysize; column++) {
			for (int row = 0; row < xsize; row++) {
				slots[row][column] = new JLabel();
				slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
				slots[row][column].setBorder(new LineBorder(Color.black));
				panel.add(slots[row][column]);
			}
		}

		// jframe stuff
		frame.setContentPane(panel);
		frame.setSize(700, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class AddAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) // Controle des actions gerees par ActionListener
		{
			Object source = evt.getSource();
			nbTour++;
			jeu.setNbTour(nbTour);

			System.out.println("jeu tour :" + jeu.getNbTour());
			char SymboleAPlace;
			if (jeu.getNbTour() % 2 == 0) {
				frame.setTitle("C'est au tour du joueur jaune de jouer");
				SymboleAPlace = 'O';
			} else {
				frame.setTitle("C'est au tour du joueur rouge de jouer");
				SymboleAPlace = 'X';
			}

			for (int i = 0; i <= ysize; i++) {

				JButton colonne = buttons[i];

				if (source == colonne) {
					colonnejoue = jeu.placementJeton(i, SymboleAPlace, tableau);
					System.out.println("i " + i);
					System.out.println("avant fonction gagnant " + SymboleAPlace);
					jeu.gagnant(i, colonnejoue, SymboleAPlace);
					jeu.affichetabJeu();
				}

			}
			updateBoard();
		}
	}

	public void updateBoard() {// keeps the gui in sync with the logggggtjiic and grid
		for (int row = 0; row < xsize; row++) {
			for (int column = 0; column < ysize; column++) {
				if (jeu.getEqualCase(row, column, 'X')) {
					slots[row][column].setIcon(new ImageIcon("./assets/images/jeton_jaune.png"));
					slots[row][column].setOpaque(true);
					//slots[row][column].setBackground(Color.red);
				}
				if (jeu.getEqualCase(row, column, 'O')) {
					slots[row][column].setIcon(new ImageIcon("./assets/images/jeton_rouge.png"));
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.blue);
				}
			}
		}
	}

}
