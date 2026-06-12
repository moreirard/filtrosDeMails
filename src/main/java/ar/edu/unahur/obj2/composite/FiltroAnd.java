package ar.edu.unahur.obj2.composite;

import java.util.Arrays;
import java.util.List;

public class FiltroAnd implements Filtro {
    
    private List<Filtro> filtros;

    // Usamos varargs para poder pasar 2, 3 o más filtros fácilmente
    public FiltroAnd(Filtro... filtros) {
        this.filtros = Arrays.asList(filtros);
    }

    @Override
    public Boolean cumple(Correo correo) {
        // Si usamos Java 8+, queda muy elegante con Streams:
        // return filtros.stream().allMatch(filtro -> filtro.cumple(correo));
        
        // O con un foreach tradicional para que se entienda la lógica paso a paso:
        for (Filtro filtro : filtros) {
            if (!filtro.cumple(correo)) {
                return false; // Al primer filtro que no cumple, el AND es falso
            }
        }
        return true;
    }
}