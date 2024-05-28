package com.iessanalberto.dam1.jdbc.navigation;

import com.iessanalberto.dam1.jdbc.screens.LoginScreen;
import com.iessanalberto.dam1.jdbc.screens.NewProjectScreen;
import com.iessanalberto.dam1.jdbc.screens.ProjectsScreen;
import com.iessanalberto.dam1.jdbc.screens.RegisterScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    public static Stage stage = new Stage();
    public static void navigate(String destiny){
        switch (destiny){
            case "LoginScreen" -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene sceneLogin = new Scene(loginScreen.root,380,450);
                stage.setTitle("Conéctate a la Factoría de Proyectos");
                stage.setScene(sceneLogin);
                stage.show();
            }
            case "RegisterScreen" -> {
                RegisterScreen registerScreen = new RegisterScreen();
                Scene sceneRegister = new Scene(registerScreen.root,1200,275);
                stage.setTitle("Alta usuario");
                stage.setScene(sceneRegister);
                stage.show();
            }
            case "ProjectsScreen" -> {
                ProjectsScreen projectsScreen = new ProjectsScreen();
                Scene sceneProjects = new Scene(projectsScreen.root,1200,480);
                stage.setTitle("FP - Factoría de Proyectos");
                stage.setScene(sceneProjects);
                stage.show();
            }
            case "NewProjectScreen" -> {
                NewProjectScreen newProjectScreen = new NewProjectScreen();
                Scene sceneNewProject = new Scene(newProjectScreen.root,1200,480);
                stage.setTitle("FP - Factoría de Proyectos");
                stage.setScene(sceneNewProject);
                stage.show();
            }
        }
    }
}
