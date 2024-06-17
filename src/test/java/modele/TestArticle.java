package modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArticle {
    private Article article;
    private Fromage fromage;

    @Before
    public void setUp() {
        fromage = new Fromage("Brie aux truffes");
        article = new Article(fromage, "a1", 5F);
    }

    @Test
    public void testGetFromage() {
        assertEquals(fromage, article.getFromage());
    }

    @Test
    public void testGetPrixTTC() {
        assertEquals(5F, article.getPrixTTC(), 0.001);
    }

    @Test
    public void testGetQuantitéEnStock() {
        assertEquals(0, article.getQuantitéEnStock());
        article.setQuantitéEnStock(10);
        assertEquals(10, article.getQuantitéEnStock());
    }

    @Test
    public void testGetClé() {
        assertEquals("a1", article.getClé());
    }

    @Test
    public void testSetQuantitéEnStock() {
        article.setQuantitéEnStock(10);
        assertEquals(10, article.getQuantitéEnStock());
    }

    @Test
    public void testPréempterQuantité() {
        article.setQuantitéEnStock(10);
        article.préempterQuantité(3);
        assertEquals(7, article.getQuantitéEnStock());
    }

    @Test
    public void testRendreQuantité() {
        article.setQuantitéEnStock(10);
        article.préempterQuantité(3);
        article.rendreQuantité(3);
        assertEquals(10, article.getQuantitéEnStock());
    }

    @Test
    public void testToString() {
        String expected = "Brie aux truffes, a1, Prix TTC : 5.0 €";
        assertEquals(expected, article.toString());
    }

    @Test
    public void testToStringAvecStock() {
        article.setQuantitéEnStock(10);
        String expected = "Brie aux truffes, a1, Prix TTC : 5.0 €, Quantité en stock : 10";
        assertEquals(expected, article.toStringAvecStock());
    }

    @Test
    public void testEquals() {
        Article otherArticle = new Article(fromage, "a1", 5F);
        assertTrue(article.equals(otherArticle));
        
        Fromage autreFromage = new Fromage("Camembert");
        Article differentArticle = new Article(autreFromage, "a1", 5F);
        assertFalse(article.equals(differentArticle));
        
        Article differentArticleWithDifferentKey = new Article(fromage, "a2", 5F);
        assertFalse(article.equals(differentArticleWithDifferentKey));
    }
}
