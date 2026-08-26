package com.riwi.talent;

import com.riwi.talent.controller.EmpleadoController;
import com.riwi.talent.model.EmpleadoDAOImpl;
import com.riwi.talent.view.ConsoleView;

public class App {

    public static void main(String[] args) {
        // 1. Instanciar la vista
        ConsoleView view = new ConsoleView();

        // 2. Instanciar el modelo (DAO)
        EmpleadoDAOImpl dao = new EmpleadoDAOImpl();

        // 3. Ensamblar el controlador
        EmpleadoController controller = new EmpleadoController(view, dao);

        // 4. Iniciar la app
        controller.iniciarAplicacion();
    }
}
