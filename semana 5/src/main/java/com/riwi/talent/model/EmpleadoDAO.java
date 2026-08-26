package com.riwi.talent.model;

import com.riwi.talent.exception.EmpleadoYaExisteException;
import com.riwi.talent.exception.DatabaseException;
import java.util.List;

public interface EmpleadoDAO {

    void insert(Empleado empleado) throws EmpleadoYaExisteException, DatabaseException;

    List<Empleado> findAll() throws DatabaseException;

    boolean update(Empleado empleado) throws DatabaseException;

    boolean delete(String id) throws DatabaseException;

    void generateFinalReport() throws DatabaseException;
}
