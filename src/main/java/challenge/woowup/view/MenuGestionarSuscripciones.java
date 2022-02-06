package challenge.woowup.view;

import java.util.Scanner;

import challenge.woowup.model.Usuario;

public class MenuGestionarSuscripciones {
    public static int presentar(Scanner input, Usuario usuarioActual) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println("Menu principal -> Seleccionar usuario -> Menu usuario -> * Gestionar suscripciones *");
        System.out.println("--- 0 para volver al menu principal ---");
        System.out.println("Usuario logueado:  " + usuarioActual.getNombre());
        System.out.println("1 - Suscribirme a un tema");
        System.out.println("2 - Eliminar una suscripcion");
        seleccionado = input.nextInt();
        return seleccionado;
    }

}
