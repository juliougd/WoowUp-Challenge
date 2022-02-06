package challenge.woowup.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Alerta;
import challenge.woowup.model.Tema;
import challenge.woowup.model.Usuario;
import challenge.woowup.view.MenuGestionarAlertas;
import challenge.woowup.view.MenuGestionarSuscripciones;
import challenge.woowup.view.MenuListaAlertas;
import challenge.woowup.view.MenuListaDeTemas;
import challenge.woowup.view.MenuListaSuscripcionesActivas;
import challenge.woowup.view.MenuListaSuscripcionesInactivas;
import challenge.woowup.view.MenuListaUsuarios;
import challenge.woowup.view.MenuPrincipal;
import challenge.woowup.view.MenuSeleccionarUsuarioRegistrado;
import challenge.woowup.view.MenuTipoAlerta;
import challenge.woowup.view.MenuUsuario;
import challenge.woowup.view.UtilView;

public class ControladorVista {
    ControladorModelo controladorModelo;
    Scanner input = new Scanner(System.in);

    public ControladorVista(ControladorModelo controladorModelo) {
        this.controladorModelo = controladorModelo;

    }

    public void presentarMenuPrincipal() {
        UtilView.clearConsole();
        System.out.println("Presiona Enter para continuar");
        input.nextLine();
        int cantidadUsuarios = controladorModelo.getTodosLosUsuarios().size();
        int cantidadTemas = controladorModelo.getTodosLosTemas().size();
        int cantidadAlertas = controladorModelo.getTodasLasAlertas().size();

        int opcionSeleccionada = MenuPrincipal.presentar(this.input, cantidadUsuarios, cantidadTemas, cantidadAlertas);
        switch (opcionSeleccionada) {
            case 1:
                String nombreNuevoUsuario = this.solicitarNombrePorConsola("para el nuevo usuario");
                this.controladorModelo.registrarNuevoUsuario(nombreNuevoUsuario);
                presentarMenuPrincipal();
                break;
            case 2:
                String nombreNuevoTema = this.solicitarNombrePorConsola("para el nuevo tema");
                this.controladorModelo.registrarNuevoTema(nombreNuevoTema);
                presentarMenuPrincipal();
                break;
            case 3:
                this.seleccionarUsuarioRegistrado();
                presentarMenuPrincipal();
                break;
            case 4:
                this.gestionarAlertas();
                presentarMenuPrincipal();
                break;
            default:
                System.out.println("valor Incorrecto intente nuevamente");
                presentarMenuPrincipal();
                break;
        }
    }

    /**
     * 
     * @param mensajePersonalizado String que representa el mensaje personalizado
     *                             que se muestra al usuario durante la captura por
     *                             consola
     * @return String ingresado por el usuario
     */
    private String solicitarNombrePorConsola(String mensajePersonalizado) {
        UtilView.clearConsole();
        System.out.println("Ingrese el nombre " + mensajePersonalizado + " o presione 'Enter' para salir <-");
        input.nextLine();
        String nombreNuevoUsuario = input.nextLine();
        return nombreNuevoUsuario;
    }

    private void gestionarAlertas() {
        int opcionSeleccionada = MenuGestionarAlertas.presentar(this.input);
        switch (opcionSeleccionada) {
            case 1:
                this.menuTipoAlerta();
                break;
            case 2:
                this.listarAlertasNoExpiradasParaUnTema();
                break;
            default:
                break;
        }
    }

    private void listarAlertasNoExpiradasParaUnTema() {
        List<Tema> todosLosTemas = this.controladorModelo.getTodosLosTemas();
        int indexTemaSeleccionado = MenuListaDeTemas.presentar(todosLosTemas, this.input);

        // si existe al menos 1 tema registrado en el sistema
        if (indexTemaSeleccionado != 0) {
            List<Alerta> alertasNoExpiradas = this.controladorModelo
                    .getAlertasNoExpiradasDeUnTema(indexTemaSeleccionado);
            // si existe al menos 1 alerta no expirada
            if (alertasNoExpiradas.size() > 0) {
                this.imprimirAlertasNoExpiradasOrdenadas(alertasNoExpiradas);
            }
        }

    }

    private void imprimirAlertasNoExpiradasOrdenadas(List<Alerta> alertasNoExpiradas) {
        String nombreTema = alertasNoExpiradas.get(0).getUnTema().getNombre();
        UtilView.clearConsole();
        // se ordenan las alertas de la mas reciente a la mas antigua
        Collections.sort(alertasNoExpiradas);
        System.out.println("Estas son todas las alertas sobre " + nombreTema
                + "no expiradas y ordenadas de: Mas reciente a mas antigua");
        System.out.println();
        for (Alerta alerta : alertasNoExpiradas) {
            System.out.println(alerta.getPresentacion());
        }
        input.next();// console Pause
    }

    private void menuTipoAlerta() {
        int opcionSeleccionada = MenuTipoAlerta.presentar(this.input);
        switch (opcionSeleccionada) {
            case 1:
                this.menuListaTemasParaAlerta();
                break;
            case 2:
                this.menuTiempoExpiracion();
                break;
            case 3:
                this.menuListaUsuarios();
                break;
            default:
                break;
        }
    }

    private void menuListaUsuarios() {
        List<Usuario> todosLosUsuarios = this.controladorModelo.getTodosLosUsuarios();
        // si existen usuarios registrados en el sistema
        if (todosLosUsuarios.size() > 0) {
            int indexUsuarioSeleccionado = MenuListaUsuarios.presentar(todosLosUsuarios, this.input);
            if (indexUsuarioSeleccionado != 0) {
                this.enviarAlertaEspecifica(indexUsuarioSeleccionado);
            }
        }

    }

