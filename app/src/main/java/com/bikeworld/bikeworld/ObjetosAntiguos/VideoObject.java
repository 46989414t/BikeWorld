package com.bikeworld.bikeworld.ObjetosAntiguos;

/**
 * Created by enric on 8/12/15.
 */
public class VideoObject extends User{
    public String date;
    public String url;
    public String title;
    public String description;

    public VideoObject(String userName, String password, String date, String url, String title, String description) {
        super(userName, password);
        this.date = date;
        this.url = url;
        this.title = title;
        this.description = description;
    }
    public VideoObject(){

    }

    public VideoObject(String date, String url, String title, String description) {
        this.date = date;
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VideoObject{" +
                "date='" + date + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
