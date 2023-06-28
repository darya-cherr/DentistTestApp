module com.example.dentisttestapp {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires org.burningwave.core;
    requires itextpdf;
    requires java.desktop;

    opens com.example.dentisttestapp to javafx.fxml;
    exports com.example.dentisttestapp;
}