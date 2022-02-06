package challenge.woowup.view;

import java.util.Scanner;

public class MenuGestionarAlertas {
    public static int presentar(Scanner input) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println("Menu principal -> * Gestionar alertas *");
        System.out.println("------------0 para volver al menu principal-------------\n");
        System.out.println("1 - Enviar nueva Alerta");
        System.out.println("2 - Listar alertas no expiradas para un tema");

        seleccionado = input.nextInt();

        return seleccionado;
    }
}
