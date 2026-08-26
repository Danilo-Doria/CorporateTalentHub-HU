package com.riwi.talent.controller;

import com.riwi.talent.exception.DatabaseException;
import com.riwi.talent.exception.EmpleadoYaExisteException;
import com.riwi.talent.model.Empleado;
import com.riwi.talent.model.EmpleadoDAO;
import com.riwi.talent.view.ConsoleView;

import java.util.List;

public class EmpleadoController {
    private final ConsoleView view;
    private final EmpleadoDAO dao;

    public EmpleadoController(ConsoleView view, EmpleadoDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void iniciarAplicacion() {
        boolean activo = true;
        while (activo) {
            int opcion = view.mostrarMenuYLeerOpcion();
            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> listarEmpleados();
                case 3 -> generarReporte();
                case 0 -> activo = false;
                default -> view.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void registrarEmpleado() {
        String[] datos = view.solicitarDatosEmpleado();
        try {
            Empleado emp = new Empleado(
                    datos[0], 
                    datos[1], 
                    Byte.parseByte(datos[2]), 
                    Double.parseDouble(datos[3])
            );
            
            dao.insert(emp);
            view.mostrarMensaje("Empleado guardado exitosamente.");

        } catch (NumberFormatException e) {
            view.mostrarMensaje("Error: Edad o salario contienen un formato numérico inválido.");
        } catch (EmpleadoYaExisteException e) {
            view.mostrarMensaje(e.getMessage());
        } catch (DatabaseException e) {
            view.mostrarMensaje("Error crítico de infraestructura: " + e.getMessage());
        }
    }

    private void listarEmpleados() {
        try {
            List<Empleado> lista = dao.findAll();
            if (lista.isEmpty()) {
                view.mostrarMensaje("No hay empleados registrados.");
                return;
            }
            lista.forEach(emp -> view.mostrarMensaje(emp.toString()));
        } catch (DatabaseException e) {
            view.mostrarMensaje("Error al obtener los empleados: " + e.getMessage());
        }
    }

    private void generarReporte() {
        try {
            dao.generateFinalReport();
        } catch (DatabaseException e) {
            view.mostrarMensaje("Error al generar el reporte: " + e.getMessage());
        }
    }
}