package com.bikeworld.bikeworld;

/**
 * Created by enric on 8/12/15.
 */
public class VideoObject extends User{
    public String fecha;
    public String url;
    public String titulo;
    public String descripcion;

    public VideoObject(String userName, String password, String fecha, String url, String titulo, String descripcion) {
        super(userName, password);
        this.fecha = fecha;
        this.url = url;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public VideoObject(String fecha, String url, String titulo, String descripcion) {
        this.fecha = fecha;
        this.url = url;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
