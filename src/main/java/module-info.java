module com.iessanalberto.dam1.jdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.iessanalberto.dam1.jdbc to javafx.fxml;
    opens com.iessanalberto.dam1.jdbc.models to javafx.base;
    exports com.iessanalberto.dam1.jdbc;
    opens com.iessanalberto.dam1.jdbc.models.DTO to javafx.base;
}