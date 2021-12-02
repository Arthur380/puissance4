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
	int joueur = 1; // creation variable joueur
	private JButton[] colonnes;

	//////////
	private JFrame frame;
	private JLabel[][] slots;
	private JButton[] buttons;
	// variables used in grid
	private int xsize = 7;
	private int ysize = 6;
	private Grille grille;
	private int nbTour = 1;
	private JoueurAbstrait joueurA;
	private JoueurAbstrait joueurB;
	private boolean gagner = false;
	private JPanel panel;


	public JTableBasiqueAvecModeleDynamiqueObjet() {
		super();

		boolean win = true;
		grille = new Grille();
		joueurA = new Ordinateur('X', 6);
		joueurA.setNom("2");
		joueurB = new Ordinateur('O', 4);
		joueurB.setNom("2");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		frame = new JFrame("connect four");
		frame.setTitle("C'est au tour du joueur rouge de jouer");
		
		panel = (JPanel) frame.getContentPane();
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
		updateBoard();
		scenarioIA();
	}

	private class AddAction extends AbstractAction {
		public void actionPerformed(ActionEvent evt) // Controle des actions gerees par ActionListener
		{
			Object source = evt.getSource();
			char SymboleAPlace;

			frame.setTitle("C'est au tour du joueur "+grille.getTourDeQuelJoueur().getCouleur()+" de jouer");

			for (int i = 0; i <= ysize; i++) {

				JButton colonne = buttons[i];

				if (source == colonne) {
					grille.insere(i, grille.getTourDeQuelJoueur().getSymbole());
					System.out.println("i " + i);
					System.out.println("avant fonction gagnant " +  grille.getTourDeQuelJoueur().getSymbole());
					gagner = grille.Victoire(grille.getTourDeQuelJoueur().getSymbole(), grille.getTourJoueurSuivant().getSymbole());
					grille.afficheGrille();
				}

			}
			updateBoard();
			if(gagner) {
				Component[] com = panel.getComponents();

				for (int a = 0; a < com.length; a++) {
					panel.remove(com[a]);
				}
				JLabel jlabel = new JLabel("Victoire du joueur "+ grille.getTourDeQuelJoueur().getCouleur());
				panel.add(jlabel);
				frame.setTitle("Le joueur "+grille.getTourDeQuelJoueur().getCouleur()+" a gagn�");
			}else {
				scenarioIA();

			}
		}
	}

	public void updateBoard() {// keeps the gui in sync with the logggggtjiic and grid
		for (int row = 0; row < xsize; row++) {
			for (int column = 0; column < ysize; column++) {
				if (grille.getEqualCase(row, column, 'X')) {
					slots[row][column].setIcon(new ImageIcon("./assets/images/jeton_jaune_p4.png"));
					slots[row][column].setOpaque(true);
				}else if (grille.getEqualCase(row, column, 'O')) {
					slots[row][column].setIcon(new ImageIcon("./assets/images/jeton_rouge_p4.png"));
					slots[row][column].setOpaque(true);
				}else {
					slots[row][column].setIcon(new ImageIcon("./assets/images/jeton_blanc_p4.png"));
					slots[row][column].setOpaque(true);
				}
			}
		}
	}
	
	public void scenarioIA() {
		grille.afficheGrille();
		JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
		JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
		while(JoueurActuel.getNom() == "2"  && !gagner) {
			int colonneAJouer;
			colonneAJouer = JoueurActuel.placerChar(grille);
			grille.insere(colonneAJouer,JoueurActuel.getSymbole());
			if (grille.Victoire(JoueurActuel.getSymbole(), JoueurSuivant.getSymbole())) {
				gagner =true;

				Component[] com = panel.getComponents();

				for (int a = 0; a < com.length; a++) {
			//		panel.remove(com[a]);
				}
				JLabel jlabel = new JLabel("Victoire du joueur "+ grille.getTourDeQuelJoueur().getCouleur());
				//panel.add(jlabel);
				frame.setTitle("Le joueur "+grille.getTourDeQuelJoueur().getCouleur()+" a gagn�");
			}
			
		//	grille.afficheGrille();
			JoueurActuel = grille.getTourDeQuelJoueur();
			JoueurSuivant = grille.getTourJoueurSuivant();		
		}
		JoueurActuel.placerChar(grille);
		
		updateBoard();

	}

}
