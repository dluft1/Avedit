module com.example.avedit {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.avedit to javafx.fxml;
    exports com.example.avedit;
}