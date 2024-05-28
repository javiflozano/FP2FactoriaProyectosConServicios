package com.iessanalberto.dam1.jdbc.models.DAO;

public class UserDao {
    private int id;
    private String name;
    private String surname;
    private String loginEmail;
    private String linkedIn;
    private int idInstitution;

    public UserDao(int id, String name, String surname, String loginEmail, String linkedIn, int idInstitution) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.loginEmail = loginEmail;
        this.linkedIn = linkedIn;
        this.idInstitution = idInstitution;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public int getIdInstitution() {
        return idInstitution;
    }
}
