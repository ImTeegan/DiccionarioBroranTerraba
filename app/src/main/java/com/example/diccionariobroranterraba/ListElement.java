package com.example.diccionariobroranterraba;

public class ListElement {
    public String color;
    public String name;
    public String city;

    public ListElement( String name, String city) {

        this.name = name;
        this.city = city;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
