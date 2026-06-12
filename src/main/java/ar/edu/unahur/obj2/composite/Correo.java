package ar.edu.unahur.obj2.composite;

public class Correo {
    private boolean tieneAdjuntos;

    // Usamos un constructor simple por ahora
    public Correo(Boolean tieneAdjuntos) {
        this.tieneAdjuntos = tieneAdjuntos;
    }

    public Boolean tieneAdjuntos() {
        return this.tieneAdjuntos;
    }
}