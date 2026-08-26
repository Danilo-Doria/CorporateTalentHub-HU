package com.riwi.talent.view;

import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("""
                =====================================
                    CORPORATE TALENT HUB
                =====================================
                1. Registrar empleado
                2. Mostrar todos los empleados
                3. Actualizar empleado
                4. Eliminar empleado
                5. Generar Reporte Consolidado
                0. Salir
                """);
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    public String solicitarId() {
        System.out.print("Ingrese el ID del empleado: ");
        return scanner.nextLine();
    }

    public String[] solicitarDatosEmpleado() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Salario: ");
        String salario = scanner.nextLine();

        return new String[]{id, nombre, edad, salario};
    }

    public String[] solicitarDatosEmpleadoActualizar() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Salario: ");
        String salario = scanner.nextLine();

        return new String[]{nombre, edad, salario};
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
