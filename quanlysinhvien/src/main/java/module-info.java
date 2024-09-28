module com.group21 {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;

    opens com.group21 to javafx.fxml;
    exports com.group21;
}
