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

    @Test
    public void testFiltroAndCumpleSoloCuandoTodosSusFiltrosCumplen() {
        // Arrange
        Correo correo = new Correo("Envío de factura importante", true); 
        
        Filtro filtroAdjunto = new FiltroTieneAdjunto();
        Filtro filtroAsunto = new FiltroPorAsunto("factura");
        
        // Pasamos los filtros simples a nuestro nuevo filtro compuesto
        FiltroAnd filtroAnd = new FiltroAnd(filtroAdjunto, filtroAsunto);

        // Act & Assert
        assertTrue(filtroAnd.cumple(correo));
    }

    @Test
    public void testFiltroAndNoCumpleSiFallaAlMenosUnFiltro() {
        // Arrange
        Correo correoSinAdjunto = new Correo("Envío de factura importante", false); 
        Correo correoSinAsunto = new Correo("Saludos cordiales", true);
        
        Filtro filtroAdjunto = new FiltroTieneAdjunto();
        Filtro filtroAsunto = new FiltroPorAsunto("factura");
        
        FiltroAnd filtroAnd = new FiltroAnd(filtroAdjunto, filtroAsunto);

        // Act & Assert
        assertFalse(filtroAnd.cumple(correoSinAdjunto), "Debe fallar porque no tiene adjunto");
        assertFalse(filtroAnd.cumple(correoSinAsunto), "Debe fallar porque el asunto no coincide");
    }
}