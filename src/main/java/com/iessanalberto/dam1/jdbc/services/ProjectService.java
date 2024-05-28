package com.iessanalberto.dam1.jdbc.services;

import com.iessanalberto.dam1.jdbc.models.Project;
import com.iessanalberto.dam1.jdbc.models.ProjectShort;
import com.iessanalberto.dam1.jdbc.repositories.ProjectRepository;
import com.iessanalberto.dam1.jdbc.repositories.ProjectRepositoryBD;

import java.util.ArrayList;

public class ProjectService {
    public ArrayList<ProjectShort> loadProjectTable() throws Exception{
        return ProjectRepositoryBD.loadProjectTable();
    }


    // TODO DE AQUÍ PARA ABAJO SON LOS SERVICIOS SIN BD
    // IR SUSTITUYÉNDOLOS PROGRESIVAMENTE
    // Creo un repositorio (único gracias al patrón singleton) para poder trabajar con él
    // Mi servicio dependerá de dicho repositorio
    private ProjectRepository projectRepository = ProjectRepository.getInstance();
    // Defino los servicios que ofrece mi ProjectService
    // Insertar un proyecto nuevo
    public void insertProject(Project project) throws Exception{
        // Validamos el proyecto según nuestra lógica de negocio
        if (isProjectBlank(project)){
            throw new Exception("Todos los campos son obligatorios");
        }
        projectRepository.insertProject(project);
    }
    // SERVICIOS DE BÚSQUEDA DE PROYECTOS
    // Devolver todos los proyectos
    public ArrayList<Project> loadProjects(){
        return projectRepository.loadProjects();
    }
    // Búsqueda por nombre del proyecto
    public ArrayList<Project> loadProjectsByName(String name){
        return projectRepository.findProjectByName(name);
    }
    // Búsqueda por institución del proyecto
    public ArrayList<Project> loadProjectsByInstitution(String institution){
        return projectRepository.findProjectByInstitution(institution);
    }
    // Búsqueda por familia profesional del proyecto
    public ArrayList<Project> loadProjectsByFamily(String family){
        return projectRepository.findProjectByFamily(family);
    }
    // Búsqueda por responsable
    // TODO Hacerlo por nombre y apellidos o por Id
    public ArrayList<Project> loadProjectsByResponsable(String responsable){  //TODO cambiar responsable por un objeto User
        return projectRepository.findProjectByResponsable(responsable);
    }
    // Microservicios y servicios auxiliares
    private boolean isProjectBlank(Project project){
        return  (project.getName().isBlank() ||
                project.getInstitution().isBlank() ||
                project.getFamily().isBlank() ||
                project.getResponsable() == null ||
                project.getInitDate() == null ||
                project.getDescription().isBlank()
        );
    }
}
