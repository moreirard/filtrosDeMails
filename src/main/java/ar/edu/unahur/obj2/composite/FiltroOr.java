package ar.edu.unahur.obj2.composite;

import java.util.Arrays;
import java.util.List;

public class FiltroOr implements Filtro {
    
    private List<Filtro> filtros;

    public FiltroOr(Filtro... filtros) {
        this.filtros = Arrays.asList(filtros);
    }

    /*
    // Nuevo constructor que recibe la interfaz List
    public FiltroOr(List<Filtro> filtros) {
        this.filtros = filtros;
    }
    */

    @Override
    public Boolean cumple(Correo correo) {
        // Con Streams de Java 8+:
        // return filtros.stream().anyMatch(filtro -> filtro.cumple(correo));
        
        // Con el bucle tradicional para analizar la lógica:
        for (Filtro filtro : filtros) {
            if (filtro.cumple(correo)) {
                return true; // Al primer filtro que cumple, el OR ya es verdadero
            }
        }
        return false; // Si recorrió todo y ninguno cumplió
    }
}