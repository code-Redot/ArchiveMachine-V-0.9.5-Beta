package com.Redot;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class DirectorySelectorApp extends Application {

    public TextField sourceDirectoryField;
    public TextField destinationDirectoryField;
    private TextField folderItemLimiter;

    @Override
    public void start(Stage primaryStage) {

        // create grid pane to hold UI elements
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // source directory selector
        Button sourceDirectoryButton = new Button("Select...");
        Label sourceDirectoryLabel = new Label("Source Directory:");
        sourceDirectoryField = new TextField();
        sourceDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                sourceDirectoryField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        // target directory selector
        Button targetDirectoryButton = new Button("Select...");
        Label targetDirectoryLabel = new Label("Destination Directory:");
        destinationDirectoryField = new TextField();

        targetDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                destinationDirectoryField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        // folder item limitation
        Label folderItemLimiterLabel = new Label("Items in folder limit (number):");
        folderItemLimiter = new TextField();
        destinationDirectoryField.setEditable(true);

        // ignore shortcuts check box
        CheckBox ignoreShortcutsCheckBox = new CheckBox("Ignore Shortcuts");

        // start button
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {

            // do file copying here

            //destination naming
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy MMM");
            String destination = destinationDirectoryField.getText() + "\\" + (myDateObj.format(myFormatObj));


            System.out.println(sourceDirectoryField.getText()); //DELETE ME!!!



            //Checks if source exists
            if (!DirectoryFileFilter.DIRECTORY.accept(new File(sourceDirectoryField.getText()))) {
                System.out.println("Source does not exist \nExiting...");
                System.exit(0);
            }

            //Checks if destination exists
            if (!DirectoryFileFilter.DIRECTORY.accept(new File(destinationDirectoryField.getText()))) {
                System.out.println("Creating the destination...");
                try {
                    FileUtils.forceMkdir(new File(destinationDirectoryField.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            try {
                CopyMachine.copyRecurseHandler(sourceDirectoryField.getText(), destination, Integer.parseInt(folderItemLimiter.getText()), 1, ignoreShortcutsCheckBox.isSelected());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            // end file copying here
        });


        // add UI elements to grid pane
        gridPane.add(sourceDirectoryLabel, 0, 0);
        gridPane.add(sourceDirectoryField, 1, 0);
        gridPane.add(sourceDirectoryButton, 2, 0);
        gridPane.add(targetDirectoryLabel, 0, 1);
        gridPane.add(destinationDirectoryField, 1, 1);
        gridPane.add(targetDirectoryButton, 2, 1);
        gridPane.add(folderItemLimiterLabel, 0, 2);
        gridPane.add(folderItemLimiter, 1, 2);
        gridPane.add(ignoreShortcutsCheckBox, 2, 2);

        gridPane.add(startButton, 1, 3);

        // create vertical box to hold grid pane and log label
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        //vbox.getChildren().addAll(gridPane, logLabel);
        vbox.getChildren().addAll(gridPane);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        // show the window

        primaryStage.setResizable(false);
        primaryStage.show();


    }
    public static void starter(){
        launch();
    }


}

