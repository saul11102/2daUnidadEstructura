/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author alejandro
 */
public class Estudiante {
    private int id;
    private String cedula;
    private String nombre;
    private Integer id_Direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_Direccion() {
        return id_Direccion;
    }

    public void setId_Direccion(Integer id_Direccion) {
        this.id_Direccion = id_Direccion;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "cedula=" + cedula + ", nombre=" + nombre + '}';
    }
    
    
}
