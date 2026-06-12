package ar.edu.unahur.obj2.composite;

public class Correo {
    private Boolean tieneAdjuntos;
    private String asunto;

    // Usamos un constructor simple por ahora
    public Correo(Boolean tieneAdjuntos) {
        this.tieneAdjuntos = tieneAdjuntos;
    }

    public Correo(String asunto, Boolean tieneAdjuntos) {
        this.asunto = asunto;
        this.tieneAdjuntos = tieneAdjuntos;
    }

    public Boolean tieneAdjuntos() {
        return this.tieneAdjuntos;
    }

    public String getAsunto() {
       return this.asunto;
    }
}