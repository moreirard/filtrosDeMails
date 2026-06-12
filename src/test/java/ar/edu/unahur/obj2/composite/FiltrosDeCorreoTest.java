package ar.edu.unahur.obj2.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void testUnCorreoSinAdjuntosNoPasaElFiltroDeAdjuntos() {
        // Arrange
        Correo correo = new Correo(false); // false indica que NO tiene adjuntos
        FiltroTieneAdjunto filtro = new FiltroTieneAdjunto();

        // Act & Assert
        assertFalse(filtro.cumple(correo));
    }

    @Test
    public void testUnCorreoCuyoAsuntoContieneLaPalabraRequeridaPasaElFiltro() {
        // Arrange
        Correo correo = new Correo("Reunión importante de consorcio", false);
        FiltroPorAsunto filtro = new FiltroPorAsunto("importante");

        // Act & Assert
        assertTrue(filtro.cumple(correo));
    }
}