package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.models.DTO.User;
import com.iessanalberto.dam1.jdbc.models.Institution;
import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.FamilyService;
import com.iessanalberto.dam1.jdbc.services.InstitutionService;
import com.iessanalberto.dam1.jdbc.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegisterScreen {

    // Definimos los elementos de layout
    public GridPane root = new GridPane();

    // Definimos los componentes de la ventana
    Label lblName = new Label("Nombre");
    TextField txtName = new TextField();
    Label lblSurname = new Label("Apellidos");
    TextField txtSurname = new TextField();
    Label lblCorreo = new Label("Correo electrónico");
    TextField txtEmail = new TextField();
    Label lblInstitution = new Label("Institución/centro educativo/empresa");
    ObservableList<Institution> institutionList = FXCollections.observableArrayList();
    ComboBox<Institution> cmbInstitution = new ComboBox<>(institutionList);
    Label lblLinkedin = new Label("Linkedin");
    TextField txtLinkedin = new TextField();
    Label lblPassword = new Label("Contraseña");
    PasswordField txtPassword = new PasswordField();
    Label lblRepeatPassword = new Label("Repite la contraseña");
    PasswordField txtRepeatPassword = new PasswordField();
    Button btnCancelar = new Button("Cancelar");
    Button btnRegister = new Button("Guardar");
    // Definimos los servicios que puede usar la ventana
    FamilyService familyService = new FamilyService();
    InstitutionService institutionService = new InstitutionService();
    UserService userService = new UserService();

    public RegisterScreen(){
        // Definimos los parámetros del layout principal
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        // Asignamos los elementos al gridpane
        // Fila 1
        root.add(lblName,0,0);
        root.add(txtName, 1, 0);
        root.add(lblSurname,2,0);
        root.add(txtSurname,3,0);
        txtSurname.setPrefWidth(350);
        // Fila 2
        root.add(lblCorreo,0,1);
        root.add(txtEmail,1,1);
        // Fila 3
        root.add(lblInstitution,0,2);
        root.add(cmbInstitution,1,2);
        // Fila 4
        root.add(lblLinkedin,0,3);
        root.add(txtLinkedin,1,3);
        // Fila 5
        root.add(lblPassword,0,4);
        root.add(txtPassword,1,4);
        root.add(lblRepeatPassword,2,4);
        root.add(txtRepeatPassword,3,4);
        // Fila 6
        root.add(btnCancelar,2,5);
        root.add(btnRegister,3,5);
        // Asignamos las institutciones al combobox
        try {
            institutionList.addAll(institutionService.loadAllInstitutionsNames());
        } catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la base de datos");
            alert.setHeaderText("Error recuperando las instituciones");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
        // Asignamos la interactividad a los botones
        btnCancelar.setOnAction(actionEvent -> Navigation.navigate("LoginScreen"));
        btnRegister.setOnAction(actionEvent -> {
            try {
                userService.insertUser(new User(
                        0, // asignamos un iD por defecto
                        txtName.getText(),
                        txtSurname.getText(),
                        txtEmail.getText(),
                        cmbInstitution.getValue().getId(),
                        txtLinkedin.getText(),
                        txtPassword.getText()),txtRepeatPassword.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro del usuario");
                alert.setHeaderText(null);
                alert.setContentText("Se ha completado el registro");
                alert.showAndWait();
                Navigation.navigate("LoginScreen");
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error en el registro");
                alert.setHeaderText("No se ha completado el registro");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });
    }
}
