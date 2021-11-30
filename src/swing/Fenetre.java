package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
class Fenetre implements ActionListener // L'implementation de la fenetre est ActionListener car la fenetre doit savoir gerer l'action du bouton
{ //tant que la fenetre existe la fenetre2 n'est pas ephemere et reste
    private JFrame  frame1; // Jframe correspondant au corps dela fenetre
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel MonLabel;
     
    Fenetre () // Constructeur de la classe fenetre   : remarque un constructeur est toujours public car il doit etre vu depuis l'exterieur de la classe
    {
        frame1 = new JFrame ("PUISSANCE 4"); // Creation de la JFrame par appel du constructeur
        frame1.setSize(600 , 400);
         
         
         
        DesignFenetre(); // Fonction mettant en place le comptenu de la fenetre    
         
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE) ; // Gestion de la fermutre de la fenetre
        frame1.setVisible(true) ;                               // Affichage de la fenetre a l'ecran
    }
     
    private void DesignFenetre()
    {
         
        frame1.setLayout(null);  // L'agencement (Layout) null permet un positionnement en fonction des coordonnees relative en pixel par l'utilisation de la commande setBounds
         
        MonLabel = new JLabel("PUISSANCE 4 (pas pique des hannetons) ");
         
        MonLabel.setBounds( 150, 5, 500, 50 ); // Le JLabel est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(MonLabel);         // Placement du JLabel sur la fenetre
         
        button1 = new JButton("Un joueur"); // Creation du JButton par appel du constructeur
         
        button1.setBounds( 200, 50, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(button1);         // Placement du bouton sur la fenetre
         
        button1.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
  
 
        button2 = new JButton("Deux joueurs"); // Creation du JButton par appel du constructeur
         
        button2.setBounds( 200, 150, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        frame1.add(button2);         // Placement du bouton sur la fenetre
         
        button2.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
         
        button3 = new JButton("Quitter"); // Creation du JButton par appel du constructeur
         
        button3.setBounds( 200, 250, 200, 50 ); // Le bouton est place aux coordonnees 100 en abscisse et 150 en ordonnee dans le repere de la fenetre.
 
        button3.addActionListener(this); // On precise que le bouton doit etre ecoute afin de gerer les actions associees a celui-ci.
         
        frame1.add(button3);         // Placement du bouton sur la fenetre
         
 
    }
    public void actionPerformed(ActionEvent evt) // Controle des actions gerees par ActionListener
    {
        Object source = evt.getSource();
         
        if ( source == button1 )
        {
        	 new JTableBasiqueAvecModeleDynamiqueObjet().setVisible(true);
        }  
 
        if ( source == button2 )
        {
        	 new JTableBasiqueAvecModeleDynamiqueObjet().setVisible(true);
        }           
         
        if ( source == button3)
        {
        System.exit(0);    
        }  
        //if(evt.getSource()==button3)
        //{
        //this.setVisible(false);
        //this.dispose();
        //}
    }
}