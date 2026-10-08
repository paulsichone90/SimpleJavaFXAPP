module com.example.lodgebilling {

    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.lodgebilling;

    opens com.example.lodgebilling to javafx.fxml;
}