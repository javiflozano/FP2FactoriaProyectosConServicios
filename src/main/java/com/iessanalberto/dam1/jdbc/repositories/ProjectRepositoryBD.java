package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.ProjectShort;
import com.iessanalberto.dam1.jdbc.utils.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProjectRepositoryBD {
    public static ArrayList<ProjectShort> loadProjectTable() throws Exception{
        ArrayList<ProjectShort> projectShortList = new ArrayList<>();
        try ( Statement selectProjects = ConnectionDB.connect().createStatement();
              ResultSet resultProjectShort = selectProjects.executeQuery("SELECT iD, Title, ProjectDescription, State FROM PROJECT")
        ) {
            while (resultProjectShort.next()){
                // Para cada proyecto obtenemos su Institution y su Family
                projectShortList.add(new ProjectShort(
                        resultProjectShort.getInt("Id"),
                        resultProjectShort.getString("Title"),
                        "", // TODO OBTENER INSTITUTION
                        findFamilyByIdProject(resultProjectShort.getInt("Id")),
                        "", // TODO Obtener responsable
                        resultProjectShort.getString("State"),
                        resultProjectShort.getString("ProjectDescription")
                ));
            }
        } catch (Exception exception) {
            throw new Exception("Error en la base de datos. Información adicional: " + exception.getMessage());
        }

        return projectShortList;
    }
    private static String findFamilyByIdProject(int idProject) throws Exception{
        String families = "";
        try (PreparedStatement selectFamilyByIdProject = ConnectionDB.connect().prepareStatement("SELECT IdFamily FROM COLLABORAION WHERE IdProject = ?");
        ) {
            selectFamilyByIdProject.setInt(1,idProject);
            ResultSet familyId = selectFamilyByIdProject.executeQuery();
            while (familyId.next()){
                try (PreparedStatement selectFamilyNameById = ConnectionDB.connect().prepareStatement("SELECT FamilyName FROM FAMILY WHERE Id = ?");)
                {
                    selectFamilyNameById.setInt(1,familyId.getInt("IdFamily"));
                    ResultSet rsFamilyName = selectFamilyNameById.executeQuery();
                    while (rsFamilyName.next()){
                        families.concat(rsFamilyName.getString("FamilyName") + " ");
                    }
                }
            }
        } catch (Exception exception){
            throw new Exception("Error en la base de datos: Información adicional: " + exception.getMessage());
        }
        return families;
    }
}
