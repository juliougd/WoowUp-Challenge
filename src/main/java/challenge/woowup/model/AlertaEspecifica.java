package challenge.woowup.model;

import java.util.Date;
import java.util.List;

public class AlertaEspecifica extends Alerta {
    private Usuario usuario;

    public AlertaEspecifica(Date fechaYHoraCreacion, Tema unTema, String nombre, Usuario usuario) {
        super(fechaYHoraCreacion, unTema, nombre);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void enviar(List<Usuario> usuarios) {
        this.usuario.getPerfil().getGestorAlertas().setNuevaAlerta(this);
    }

    @Override
    public boolean estaExpirada() {
        return false;
    }

    @Override
    public String getPresentacion() {
        String presentacion = " * - " + this.nombre + " | Usuario Especifico: " + this.usuario.getNombre();
        return presentacion;
    }

}
