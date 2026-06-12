package ar.edu.unahur.obj2.composite;

public class FiltroPorAsunto {

    private String palabraClave;

    public FiltroPorAsunto(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public Boolean cumple(Correo correo) {
        return correo.getAsunto().contains(palabraClave);
    }

}
