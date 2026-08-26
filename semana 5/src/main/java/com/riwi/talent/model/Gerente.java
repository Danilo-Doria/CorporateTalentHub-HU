package com.riwi.talent.model;

public final class Gerente extends Persona implements Promocionable {

    private double presupuestoMensual;

    public Gerente(String nombre, byte edad, String id, double presupuestoMensual) {
        super(nombre, edad, id);
        this.presupuestoMensual = presupuestoMensual;
    }

    public double getPresupuestoMensual() {
        return presupuestoMensual;
    }

    @Override
    public void trabajar() {
        System.out.println("El gerente " + nombre + " gestiona un presupuesto de " + presupuestoMensual);
    }
    
    @Override
    public double calcularBonoAscenso() {
        return presupuestoMensual * 0.05;
    }
}
