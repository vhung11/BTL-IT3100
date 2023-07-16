package com.example.btloop;

import com.example.btloop.Models.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionInfoController implements Initializable {
    @FXML
    private Button btnEdit;
    @FXML
    private Label infoQuestionLabel;
    @FXML
    private CheckBox selectCheckBox;
    private Question question;
    private String categoryName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEdit.setOnAction(actionEvent -> {
            ViewFactory.getInstance().routes(ViewFactory.SCENES.EDIT_QUESTION);
        });
    }

    public void updateInfoQuestion(Question question, String categoryName) {
        infoQuestionLabel.setText(question.getQuestionContent());
        this.question = question;
        this.categoryName = categoryName;
    }
}
