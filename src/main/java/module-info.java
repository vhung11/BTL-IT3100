module com.example.btloop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.btloop to javafx.fxml;
    exports com.example.btloop;
}