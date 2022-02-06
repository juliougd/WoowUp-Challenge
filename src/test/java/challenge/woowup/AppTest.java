package challenge.woowup;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import challenge.woowup.controller.ControladorModelo;
import challenge.woowup.model.Alerta;
import challenge.woowup.model.Tema;

public class AppTest {
    ControladorModelo controlador = new ControladorModelo();
    String nombreTema1 = "Tema test 1";
    String nombreTema2 = "Tema test 2";
    String nombreUsuario1 = "Usuario Tester 1";
    String nombreUsuario2 = "Usuario Tester 2";

    @Before
    public void init() {
        this.controlador = new ControladorModelo();
        // Se establecen como recurso inicial para todas las pruebas del sistema:
        // 2 usuarios y 2 temas de ejemplo
        this.controlador.registrarNuevoUsuario(nombreUsuario1);
        this.controlador.registrarNuevoUsuario(nombreUsuario2);
        this.controlador.registrarNuevoTema(nombreTema1);
        this.controlador.registrarNuevoTema(nombreTema2);
    }

    @Test
    public void testCrearUsuario() {
        int usuariosAntesDeRegistrar = this.controlador.getTodosLosUsuarios().size();
        // paso 1: crea nuevo usuario
        this.controlador.registrarNuevoUsuario("Usuario Tester 3");
        int usuariosDespuesDeRegistrar = this.controlador.getTodosLosUsuarios().size();
        // paso 2: verifica que la cantidad de usuarios creados haya incrementado en una
        // unidad
        assertTrue(usuariosAntesDeRegistrar == (usuariosDespuesDeRegistrar - 1));
    }

    @Test
    public void testCrearTema() {
        // paso 1: verifica la cantidad de temas registrados en sistema al inicio
        int temasRegistradosAntesDeCrear = this.controlador.getTodosLosTemas().size();
        // paso 2: crea nuevo tema
        this.controlador.registrarNuevoTema("Tema test 3");
        int temasRegistradosDespuesDeCrear = this.controlador.getTodosLosTemas().size();
        // paso 3: verifica que la cantidad de temas creados haya incrementado en 1
        assertTrue(temasRegistradosAntesDeCrear == (temasRegistradosDespuesDeCrear - 1));
    }

    @Test
    public void testSuscribcion() {
        // paso 1: Hacer login con el usuario creado 1
        this.controlador.setUsuarioActual(1);
        // paso 2: Suscribir el usuario logueado al tema 1
        this.controlador.suscribirUsuarioActualAUnTema(1);
        // paso 3: Obtener el tema al que esta suscripto el usuario actual
        Tema suscripcionDelUsuarioActual = this.controlador.getSuscripcionesDelUsuarioActual().get(0);
        // paso 4: verificar que el el usuario este suscripto al tema 1
        assertTrue(suscripcionDelUsuarioActual.getNombre() == this.nombreTema1);
    }

    @Test
    public void testAlertaUrgente() {
        // Una alerta urgente debe enviarse a todos los usuarios registrados
        // indedpendientemente de a que temas esten suscriptos

        // paso 1 : loguear con el usuario 1
        this.controlador.setUsuarioActual(1);
        // paso 2 : suscribir al usuario actual al tema 2
        this.controlador.suscribirUsuarioActualAUnTema(2);
        // paso 3 : enviar una alerta urgente sobre el tema 2
        String nombreDeAlerta = "una alerta urgente";
        this.controlador.enviarNuevaAlertaUrgente(2, nombreDeAlerta);
        // paso 4 verificar que la alerta llego al usuario 1 que si esta suscripto al
        // tema 2
        Alerta alerta = this.controlador.getAlertasNoLeidasDelUsuarioActual().get(0);
        assertTrue(alerta.getNombre() == nombreDeAlerta);
        // paso 5 verificar que la alerta llego al usuario 2 aunque no este suscripto al
        // tema 2
        this.controlador.setUsuarioActual(2);
        alerta = this.controlador.getAlertasNoLeidasDelUsuarioActual().get(0);
        assertTrue(alerta.getNombre() == nombreDeAlerta);

    }

