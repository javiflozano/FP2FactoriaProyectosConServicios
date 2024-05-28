package com.iessanalberto.dam1.jdbc.services;

import com.iessanalberto.dam1.jdbc.models.DAO.UserDao;
import com.iessanalberto.dam1.jdbc.models.DTO.User;
import com.iessanalberto.dam1.jdbc.repositories.UserRepository;
import com.iessanalberto.dam1.jdbc.repositories.UserRepositoryBD;

import java.util.ArrayList;

public class UserService {
    // Creo un repositorio (único gracias al patrón singleton) para poder trabajar con él
    // Mi servicio dependerá de dicho repositorio
    private UserRepository userRepository = UserRepository.getInstance();
    // Defino los servicios que ofrece mi UserService: alta, modificación y baja de usuarios
    // Solo llamaré al repositorio después de validar los datos
    public void insertUser(User user, String repeatPassword) throws Exception{
        // Validamos que los campos estén informados
        if (isUserBlank(user)){
            throw new Exception("Todos los campos son obligatorios");
        }
        // Validamos que las contraseñas coincidan
        if (!user.getPassword().equals(repeatPassword)){
            throw new Exception("Las contraseñas no coinciden");
        }
        // Validamos que el usuario no exista - No puede haber dos usuarios con el mismo correo
        if (UserRepositoryBD.isEmailRegistered(user.getLoginEmail())){
            throw new Exception("El correo " + user.getLoginEmail() + " ya está registrado.");
        }
        // Si no han saltado las excepciones damos de alta el usuario en el repositorio
        // Transformamos de userDto a userDao
        UserRepositoryBD.insertUser(userDtoToUserDao(user));

    }
    private boolean isUserBlank(User user){
        return user.getName().isBlank() ||
                user.getSurname().isBlank() ||
                user.getLoginEmail().isBlank() ||
                user.getPassword().isBlank();
    }
    private UserDao userDtoToUserDao(User user){
        return new UserDao(
                0,
                user.getName(),
                user.getSurname(),
                user.getLoginEmail(),
                user.getLinkedIn(),
                user.getIdInstitution());
    }
    public User loginUser(String email, String password) throws Exception{
        if (email.isBlank() || password.isBlank()){
            throw new Exception("Todos los campos son obligatorios");
        }
        if (userRepository.loginUser(email,password) == null){
            throw new Exception("Usuario/contraseña no encontrado");
        }
        return userRepository.loginUser(email,password);
    }
    // Devuelve todos los usuarios
    public ArrayList<User> loadAllUsers(){
        return userRepository.loadAllUsers();
    }
    // Devuelve una lista con el nombre y apellido de todos los usuarios

}
