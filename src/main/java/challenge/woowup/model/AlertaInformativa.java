package challenge.woowup.model;

import java.util.Date;
import java.util.List;

public class AlertaInformativa extends Alerta {

    private Date fechaYHoraExpiracionDate;

    public AlertaInformativa(Date fechaYHoraCreacion, Tema unTema, String nombre, Date fechaYHoraExpiracionDate) {
        super(fechaYHoraCreacion, unTema, nombre);
        this.fechaYHoraExpiracionDate = fechaYHoraExpiracionDate;
    }

    public boolean estaExpirada() {
        return this.fechaYHoraExpiracionDate.before(new Date());
    }

    @Override
    public void enviar(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            List<Tema> suscripcionesDelUsuario = usuario.getPerfil().getGestorAlertas().getSuscripciones();
            if (suscripcionesDelUsuario.contains(this.unTema)) {
                usuario.getPerfil().getGestorAlertas().setNuevaAlerta(this);
            }
        }
    }

    @Override
    public String getPresentacion() {
        String presentacion = " * - " + this.nombre + " | Usuario objetivo: suscriptos a " + this.unTema.getNombre();
        return presentacion;
    }

}
