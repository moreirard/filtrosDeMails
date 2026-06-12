package ar.edu.unahur.obj2.composite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltrosDeCorreoTest {

    @Test
    public void testUnCorreoConAdjuntosPasaElFiltroDeAdjuntos() {
        // Arrange
        Correo correo = new Correo(true); // true representa que tiene adjunto
        FiltroTieneAdjunto filtro = new FiltroTieneAdjunto();

        // Act & Assert
        assertTrue(filtro.cumple(correo));
    }
}