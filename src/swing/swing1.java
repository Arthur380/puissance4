package swing;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class swing1 extends JFrame {

  public swing1() {

     super("titre de l'application");

      WindowListener l = new WindowAdapter() {
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }
      };

      addWindowListener(l);

      ImageIcon img = new ImageIcon("tips.gif");
      JButton bouton = new JButton("Mon bouton",img);

      JPanel panneau = new JPanel();
      panneau.add(bouton);
      setContentPane(panneau);
      setSize(200,100);
      setVisible(true);
   }

  public static void main(String argv[]) {

	    JFrame f = new JFrame("ma fenetre");
	    f.setSize(300,100);
	    JPanel pannel = new JPanel(); 

	    JButton bouton = new JButton("saisir");
	    pannel.add(bouton);

	    JTextField jEdit = new JTextField("votre nom");

	    JLabel jLabel1 =new JLabel("Nom : "); 
	    jLabel1.setBackground(Color.red);
	    jLabel1.setDisplayedMnemonic('n');
	    jLabel1.setLabelFor(jEdit);
	    pannel.add(jLabel1);
	    pannel.add(jEdit);

	    f.getContentPane().add(pannel);
	    f.setVisible(true);
	  }
}