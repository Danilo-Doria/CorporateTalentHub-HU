package com.riwi.talent.model;

import java.time.LocalDateTime;

public interface Promocionable {

    // Método abstracto tradicional (Obligatorio de implementar)
    double calcularBonoAscenso();

    /*
     * Evolución (Java 8+): Método 'default'.
     * Permite agregar lógica concreta a una interfaz. Si mañana añadimos esta
     * interfaz a cientos de clases, este nuevo método no "romperá" el código 
     * existente obligando a reescribirlas todas, ya que provee un comportamiento base.
     */
    default void registrarLogPromocion(String nombre) {
        System.out.println("[LOG PROMOCIÓN] - " + LocalDateTime.now()
                + ": Se evaluó el bono de ascenso para " + nombre);
    }
}
