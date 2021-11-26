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
		int j = IA.ouJouer(this, grille);
		System.out.print("\n Je joue "+this.getSymbole()+" en " + (j+1) +"\n");
		return j;
	}

}
