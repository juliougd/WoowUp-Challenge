package challenge.woowup.view;

import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Tema;

public class MenuListaDeTemas {
    public static int presentar(List<Tema> todosLosTemas, Scanner input) {
        if (todosLosTemas.size() > 0) {
            UtilView.clearConsole();
            int seleccionado;
            System.out.println("Menu principal -> Gestionar alertas -> * Seleccionar un tema *");
            System.out.println("----------- 0 para volver al menu principal --------------\n");

            for (int i = 0; i < todosLosTemas.size(); i++) {
                System.out.println((i + 1) + " - " + todosLosTemas.get(i).getNombre());
            }

            seleccionado = input.nextInt();

            if (seleccionado > 0 && seleccionado < todosLosTemas.size() + 1) {
                return seleccionado;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
