package challenge.woowup.model;

import java.util.Date;
import java.util.List;

public abstract class Alerta implements Comparable<Alerta> {
    private Date fechaYHoraCreacionDate;
    protected Tema unTema;
    protected String nombre;

    public Alerta(Date fechaYHoraCreacion, Tema unTema, String nombre) {
        this.fechaYHoraCreacionDate = fechaYHoraCreacion;
        this.unTema = unTema;
        this.nombre = nombre;
    }

    public Date getFechaYHoraCreacion() {
        return fechaYHoraCreacionDate;
    }

    public Tema getUnTema() {
        return unTema;
    }

    public void setFechaYHoraCreacion(Date fechaYHoraCreacion) {
        this.fechaYHoraCreacionDate = fechaYHoraCreacion;
    }

    public void setUnTema(Tema unTema) {
        this.unTema = unTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaYHoraCreacionDate() {
        return fechaYHoraCreacionDate;
    }

    public void setFechaYHoraCreacionDate(Date fechaYHoraCreacionDate) {
        this.fechaYHoraCreacionDate = fechaYHoraCreacionDate;
    }

    /**
     * 
     * @param usuarios : una lista de todos los usuarios que estan registrados en el
     *                 sistema
     * @category Urgente : La alerta se envia a todos los usuarios sin importar si
     *           estan suscriptos al tema
     * @category Informativa : La alerta se envia a todos los usuarios suscriptos al
     *           tema
     * @category Especifica : La alerta se envia solo al usuario especifico sin
     *           importar si esta suscripto al tema
     */
    public abstract void enviar(List<Usuario> usuarios);

    public abstract String getPresentacion();

    public abstract boolean estaExpirada();

    @Override
    public int compareTo(Alerta o) {
        if (o.getFechaYHoraCreacion().before(this.fechaYHoraCreacionDate)) {
            return -1;
        } else {
            return 1;
        }
    }

}
