module com.ml.crudapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ml.crudapp to javafx.fxml;
    exports com.ml.crudapp;
}