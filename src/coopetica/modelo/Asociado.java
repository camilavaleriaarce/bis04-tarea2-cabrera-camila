/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coopetica.modelo;

/**
 *
 * @author PERSONAL
 */
public class Asociado {
    private String cedula;
    private String nombre;
    private double saldoAportes;
    public Asociado (String cedula, String nombre, double saldoAportes) {
    this.cedula = cedula;
    this.nombre = nombre; 
    this.saldoAportes = saldoAportes; 
    
    }
    public String getCedula ()  { return cedula; }
    public String getNombre ()  { return nombre; }
    public double getSaldoAportes () { return saldoAportes; }
    
    public void setSaldoAportes (double saldoAportes) {
     this.saldoAportes = saldoAportes;
    }
    @Override
    public String toString () {
    return cedula + " - " + nombre + " - Aportes: " + saldoAportes; 
    }
    
}
