module com.future.contactapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.future.contactapp to javafx.fxml;
    exports com.future.contactapp;
    exports com.future.contactapp.controller;
    exports com.future.contactapp.model;
//    opens com.future.contactapp.view to javafx.fxml;
    opens com.future.contactapp.controller to javafx.fxml;
}