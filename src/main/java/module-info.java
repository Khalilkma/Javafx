module com.primeiro.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.primeiro.javafx to javafx.fxml;
    exports com.primeiro.javafx;
    exports com.primeiro.javafx.Controllers;
    opens com.primeiro.javafx.Controllers to javafx.fxml;
}