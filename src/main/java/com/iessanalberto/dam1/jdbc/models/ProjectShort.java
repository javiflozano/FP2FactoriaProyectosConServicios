package com.iessanalberto.dam1.jdbc.models;

public class ProjectShort {
    private int id;
    private String name;
    private String institution;
    private String family;
    private String responsable;
    private String state;
    private String description;

    public ProjectShort(int id, String name, String institution, String family, String responsable,String state, String description) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.family = family;
        this.responsable = responsable;
        this.state = state;
        this.description = description;
    }


}
