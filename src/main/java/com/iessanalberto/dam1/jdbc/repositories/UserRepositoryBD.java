package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.DAO.UserDao;
import com.iessanalberto.dam1.jdbc.utils.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepositoryBD {
    public static boolean isEmailRegistered(String eMail) throws Exception{
        try (PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                "SELECT Id FROM USERS WHERE Email = ?"
        )){
            preparedStatement.setString(1,eMail);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception exception){
            throw new Exception("Error al comprobar el correo electrónico");
        }
    }
    public static void insertUser(UserDao userDao) throws Exception{
        try (PreparedStatement insertSQL = ConnectionDB.connect().prepareStatement(
                "INSERT INTO USERS (UserName,Surname,Email,LinkedIn,IdInstitution) VALUES (?,?,?,?,?)"
        )){
            insertSQL.setString(1,userDao.getName());
            insertSQL.setString(2,userDao.getSurname());
            insertSQL.setString(3,userDao.getLoginEmail());
            insertSQL.setString(4,userDao.getLinkedIn());
            insertSQL.setInt(4,userDao.getIdInstitution());
            insertSQL.executeUpdate();
        } catch (Exception exception){
            throw new Exception("Error al insertar el usuario. Info: " + exception.getMessage());
        }
    }
}
