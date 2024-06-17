package modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAchats {
    private Article article;
    private Achats achats;

    @Before
    public void setUp() {
        article = new Article(new Fromage("Brie aux truffes"), "a1", 5F);
        achats = new Achats(article, 3);
        article.setQuantitéEnStock(10);
    }

    
   
    @Test
    public void testGetArticle() {
        assertEquals(article, achats.getArticle());
    }

    @Test
    public void testGetQuantité() {
        assertEquals(3, achats.getQuantité());
    }

    @Test
    public void testAjouterQuantité() {
        achats.ajouterQuantité(2);
        assertEquals(5, achats.getQuantité());
        assertEquals(8, article.getQuantitéEnStock());
        
    }

    @Test
    public void testEnleverQuantité() {
        achats.enleverQuantité(1);
        assertEquals(2, achats.getQuantité());
        assertEquals(11, article.getQuantitéEnStock()); 
    }

    @Test
    public void testPrix() {
        assertEquals(15.0, achats.prix(), 0.001); // 3 * 5.0 = 15.0
    }

}
