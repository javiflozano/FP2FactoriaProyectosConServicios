package com.iessanalberto.dam1.jdbc.repositories;

import com.iessanalberto.dam1.jdbc.models.Project;
import com.iessanalberto.dam1.jdbc.models.DTO.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ProjectRepository {
    private static ProjectRepository projectRepository = null;
    private ArrayList<Project> projectsList = new ArrayList<>(); // Lista con todos los projectos
    public static ProjectRepository getInstance(){
        if (projectRepository == null){
            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }
    private ProjectRepository(){
    }
    // Inserción de proyectos
    public void insertProject(Project project){
        projectsList.add(project);
    }
    // Buscar todos los proyectos
    public ArrayList<Project> loadProjects(){
        return projectsList;
    }
    // BÚSQUEDA DE PROYECTOS
    // Por nombre
    public ArrayList<Project> findProjectByName(String name){
        ArrayList<Project> projectsFound = new ArrayList<>();
        for (Project project: projectsList){
            if (project.getName().equals(name)){
                projectsFound.add(project);
            }
        }
        return projectsFound;
    }
    // Por institución
    public ArrayList<Project> findProjectByInstitution(String institution){
        ArrayList<Project> projectsFound = new ArrayList<>();
        for (Project project: projectsList){
            if (project.getInstitution().equals(institution)){
                projectsFound.add(project);
            }
        }
        return projectsFound;
    }
    // Por familia profesional
    public ArrayList<Project> findProjectByFamily(String family){
        ArrayList<Project> projectsFound = new ArrayList<>();
        for (Project project: projectsList){
            if (project.getFamily().equals(family)){
                projectsFound.add(project);
            }
        }
        return projectsFound;
    }
    // Por responsable  // TODO la búsqueda por responsable debería hacerse por Usuario (nombre + apellidos o Id)
    public ArrayList<Project> findProjectByResponsable(String responsable){
        ArrayList<Project> projectsFound = new ArrayList<>();
        for (Project project: projectsList){
            if (project.getResponsable().equals(responsable)){  //TODO sustituir responsable por nombre y apellidos o pensar cómo hacerlo
                projectsFound.add(project);
            }
        }
        return projectsFound;
    }

}
