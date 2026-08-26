package com.riwi.talent.model;

/*
import java.util.Objects;

public class DesempenioReport{
    int idEmpleado;
    double promedio;
    String feedback;

    public DesempenioReport(int idEmpleado, double promedio, String feedback) {
        this.idEmpleado = idEmpleado;
        this.promedio = promedio;
        this.feedback = feedback;
    }
    
    // Getters
    public int getIdEmpleado() {return idEmpleado;}

    public double getPromedio() {return promedio;}

    public String getFeedback() {return feedback;}
    
    // Setters
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}

    public void setPromedio(double promedio) {this.promedio = promedio;}

    public void setFeedback(String feedback) {this.feedback = feedback;}
    
    // Métodos de infraestructura (Equals, HashCode, ToString)
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idEmpleado;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.promedio) ^ (Double.doubleToLongBits(this.promedio) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.feedback);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DesempenioReport other = (DesempenioReport) obj;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        if (Double.doubleToLongBits(this.promedio) != Double.doubleToLongBits(other.promedio)) {
            return false;
        }
        return Objects.equals(this.feedback, other.feedback);
    }

    @Override
    public String toString() {
        return "DesempenioReport{" + "idEmpleado=" + idEmpleado + ", promedio=" + promedio + ", feedback=" + feedback + '}';
    }
}
 */
public record DesempenioReport(
        String idEmpleado,
        String nombre,
        double promedio,
        String feedback,
        String estado) {

}
