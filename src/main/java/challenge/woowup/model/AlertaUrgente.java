package challenge.woowup.model;

import java.util.Date;
import java.util.List;

public class AlertaUrgente extends Alerta {

    public AlertaUrgente(Date fechaYHoraCreacion, Tema unTema, String nombre) {
        super(fechaYHoraCreacion, unTema, nombre);
    }

    @Override
    public void enviar(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            usuario.getPerfil().getGestorAlertas().setNuevaAlerta(this);
        }
    }

    @Override
    public String getPresentacion() {
        String presentacion = " * - " + this.nombre + " | Usuario objetivo: Todos";
        return presentacion;
    }

    @Override
    public boolean estaExpirada() {
        return false;
    }

}
