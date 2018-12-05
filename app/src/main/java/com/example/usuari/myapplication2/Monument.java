package com.example.usuari.myapplication2;

public class Monument {

    public String name;
    public double lat;
    public double lon;
    public String cat;
    public String param;
    public String description;
    public String imageurl;


    public Monument (String name, double lat, double lon, String cat, String param, String description, String imageurl){
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.cat = cat;
        this.param = param;
        this.description = description;
        this.imageurl = imageurl;
    }
}