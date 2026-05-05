module com.habis.notatnik {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.habis.notatnik to javafx.fxml;
    exports com.habis.notatnik;
}