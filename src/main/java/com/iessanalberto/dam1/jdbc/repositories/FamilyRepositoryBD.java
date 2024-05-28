package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Family;
import com.iessanalberto.dam1.jdbc.utils.ConnectionDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FamilyRepositoryBD {
    public static ArrayList<String> getFamilyList() throws Exception{
        ArrayList<String> familiesAux = new ArrayList<>();
        try (Statement statement = ConnectionDB.connect().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT FamilyName FROM FAMILY")
        )
        {
            while (resultSet.next()){
                familiesAux.add(resultSet.getString("FamilyName"));
            }
        } catch (Exception exception){
            throw new Exception("Error en la base de datos: " +  exception.getMessage());
        }
        return familiesAux;
    }
}
