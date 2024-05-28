package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.Institution;
import com.iessanalberto.dam1.jdbc.models.ProjectShort;
import com.iessanalberto.dam1.jdbc.models.DTO.User;
import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class ProjectsScreen {
    public GridPane root = new GridPane();
    // Definimos los componentes de la ventana
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

    Button btnFitrar = new Button("Filtrar");
    // Tabla de proyectos
    TableView<ProjectShort> tblProjects = new TableView<>();
    // Columnas de la tabla
    TableColumn<ProjectShort, Integer> colId = new TableColumn<ProjectShort, Integer>("Código");
    TableColumn<ProjectShort,String> colName = new TableColumn<>("Nombre");
    TableColumn<ProjectShort,String> colInstitution = new TableColumn<>("Centro/empresa");
    TableColumn<ProjectShort,String> colFamily = new TableColumn<>("Familia profesional");
    TableColumn<ProjectShort,String> colResponsable = new TableColumn<>("Responsable");
    TableColumn<ProjectShort,String> colStatus = new TableColumn<>("Estado");
    TableColumn<ProjectShort,String> colDescription = new TableColumn<>("Descripción");
    Button btnNewProject = new Button("Nuevo proyecto");
    // Definimos los servicios que va a usar la ventana

    FamilyService familyService = new FamilyService();
    InstitutionService institutionService = new InstitutionService();
    UserService userService = new UserService();
    ProjectService projectService = new ProjectService();

    public ProjectsScreen() {
        // Definimos los parámetros del layout principal
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        // Asignamos los elementos al gridpane
        // Fila 1
        root.add(lblName,0,0);
        root.add(txtName, 1, 0);
        root.add(lblInstitution,2,0);
        root.add(cmbInstitution,3,0);
        // Fila 2
        root.add(lblResponsable,0,1);
        root.add(cmbResponsable,1,1);
        root.add(lblFamily,2,1);
        root.add(cmbFamily,3,1);
        root.add(btnFitrar,4,1);
        // Añadimos la tabla
        root.add(tblProjects,0,2,5,1);
        root.add(btnNewProject,4,3);

        // ASIGNAMOS los datos de los Combos (listas desplegables)
        responsableList.addAll(userService.loadAllUsers());
        cmbResponsable.setValue(responsableList.get(0));
        // Asignamos las instituciones al comboBox y un valor por defecto
        try {
            institutionList.addAll(institutionService.loadAllInstitutionsNames());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la base de datos");
            alert.setHeaderText("Error al recuperar las instituciones");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
        // Asignamos las familias al comboBox y un valor por defecto
        try {
            familyList.addAll(familyService.loadFamilies());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la base de datos");
            alert.setHeaderText("Error al recuperar las familias profesionales");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }

        cmbFamily.setValue(familyList.get(0));
        // Asignamos las columnas a la tabla
        tblProjects.getColumns().addAll(colId,colName,colInstitution,colFamily,colResponsable,colStatus,colDescription);
        colId.setCellValueFactory(new PropertyValueFactory<ProjectShort,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("name"));
        colInstitution.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("institution"));
        colFamily.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("family"));
        colResponsable.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("responsable"));
        colFamily.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<ProjectShort,String>("description"));
        // Añadimos el contenido inicial a la tabla
        try {
            tblProjects.getItems().addAll(projectService.loadProjectTable());
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la base de datos");
            alert.setHeaderText("Error al recuperar el listado de proyectos");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }

        // Definimos la interactividad de la ventana
        btnNewProject.setOnAction(actionEvent -> Navigation.navigate("NewProjectScreen"));
    }
}
