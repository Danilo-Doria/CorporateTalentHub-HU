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
            int opcion = view.mostrarMenu();
            switch (opcion) {
                case 1 ->
                    registrarEmpleado();
                case 2 ->
                    listarEmpleados();
                case 3 ->
                    actualizarEmpleado();
                case 4 ->
                    eliminarEmpleado();
                case 5 ->
                    generarReporte();
                case 0 ->
                    activo = false;
                default ->
                    view.mostrarMensaje("Opción no válida.");
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
            view.mostrarMensaje("Error al registrar al empleado: " + e.getMessage());
        }
    }

    private void actualizarEmpleado() {
        String id = view.solicitarId();
        String[] datos = view.solicitarDatosEmpleadoActualizar();

        try {
            Empleado emp = new Empleado(
                    id,
                    datos[0],
                    Byte.parseByte(datos[1]),
                    Double.parseDouble(datos[2])
            );

            boolean exito = dao.update(emp);
            if (exito) {
                view.mostrarMensaje("✅Empleado actualizado exitosamente.");
            } else {
                view.mostrarMensaje("No se encontró ningún empleado con el ID: " + id);
            }
        } catch (NumberFormatException e) {
            view.mostrarMensaje("Error: Edad o salario contienen un formato numérico inválido.");
        } catch (DatabaseException e) {
            view.mostrarMensaje("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    private void eliminarEmpleado() {
        String id = view.solicitarId();

        try {
            boolean exito = dao.delete(id);
            if (exito) {
                view.mostrarMensaje("✅Empleado eliminado exitosamente.");
            } else {
                view.mostrarMensaje("No se encontró ningún empleado con el ID: " + id);
            }
        } catch (DatabaseException e) {
            view.mostrarMensaje("Error al eliminar al empleado: " + e.getMessage());
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
