package modele;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestPanier {
    private Panier panier;
    private Article article1;
    private Article article2;
    private Achats achat1;
    private Achats achat2;
    private Achats achat3;

    @Before
    public void setUp() {
        panier = new Panier();
        // Créez des articles de test
        article1 = new Article(new Fromage("Brie aux truffes"), "500g", 5F);
        article1.setQuantitéEnStock(10);
        article2 = new Article(new Fromage("Abondance"), "à l'unité", 3F);
        article2.setQuantitéEnStock(10);

        // Créez des achats
        achat1 = new Achats(article1, 3);
        achat2 = new Achats(article2, 3);
        
        achat3 = new Achats(article1, 2);
    }

    @Test
    public void testAjouterAchat() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        assertFalse(panier.isEmpty());
        assertTrue(panier.contient(achat1));
        assertEquals(7, article1.getQuantitéEnStock());
        assertEquals(15.0F, panier.getPrixTotal(), 0.001); // 5 (prix unitaire) * 2 (quantité)
    }
    
    @Test
    public void testAjouterAchatDejaPresent() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        panier.ajouterAchat(achat3, achat3.getQuantité());
        assertEquals(5, article1.getQuantitéEnStock());
        assertEquals(25.0F, panier.getPrixTotal(), 0.001); // 5 (prix unitaire) * 2 (quantité)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAjouterAchatQuantitéIndisponible() {
        panier.ajouterAchat(achat1, 20); // Doit lancer une exception car la quantité demandée dépasse le stock
    }


    @Test
    public void testReduireUnAchat() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        panier.reduireUnAchat(achat1);
        assertEquals(1, panier.getProduits().size());
        assertEquals(8, article1.getQuantitéEnStock());
        
    }

    @Test
    public void testSupprimerAchat() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        panier.supprimerAchat(achat1); // Supprime l'achat
        assertEquals(0, panier.getProduits().size()); // Le panier doit être vide
        assertEquals(10, article1.getQuantitéEnStock()); // Le stock doit être restauré à 10
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSupprimerAchatThrowsExceptionWhenNotInPanier() {
        panier.supprimerAchat(achat1); // Doit lancer une exception car l'achat n'est pas dans le panier
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReduireUnAchatThrowsExceptionWhenNotInPanier() {
        panier.reduireUnAchat(achat1); // Doit lancer une exception car l'achat n'est pas dans le panier
    }

    @Test
    public void testCalculerPrixPanier() {
        panier.ajouterAchat(achat1, achat1.getQuantité()); 
        panier.ajouterAchat(achat2, achat2.getQuantité()); 
        panier.calculerPrixPanier();
        assertEquals(5 * 3 + 3 * 3, panier.getPrixTotal(), 0.001); // Vérifie le calcul du prix total
    }

    @Test
    public void testContientAchat() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        assertTrue(panier.contient(achat1)); // Le panier doit contenir achat1
        assertFalse(panier.contient(achat2)); // Le panier ne doit pas contenir achat2
    }

    @Test
    public void testViderPanier() {
        panier.ajouterAchat(achat1, achat1.getQuantité());
        panier.ajouterAchat(achat2,  achat1.getQuantité());
        panier.viderPanier(); // Vide le panier
        assertTrue(panier.isEmpty()); // Le panier doit être vide
        assertEquals(10, article1.getQuantitéEnStock()); // Le stock doit être restauré pour article1
        assertEquals(10, article2.getQuantitéEnStock()); // Le stock doit être restauré pour article2
    }
    
    @Test
    public void testGetAchat() {
        panier.ajouterAchat(achat1, 2);
        panier.ajouterAchat(achat2, 10);
        assertEquals(1, panier.getAchats(achat2));
    }
    
    @Test
    public void testCalculerFraisDePortColissimo() {
        panier.ajouterAchat(achat1, 2); // Ajoute 2 unités de Brie aux truffes
        panier.ajouterAchat(achat2, 3); // Ajoute 3 unités de Abondance
        Transport transportColissimo = new Transport(TypeTransporteur.COLISSIMO);
        
        float fraisDePort = panier.calculerFraisDePort(transportColissimo);
        
        // Supposons que COLISSIMO calcule les frais comme un pourcentage du prix total (ajuster selon l'implémentation réelle)
        float prixTotal = panier.getPrixTotal();
        float expectedFrais = transportColissimo.calculerFraisDePort(prixTotal);
        
        assertEquals(expectedFrais, fraisDePort, 0.001);
    }

   
    @Test
    public void testCalculerFraisDePortPanierVide() {
        Transport transportColissimo = new Transport(TypeTransporteur.COLISSIMO);
        
        float fraisDePort = panier.calculerFraisDePort(transportColissimo);
        
        // Pour un panier vide, les frais devraient correspondre à la base de calcul du transporteur
        float prixTotal = panier.getPrixTotal();
        float expectedFrais = transportColissimo.calculerFraisDePort(prixTotal);
        
        assertEquals(expectedFrais, fraisDePort, 0.001);
    }
    
    @Test
    public void testCalculerFraisDePortPanierPlein() {
        panier.ajouterAchat(achat1, achat1.getQuantité()); // Ajoute 5 unités de Brie aux truffes
        panier.ajouterAchat(achat2, achat2.getQuantité()); // Ajoute 7 unités de Abondance
        Transport transportChronorelais = new Transport(TypeTransporteur.CHRONORELAIS);
        
        float fraisDePort = panier.calculerFraisDePort(transportChronorelais);
        
        // Pour un panier plein, les frais devraient être calculés sur le prix total
        float prixTotal = panier.getPrixTotal();
        float expectedFrais = transportChronorelais.calculerFraisDePort(prixTotal);
        
        assertEquals(expectedFrais, fraisDePort, 0.001);
    }

    @Test
    public void testToString() {
        panier.ajouterAchat(achat1, 3);
        panier.ajouterAchat(achat2, 3);
        String expected = "Panier [prix=24.0, produits={quantité=3, article=Brie aux truffes, 500g, Prix TTC : 5.0 €}, {quantité=3, article=Abondance, à l'unité, Prix TTC : 3.0 €}]";
        assertEquals(expected, panier.toString());
    }
}
