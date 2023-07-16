package com.example.btloop;

import com.example.btloop.Models.Category;
import com.example.btloop.Models.Question;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.nio.Buffer;
import java.util.*;

public class QuestionBankController implements Initializable {
    @FXML
    private ImageView btn_menu_return;
    @FXML
    private TabPane tabPane;

    // Tab Questions
    @FXML
    private ComboBox<String > btn_category;
    @FXML
    private TreeView<String> category;
    @FXML
    private Button btnCreateQuestion;
    @FXML
    private VBox container;
    @FXML
    private Label message;
    private final List<Question> questionList = new ArrayList<>();

    // Tab Categories
    private int parentId = -1;
    private final List<Category> categoryList = new ArrayList<>();
    @FXML
    private Button btnAddCategory;
    @FXML
    private ComboBox<String> btnCategory2;
    @FXML
    private TextArea categoryInfo;
    @FXML
    private TextField categoryName;
    @FXML
    private TreeView<String> categoryTreeView2;
    @FXML
    private TextField idNewCategory;
    @FXML
    private Label noticeAddCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure btn_menu_return - come back to home
        btn_menu_return.setOnMouseClicked(mouseEvent -> ViewFactory.getInstance().routes(ViewFactory.SCENES.HOME));

        TreeItem<String> rootNode = new TreeItem<>("Default");
        categoryTreeView2.setRoot(rootNode);
        category.setRoot(rootNode);
        updateCategory();

        // Tab Questions
        // Configure btnCreateQuestion
        btnCreateQuestion.setOnAction(actionEvent -> ViewFactory.getInstance().routes(ViewFactory.SCENES.ADD_QUESTION));

        // Configure btn_category
        btn_category.getParent().setOnMouseClicked(mouseEvent -> category.setVisible(false));
        btn_category.setOnMouseClicked(mouseEvent -> category.setVisible(!category.isVisible()));

        category.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TreeItem<String> selectedItem = category.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    container.getChildren().remove(message);

                    String categoryName = selectedItem.getValue();

                    btn_category.setValue(categoryName);
                    category.setVisible(false);
                }
            }
        });

        // Tab Categories
        categoryTreeView2.getParent().setOnMouseClicked(mouseEvent -> categoryTreeView2.setVisible(false));

        // Configure click to Default
        btnCategory2.setOnMouseClicked(mouseEvent -> {
            categoryTreeView2.setVisible(!categoryTreeView2.isVisible());
        });

        categoryTreeView2.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                TreeItem<String> selectedItem = categoryTreeView2.getSelectionModel().getSelectedItem();
                if (selectedItem.getValue().equals("Default")) {
                    parentId = 0;
                } else {
                    parentId = getParentId(selectedItem.getValue());
                }
                System.out.println(parentId);
                btnCategory2.setValue(selectedItem.getValue());
                categoryTreeView2.setVisible(false);
            }
        });

        // Configure btnAddCategory
        btnAddCategory.setOnAction(actionEvent -> {
            String name = categoryName.getText();
            String info = categoryInfo.getText();
            if (name.equals("") || idNewCategory.getText().equals("")) {
                noticeAddCategory.setText("Please fill in all fields required!");
            } else if (parentId < 0){
                noticeAddCategory.setText("Please choose a parent category!");
            } else {
                int id = Integer.parseInt(idNewCategory.getText());
                if (isIDExists(id)) {
                    noticeAddCategory.setText("ID is already existed!");
                } else if (isNameExists(name)) {
                    noticeAddCategory.setText("Name is already existed!");
                } else {
                    Category category = new Category(id, name, parentId, 0, info);
                    categoryList.add(category);
                    updateCategory();
                    noticeAddCategory.setText("Add category successfully!");
                }
            }
        });
    }

    public void setTabPane(int index) {
        tabPane.getSelectionModel().select(index);
    }

    private int getParentId(String categoryName) {
        for (Category category1 : categoryList) {
            if (category1.getCategoryName().equals(categoryName)) return category1.getCategoryID();
        }
        return 0;
    }

    private int getID(String categoryName) {
        for (Category category1 : categoryList) {
            if (category1.getCategoryName().equals(categoryName)) return category1.getCategoryID();
        }
        return 0;
    }

    private void updateCategory() {
        TreeItem<String> rootNode = new TreeItem<>("Default");
        Map<Integer, TreeItem<String>> treeItems = new HashMap<>();
        treeItems.put(0, rootNode);

        for (Category category1 : categoryList) {
            TreeItem<String> treeItem = new TreeItem<>(category1.getCategoryName());
            treeItems.put(category1.getCategoryID(), treeItem);
            treeItems.get(category1.getParentID()).getChildren().add(treeItem);
        }

        rootNode.setExpanded(true);
        expandAll(rootNode);

        categoryTreeView2.setRoot(rootNode);
        category.setRoot(rootNode);
    }

    private void expandAll(TreeItem<?> item) {
        if (item != null && !item.isLeaf()) {
            item.setExpanded(true);
            for (TreeItem<?> child : item.getChildren()) {
                expandAll(child);
            }
        }
    }

    private boolean isIDExists(int ID) {
        for (Category category1 : categoryList) {
            if (category1.getCategoryID() == ID) {
                return true;
            }
        }
        return false;
    }

    private boolean isNameExists(String name) {
        for (Category category1 : categoryList) {
            if (category1.getCategoryName().equals(name)) return true;
        }
        return false;
    }
}
