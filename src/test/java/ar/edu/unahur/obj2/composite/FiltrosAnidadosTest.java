package ar.edu.unahur.obj2.composite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FiltrosAnidadosTest {

    @Test
    public void testFiltroAnidadoComplejoAceptaCorreosValidos() {
        // Arrange
        // Regla: NO es "Spam" AND (Tiene Adjunto OR (Tamaño >= 500 AND Asunto es "urgente"))
        Filtro filtroComplejo = new FiltroAnd(
            new FiltroNot(new FiltroPorAsunto("Spam")),
            new FiltroOr(
                new FiltroTieneAdjunto(),
                new FiltroAnd(
                    new FiltroPorTamanio(500),
                    new FiltroPorAsunto("urgente")
                )
            )
        );

        // Escenario A: Pasa por la rama derecha del OR (sin adjunto, pero grande y urgente)
        Correo correoPesadoYUrgente = new Correo("Reporte urgente de métricas", false, 600);
        
        // Escenario B: Pasa por la rama izquierda del OR (tiene adjunto, no es grande ni urgente)
        Correo correoConAdjunto = new Correo("Fotos de la oficina", true, 150);

        // Act & Assert
        assertTrue(filtroComplejo.cumple(correoPesadoYUrgente), 
            "Debe pasar porque cumple la rama del tamaño y asunto urgente");
        
        assertTrue(filtroComplejo.cumple(correoConAdjunto), 
            "Debe pasar porque cumple la rama del adjunto");
    }

    @Test
    public void testFiltroAnidadoComplejoRechazaCorreosInvalidos() {
        // Arrange
        // Misma estructura anidada
        Filtro filtroComplejo = new FiltroAnd(
            new FiltroNot(new FiltroPorAsunto("Spam")),
            new FiltroOr(
                new FiltroTieneAdjunto(),
                new FiltroAnd(
                    new FiltroPorTamanio(500),
                    new FiltroPorAsunto("urgente")
                )
            )
        );

        // Escenario C: Es bloqueado por la regla principal (es Spam, aunque tenga adjunto)
        Correo correoSpam = new Correo("Spam: Ganaste un viaje", true, 100);
        
        // Escenario D: No es Spam, pero no tiene adjunto ni es urgente (falla el bloque OR completo)
        Correo correoNormal = new Correo("Saludos cordiales", false, 300);

        // Act & Assert
        assertFalse(filtroComplejo.cumple(correoSpam), 
            "Debe fallar porque el filtro NOT principal bloquea el asunto Spam");
        
        assertFalse(filtroComplejo.cumple(correoNormal), 
            "Debe fallar porque no cumple ninguna de las condiciones internas del OR");
    }
}