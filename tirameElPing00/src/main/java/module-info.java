module com.example.tirameelping00 {
    requires javafx.controls;
    requires javafx.fxml;
    requires DS.Desktop.Notify;


    opens com.example.tirameelping00 to javafx.fxml;
    exports com.example.tirameelping00;
}