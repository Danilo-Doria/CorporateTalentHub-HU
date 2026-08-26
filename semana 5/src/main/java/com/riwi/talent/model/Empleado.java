package com.riwi.talent.model;

/*
    Modelo tradicional compatible con la sintaxis de Java 8.
 
    Esta clase es más verbosa que un Record porque declara campos, constructor,
    getters, setter y métodos explícitamente. Esa verbosidad es útil cuando el
    objeto necesita estado mutable, como bonoMensual o nombre.
 */
public final class Empleado extends Persona {

    private final double salario;
    private double promedioDesempeno;

    public Empleado(String id, String nombre, byte edad, double salario) {
        super(nombre, edad, id);
        this.salario = salario;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public double getSalario() {
        return salario;
    }

    public double getPromedioDesempeno() {
        return promedioDesempeno;
    }

    public void setPromedioDesempeno(double promedioDesempeno) {
        this.promedioDesempeno = promedioDesempeno;
    }

    @Override
    public String toString() {
        return "Empleado " + "id= " + id + ", nombre= " + nombre + ", salario= " + salario;
    }

}
