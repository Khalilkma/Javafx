module com.primeiro.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.primeiro.javafx to javafx.fxml;
    opens com.primeiro.javafx.model.entities to javafx.base;
    exports com.primeiro.javafx;
    exports com.primeiro.javafx.Controllers;
    opens com.primeiro.javafx.Controllers to javafx.fxml;
}