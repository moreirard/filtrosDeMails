package ar.edu.unahur.obj2.composite;

public class FiltroPorAsunto implements Filtro{

    private String palabraClave;

    public FiltroPorAsunto(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    @Override
    public Boolean cumple(Correo correo) {
        return correo.getAsunto().contains(palabraClave);
    }

}
