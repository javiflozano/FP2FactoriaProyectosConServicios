package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Institution;
import com.iessanalberto.dam1.jdbc.utils.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InstitutionRepositoryBD {
    public static ArrayList<Institution> getInstitutionList() throws Exception {
        ArrayList<Institution> institutionAux = new ArrayList<>();
        try (Statement statement = ConnectionDB.connect().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Id, InstitutionName, IdCategory FROM INSTITUTION")
        ) {
            while (resultSet.next()) {
                try (PreparedStatement preparedStatement = ConnectionDB.connect().prepareStatement(
                        "SELECT ShortCat FROM CATEGORY WHERE Id = ?"
                )) {
                    preparedStatement.setString(1, String.valueOf(resultSet.getInt("IdCategory")));
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    resultSet1.next();
                    institutionAux.add(new Institution(resultSet.getInt("Id"), resultSet1.getString("ShortCat") + " " + resultSet.getString("InstitutionName")));

                }

            }
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos: " + exception.getMessage());
        }
        return institutionAux;
    }

}
