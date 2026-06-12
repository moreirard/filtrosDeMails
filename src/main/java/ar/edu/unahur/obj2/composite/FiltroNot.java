package ar.edu.unahur.obj2.composite;

public class FiltroNot implements Filtro {
    private Filtro filtroDelegado;

    public FiltroNot(Filtro filtroDelegado) {
        this.filtroDelegado = filtroDelegado;
    }

    @Override
    public Boolean cumple(Correo correo) {
        // Simplemente delegamos y negamos el resultado lógico
        return !this.filtroDelegado.cumple(correo);
    }
}