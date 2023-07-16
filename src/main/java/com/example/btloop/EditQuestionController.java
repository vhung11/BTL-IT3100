package com.example.btloop;

import com.example.btloop.Models.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditQuestionController implements Initializable {
    @FXML
    private ImageView btn_menu_return;
    private final List<ChoiceBoxController> listChoiceBoxController = new ArrayList<>();
    private int countChoice = 0;
    @FXML
    private Label labelAlert;
    @FXML
    private TextField text_DefaultMark;
    @FXML
    private TextField text_QuestionName;
    @FXML
    private TextArea text_QuestionText;
    @FXML
    private VBox vBoxAddChoiceBox;
    @FXML
    private ComboBox<String> kindOfCategory;
    @FXML
    private Button btn_blankChoice;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btnInsertPicture;
    private ImageView imageView;
    @FXML
    private VBox vBoxAddImage;
    private Question question;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add 2 choiceBox
        addChoiceBox(2);
        text_DefaultMark.setText("1");

        // Configure btn_menu_return
        btn_menu_return.setOnMouseClicked(mouseEvent -> {
            this.reset();
            ViewFactory.getInstance().routes(ViewFactory.SCENES.HOME);
        });

        // Configure btn_blankChoice
        btn_blankChoice.setOnAction(actionEvent -> {
            if (countChoice < 5) {
                addChoiceBox(3);
            } else {
                labelAlert.setText("You can add only 5 choices!");
            }
        });

        // When "Cancel" is clicked, return to the QuestionBank view.
        btn_cancel.setOnAction(event -> {
            this.reset();
            ViewFactory.getInstance().routes(ViewFactory.SCENES.QUESTION_BANK);
        });

        // Configure btnInsertPicture
        btnInsertPicture.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image File");
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                String imageUrl = selectedFile.toURI().toString();
                Image image = new Image(imageUrl);
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
            }
            vBoxAddImage.getChildren().add(imageView);
        });
    }

    private void addChoiceBox(int numberChoice) {
        try {
            for (int i = 0; i < numberChoice; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoiceBoxView.fxml"));
                Parent root = loader.load();
                ChoiceBoxController choiceBoxController = loader.getController();
                choiceBoxController.setNumberChoice(countChoice + 1);
                listChoiceBoxController.add(choiceBoxController);
                vBoxAddChoiceBox.getChildren().add(countChoice++, root);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reset all input fields to their initial state
    private void reset() {
        for (ChoiceBoxController choiceBoxController : listChoiceBoxController) {
            choiceBoxController.reset();
        }
        labelAlert.setText("");
        text_QuestionName.setText(null);
        text_QuestionText.setText(null);
        text_DefaultMark.setText("1");
        kindOfCategory.setValue(null);
    }

    public void setInfo(Question oldQuestion, String categoryName) {
        this.question = oldQuestion;
        text_QuestionName.setText(oldQuestion.getQuestion_name());
        text_QuestionText.setText(oldQuestion.getQuestion_text());
        kindOfCategory.setValue(categoryName);
        text_DefaultMark.setText(String.valueOf(oldQuestion.getMark()));
    }
}
