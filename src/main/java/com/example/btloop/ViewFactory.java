package com.example.btloop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private static ViewFactory instance;
    private final Stage stage;
    private Scene homeScene;
    private Scene questionBankScene;
    private QuestionBankController questionBankController;
    private Scene addQuestionScene;
    private Scene editQuestionScene;
    private EditQuestionController editQuestionController;
    private Scene addQuizScene;

    private ViewFactory() {
        stage = new Stage();

        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomeView.fxml"));
        FXMLLoader addQuestionLoader = new FXMLLoader(getClass().getResource("AddQuestionView.fxml"));
        FXMLLoader editQuestionLoader = new FXMLLoader(getClass().getResource("EditQuestionView.fxml"));
        FXMLLoader addQuizLoader = new FXMLLoader(getClass().getResource("AddQuizView.fxml"));
        try {
            homeScene = new Scene(homeLoader.load());

            HomeController homeController = homeLoader.getController();
            questionBankController = homeController.getQuestionBankController();
            questionBankScene = new Scene(homeController.getQuestionBankView());

            addQuestionScene = new Scene(addQuestionLoader.load());

            editQuestionScene = new Scene(editQuestionLoader.load());
            editQuestionController = editQuestionLoader.getController();

            addQuizScene = new Scene(addQuizLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Quiz App");
        stage.setResizable(false);
        stage.setScene(homeScene);
        stage.show();
    }

    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    public void routes(SCENES scene) {
        switch (scene) {
            case HOME: {
                stage.setScene(homeScene);
                break;
            }
            case QUESTION_BANK: {
                stage.setScene(questionBankScene);
                break;
            }
            case ADD_QUESTION: {
                stage.setScene(addQuestionScene);
                break;
            }
            case EDIT_QUESTION: {
                stage.setScene(editQuestionScene);
                break;
            }
            case ADD_QUIZ: {
                stage.setScene(addQuizScene);
                break;
            }
            default:
                stage.setScene(homeScene);
        }
    }
    public enum SCENES {
        HOME,
        QUESTION_BANK,
        ADD_QUESTION,
        EDIT_QUESTION,
        ADD_QUIZ
    }
}