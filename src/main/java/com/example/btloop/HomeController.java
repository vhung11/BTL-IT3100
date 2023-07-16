package com.example.btloop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_category;
    @FXML
    private Button btn_export;
    @FXML
    private Button btn_import;
    @FXML
    private Button btn_question;
    @FXML
    private ScrollPane first_pane;
    @FXML
    private AnchorPane second_pane;
    private Parent questionBankView;
    private QuestionBankController questionBankController;
    @FXML
    private Button btn_turnEditingOn;

    /*
    * Reset Home status
    */
    private void reset() {
        second_pane.setVisible(false);
        first_pane.setVisible(true);
        first_pane.setOpacity(1);
        first_pane.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure btn_add, btn_back
        btn_add.setOnAction(e -> {
            second_pane.setVisible(true);
            first_pane.setOpacity(0.1);
            first_pane.setDisable(true);
        });

        btn_back.setOnAction(e -> reset());

        // Configure btn_question, btn_category, btn_import, btn_export
        FXMLLoader questionBankView = new FXMLLoader(getClass().getResource("QuestionBankView.fxml"));
        try {
            this.questionBankView = questionBankView.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        questionBankController = questionBankView.getController();

        btn_question.setOnAction(event -> {
            // Reset home status
            second_pane.setVisible(false);
            first_pane.setVisible(true);
            first_pane.setOpacity(1);
            first_pane.setDisable(false);
            // Switch to question tab
            ViewFactory.getInstance().routes(ViewFactory.SCENES.QUESTION_BANK);
            questionBankController.setTabPane(0);
        });

        btn_category.setOnAction(actionEvent -> {
            reset();
            // Switch to category tab
            ViewFactory.getInstance().routes(ViewFactory.SCENES.QUESTION_BANK);
            questionBankController.setTabPane(1);
        });

        btn_import.setOnAction(actionEvent -> {
            reset();
            // Switch to import tab
            ViewFactory.getInstance().routes(ViewFactory.SCENES.QUESTION_BANK);
            questionBankController.setTabPane(2);
        });

        btn_export.setOnAction(actionEvent -> {
            reset();
            // Switch to export tab
            ViewFactory.getInstance().routes(ViewFactory.SCENES.QUESTION_BANK);
            questionBankController.setTabPane(3);
        });

        // Configure btn_turnEditingOn
        btn_turnEditingOn.setOnAction(actionEvent -> {
            ViewFactory.getInstance().routes(ViewFactory.SCENES.ADD_QUIZ);
        });
    }

    public Parent getQuestionBankView() {
        return questionBankView;
    }

    public QuestionBankController getQuestionBankController() {
        return questionBankController;
    }
}
