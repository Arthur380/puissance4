/*
 * Gestion de la partie graphique du projet
 * 
 */

package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// L'impl�mentation de la fen�tre est ActionListener car la fen�tre doit savoir g�rer l'action du bouton
class Fenetre implements ActionListener
{ 
	//tant que la fen�tre existe la fen�tre 2 n'est pas �phem�re et reste
    private JFrame  frame1; //correspond au corps de la fen�tre
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel MonLabel;
    
	private static Fenetre fen;      

    
//--------------------------CONSTRUCTEUR-------------------------------------------------
    /**Constructeur fen�tre
     * (public pour �tre vu par tous depuis l'ext�rieur)
     */
    Fenetre () 
    {
    	//cr�ation de la JFrame par appel du constructeur
        frame1 = new JFrame ("PUISSANCE 4");
        frame1.setSize(600, 400);
        
        //mise en place du contenu de la fen�tre 
        DesignFenetre();  

        //gestion de fermeture et affichage de la fen�tre
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
     
    

    private void DesignFenetre()
    {
         
        frame1.setLayout(null);  // L'agencement (Layout) null permet un positionnement en fonction des coordonnees relative en pixel par l'utilisation de la commande setBounds
         
        MonLabel = new JLabel("PUISSANCE 4  Arthur Raimbert\r\n"
        		+ " Alice Foraison \r\n"
        		+ "La�di Debbouze \r\n"
        		+ "");
         
        MonLabel.setBounds( 150, 5, 500, 50 ); // Le JLabel est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(MonLabel);         // Placement du JLabel sur la fenetre
         
        button1 = new JButton("Une IA"); // Creation du JButton par appel du constructeur
         
        button1.setBounds( 200, 50, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(button1);         // Placement du bouton sur la fenetre
         
        button1.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
  
 
        button2 = new JButton("Deux IA"); // Creation du JButton par appel du constructeur
         
        button2.setBounds( 200, 150, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(button2);         // Placement du bouton sur la fenetre
         
        button2.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
         
        button3 = new JButton("Quitter"); // Creation du JButton par appel du constructeur
         
        button3.setBounds( 200, 250, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        button3.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
         
        frame1.add(button3);         // Placement du bouton sur la fenetre
    }
    
//----------------------------------------------------------------------------------------
    /**Controle des actions g�r�es par ActionListener
     * 
     * @param evt �venement g�n�r� sur la grille de jeu
     * 
     * @return void
     */
    public void actionPerformed(ActionEvent evt) // 
    {
        Object source = evt.getSource();
         
        if ( source == button1 )
        {
        	 new JTableBasiqueAvecModeleDynamiqueObjet().setVisible(true);
        }  
 
        if ( source == button2 )
        {
        	 new JTableBasiqueAvecModeleDynamiqueObjet2IA().setVisible(true);
        }           
         
        if ( source == button3)
        {
        System.exit(0);    
        }  
    }
    
    public static void main(String[] args)
    { 
    	//cr�ation de la fen�tre par appel du constructeur
        fen = new Fenetre();
    }
}