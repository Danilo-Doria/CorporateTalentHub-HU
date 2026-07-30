package com.mycompany.corporatetalenthub.modelo;

/*
    Modelo tradicional compatible con la sintaxis de Java 8.
 
    Esta clase es más verbosa que un Record porque declara campos, constructor,
    getters, setter y métodos explícitamente. Esa verbosidad es útil cuando el
    objeto necesita estado mutable, como bonoMensual o nombre.
 */
public class Empleado {

    // Los 8 tipos primitivos requeridos:
    private byte nivelAcceso;
    private short anioIngreso;
    private int idEmpleado;
    private long numeroDocumento;
    private float puntajeTest;
    private double salarioBase;
    private char tipoContrato;
    private boolean esActivo;

    private int edad;
    private int idSede;
    private double bonoMensual;

    // String no es primitivo: es una clase y esta variable guarda 
    // una referencia en memoria.
    private String nombre;

    // Constructor
    public Empleado(
            byte nivelAcceso,
            short anioIngreso,
            int idEmpleado,
            long numeroDocumento,
            float puntajeTest,
            double salarioBase,
            char tipoContrato,
            boolean esActivo,
            String nombre,
            int edad,
            int idSede,
            double bonoMensual) {

        this.nivelAcceso = nivelAcceso;
        this.anioIngreso = anioIngreso;
        this.idEmpleado = idEmpleado;
        this.numeroDocumento = numeroDocumento;
        this.puntajeTest = puntajeTest;
        this.salarioBase = salarioBase;
        this.tipoContrato = tipoContrato;
        this.esActivo = esActivo;
        this.nombre = nombre;
        this.edad = edad;
        this.idSede = idSede;
        this.bonoMensual = bonoMensual;
    }

    // Metodos - Logica de negocio
    // Getters
    public double calcularSalarioFinal() {
        // Orden: paréntesis internos -> multiplicaciones -> suma -> resta.
        // 1. bonoMensual * 1.10
        // 2. salarioBase + (paso 1)
        // 3. salarioBase * 0.05
        // 4. (paso 2) - (paso 3)

        return (salarioBase + (bonoMensual * 1.10)) - (salarioBase * 0.05);
    }

    public boolean tieneBonoExtra() {

        return idEmpleado % 2 == 0;
    }

    public boolean validarElegibilidad() {

        return (puntajeTest > 85 && edad < 30) || (idSede == 1 && !esActivo);
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public double getBonoMensual() {
        return bonoMensual;
    }

    public String getNombre() {
        return nombre;
    }

    // setters
    public void actualizarBonoMensual(double incremento) {
        bonoMensual += incremento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /* 
        Se utiliza @Override porque estamos reemplazando (sobrescribiendo)
        el método toString() que viene heredado de la clase Object.
        Todos los objetos en Java heredan este método, pero su versión original
        solo muestra el nombre de la clase y un hash del objeto, por ejemplo:
        com.mycompany.corporatetalenthub.modelo.Empleado@4e25154f

        Al sobrescribir toString(), definimos una representación personalizada
        del objeto para que al imprimirlo con System.out.println() muestre
        información útil de sus atributos en lugar de la referencia por defecto.
     */
    @Override
    public String toString() {
        return "Empleado" +
                "\nnivelAcceso= " + nivelAcceso +
                "\nanioIngreso= " + anioIngreso +
                "\nidEmpleado= " + idEmpleado +
                "\nnumeroDocumento= " + numeroDocumento +
                "\npuntajeTest= " + puntajeTest +
                "\nsalarioBase= " + salarioBase +
                "\ntipoContrato= " + tipoContrato +
                "\nesActivo= " + esActivo +
                "\nnombre= " + nombre +
                "\nedad= " + edad +
                "\nidSede= " + idSede +
                "\nbonoMensual= " + bonoMensual
                ;
    }
}
