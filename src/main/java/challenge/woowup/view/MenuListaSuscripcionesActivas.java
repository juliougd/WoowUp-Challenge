package challenge.woowup.view;

import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Tema;

public class MenuListaSuscripcionesActivas {
    public static int presentar(List<Tema> temasSuscriptos, Scanner input) {
        UtilView.clearConsole();
        int seleccionado;
        System.out.println(
                "Menu principal -> Seleccionar usuario -> Menu usuario -> Gestionar suscripciones -> * Suscripciones activas *");
        System.out.println("---------- 0 para volver al menu principal ---------------\n");

        for (int i = 0; i < temasSuscriptos.size(); i++) {
            System.out.println((i + 1) + " - " + temasSuscriptos.get(i).getNombre());
        }

        seleccionado = input.nextInt();

        if (seleccionado > 0 && seleccionado < temasSuscriptos.size() + 1) {
            return seleccionado;
        } else {
            return 0;
        }
    }
}
