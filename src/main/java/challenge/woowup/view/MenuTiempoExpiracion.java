package challenge.woowup.view;

import java.util.Scanner;

public class MenuTiempoExpiracion {

    public static int presentar(Scanner input) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println(
                "Menu principal -> Gestionar alertas -> Seleccionar tipo de alerta -> * Seleccionar tiempo de expiracion *");
        System.out.println("--------- 0 para volver al menu principal ----------------\n");
        System.out.println("1 - Expira en 1 minuto");
        System.out.println("2 - Expira en 2 minutos");
        System.out.println("3 - Expira en 5 minutos");

        seleccionado = input.nextInt();

        return seleccionado;
    }
}
