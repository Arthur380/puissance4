package swing;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;





public class JTableBasiqueAvecModeleDynamiqueObjet extends JFrame {
    private ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet();
    private JTable tableau;
	private int[][] tab;
	int colonnejoue;
	private Jeu jeu;
	int joueur = 1; // creation variable joueur
	private JButton[] colonnes;
	
    public JTableBasiqueAvecModeleDynamiqueObjet() {
        super();
 
        setTitle("JTable avec modèle dynamique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
        tab = new int[7][1];
		colonnes = new JButton[7];
		jeu = new Jeu(7, 6, 4);		

		for (int x = 0; x < 7; x++) {
			JButton colonne = new JButton("Col " + x); // Creation du JButton par appel du constructeur
			colonne.setBounds(0 + (70 * x), 50, 70, 50); // Le bouton est place aux coordonnees 100 en abscisse et
															// 150
															// en ordonnee dans le repere de la fenetre.
			getContentPane().add(colonne);
			colonnes[x] = colonne;
			colonne.addActionListener(new AddAction()); // On precise que le bouton doit etre ecoute afin de gerer les actions
												// associees a celui-ci.
		}
        
        tableau = new JTable(modele);
     //  boutons.add(new JButton(new AddAction()));
        
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        tableau.setRowHeight(30);
 
        pack();
    }
 
    private class AddAction extends AbstractAction {
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
				colonnejoue = jeu.placementJeton(i, SymboleAPlace, tableau);
				System.out.println("avant fonction gagnant " + SymboleAPlace);
				jeu.gagnant(i, colonnejoue, SymboleAPlace);
				jeu.affichetabJeu();
			}

		}
	}}

}