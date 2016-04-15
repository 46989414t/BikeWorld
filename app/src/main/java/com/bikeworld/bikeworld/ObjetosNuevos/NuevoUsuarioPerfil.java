package com.bikeworld.bikeworld.ObjetosNuevos;

/**
 * Created by enric on 15/4/16.
 */
public class NuevoUsuarioPerfil {
    private String nombre;
    private String email;

    public NuevoUsuarioPerfil() {
    }

    public NuevoUsuarioPerfil(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
