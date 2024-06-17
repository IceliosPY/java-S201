package modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private float prixTotal;
    private List<Achats> achats;

    public Panier() {
        this.prixTotal = 0.0F;
        this.achats = new ArrayList<Achats>();
    }

    /**
     * @return the prix
     */
    public float getPrixTotal() {
        return prixTotal;
    }

    /**
     * @return the produits
     */
    public List<Achats> getProduits() {
        return this.achats;
    }

    
    public void ajouterAchat(Achats achat, int quantité) throws IllegalArgumentException {
        if (achat.getArticle().getQuantitéEnStock() < quantité) {
            throw new IllegalArgumentException("L'article n'est plus disponible");
        }

        if (quantité != 0) { // Vérifiez la quantité réelle à ajouter

            if (!this.contient(achat)) {
                // Clonez l'achat pour éviter d'ajouter la quantité initiale
                Achats nouvelAchat = new Achats(achat.getArticle(), 0);
                nouvelAchat.ajouterQuantité(quantité); // Ajoutez la quantité réelle
                this.achats.add(nouvelAchat);
            } else {
                int i = this.achats.indexOf(achat);
                Achats a = this.achats.get(i);
                a.ajouterQuantité(quantité);
            }
            this.calculerPrixPanier();
        }
    }

      

    public void reduireUnAchat(Achats achat) throws IllegalArgumentException {
        if (!this.achats.contains(achat)) {
            throw new IllegalArgumentException("L'article n'est pas dans le panier");
        }
        int i = this.achats.indexOf(achat);
        this.achats.get(i).enleverQuantité(1);
        
        this.calculerPrixPanier();
    }
    

    public void supprimerAchat(Achats achat) throws IllegalArgumentException {
        if (!this.contient(achat)) {
            throw new IllegalArgumentException("L'article n'est pas dans le panier");
        }
        int i = this.achats.indexOf(achat);
        int quantite = this.achats.get(i).getQuantité();
        achat.enleverQuantité(quantite);
        this.achats.remove(achat);        
        this.calculerPrixPanier();
    }


    public void calculerPrixPanier() {
        this.prixTotal = 0; // Reset the total price before calculation
        for (Achats a : this.achats) {
            this.prixTotal += a.prix();
        }
    }
    
    public boolean contient(Achats achat) {
        for (Achats produit : this.getProduits()) {
            if (produit.equals(achat)) {
                return true;
            }
        }
        return false;
    }
    
    public int getAchats(Achats achat) {
    	
    	int index = 0;
        for (Achats produit : this.getProduits()) {
        	
            if (produit.equals(achat)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    
    public boolean isEmpty() {
    	return this.getProduits().isEmpty();
    }
    
    public void viderPanier() {
        for (Achats achat : achats) {
            achat.enleverQuantité(achat.getQuantité()); // Remettre les quantités en stock
        }
        achats.clear();
        this.prixTotal = 0.0F; // Réinitialiser le prix total à 0
    }
    
    public float calculerFraisDePort(Transport t) {
    	return t.calculerFraisDePort(this.getPrixTotal());
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Panier [prix=").append(prixTotal).append(", produits=");

        if (this.achats.isEmpty()) {
            sb.append("Aucun produit");
        } else {
            for (Achats achat : this.achats) {
                sb.append(achat.toString()).append(", ");
            }
            // Supprimer la dernière virgule et l'espace
            sb.setLength(sb.length() - 2);
        }

        sb.append("]");
        return sb.toString();
    }

}
