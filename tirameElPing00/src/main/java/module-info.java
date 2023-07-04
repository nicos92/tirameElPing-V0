module com.example.tirameelping00 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.dustinredmond.fxtrayicon;
    requires controlsfx;
    requires TrayTester;


    opens com.example.tirameelping00 to javafx.fxml;
    exports com.example.tirameelping00;
}