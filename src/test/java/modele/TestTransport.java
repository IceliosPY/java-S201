package modele;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTransport {

    @Test
    public void testFraisColissimo() {
        Transport transport = new Transport(TypeTransporteur.COLISSIMO);

        assertEquals(14.90F, transport.calculerFraisDePort(50F), 0.0);
        assertEquals(9.90F, transport.calculerFraisDePort(70F), 0.0);
        assertEquals(4.90F, transport.calculerFraisDePort(100F), 0.0);
        assertEquals(0.0F, transport.calculerFraisDePort(150F), 0.0);
    }

    @Test
    public void testFraisChronorelais() {
        Transport transport = new Transport(TypeTransporteur.CHRONORELAIS);

        assertEquals(14.90F, transport.calculerFraisDePort(50F), 0.0);
        assertEquals(9.90F, transport.calculerFraisDePort(70F), 0.0);
        assertEquals(4.90F, transport.calculerFraisDePort(100F), 0.0);
        assertEquals(0.0F, transport.calculerFraisDePort(150F), 0.0);
    }

    @Test
    public void testFraisChronofresh() {
        Transport transport = new Transport(TypeTransporteur.CHRONOFRESH);

        assertEquals(23.80F, transport.calculerFraisDePort(40F), 0.0);
        assertEquals(17.80F, transport.calculerFraisDePort(70F), 0.0);
        assertEquals(9.90F, transport.calculerFraisDePort(100F), 0.0);
        assertEquals(0.0F, transport.calculerFraisDePort(150F), 0.0);
    }

}
