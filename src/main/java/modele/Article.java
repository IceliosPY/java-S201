package modele;

public class Article {

	private Fromage fromage;
	private String clé; // si c'est à l'unité...
	private float prixTTC;
	private int quantitéEnStock;

	public Article(Fromage fromage, String clé, float prixTTC) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixTTC = prixTTC;
		this.quantitéEnStock = 0;
	}

	public Fromage getFromage() {
		return this.fromage;
	}

	public float getPrixTTC() {
		return this.prixTTC;
	}

	public int getQuantitéEnStock() {
		return this.quantitéEnStock;
	}

	public String getClé() {
		return this.clé;
	}

	public void setQuantitéEnStock(int quantitéEnStock) {
		this.quantitéEnStock = quantitéEnStock;
	}
	
	public void préempterQuantité(int quantité) {
		this.quantitéEnStock -= quantité; 
	}
	
	public void rendreQuantité(int quantité) {
		this.quantitéEnStock += quantité; 
	}

	@Override
	public String toString() {
		if (this.clé.equals("")) {
			return this.fromage.getDésignation() + ", Prix TTC : "
			        + this.getPrixTTC() + " €";
		} else {
			return this.fromage.getDésignation() + ", " + this.clé
			        + ", Prix TTC : " + this.getPrixTTC() + " €";
		}
	}

	public String toStringAvecStock() {
		return this.toString() + ", Quantité en stock : "
		        + this.quantitéEnStock;
	}


	@Override
    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}
    	if (!(this instanceof Article)) {
    		return false;
    	}
    	Article other = (Article) object;
    	return (this.fromage.equals(other.fromage)
		        && this.clé.equals(other.clé));
    }

}
