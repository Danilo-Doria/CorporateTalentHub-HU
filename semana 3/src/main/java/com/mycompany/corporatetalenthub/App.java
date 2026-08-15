package com.mycompany.corporatetalenthub;

import com.mycompany.corporatetalenthub.modelo.Empleado;
import com.mycompany.corporatetalenthub.modelo.EmpresaRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static final int MAXIMO_EMPLEADOS = 50;
    private static final int CANTIDAD_TRIMESTRES = 3;
    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 100.0;
    private static final double PROMEDIO_PARA_PROMOCION = 80.0;

    /*
     * =====================================================================================
     * EXPLICACIÓN FACTORY METHODS (Java 9/11): List.of() y Map.of()
     * =====================================================================================
     * Estas colecciones son más seguras que un ArrayList o HashMap tradicional para
     * almacenar catálogos o datos fijos porque son estrictamente INMUTABLES. 
     * Una vez que se inician, no permiten que su contenido se altere. 
     * Si un programador intenta usar métodos como TECNOLOGIAS.add() o SEDES.put(),
     * Java lanzará una excepción (UnsupportedOperationException) bloqueando la acción.
     * Esto protege los datos de referencia contra modificaciones accidentales en tiempo
     * de ejecución, garantizando su integridad en todo el ciclo de vida de la aplicación.
     * =====================================================================================
     */
    private static final List<String> TECNOLOGIAS = List.of(
            "Java", "Spring Boot", "React", "Angular", "PostgreSQL", "MongoDB"
    );

    private static final Map<String, String> SEDES = Map.of(
            "BOG", "Bogotá - Sede Central",
            "MED", "Medellín - Centro de Innovación",
            "CAL", "Cali - Hub de Desarrollo",
            "BAQ", "Barranquilla - Sede Caribe"
    );

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {

            var listaEmpleados = new ArrayList<Empleado>(MAXIMO_EMPLEADOS);
            var mapaEmpleados = new HashMap<String, Empleado>();
            var calificaciones = new HashMap<String, double[]>();

            var sistemaActivo = true;

            do {
                mostrarMenu();

                try {
                    System.out.print("Seleccione una opción: ");
                    var opcion = scanner.nextInt();
                    scanner.nextLine(); // Consume el salto de línea pendiente cuando usas nextInt().

                    /*
                     * Switch tradicional, compatible con Java 8.
                     * Cada case necesita break para impedir el fall-through. Si se
                     * olvida, Java continúa ejecutando el siguiente case. La Switch
                     * Expression moderna con -> no tiene ese riesgo por defecto y,
                     * además, puede producir directamente un valor.
                     */
                    switch (opcion) {
                        case 1:
                            if (listaEmpleados.size() >= MAXIMO_EMPLEADOS) {
                                System.out.println("No hay espacio para más empleados.");
                            } else {
                                registrarEmpleado(
                                        scanner,
                                        listaEmpleados,
                                        mapaEmpleados,
                                        calificaciones);
                            }
                            break;

                        case 2:
                            mostrarReporte(
                                    listaEmpleados,
                                    calificaciones);
                            break;

                        case 3:
                            mostrarCategoriasSalariales();
                            break;

                        case 4:

                            buscarEmpleado(scanner, mapaEmpleados);

                            break;

                        case 5:
                            eliminarEmpleado(
                                    scanner,
                                    listaEmpleados,
                                    mapaEmpleados,
                                    calificaciones);

                            break;

                        case 6:
                            mostrarDatosInmutables();

                            break;

                        case 7:
                            mostrarColeccionesSequenciale(listaEmpleados);

                            break;

                        case 8:
                            eliminarNoPromovidos(listaEmpleados);

                            break;

                        case 0:
                            sistemaActivo = false;
                            System.out.println("Sesión finalizada.");
                            break;

                        default:
                            System.out.println("Opción fuera del menú.");
                            break;
                    }

                } catch (InputMismatchException excepcion) {
                    System.out.println(
                            "Entrada inválida. Debe escribir un valor numérico "
                            + "del tipo solicitado.");

                    // Descarta la entrada que provocó la excepción. Sin esta línea,
                    // Scanner intentaría leer el mismo dato inválido nuevamente.
                    scanner.nextLine();

                    /*
                     * Java 8 ya entrega el tipo de excepción y el stack trace. Las
                     * versiones modernas mejoraron especialmente algunos diagnósticos,
                     * como Helpful NullPointerExceptions desde Java 14, indicando qué
                     * referencia era null en una expresión. Esto no significa que el
                     * mensaje de toda InputMismatchException sea siempre más detallado;
                     * por eso la aplicación muestra un mensaje comprensible al usuario.
                     */
                }
            } while (sistemaActivo);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                =====================================
                     CORPORATE TALENT HUB
                =====================================
                1. Registrar empleado y calificaciones
                2. Mostrar reporte de desempeño
                3. Consultar categorías salariales
                4. Buscar usuario por ID
                5. Eliminar empleado por ID
                6. Mostrar ejemplo de datos inmutables
                7. Mostrar Colecciones Sequenciales
                8. Eliminar No Promovidos (removeIf)
                0. Salir
                """);
    }

    private static boolean registrarEmpleado(
            Scanner scanner,
            ArrayList<Empleado> listaEmpleados,
            HashMap<String, Empleado> mapaEmpleados,
            HashMap<String, double[]> calificaciones) {

        System.out.print("ID: ");
        var id = scanner.nextLine().trim();

        if (id.isBlank()) {
            System.out.println("El ID no puede estar vacio.");
            return false;
        } else if (mapaEmpleados.containsKey(id)) {
            System.out.println("Ya existe un empleado con ese ID.");
            return false;
        }

        System.out.print("Nombre: ");
        var nombre = scanner.nextLine().trim();

        if (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        System.out.print("Edad entre 18 y 100: ");
        var edadIngresada = scanner.nextInt();

        if (edadIngresada < 18 || edadIngresada > 100) {
            System.out.println("La edad está fuera del rango permitido.");
            scanner.nextLine();
            return false;
        }

        // Scanner entrega un int; después de validar el rango se convierte a byte.
        var edad = (byte) edadIngresada;

        System.out.print("Salario mayor que cero: ");
        var salario = scanner.nextDouble();

        if (salario <= 0) {
            System.out.println("El salario debe ser mayor que cero.");
            scanner.nextLine();
            return false;
        }

        double suma = 0;
        var notasDelEmpleado = new double[CANTIDAD_TRIMESTRES];

        for (var trimestre = 0; trimestre < CANTIDAD_TRIMESTRES; trimestre++) {
            System.out.printf(
                    "Calificación del trimestre %d (0 a 100): ", trimestre + 1);

            var calificacion = scanner.nextDouble();
            suma += calificacion;

            if (calificacion < NOTA_MINIMA || calificacion > NOTA_MAXIMA) {
                System.out.println("La calificación está fuera del rango permitido.");
                scanner.nextLine();
                return false;
            }

            notasDelEmpleado[trimestre] = calificacion;
        }

        scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(id, nombre, edad, salario);
        nuevoEmpleado.setPromedioDesempeno(suma / CANTIDAD_TRIMESTRES);

        listaEmpleados.add(nuevoEmpleado);
        mapaEmpleados.put(id, nuevoEmpleado);
        calificaciones.put(id, notasDelEmpleado);

        System.out.println("Empleado registrado correctamente.");
        return true;
    }

    private static void mostrarReporte(
            ArrayList<Empleado> listaEmpleados,
            HashMap<String, double[]> calificaciones) {

        if (listaEmpleados != null && listaEmpleados.isEmpty()) {
            System.out.println("Todavía no hay empleados registrados.");
            return;
        }

        System.out.println("\nREPORTE DE DESEMPEÑO");

        for (var empleado : listaEmpleados) {
            var suma = 0.0;

            double[] notas = calificaciones.get(empleado.getId());

            for (var columna = 0; columna < CANTIDAD_TRIMESTRES; columna++) {
                suma += notas[columna];
            }

            var promedio = suma / CANTIDAD_TRIMESTRES;
            empleado.setPromedioDesempeno(promedio);

            /*
                 * Casting explícito de double a int. Se elimina la parte decimal, no
                 * se redondea: 89.99 se convierte en 89. Esto implica pérdida de precisión.
             */
            var puntajeSimplificado = (int) promedio;
            // Operador ternario: condición ? resultadoSiTrue : resultadoSiFalse.
            var estadoPromocion = promedio >= PROMEDIO_PARA_PROMOCION
                    ? "PROMOVIDO"
                    : "NO PROMOVIDO";

            var categoria = obtenerCategoriaSalarial(
                    empleado.getSalario());

            System.out.printf(
                    "ID: %s | Nombre: %s | Promedio: %.2f | "
                    + "Simplificado: %d | Estado: %s | Categoría: %s%n",
                    empleado.getId(),
                    empleado.getNombre(),
                    promedio,
                    puntajeSimplificado,
                    estadoPromocion,
                    categoria);
        }
    }

    public static String obtenerCategoriaSalarial(double salario) {
        var rango = determinarRangoSalarial(salario);

        /*
         * Switch Expression moderna. La flecha evita el fall-through y el switch
         * devuelve un valor, por lo que no se necesita asignar y usar break en cada case.
         */
        return switch (rango) {
            case 1 ->
                "JUNIOR";
            case 2 ->
                "SEMISENIOR";
            case 3 ->
                "SENIOR";
            case 4 ->
                "LÍDER";
            default ->
                throw new IllegalArgumentException(
                        "Rango salarial no reconocido: " + rango);
        };
    }

    private static int determinarRangoSalarial(double salario) {
        if (salario < 2_000_000.0) {
            return 1;
        } else if (salario < 4_000_000.0) {
            return 2;
        } else if (salario < 7_000_000.0) {
            return 3;
        } else {
            return 4;
        }
    }

    private static void mostrarCategoriasSalariales() {
        System.out.println("""
                Categorías:
                - Menos de $2.000.000: JUNIOR
                - Desde $2.000.000 y menos de $4.000.000: SEMISENIOR
                - Desde $4.000.000 y menos de $7.000.000: SENIOR
                - Desde $7.000.000: LÍDER
                """);
    }

    private static void buscarEmpleado(
            Scanner scanner,
            HashMap<String, Empleado> mapaEmpleados
    ) {
        System.out.print("Digite el ID: ");
        var idBuscado = scanner.nextLine().trim();

        Empleado empleado = mapaEmpleados.get(idBuscado);

        if (empleado != null) {
            System.out.printf("ID: %s | Nombre: %s | Promedio: %.2f | "
                    + "Edad: %d | Salario: %.2f\n",
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getPromedioDesempeno(),
                    empleado.getEdad(),
                    empleado.getSalario()
            );
        } else {
            System.out.println("No se encuantra el empleado con ese ID.\n");
        }

    }

    private static void eliminarEmpleado(
            Scanner scanner,
            ArrayList<Empleado> listaEmpleados,
            HashMap<String, Empleado> mapaEmpleados,
            HashMap<String, double[]> calificaciones) {

        if (mapaEmpleados.isEmpty()) {
            System.out.println("No hay empleados para eliminar.");
            return;
        }

        System.out.print("Digite el ID: ");
        var idBuscado = scanner.nextLine().trim();

        if (mapaEmpleados.containsKey(idBuscado)) {
            Empleado empleadoAEliminar = mapaEmpleados.remove(idBuscado);
            listaEmpleados.remove(empleadoAEliminar);
            calificaciones.remove(empleadoAEliminar);

            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("Error: No se encontró ningún empleado con el ID: " + idBuscado);
        }
    }

    private static void mostrarDatosInmutables() {
        System.out.println("\n--- TECNOLOGÍAS SOPORTADAS ---");
        System.out.println(TECNOLOGIAS);

        System.out.println("\n--- SEDES DE LA EMPRESA ---");
        System.out.println(SEDES);
    }

    private static void mostrarColeccionesSequenciale(ArrayList<Empleado> listaEmpleados) {

        if (listaEmpleados.isEmpty()) {
            System.out.println("Debes registrar al menos un empleado para usar esta función.\n");
            return;
        }

        /*
         * =====================================================================================
         * MEJORA DE JAVA 21: SEQUENCED COLLECTIONS
         * =====================================================================================
         * SINTAXIS LEGACY (Java 8/11): Para obtener los extremos debíamos usar índices matemáticos
         * como lista.get(0) y lista.get(lista.size() - 1). Esto disminuía la legibilidad y 
         * era muy fácil cometer el error de no restar "1" y causar un IndexOutOfBoundsException.
         * Para invertir la lista, tocaba usar un ciclo "for" en reversa o usar Collections.reverse().
         * 
         * SINTAXIS MODERNA (Java 21): Las listas implementan la interfaz SequencedCollection.
         * Ahora tenemos métodos expresivos, directos y seguros:
         * - getFirst(): Retorna el primero sin tocar índices.
         * - getLast(): Retorna el último sin cálculos de tamaño.
         * - reversed(): Retorna una vista de la lista al revés, sin desordenar la lista original.
         * =====================================================================================
         */
        System.out.println("\n--- Sintaxis Legacy (8/11) ---");
        System.out.println("Primer Empleado: " + listaEmpleados.get(0));
        System.out.println("Ultimo Empleado: " + listaEmpleados.get(listaEmpleados.size() - 1));

        System.out.println("\n--- Sintaxis Moderna (Java 21) ---");
        System.out.println("Primer Empleado: " + listaEmpleados.getFirst());
        System.out.println("Ultimo Empleado: " + listaEmpleados.getLast());
        System.out.println("Lista revertida de Empleado: " + listaEmpleados.reversed());
    }

    private static void eliminarNoPromovidos(List<Empleado> listaEmpleados) {

        if (listaEmpleados.isEmpty()) {
            System.out.println("Debes registrar al menos un empleado para usar esta función.\n");
            return;
        }

        listaEmpleados.removeIf(
                empleado -> empleado.getPromedioDesempeno() < PROMEDIO_PARA_PROMOCION
        );

        System.out.println(
                "Los empleados que no han sido promovidos o con un puntaje inferior a 80, se han eliminado "
                + "correctamente"
        );

        System.out.println("\n=== REPORTE FINAL DE PLANILLA ===");

        var totalEmpleados = listaEmpleados.size();

        var sumaSalarios = 0.0;

        for (var empleado : listaEmpleados) {
            sumaSalarios += empleado.getSalario();
        }

        var promedioSalarios = totalEmpleados > 0 ? sumaSalarios / totalEmpleados : 0.0;

        System.out.printf("Total de empleados activos : %d%n", totalEmpleados);
        System.out.printf("Promedio de salarios       : $%.2f%n\n", promedioSalarios);
    }
}
