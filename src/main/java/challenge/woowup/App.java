package challenge.woowup;

import challenge.woowup.controller.ControladorModelo;
import challenge.woowup.controller.ControladorVista;

public class App {
    public static void main(String[] args) {

        ControladorModelo controladorModelo = new ControladorModelo();
        ControladorVista controladorVista = new ControladorVista(controladorModelo);
        controladorVista.presentarMenuPrincipal();
    }
}
