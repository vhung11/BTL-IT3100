package com.example.btloop.Models;

public class Question {
    private int question_id;
    private String question_name;
    private String question_text;
    private String question_image;
    private int mark = 1;
    private int category_id;

    public Question(int id, String question_text, String image) {
        this.question_id = id;
        this.question_text = question_text;
        this.question_image = image;
        category_id = 0;
    }

    public Question(String question_name, String question_text, String image, int mark, int category_id) {
        this.question_name = question_name;
        this.question_text = question_text;
        this.mark = mark;
        this.question_image = image;
        this.category_id = category_id;
    }

    public Question(int question_id, String question_name, String question_text, String image, int mark, int category_id) {
        this.question_id = question_id;
        this.question_name = question_name;
        this.question_text = question_text;
        this.question_image = image;
        this.mark = mark;
        this.category_id = category_id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(String question_image) {
        this.question_image = question_image;
    }

    public int getMark() {
        return mark;
    }

    public String getQuestionContent() {
        return question_name + ": " + question_text;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setInfo(String question_name, String question_text, int mark, int category_id) {
        this.question_name = question_name;
        this.question_text = question_text;
        this.mark = mark;
        this.category_id = category_id;
    }
}
