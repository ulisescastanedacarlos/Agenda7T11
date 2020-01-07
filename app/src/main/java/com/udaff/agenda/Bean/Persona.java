package com.udaff.agenda.Bean;

public class Persona {

    private String nombre;
    private String celular;
    private String direccion;

    public Persona(){

    }


    public Persona(String nombre, String celular, String direccion) {
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String ape_paterno) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String ape_materno) {
        this.direccion = direccion;
    }
}

