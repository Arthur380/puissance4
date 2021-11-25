package swing;


//je cree un ordinateur qui herite de joueur abstrait l'id
public class Ordinateur extends JoueurAbstrait {

	private AlgoAlphaBeta IA;
	
	public Ordinateur(char symbole, int niveau) {
		super(symbole,niveau);
		this.IA = new AlgoAlphaBeta(niveau);
	}

// on qui va servir a faire jouer L'IA ! =)
	@Override
	public int placerChar(Grille grille)  {
		System.out.print("IA DEVRAIT JOUER = " + IA.ouJouer(this, grille) );
		return IA.ouJouer(this, grille);
	}

}
