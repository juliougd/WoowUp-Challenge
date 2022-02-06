package challenge.woowup.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorDeAlertas {
    private List<Tema> suscripciones = new ArrayList<Tema>();
    private List<Alerta> alertasLeidas = new ArrayList<Alerta>();
    private List<Alerta> alertasNoLeidas = new ArrayList<Alerta>();

    public GestorDeAlertas() {

    }

    public List<Alerta> getAlertasLeidas() {
        return alertasLeidas;
    }

    /**
     * @return coleccion de alertas no leidas del usuario que aun no expiraron.
     *         Ordenadas de la mas reciente a la mas antigua segun
     *         el metodo compareTo de la clase alerta
     */
    public List<Alerta> getAlertasNoLeidas() {
        List<Alerta> noLeidasNoExpiradas = new ArrayList<>();
        for (Alerta alerta : this.alertasNoLeidas) {
            if (!alerta.estaExpirada()) {
                noLeidasNoExpiradas.add(alerta);
            }
        }
        Collections.sort(noLeidasNoExpiradas);
        return noLeidasNoExpiradas;
    }

    public List<Tema> getSuscripciones() {
        return suscripciones;
    }

    public void setAlertasLeidas(List<Alerta> alertasLeidas) {
        this.alertasLeidas = alertasLeidas;
    }

    public void setAlertasNoLeidas(List<Alerta> alertasNoLeidas) {
        this.alertasNoLeidas = alertasNoLeidas;
    }

    public void setSuscripciones(List<Tema> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public void setNuevaAlerta(Alerta nuevaAlerta) {
        this.alertasNoLeidas.add(nuevaAlerta);
    }

    public void setNuevaSuscripcion(Tema nuevoTema) {
        this.suscripciones.add(nuevoTema);
    }

    /**
     * 
     * @param todosLosTemas Todos los temas registrado actualmente en el sistema
     * @return sub-conjunto de temas a los que el usuario no esta
     *         suscripto
     */
    public List<Tema> getTemasNoSuscriptos(List<Tema> todosLosTemas) {
        ArrayList<Tema> temasNoSuscriptos = new ArrayList<>();

        for (Tema tema : todosLosTemas) {
            if (!this.suscripciones.contains(tema)) {
                temasNoSuscriptos.add(tema);
            }
        }
        return temasNoSuscriptos;
    }

    public void eliminarSuscripcion(int indexSuscripcion) {
        this.suscripciones.remove(indexSuscripcion);
    }

    /**
     * 
     * @param idexAlertaLeida posicion del la alerta leida
     */
    public void marcarComoLeida(int idexAlertaLeida) {
        this.alertasLeidas.add(this.alertasNoLeidas.get(idexAlertaLeida));
        this.alertasNoLeidas.remove(idexAlertaLeida);

    }

}
