package challenge.woowup.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import challenge.woowup.model.Alerta;
import challenge.woowup.model.AlertaEspecifica;
import challenge.woowup.model.AlertaInformativa;
import challenge.woowup.model.AlertaUrgente;
import challenge.woowup.model.GestorDeAlertas;
import challenge.woowup.model.Perfil;
import challenge.woowup.model.Tema;
import challenge.woowup.model.Usuario;
import challenge.woowup.view.UtilView;

public class ControladorModelo {

    Usuario usuarioActual;
    List<Usuario> todosLosUsuarios = new ArrayList<Usuario>();
    List<Tema> todosLosTemas = new ArrayList<Tema>();
    List<Alerta> todasLasAlertas = new ArrayList<Alerta>();

    public ControladorModelo() {

    }

    public List<Alerta> getTodasLasAlertas() {
        return todasLasAlertas;
    }

    public void setTodasLasAlertas(List<Alerta> todasLasAlertas) {
        this.todasLasAlertas = todasLasAlertas;
    }

    /**
     * 
     * @return usuario logueado actualmente en el sistema
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * 
     * @param indexUsuarioActual indica la posicion del usuario en la lista de
     *                           usuarios registrados
     */
    public void setUsuarioActual(int indexUsuarioActual) {
        this.usuarioActual = this.todosLosUsuarios.get(indexUsuarioActual - 1);
    }

    public List<Tema> getTodosLosTemas() {
        return todosLosTemas;
    }

    public void setTodosLosTemas(List<Tema> todosLosTemas) {
        this.todosLosTemas = todosLosTemas;
    }

    public List<Usuario> getTodosLosUsuarios() {
        return todosLosUsuarios;
    }

    public void setTodosLosUsuarios(List<Usuario> todosLosUsuarios) {
        this.todosLosUsuarios = todosLosUsuarios;
    }

    /**
     * 
     * @implNote Solicita un nombre por consola y realiza el Alta de un nuevo
     *           usuario con su perfil y su gestor de alertas.
     */
    public void registrarNuevoUsuario(String nombreNuevoUsuario) {

        if (nombreNuevoUsuario != "") {
            GestorDeAlertas gestorAlertas = new GestorDeAlertas();
            Perfil perfil = new Perfil(gestorAlertas);
            Usuario nuevoUsuario = new Usuario(nombreNuevoUsuario, perfil);
            todosLosUsuarios.add(nuevoUsuario);
        }

    }

    public void registrarNuevoTema(String nombreNuevoTema) {

        if (nombreNuevoTema != "") {
            Tema nuevoTema = new Tema(nombreNuevoTema);
            todosLosTemas.add(nuevoTema);
        }
    }

    /**
     * 
     * @return lista de temas a los que no está suscripto el usuario logueado
     *         actualmente
     */
    public List<Tema> getTemasNoSuscriptosDelUsuarioActual() {
        return this.usuarioActual.getPerfil().getGestorAlertas().getTemasNoSuscriptos(this.todosLosTemas);
    }

    /**
     * 
     * @param temaIndex indica la posicion del tema en la lista de
     *                  temas registrados en el sistema
     */
    public void suscribirUsuarioActualAUnTema(int temaIndex) {
        Tema temaSeleccionado = this.todosLosTemas.get(temaIndex - 1);
        this.usuarioActual.getPerfil().getGestorAlertas().setNuevaSuscripcion(temaSeleccionado);
    }

    /**
     * 
     * @return lista de temas a los que está suscripto el usuario logueado
     *         actualmente
     */
    public List<Tema> getSuscripcionesDelUsuarioActual() {
        return this.usuarioActual.getPerfil().getGestorAlertas().getSuscripciones();
    }

    /**
     * 
     * @param indexSuscripcion indica la posicion de la suscripcion del usuario
     *                         actual que se desea eliminar
     */
    public void eliminarSuscripcionDeUsuarioActual(int indexSuscripcion) {
        this.usuarioActual.getPerfil().getGestorAlertas().eliminarSuscripcion(indexSuscripcion - 1);
    }

    public List<Alerta> getAlertasNoLeidasDelUsuarioActual() {
        return this.usuarioActual.getPerfil().getGestorAlertas().getAlertasNoLeidas();
    }

    public void marcarAlertaComoLeida(int idexAlertaLeida) {
        this.usuarioActual.getPerfil().getGestorAlertas().marcarComoLeida(idexAlertaLeida - 1);
    }

