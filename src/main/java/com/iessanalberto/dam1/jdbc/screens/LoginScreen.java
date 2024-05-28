package com.iessanalberto.dam1.jdbc.screens;

import com.iessanalberto.dam1.jdbc.navigation.Navigation;
import com.iessanalberto.dam1.jdbc.services.UserService;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginScreen {
    // Definimos los elementos de layout
    public VBox root = new VBox();
    GridPane gridPane = new GridPane();
    // Definimos los compoenentes de mi ventana

    ImageView logoView = new ImageView(new Image("logo.png"));

    Label lblUser = new Label("Usuario");
    TextField txtUser = new TextField();
    Label lblPassword = new Label("Contraseña");
    PasswordField txtPassword = new PasswordField();
    Button btnConectar = new Button("Conectar");
    Hyperlink linkRegister = new Hyperlink("Registrarse");
    Hyperlink linkInvitado = new Hyperlink("Acceso invitados");

    // Definimos los servicios que puede emplear mi ventana
    UserService userService = new UserService();

    public LoginScreen(){
        // Configurar los elementos de layout
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        // Ajustamos la imagen
        logoView.setFitWidth(250);
        logoView.setPreserveRatio(true);
        // Asignamos los componentes al layout correspondiente
        gridPane.add(lblUser,0,0);
        gridPane.add(txtUser,1,0);
        gridPane.add(lblPassword,0,1);
        gridPane.add(txtPassword,1,1);
        gridPane.add(btnConectar,1,2);
        gridPane.add(linkRegister,0,4);
        gridPane.add(linkInvitado,1,4);
        root.getChildren().addAll(logoView,gridPane);
        // Definimos la interactividad de mi ventana
        linkRegister.setOnAction(actionEvent -> Navigation.navigate("RegisterScreen"));
        linkInvitado.setOnAction(actionEvent -> Navigation.navigate("ProjectsScreen"));
        btnConectar.setOnAction(actionEvent -> {
            try {
                userService.loginUser(txtUser.getText(),txtPassword.getText());
                Navigation.navigate("ProjectsScreen");
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });
    }
}
