package challenge.woowup.view;

import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Usuario;

public class MenuListaUsuarios {

    public static int presentar(List<Usuario> todosLosUsuarios, Scanner input) {
        UtilView.clearConsole();
        int seleccionado;
        System.out.println(
                "Menu principal -> Gestionar alertas -> Seleccionar tipo de alerta -> * Seleccionar un usuario *");
        System.out.println("--------- 0 para volver al menu principal ----------------\n");

        for (int i = 0; i < todosLosUsuarios.size(); i++) {
            System.out.println((i + 1) + " - " + todosLosUsuarios.get(i).getNombre());
        }

        seleccionado = input.nextInt();

        if (seleccionado > 0 && seleccionado < todosLosUsuarios.size() + 1) {
            return seleccionado;
        } else {
            return 0;
        }
    }
}
