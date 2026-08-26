package com.riwi.talent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/riwi_talent_hub";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres_123";

    // Constructor privado para evitar instanciación externa
    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*
     * SINTAXIS LEGACY (Java 8 y anteriores):
     * Anteriormente, los recursos como Connection, PreparedStatement y ResultSet
     * debían cerrarse manualmente dentro de un bloque 'finally'. 
     * Si ocurría una excepción durante el '.close()', se requerían bloques try-catch 
     * anidados, lo que generaba un código verboso (Boilerplate) y propenso a errores.
     * 
     * SINTAXIS MODERNA (Java 17/21):
     * Mediante el bloque 'try-with-resources', cualquier objeto que implemente 
     * la interfaz 'AutoCloseable' se declara entre paréntesis del try (). 
     * Java garantiza la invocación automática del método .close() al finalizar 
     * el bloque, independientemente de si el código finaliza con éxito o lanza una excepción.
     * 
     * PREVENCIÓN DE MEMORY LEAKS (Fugas de memoria):
     * Un Memory Leak en base de datos ocurre cuando se abren conexiones pero nunca 
     * se cierran, agotando el Pool de conexiones del servidor y tumbando la aplicación.
     * El 'try-with-resources' previene esto eliminando el factor de error humano: 
     * es imposible olvidar liberar los recursos (Connection, Statement, ResultSet) 
     * porque el compilador lo hace por nosotros de manera determinista.
     */
}
