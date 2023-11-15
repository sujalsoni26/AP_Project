module com.example.project_deadline1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_deadline1 to javafx.fxml;
    exports com.example.project_deadline1;
}