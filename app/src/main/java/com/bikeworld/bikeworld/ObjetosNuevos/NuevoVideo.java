package com.bikeworld.bikeworld.ObjetosNuevos;

import java.util.Date;

/**
 * Created by enric on 15/4/16.
 */
public class NuevoVideo {
    String emaiUser;
    String titulo;
    String descripcion;
    Date fecha;
    String url;


    public NuevoVideo() {
    }

    public NuevoVideo(String emaiUser, String titulo, String descripcion, Date fecha, String url) {
        this.emaiUser = emaiUser;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.url = url;
    }

    public String getEmaiUser() {
        return emaiUser;
    }

    public void setEmaiUser(String emaiUser) {
        this.emaiUser = emaiUser;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
