package com.example.btloop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChoiceBoxController implements Initializable {
    private static double totalGrade = 0;
    private final String[] gradeList = {"None", "100%", "83.333333%", "80%", "75%", "70%", "66,66667%", "60%", "50%", "40%",
            "33,33333%", "30%", "25%", "20%", "16.66667%", "14.28571%", "12.5%", "11.11111%", "10%", "5%", "-5%", "-10%",
            "-11.11111%", "-12.5%", "-14.28571%", "-16.66667%", "-20%", "-25%", "-30%", "-33,33333%", "-40%", "-50%", "-60%", "-66,66667%",
            "-70%", "-75%", "-80%", "-83.333333%"};
    @FXML
    private Label choiceNumberLabel;
    @FXML
    private TextField choiceTextField;
    @FXML
    private ComboBox<String> gradeComboBox;
    @FXML
    private Button btnInsertPicture;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox vBoxAddPicture;

    public static int getTotalGrade() {
        return (int) totalGrade;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Do not call reset because it will reset totalGrade to 0
        choiceTextField.setText("");
        gradeComboBox.setDisable(true);
        gradeComboBox.setValue("None");
        gradeComboBox.getItems().addAll(gradeList);

        choiceTextField.textProperty().addListener((observableValue, oldValue, newValue)
                -> gradeComboBox.setDisable(newValue.equals("")));

        gradeComboBox.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            totalGrade += getGrade(newValue) - getGrade(oldValue);
            if (totalGrade < 0) {
                totalGrade = 0;
            }
        });

        // Configure btnInsertPicture
        btnInsertPicture.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image File");
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                String imageUrl = selectedFile.toURI().toString();
                Image image = new Image(imageUrl);
                imageView.setImage(image);
            }
            vBoxAddPicture.getChildren().add(imageView);
            gradeComboBox.setDisable(false);
        });
    }

    private double getGrade(String grade) {
        if (grade == null || Objects.equals(grade, "None")) {
            return 0;
        } else {
            // Remove "%" from string
            grade = grade.substring(0, grade.length() - 1);
            grade = grade.replace(",", ".");
            return Double.parseDouble(grade);
        }
    }

    public void setNumberChoice(int num) {
        choiceNumberLabel.setText("Choice " + num);
    }

    public void reset() {
        totalGrade = 0;
        choiceTextField.setText("");
        gradeComboBox.setDisable(true);
        gradeComboBox.setValue("None");
    }
}
