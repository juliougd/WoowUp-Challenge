package challenge.woowup.view;

import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Alerta;

public class MenuListaAlertas {
    public static int presentar(List<Alerta> alertas, Scanner input) {
        UtilView.clearConsole();
        int seleccionado;
        System.out.println("Menu principal -> Seleccionar usuario -> Menu usuario -> * Seleccionar alerta *");
        System.out.println("-----------ingrese 0 para volver al menu principal -------------\n");

        System.out.println("Seleccione una alerta de la lista para marcarla como leida");

        for (int i = 0; i < alertas.size(); i++) {
            System.out.println((i + 1) + " - " + alertas.get(i).getNombre());
        }
        seleccionado = input.nextInt();

        if (seleccionado > 0 && seleccionado < alertas.size() + 1) {
            return seleccionado;
        } else {
            return 0;
        }
    }
}
