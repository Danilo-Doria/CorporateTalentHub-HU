package com.riwi.talent.model;

/* 
    Las Sealed Classes (Clases Selladas) introducidas en Java representan 
    un cambio de paradigma fundamental frente a la herencia abierta tradicional, 
    aportando beneficios críticos en la arquitectura y seguridad de las APIs:

    Control Exhaustivo del Dominio de Tipos (Modelado Cerrado):
    Con la herencia abierta tradicional (extends sin restricciones), 
    cualquier clase en cualquier paquete o librería externa puede extender una 
    clase base o interfaz. Esto vulnera el principio de encapsulamiento porque 
    el autor de la API pierde el control sobre quién forma parte de la jerarquía 
    de tipos. Las clases selladas restringen explícitamente los subtipos permitidos 
    mediante la cláusula permits, asegurando que el dominio del negocio no sea 
    alterado ni extendido arbitrariamente por código de terceros.

    Mitigación del Problema de la Clase Frágil (Fragile Base Class Problem):
    En arquitecturas abiertas, modificar o añadir lógica a una clase base puede 
    romper de forma impredecible implementaciones externas desconocidas. 
    Al limitar las subclases a un conjunto finito y conocido en tiempo de 
    compilación, los cambios en la clase base tienen un impacto acotado y 
    predecible, facilitando la refactorización segura y el mantenimiento 
    a largo plazo de la API.

    Exhaustividad Verificada por el Compilador (Exhaustiveness Checking):
    Uno de los mayores valores de seguridad lógica se manifiesta al combinar 
    clases selladas con expresiones switch mejoradas (Pattern Matching). 
    El compilador de Java conoce de antemano todas las implementaciones 
    posibles de una clase sellada. Por lo tanto, exige o valida la cobertura 
    exhaustiva de todos los casos, eliminando la necesidad de bloques 
    default o else genéricos que suelen enmascarar errores lógicos o 
    excepciones de tipo inesperadas (MatchException o ClassCastException) 
    cuando el dominio evoluciona.
 */

// Estilo Moderno (Java 17/21)
public abstract sealed class Persona permits Empleado, ConsultorExterno,
        Desarrollador, Gerente {

    protected final String nombre;
    protected final byte edad;
    protected final String id;

    public Persona(String nombre, byte edad, String id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void trabajar() {
    }
;
}
