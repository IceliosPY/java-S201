package modele;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestClient {
    private Client client;

    @Before
    public void setUp() {
        client = new Client("Doe", "John", "123 Main St", "Apt 4", "12345", "Springfield", "0123456789", "john.doe@example.com");
    }

    @Test
    public void testGetters() {
        assertEquals("Doe", client.getNom());
        assertEquals("John", client.getPrénom());
        assertEquals("123 Main St", client.getAdresse1());
        assertEquals("Apt 4", client.getAdresse2());
        assertEquals("12345", client.getCodePostal());
        assertEquals("Springfield", client.getVille());
        assertEquals("0123456789", client.getTéléphone());
        assertEquals("john.doe@example.com", client.getMail());
    }

    @Test
    public void testSetters() {
        client.setNom("Smith");
        client.setPrénom("Jane");
        client.setAdresse1("456 Elm St");
        client.setAdresse2("Suite 2");
        client.setCodePostal("67890");
        client.setVille("Shelbyville");
        client.setTéléphone("0987654321");
        client.setMail("jane.smith@example.com");

        assertEquals("Smith", client.getNom());
        assertEquals("Jane", client.getPrénom());
        assertEquals("456 Elm St", client.getAdresse1());
        assertEquals("Suite 2", client.getAdresse2());
        assertEquals("67890", client.getCodePostal());
        assertEquals("Shelbyville", client.getVille());
        assertEquals("0987654321", client.getTéléphone());
        assertEquals("jane.smith@example.com", client.getMail());
    }

    @Test
    public void testInformationsCompletes() {
        assertTrue(client.informationsCompletes());

        client.setMail("");
        assertFalse(client.informationsCompletes());

        client.setMail("jane.smith@example.com");
        client.setNom(null);
        assertFalse(client.informationsCompletes());

        client.setNom("Smith");
        client.setPrénom("");
        assertFalse(client.informationsCompletes());

        client.setPrénom("Jane");
        client.setAdresse1("");
        assertFalse(client.informationsCompletes());

        client.setAdresse1("456 Elm St");
        client.setCodePostal("");
        assertFalse(client.informationsCompletes());

        client.setCodePostal("67890");
        client.setVille("");
        assertFalse(client.informationsCompletes());

        client.setVille("Shelbyville");
        client.setTéléphone("");
        assertFalse(client.informationsCompletes());
    }
}
