package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    Stage stageTodayPlans;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnTodayPlans;

    @FXML
    private JFXButton btnWorkingHistory;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnTodayPlansOnAction(ActionEvent event) {
        if(null==stageTodayPlans){
            stageTodayPlans=new Stage();
            try {
                stageTodayPlans.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/main_page_form.fxml"))));
                stageTodayPlans.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnWorkingHistoryOnAction(ActionEvent event) {

    }

}
