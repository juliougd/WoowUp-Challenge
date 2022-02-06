package challenge.woowup.view;

import java.util.Scanner;

public class MenuTipoAlerta {
    public static int presentar(Scanner input) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println("Menu principal -> Gestionar alertas -> * Seleccionar tipo de alerta *");
        System.out.println("-------- 0 para volver al menu principal -----------------\n");
        System.out.println("1 - Enviar nueva alerta urgente");
        System.out.println("2 - Enviar nueva alerta informativa");
        System.out.println("3 - Enviar nueva alerta especifica");

        seleccionado = input.nextInt();

        return seleccionado;
    }
}
