module com.example.stickhero_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stickhero_2 to javafx.fxml;
    exports com.example.stickhero_2;
}