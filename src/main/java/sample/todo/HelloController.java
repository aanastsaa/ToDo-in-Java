package sample.todo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HelloController {

    @FXML
    private Button add_b;

    @FXML
    private TextField field;

    @FXML
    private VBox tasks;

    DB db = null;

    @FXML
    void initialize() {

        // Инициируем объект
        db = new DB();

        // Обработчик события. Сработает при нажатии на кнопку
        add_b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            // Метод, что будет срабатывать
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Проверяем является ли поле заполненным
                    if (!field.getText().trim().equals("")) {
                        // Вызываем метод из класса DB
                        // через этот метод будет добавлено новое задание
                        db.insertTask(field.getText());
                        loadInfo(); // Метод для подгрузки заданий внутрь программы
                        field.setText(""); // Очищаем поле
                    }
                } catch (SQLException e) { // Отслеживаем ошибки
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // Метод для подгрузки заданий внутрь программы
        loadInfo();
    }

    // Метод для подгрузки заданий внутрь программы
    void loadInfo() {
        try {
            // Сначала очищаем от прошлых значений
            tasks.getChildren().clear();

            // Получаем все задания из базы данных
            ArrayList<String> taskList = db.getTasks();
            for (int i = 0; i < taskList.size(); i++) // Перебираем их через цикл
                // Добавляем каждое задание в объект VBox (all_tasks)
                tasks.getChildren().add(new Label(taskList.get(i)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
