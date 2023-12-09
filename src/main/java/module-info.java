module com.example.stickhero_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires javafx.media;


    opens com.example.stickhero_2 to javafx.fxml;
    exports com.example.stickhero_2;
}