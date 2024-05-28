package com.iessanalberto.dam1.jdbc.models.DTO;
// Usuarios que colaboran o desarrollan un proyecto (no confundir con las instituciones a las que pertenecen)
public class User {
    private int id;
    private String name;
    private String surname;
    private String loginEmail;
    private int idInstitution;
    private String linkedIn;
    private String password;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public int getIdInstitution() {
        return idInstitution;
    }

    public String getPassword() {
        return password;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public User(int id, String name, String surname, String email, int institution, String linkedIn, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.loginEmail = email;
        this.idInstitution = institution;
        this.linkedIn = linkedIn;
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
