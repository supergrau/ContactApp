module com.future.contactapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.future.contactapp to javafx.fxml;
    exports com.future.contactapp;
    exports com.future.contactapp.controller;
    opens com.future.contactapp.controller to javafx.fxml;
}