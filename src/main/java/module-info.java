module group1.csc311hw1 {
    requires com.google.gson;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens group1.csc311hw1 to javafx.fxml, com.google.gson;
    exports group1.csc311hw1;
}
