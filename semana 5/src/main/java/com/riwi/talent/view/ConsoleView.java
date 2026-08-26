package com.riwi.talent.view;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuYLeerOpcion() {
        System.out.println("""
                =====================================
                    CORPORATE TALENT HUB (MVC)
                =====================================
                1. Registrar empleado (DB)
                2. Mostrar todos los empleados (DB)
                3. Generar Reporte Consolidado (Records)
                0. Salir
                """);
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
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

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}