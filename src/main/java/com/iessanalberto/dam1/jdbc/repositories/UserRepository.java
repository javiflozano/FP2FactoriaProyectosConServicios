package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.DTO.User;

import java.util.ArrayList;

// Patrón singleton - Solo puede haber una instancia de esta clase
public class UserRepository {
    private static UserRepository userRepository = null;

    // La única instancia de la clase se obtiene con el siguiente método:
    // Si es la primera vez se crea (a través del constructor vacío), si no se devuelve la instancia ya creada
    public static UserRepository getInstance(){
        if (userRepository == null){
            userRepository = new UserRepository();
        }
        return  userRepository;
    }
    // Creamos el ArrayList donde guardaremos los usuarios
    private ArrayList<User> userArrayList = new ArrayList<>();


    // Definimos los métodos que accederán al repositorio para crear altas, bajas y modificaciones de usuarios, así cómo las diferentes consultas
    public void insertUser(User user){
        userArrayList.add(user);
    }

    public User findUserByEmail(String email){
        for (User user: userArrayList){
            if (user.getLoginEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    public User loginUser(String email, String password){
        for (User user: userArrayList){
            if (user.getLoginEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    // loadAllUsers() -> Devuelve todos los usuarios
    public ArrayList<User> loadAllUsers(){
        return userArrayList;
    }
}
