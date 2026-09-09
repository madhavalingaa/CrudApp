package com.ml.crudapp;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    // ---- "Database" : in-memory list of students ----
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    // UI components that need to be accessed across methods
    private TableView<Student> table = new TableView<>();
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField ageField = new TextField();

    private int nextId = 1; // auto-increment ID counter

    @Override
    public void start(Stage primaryStage) {

        // ---------- TABLE SETUP ----------
        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(idCol, nameCol, ageCol);
        table.setItems(studentList);

        // When a row is clicked, load its data into the text fields (for editing)
        table.setOnMouseClicked(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                idField.setText(String.valueOf(selected.getId()));
                nameField.setText(selected.getName());
                ageField.setText(String.valueOf(selected.getAge()));
            }
        });

        // ---------- FORM FIELDS ----------
        idField.setPromptText("ID (auto)");
        idField.setEditable(false); // ID is auto-generated, not user-typed

        nameField.setPromptText("Name");
        ageField.setPromptText("Age");

        // ---------- BUTTONS ----------
        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button clearBtn = new Button("Clear");

        addBtn.setOnAction(e -> addStudent());
        updateBtn.setOnAction(e -> updateStudent());
        deleteBtn.setOnAction(e -> deleteStudent());
        clearBtn.setOnAction(e -> clearFields());

        HBox buttonBox = new HBox(10, addBtn, updateBtn, deleteBtn, clearBtn);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        // ---------- FORM LAYOUT ----------
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));
        form.addRow(0, new Label("ID:"), idField);
        form.addRow(1, new Label("Name:"), nameField);
        form.addRow(2, new Label("Age:"), ageField);

        VBox formBox = new VBox(form, buttonBox);

        // ---------- MAIN LAYOUT ----------
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setCenter(table);
        root.setBottom(formBox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("JavaFX CRUD Example - Student Records");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ---------- CREATE ----------
    private void addStudent() {
        String name = nameField.getText();
        String ageText = ageField.getText();

        if (name.isEmpty() || ageText.isEmpty()) {
            showAlert("Please fill in Name and Age.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Student student = new Student(nextId++, name, age);
            studentList.add(student);
            clearFields();
        } catch (NumberFormatException ex) {
            showAlert("Age must be a number.");
        }
    }

    // ---------- UPDATE ----------
    private void updateStudent() {
        Student selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a row to update.");
            return;
        }

        try {
            selected.setName(nameField.getText());
            selected.setAge(Integer.parseInt(ageField.getText()));
            table.refresh(); // refresh table to show updated values
            clearFields();
        } catch (NumberFormatException ex) {
            showAlert("Age must be a number.");
        }
    }

    // ---------- DELETE ----------
    private void deleteStudent() {
        Student selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a row to delete.");
            return;
        }
        studentList.remove(selected);
        clearFields();
    }

    // ---------- CLEAR FORM ----------
    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
        table.getSelectionModel().clearSelection();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // ---------- MODEL CLASS ----------
    public static class Student {
        private int id;
        private String name;
        private int age;

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}