package ar.edu.unahur.obj2.composite;

public class FiltroPorTamanio implements Filtro {
    private int tamanioMinimoKB;

    public FiltroPorTamanio(int tamanioMinimoKB) {
        this.tamanioMinimoKB = tamanioMinimoKB;
    }

    @Override
    public Boolean cumple(Correo correo) {
        return correo.getTamanioKB() >= this.tamanioMinimoKB;
    }
}