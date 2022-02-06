package challenge.woowup.view;

import java.util.List;
import java.util.Scanner;

import challenge.woowup.model.Usuario;

public class MenuSeleccionarUsuarioRegistrado {

    public static int presentar(List<Usuario> usuarios, Scanner input) {
        if (usuarios.size() > 0) {
            UtilView.clearConsole();
            int seleccionado;
            System.out.println("Menu principal -> * Seleccionar usuario *");
            System.out.println("--------- 0 para volver al menu principal ----------------\n");

            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + " - " + usuarios.get(i).getNombre());
            }
            seleccionado = input.nextInt();

            return seleccionado;

        }
        return 0;
    }

}
