package challenge.woowup.view;

import java.util.Scanner;

public class MenuUsuario {
    public static int presentar(Scanner input) {
        UtilView.clearConsole();
        int seleccionado;

        System.out.println("Menu principal -> Seleccionar usuario -> * Menu de usuario *");
        System.out.println("-------- 0 para volver al menu principal -----------------\n");
        System.out.println("1 - Gestionar mis suscripciones");
        System.out.println("2 - Listar todas mis alertas no leidas");
        seleccionado = input.nextInt();

        return seleccionado;
    }

}
