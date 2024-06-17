package modele;
//permet d'enregistrer le choix du fromage
public class Achats {
	
	private int quantité;
	private Article article;
	
	
	public Achats(Article article, int quantité) {
		this.quantité = quantité;
		this.article = article; 
	}
	
	public void setQuantite(int quantité) {
		this.quantité = quantité; 
	}
	
	public Article getArticle() {
		return this.article;
	}
	
	public int getQuantité() {
		return this.quantité;
	}
	
	
	public void ajouterQuantité(int quantité) {
		if (article.getQuantitéEnStock() < quantité) {
			throw new IllegalArgumentException("Quantité en stock insuffisante pour ajouter " + quantité);
		}
		this.quantité += quantité;
		this.article.préempterQuantité(quantité);
	}
	
	public void enleverQuantité(int quantité) {
		if (this.quantité < quantité) {
			throw new IllegalArgumentException("Quantité insuffisante dans l'achat pour enlever " + quantité);
		}
		this.quantité -= quantité;
		this.article.rendreQuantité(quantité);
	}
	
	public float prix() {
		return this.getArticle().getPrixTTC() * this.getQuantité();
	}
	
	@Override
    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}
    	if (!(this instanceof Achats)) {
    		return false;
    	}
    	Achats other = (Achats) object;
    	if (!(this.article.equals(other.getArticle()))) {
    		return false;
    	}
    	return true;
    }

	@Override
	public String toString() {
		return "{quantité=" + quantité + ", article=" + article.toString() + "}";
	}
}
