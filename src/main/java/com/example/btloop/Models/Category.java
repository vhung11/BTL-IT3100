package com.example.btloop.Models;

public class Category {
    private final int categoryID;
    private final String categoryName;
    private final int parentID;
    private final int questionCount;
    private final String categoryInfo;

    public Category(int categoryID, String categoryName, int parentID, int questionCount, String categoryInfo) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.parentID = parentID;
        this.questionCount = questionCount;
        this.categoryInfo = categoryInfo;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public static String getCategoryName(String categoryName) {
        int openParentIndex = categoryName.indexOf(" (");

        // If we have no " ("
        if (openParentIndex < 0) {
            openParentIndex = categoryName.length();
        }
        return categoryName.substring(0, openParentIndex);
    }

    public int getParentID() {
        return parentID;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        if (questionCount == 0) {
            return categoryName;
        } else {
            return categoryName + " (" + questionCount +")";
        }
    }
}
