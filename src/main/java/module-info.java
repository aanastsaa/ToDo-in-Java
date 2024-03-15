module sample.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens sample.todo to javafx.fxml;
    exports sample.todo;
}