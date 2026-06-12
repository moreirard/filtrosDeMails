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

    @Test
    public void testUnCorreoConTamanioMayorOIgualAlRequeridoPasaElFiltro() {
        // Arrange
        // Parámetros: Asunto, Tiene Adjuntos, Tamaño en KB
        Correo correo = new Correo("Reporte mensual", false, 250);
        FiltroPorTamanio filtro = new FiltroPorTamanio(200); // Mínimo requerido: 200 KB

        // Act & Assert
        assertTrue(filtro.cumple(correo));
    }

    @Test
    public void testUnCorreoConTamanioMenorAlRequeridoNoPasaElFiltro() {
        // Arrange
        Correo correo = new Correo("Texto simple", false, 50);
        FiltroPorTamanio filtro = new FiltroPorTamanio(200);

        // Act & Assert
        assertFalse(filtro.cumple(correo));
    }

    @Test
    public void testEjemploDeUsoIntegradorDelEnunciado() {
        // Arrange
        // Correo del ejemplo: "Muy importante", 250 KB, con adjuntos
        Correo correoEjemplo = new Correo("Muy importante", true, 250);

        // Filtros simples
        Filtro filtroAdjuntos = new FiltroTieneAdjunto();
        Filtro filtroTamanio = new FiltroPorTamanio(230);
        Filtro filtroAsunto = new FiltroPorAsunto("importante");

        // Filtro compuesto: los agrupamos bajo un AND
        Filtro filtroConfigurado = new FiltroAnd(filtroAdjuntos, filtroTamanio, filtroAsunto);

        // Act & Assert
        // El correo debe pasar porque cumple las 3 condiciones
        assertTrue(filtroConfigurado.cumple(correoEjemplo),
                "El correo debería pasar el filtro compuesto del ejemplo");
    }

    @Test
    public void testFiltroNotInvierteElResultadoDelFiltroQueEnvuelve() {
        // Arrange
        // Correo con adjuntos
        Correo correo = new Correo("Documentación", true, 100); 
        Filtro filtroTieneAdjunto = new FiltroTieneAdjunto();
        
        // Lo envolvemos en un NOT
        Filtro filtroNoTieneAdjunto = new FiltroNot(filtroTieneAdjunto);

        // Act & Assert
        // Como el correo TIENE adjuntos, el filtro "TieneAdjunto" da true.
        // El NOT debe invertirlo a false.
        assertFalse(filtroNoTieneAdjunto.cumple(correo), 
            "El filtro NOT debe devolver false si el filtro interno devuelve true");
    }

    @Test
    public void testFiltroOrCumpleSiAlMenosUnFiltroCumple() {
        // Arrange
        // Correo: "Oferta", sin adjuntos, 50 KB
        Correo correo = new Correo("Oferta de capacitación", false, 50);
        
        Filtro filtroAdjunto = new FiltroTieneAdjunto(); // Va a dar false
        Filtro filtroAsunto = new FiltroPorAsunto("Oferta"); // Va a dar true
        
        FiltroOr filtroOr = new FiltroOr(filtroAdjunto, filtroAsunto);

        // Act & Assert
        assertTrue(filtroOr.cumple(correo), 
            "Debe pasar porque el asunto coincide, aunque no tenga adjuntos");
    }

    /*
    @Test
    public void testFiltroOrCumpleSiAlMenosUnFiltroCumple() {
        // Arrange
        Correo correo = new Correo("Oferta de capacitación", false, 50);
        
        Filtro filtroAdjunto = new FiltroTieneAdjunto(); // Va a dar false
        Filtro filtroAsunto = new FiltroPorAsunto("Oferta"); // Va a dar true
        
        // Agrupamos los filtros en una Lista
        List<Filtro> listaDeFiltros = List.of(filtroAdjunto, filtroAsunto); 
        
        // Pasamos la lista al constructor
        FiltroOr filtroOr = new FiltroOr(listaDeFiltros);

        // Act & Assert
        assertTrue(filtroOr.cumple(correo), 
            "Debe pasar porque el asunto coincide, aunque no tenga adjuntos");
    }
    */

    @Test
    public void testFiltroOrNoCumpleSiNingunFiltroCumple() {
        // Arrange
        Correo correo = new Correo("Saludos", false, 50);
        
        Filtro filtroAdjunto = new FiltroTieneAdjunto(); // Va a dar false
        Filtro filtroAsunto = new FiltroPorAsunto("Oferta"); // Va a dar false
        
        FiltroOr filtroOr = new FiltroOr(filtroAdjunto, filtroAsunto);

        // Act & Assert
        assertFalse(filtroOr.cumple(correo), 
            "Debe fallar porque ninguna de las condiciones se cumple");
    }
}