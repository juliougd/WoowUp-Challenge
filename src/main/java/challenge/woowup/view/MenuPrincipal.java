package challenge.woowup.view;

import java.util.Scanner;

public class MenuPrincipal {
    public static int presentar(Scanner input, int cantUsuarios, int cantTemas, int cantAlertas) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println("Menu principal");
        System.out.println("N° Usuarios: " + cantUsuarios + " | " + " N° Temas: " + cantTemas + " | "
                + " N° Alertas: " + cantAlertas);
        System.out.println("-------------------------\n");
        System.out.println("1 - Registrar nuevo usuario");
        System.out.println("2 - Registrar nuevo tema");
        if (cantUsuarios > 0) {
            System.out.println("3 - Ingresar con usuario registrado");
        }
        if (cantTemas > 0) {
            System.out.println("4 - Gestionar alertas");

        }

        seleccionado = input.nextInt();

        return seleccionado;
    }
}
