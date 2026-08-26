package com.riwi.talent.model;

import com.riwi.talent.exception.DatabaseException;
import com.riwi.talent.exception.EmpleadoYaExisteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    private boolean existsById(String id) throws DatabaseException {
        String sql = "SELECT 1 FROM empleados WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar existencia del empleado.", e);
        }
    }

    @Override
    public void insert(Empleado empleado) throws EmpleadoYaExisteException, DatabaseException {
        // Validación de negocio: lanzar excepción específica
        if (existsById(empleado.getId())) {
            throw new EmpleadoYaExisteException(empleado.getId());
        }

        String sql = "INSERT INTO empleados (id, nombre, edad, salario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setByte(3, empleado.getEdad());
            ps.setDouble(4, empleado.getSalario());

            ps.executeUpdate();

        } catch (SQLException e) {
            // Relanzamos como DatabaseException envolviendo la causa original (SQLException)
            throw new DatabaseException("No se pudo registrar el empleado en la base de datos.", e);
        }
    }

    @Override
    public List<Empleado> findAll() throws DatabaseException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT id, nombre, edad, salario FROM empleados";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getByte("edad"),
                        rs.getDouble("salario")
                );
                empleados.add(emp);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener el listado de empleados.", e);
        }
        return empleados;
    }

    @Override
    public boolean update(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, edad = ?, salario = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setByte(2, empleado.getEdad());
            ps.setDouble(3, empleado.getSalario());
            ps.setString(4, empleado.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void generateFinalReport() {
        String sql = "SELECT nombre, id, EXTRACT(YEAR FROM CURRENT_DATE) - 2 as anio_fundacion FROM empleados";
        List<EmpresaRecord> reportes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reportes.add(new EmpresaRecord(
                        rs.getString("nombre"),
                        rs.getString("id"),
                        rs.getInt("anio_fundacion")
                ));
            }

            String formatoReporte = """
                    ========================================
                      REPORTE EMPRESARIAL CONSOLIDADO
                    ========================================
                    Nombre Empleado: %s
                    NIT/ID Vinculado: %s
                    Año de Fundación Ref: %d
                    ----------------------------------------
                    """;

            for (EmpresaRecord rec : reportes) {
                System.out.printf(formatoReporte, rec.nombre(), rec.nit(), rec.anioFundacion());
            }

        } catch (SQLException e) {
            System.err.println("Error generando reporte: " + e.getMessage());
        }
    }
}
