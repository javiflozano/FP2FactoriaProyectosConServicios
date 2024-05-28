package com.iessanalberto.dam1.jdbc.models;

import com.iessanalberto.dam1.jdbc.models.DTO.User;

import java.util.Date;

public class Project {
    private String name;
    private String institution; // TODO Esto podría ser un objeto de la clase Institution
    private String family;
    private User responsable;
    private String description;
    private String status;
    private Date initDate;

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public String getFamily() {
        return family;
    }

    public User getResponsable() {
        return responsable;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getInitDate() {
        return initDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    private Date finishDate;

    public Project(String name, String institution, String family, User responsable, String description, Date initDate) {
        this.name = name;
        this.institution = institution;
        this.family = family;
        this.responsable = responsable;
        this.description = description;
        this.initDate = initDate;
    }
}
