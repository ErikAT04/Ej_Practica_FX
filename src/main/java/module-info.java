module com.erikat.ej_repaso_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.erikat.ej_repaso_fx to javafx.fxml;
    exports com.erikat.ej_repaso_fx;
}