    private void enviarAlertaEspecifica(int indexUsuarioSeleccionado) {
        List<Tema> todosLosTemas = this.controladorModelo.getTodosLosTemas();
        // si existen temas registrados en el sistema
        if (todosLosTemas.size() > 0) {
            int indexTemaSeleccionado = MenuListaDeTemas.presentar(todosLosTemas, this.input);
            if (indexTemaSeleccionado != 0) {
                String nombreNuevaAlerta = this.solicitarNombrePorConsola("para la nueva alerta especifica");
                this.controladorModelo.enviarNuevaAlertaEspecifica(indexUsuarioSeleccionado, indexTemaSeleccionado,
                        nombreNuevaAlerta);
            }

        }

    }

    private void menuTiempoExpiracion() {

        try {
            UtilView.clearConsole();
            System.out.println("Ingrese la duracion en minutos para la alerta");
            System.out.println();
            int minutosDeDuracionDeLaAlerta = input.nextInt();
            this.crearNuevaAlertaInformativa(minutosDeDuracionDeLaAlerta);
        } catch (Exception e) {
            System.out.println("A ocurrido un error: " + e.getMessage());
            input.nextLine();
            input.nextLine();
        }
    }

    /**
     * 
     * @param calendar : Una instancia de la clase Calendar
     * @param minutos  : valor entero que representa el numero de minutos a
     *                 incrementar
     * @return Una fecha de expiracion futura basado en el calculo de [ fecha actual
     *         + minutos ]
     */
    private Date crearFechaExpiracion(int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutos);
        Date fechaExpiracion = calendar.getTime();
        return fechaExpiracion;
    }

    private void crearNuevaAlertaInformativa(int minutosDeDuracion) {
        Date fechaExpiracion = this.crearFechaExpiracion(minutosDeDuracion);

        List<Tema> todosLosTemas = this.controladorModelo.getTodosLosTemas();
        // si existen temas registrados en el sistema
        if (todosLosTemas.size() > 0) {
            int opcionSeleccionada = MenuListaDeTemas.presentar(todosLosTemas, this.input);
            if (opcionSeleccionada != 0) {
                String nombreNuevaAlerta = this.solicitarNombrePorConsola("para la nueva alerta informativa");
                this.controladorModelo.enviarNuevaAlertaInformativa(fechaExpiracion, opcionSeleccionada,
                        nombreNuevaAlerta);
            }

        }

    }

    private void menuListaTemasParaAlerta() {
        List<Tema> todosLosTemas = this.controladorModelo.getTodosLosTemas();
        // si existen temas registrados en el sistema
        if (todosLosTemas.size() > 0) {
            int opcionSeleccionada = MenuListaDeTemas.presentar(todosLosTemas, this.input);
            if (opcionSeleccionada != 0) {
                String nombreNuevaAlerta = this.solicitarNombrePorConsola("para la nueva alerta urgente");
                this.controladorModelo.enviarNuevaAlertaUrgente(opcionSeleccionada, nombreNuevaAlerta);
            }
        }
    }

    private void seleccionarUsuarioRegistrado() {
        int opcionSeleccionada = MenuSeleccionarUsuarioRegistrado
                .presentar(this.controladorModelo.getTodosLosUsuarios(), this.input);
        if (opcionSeleccionada != 0) {
            controladorModelo.setUsuarioActual(opcionSeleccionada);
            this.switchMenuUsuario();
        }

    }

    private void switchMenuUsuario() {
        int opcionSeleccionada = MenuUsuario.presentar(this.input);
        switch (opcionSeleccionada) {
            case 1:
                this.switchGestionarMisSuscripciones();
                break;
            case 2:
                this.listarTodasMisAlertasNoLeidas();
                break;
            default:
                break;
        }
    }

    private void listarTodasMisAlertasNoLeidas() {
        List<Alerta> alertasNoLeidas = this.controladorModelo.getAlertasNoLeidasDelUsuarioActual();
        // si existe al menos una alerta no leida
        if (alertasNoLeidas.size() > 0) {
            int opcionSeleccionada = MenuListaAlertas.presentar(alertasNoLeidas, this.input);
            if (opcionSeleccionada != 0) {
                this.controladorModelo.marcarAlertaComoLeida(opcionSeleccionada);
            }
        }
    }

    private void switchGestionarMisSuscripciones() {
        Usuario usuarioActual = controladorModelo.getUsuarioActual();
        int opcionSeleccionada = MenuGestionarSuscripciones.presentar(this.input, usuarioActual);
        switch (opcionSeleccionada) {
            case 1:
                this.suscribirATema();
                break;
            case 2:
                this.eliminarSuscripcion();
                break;
            default:
                this.presentarMenuPrincipal();
                break;
        }
    }

    private void eliminarSuscripcion() {
        List<Tema> temasSuscriptos = this.controladorModelo.getSuscripcionesDelUsuarioActual();
        // si el usuario esta suscripto a al menos un tema
        if (temasSuscriptos.size() > 0) {
            int opcionSeleccionada = MenuListaSuscripcionesActivas.presentar(temasSuscriptos, this.input);
            if (opcionSeleccionada != 0) {
                this.controladorModelo.eliminarSuscripcionDeUsuarioActual(opcionSeleccionada);
            }
        }
    }

    private void suscribirATema() {
        List<Tema> temasNoSuscriptos = this.controladorModelo.getTemasNoSuscriptosDelUsuarioActual();
        // si existe al menos un tema en el sistema al que el usuario actual no este
        // suscripto
        if (temasNoSuscriptos.size() > 0) {
            int opcionSeleccionada = MenuListaSuscripcionesInactivas.presentar(temasNoSuscriptos, this.input);
            if (opcionSeleccionada != 0) {
                this.controladorModelo.suscribirUsuarioActualAUnTema(opcionSeleccionada);
            }

        }

    }

}
