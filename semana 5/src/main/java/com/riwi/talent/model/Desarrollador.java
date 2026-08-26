package com.riwi.talent.model;

public final class Desarrollador extends Persona implements Promocionable {

    private String lenguajePrincipal;

    public Desarrollador(String nombre, byte edad, String id, String lenguajePrincipal) {
        super(nombre, edad, id);
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    @Override
    public void trabajar() {
        System.out.println("El desarrollador " + nombre + " escribe código en " + lenguajePrincipal);
    }

    @Override
    public double calcularBonoAscenso() {
         return 1_500_000.0;
    }
}
