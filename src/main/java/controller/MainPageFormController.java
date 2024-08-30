package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.ToDoItem;

import java.awt.event.ActionListener;
import java.awt.image.SinglePixelPackedSampleModel;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainPageFormController implements Initializable {

    MainService service = new MainController();

    String selectedToDoTask;

    public JFXButton btnAddTask;

    public JFXCheckBox checkBox;

    public Label lblDate;

    public Label lblTime;
    @FXML
    private JFXListView<ToDoItem> listViewDone;

    @FXML
    private ListView<ToDoItem> listViewToD0;

    @FXML
    private TextField txtToDo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(String.valueOf(LocalDate.now()));

        currentTime();

        loadToDoListView();
        loadDoneListView();
    }

    private String currentTime(){

        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private void loadToDoListView() {

        listViewToD0.setCellFactory(listView -> new ListCell<ToDoItem>() {
            private CheckBox checkBox;

            @Override
            protected void updateItem(ToDoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    checkBox = new CheckBox();
                    checkBox.setText(item.getTaskDescription());
                    checkBox.setSelected(false); // Reset the checkbox state
                    checkBox.setDisable(!listViewToD0.getSelectionModel().isSelected(getIndex()));

                    listViewToD0.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                        if (newValue != null && newValue.equals(item)) {
                            selectedToDoTask=newValue.getTaskDescription();
                            checkBox.setDisable(false);
                        } else {
                            checkBox.setDisable(true);
                        }
                    });

                    checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                        if (isNowSelected) {
                            if (service.addDoneList(selectedToDoTask)){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Well Done !!!");
                                alert .show();
                                service.updateDoneList(lblDate.getText(),currentTime(),selectedToDoTask);
                                loadToDoListView();
                                loadDoneListView();
                            }
                        }
                    });

                    setGraphic(checkBox);
                }
            }
        });

        // Load the ToDo list into the ListView
        listViewToD0.setItems(service.loadToDoList());
    }


    public void btnAddTaskOnAction(ActionEvent actionEvent) {
        if (service.addTask(lblDate.getText(),txtToDo.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Task Added...");
            alert.show();
            loadToDoListView();
        }
    }

    private void loadDoneListView(){

        listViewDone.setCellFactory(listView1 -> new ListCell<ToDoItem>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(ToDoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    checkBox.setSelected(true);
                    checkBox.setDisable(true);
                    checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) ->
                            item.isDoneProperty().set(isNowSelected)
                    );
                    setGraphic(checkBox);
                    setText(item.getTaskDescription());
                }
            }
        });

        listViewDone.setItems(service.loadDoneList());

    }

}
