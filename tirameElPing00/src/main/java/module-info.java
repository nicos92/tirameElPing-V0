module com.example.tirameelping {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.dustinredmond.fxtrayicon;
    requires TrayTester;
    requires DS.Desktop.Notify;
    requires java.logging;
    requires java.sql;


    opens com.example.tirameelping00 to javafx.fxml;
    exports com.example.tirameelping00;
    exports com.example.tirameelping00.baseDatos;
    opens com.example.tirameelping00.baseDatos to javafx.fxml;
}