    @Test
    public void testAlertaInformativa() {
        // Una alerta informativa debe enviarse a todos los usuarios registrados
        // que esten suscriptos al tema de la alerta

        // paso 1 : loguear con el usuario 1
        this.controlador.setUsuarioActual(1);
        // paso 2 : suscribir al usuario actual al tema 2
        this.controlador.suscribirUsuarioActualAUnTema(2);
        // paso 3 : enviar una alerta informativa sobre el tema 2
        String nombreDeAlerta = "una alerta informativa";
        Date fechaExpiracion = this.crearFechaExpiracion(1);
        this.controlador.enviarNuevaAlertaInformativa(fechaExpiracion, 2, nombreDeAlerta);
        // paso 4 verificar que la alerta llego al usuario 1 ya que este está suscripto
        // al
        // tema 2
        Alerta alerta = this.controlador.getAlertasNoLeidasDelUsuarioActual().get(0);
        assertTrue(alerta.getNombre() == nombreDeAlerta);
        // paso 5 verificar que la alerta no llego al usuario 2 ya que este no está
        // suscripto al
        // tema 2
        this.controlador.setUsuarioActual(2);
        int cantidadDeAlertas = this.controlador.getAlertasNoLeidasDelUsuarioActual().size();
        assertTrue(cantidadDeAlertas == 0);
    }

    private Date crearFechaExpiracion(int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutos);
        Date fechaExpiracion = calendar.getTime();
        return fechaExpiracion;
    }

    @Test
    public void testAlertaEspecifica() {
        // Una alerta especifica debe llegar al usuario seleccionado idependientemente
        // de que este
        // este o no suscripto al tema de la alerta

        // paso 1 : enviar una alerta especifica al usuario 1 con el tema 1
        String nombreDeAlerta = "una alerta especifica";
        this.controlador.enviarNuevaAlertaEspecifica(1, 1, nombreDeAlerta);
        // paso 2 : verificar que la alerta llego al usuario 1
        this.controlador.setUsuarioActual(1);
        Alerta alerta = this.controlador.getAlertasNoLeidasDelUsuarioActual().get(0);
        assertTrue(alerta.getNombre() == nombreDeAlerta);
    }

    @Test
    public void testLeerUnaAlerta() {
        // paso 1 : enviar una alerta especifica al usuario 1 con el tema 1
        String nombreDeAlerta = "una alerta especifica";
        this.controlador.enviarNuevaAlertaEspecifica(1, 1, nombreDeAlerta);
        // paso 2 : verificar que el numero de alertas no leidas es 1
        this.controlador.setUsuarioActual(1);
        int cantAlertasNoLeidas = this.controlador.getAlertasNoLeidasDelUsuarioActual().size();
        assertTrue(cantAlertasNoLeidas == 1);
        // paso 3 : leer la alerta 1
        this.controlador.marcarAlertaComoLeida(1);
        // paso 4 verificar que el numero de alertas no leidas es 0
        cantAlertasNoLeidas = this.controlador.getAlertasNoLeidasDelUsuarioActual().size();
        assertTrue(cantAlertasNoLeidas == 0);
    }

    @Test
    public void testListarAlertasNoExpiradas() {
        // paso 1 : crear 3 alertas y una con fecha de expiracion como fecha actual - 1
        // minuto
        this.controlador.enviarNuevaAlertaUrgente(1, "alerta 1");
        this.controlador.enviarNuevaAlertaUrgente(1, "alerta 2");
        Date fechaExpiracion = this.crearFechaExpiracion(-1);
        this.controlador.enviarNuevaAlertaInformativa(fechaExpiracion, 1, "alerta 3");
        // paso 4 : obtener lista de alertas no expiradas y verificar que solo existen 2
        int cantAlertasNoExpiradas = this.controlador.getAlertasNoExpiradasDeUnTema(1).size();
        assertTrue(cantAlertasNoExpiradas == 2);
    }

}