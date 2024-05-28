package com.iessanalberto.dam1.jdbc.models;
// Instituciones, centros educativos o empresas que pueden formar parte de un proyecto
public class Institution {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public Institution(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
