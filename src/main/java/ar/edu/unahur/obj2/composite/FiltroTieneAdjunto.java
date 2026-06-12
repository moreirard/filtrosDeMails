package ar.edu.unahur.obj2.composite;

public class FiltroTieneAdjunto implements Filtro{

    @Override
    public Boolean cumple(Correo correo) {
        return correo.tieneAdjuntos();
     
    }

}