    /**
     * 
     * @param indexTema         indica la posicion del tema en la lista de
     *                          temas registrados en el sistema
     * @param nombreNuevaAlerta
     */
    public void enviarNuevaAlertaUrgente(int indexTema, String nombreNuevaAlerta) {
        UtilView.clearConsole();
        // se obtienen los parametros de creacion de una alerta urgente
        Tema temaSeleccionado = this.todosLosTemas.get(indexTema - 1);
        Alerta nuevaAlertaUrgente = new AlertaUrgente(new Date(), temaSeleccionado, nombreNuevaAlerta);
        // se envia la alerta
        nuevaAlertaUrgente.enviar(this.todosLosUsuarios);
        // se almacena la nueva alerta en el sistema
        this.agregarNuevaAlertaAlSistema(nuevaAlertaUrgente);

    }

    private void agregarNuevaAlertaAlSistema(Alerta nuevaAlerta) {
        this.todasLasAlertas.add(nuevaAlerta);
    }

    public void enviarNuevaAlertaInformativa(Date fechaExpiracion, int indexTema, String nombreNuevaAlerta) {
        UtilView.clearConsole();
        // se obtienen los parametros de creacion de una alerta informativa
        Tema temaSeleccionado = this.todosLosTemas.get(indexTema - 1);
        Alerta nuevaAlertaInformativa = new AlertaInformativa(new Date(), temaSeleccionado, nombreNuevaAlerta,
                fechaExpiracion);
        // se envia la alerta
        nuevaAlertaInformativa.enviar(this.todosLosUsuarios);
        // se almacena la nueva alerta en el sistema
        agregarNuevaAlertaAlSistema(nuevaAlertaInformativa);

    }

    /**
     * 
     * @param indexUsuarioSeleccionado indica la posicion del usuario en la lista de
     *                                 usuarios registrados
     * @param indexTemaSeleccionado    indica la posicion del tema en la lista de
     *                                 temas registrados
     * @param nombreNuevaAlerta
     */
    public void enviarNuevaAlertaEspecifica(int indexUsuarioSeleccionado, int indexTemaSeleccionado,
            String nombreNuevaAlerta) {
        UtilView.clearConsole();
        // se obtienen los parametros de creacion de una alerta especifica
        Tema temaSeleccionado = this.todosLosTemas.get(indexTemaSeleccionado - 1);
        Usuario usuarioSeleccionado = this.todosLosUsuarios.get(indexUsuarioSeleccionado - 1);
        Alerta nuevaAlertaEspecifica = new AlertaEspecifica(new Date(), temaSeleccionado, nombreNuevaAlerta,
                usuarioSeleccionado);
        // se envia la alerta
        nuevaAlertaEspecifica.enviar(this.todosLosUsuarios);
        // se almacena la nueva alerta en el sistema
        agregarNuevaAlertaAlSistema(nuevaAlertaEspecifica);

    }

    /**
     * 
     * @param indexTemaSeleccionado posicion del tema seleccionado por el usuario en
     *                              la lista de temas
     * @return Lista de todas las alertas no expiradas para el tema seleccioado
     */
    public List<Alerta> getAlertasNoExpiradasDeUnTema(int indexTemaSeleccionado) {
        List<Alerta> alertasNoExpiradasDeUnTema = new ArrayList<Alerta>();
        List<Alerta> alertasDeUnTema = this.getAlertasDeUnTema(indexTemaSeleccionado - 1);
        for (Alerta alerta : alertasDeUnTema) {
            if (!alerta.estaExpirada()) {
                alertasNoExpiradasDeUnTema.add(alerta);
            }
        }
        return alertasNoExpiradasDeUnTema;
    }

    /**
     * 
     * @param indexTemaSeleccionado posicion del tema seleccionado por el usuario en
     *                              la lista de temas
     * @return Lista de todas las alertas para el tema seleccioado
     */
    private List<Alerta> getAlertasDeUnTema(int indexTemaSeleccionado) {
        List<Alerta> alertasDeUnTema = new ArrayList<>();
        Tema temaSeleccionado = this.todosLosTemas.get(indexTemaSeleccionado);
        for (Alerta alerta : this.todasLasAlertas) {
            if (alerta.getUnTema().getNombre() == temaSeleccionado.getNombre()) {
                alertasDeUnTema.add(alerta);
            }
        }
        return alertasDeUnTema;
    }

}
