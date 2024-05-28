package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Institution;
import com.iessanalberto.dam1.jdbc.models.Project;
import com.iessanalberto.dam1.jdbc.models.DTO.User;
import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.FamilyService;
import com.iessanalberto.dam1.jdbc.services.InstitutionService;
import com.iessanalberto.dam1.jdbc.services.ProjectService;
import com.iessanalberto.dam1.jdbc.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class NewProjectScreen {
    public GridPane root = new GridPane();
    // Definimos los elementos de la ventana
    Label lblName = new Label("Nombre del proyecto");
    TextField txtName = new TextField();
    Label lblInstitution = new Label("Centro/empresa");
    ObservableList<Institution> institutionList = FXCollections.observableArrayList();
    ComboBox<Institution> cmbInstitution = new ComboBox<>(institutionList);
    Label lblResponsable = new Label("Responsable");
    ObservableList<User> responsableList = FXCollections.observableArrayList();
    ComboBox<User> cmbResponsable = new ComboBox<>(responsableList);
    Label lblFamily = new Label("Familia profesional");
    ObservableList<String> familyList = FXCollections.observableArrayList();
    ComboBox<String> cmbFamily = new ComboBox<>(familyList);
    Label lblInitDate = new Label("Fecha de inicio");
    DatePicker dpInitDate = new DatePicker(LocalDate.now());
    Label lblDescription = new Label("Descripción");
    TextArea txtDescription = new TextArea();
    Button btnVolver = new Button("Volver");
    Button btnNewProject = new Button("Guardar");
    // Definimos los servicios que va a usar la ventana

    FamilyService familyService = new FamilyService();
    UserService userService = new UserService();
    ProjectService projectService = new ProjectService();
    InstitutionService institutionService = new InstitutionService();

    public NewProjectScreen(){
        // Definimos los parámetros del layout principal
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        // Asignamos los elementos al gridpane
        root.add(lblName,0,0);
        root.add(txtName, 1, 0);
        root.add(lblInstitution,2,0);
        root.add(cmbInstitution,3,0);
        root.add(lblResponsable,0,1);
        root.add(cmbResponsable,1,1);
        root.add(lblFamily,2,1);
        root.add(cmbFamily,3,1);
        root.add(lblInitDate,0,2);
        root.add(dpInitDate,1,2);
        root.add(lblDescription,0,4);
        root.add(txtDescription,1,4);
        root.add(btnVolver,2,5);
        root.add(btnNewProject,3,5);
        // ASIGNAMOS los datos de los Combos (listas desplegables)
        try {
            institutionList.addAll(institutionService.loadAllInstitutionsNames());
        } catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el acceso a los datos");
            alert.setHeaderText("Error recuperando las instituciones");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }

        cmbInstitution.setValue(institutionList.get(0));
        responsableList.addAll(userService.loadAllUsers());
        cmbResponsable.setValue(responsableList.get(0));
        try {
            familyList.addAll(familyService.loadFamilies());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el acceso a los datos");
            alert.setHeaderText("Error recuperando las familias profesionales");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
        cmbFamily.setValue(familyList.get(0));
        // Definimos la interactividad de la ventana
        btnVolver.setOnAction(actionEvent -> {
            Navigation.navigate("ProjectsScreen");
        });
        btnNewProject.setOnAction(actionEvent -> {

            try {
                // TODO insertar proyecto
                /*
                projectService.insertProject(new Project(
                        txtName.getText(),
                        cmbInstitution.getValue(),
                        cmbFamily.getValue(),
                        cmbResponsable.getValue(),
                        txtDescription.getText(),
                        Date.from(Instant.from(dpInitDate.getValue()))));
                */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nuevo proyecto");
                alert.setHeaderText(null);
                alert.setContentText("El proyecto " + txtName.getText() + " se ha añadido con éxito");
                alert.showAndWait();
                Navigation.navigate("ProjectsScreen");
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en el alta del proyecto");
                alert.setHeaderText("No se ha guardado el nuevo proyecto");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });

    }
}
