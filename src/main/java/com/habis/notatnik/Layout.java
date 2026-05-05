package com.habis.notatnik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Layout {
    private Stage stage;
    private String usrHome = System.getProperty("user.home");
    private String separator = File.separator;
    private String documentPath = usrHome + separator + "Documents";

    @FXML
    TextArea textArea;
    private ListView pointList;

    public void setPointList(ListView pointList) {
        this.pointList = pointList;
        pointList.setCellFactory();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        this.stage.setTitle("Notatnik Professional");
    }

    @FXML
    public void zapiszPlik(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz plik");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "Save as txt", List.of("*.txt")
        );
        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(
                "Save as *", List.of("*.*")
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.getExtensionFilters().add(extensionFilter1);
        fileChooser.setInitialDirectory(new File(documentPath));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            try (FileWriter fileWriter = new FileWriter(selectedFile)) {
                fileWriter.write(textArea.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void otworzPlik(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "Text Files", List.of("*.txt"));
        FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(
                "Markdown Files", List.of("*.md"));
        FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter(
                "Microsoft Text Files", Arrays.asList("*.doc", "*.docx"));
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.getExtensionFilters().add(extensionFilter1);
        fileChooser.getExtensionFilters().add(extensionFilter2);
        fileChooser.setInitialDirectory(new File(documentPath));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try (Scanner scanner = new Scanner(selectedFile)) {
                StringBuilder contentOfFile = new StringBuilder();
                while (scanner.hasNextLine()) {
                    contentOfFile.append(scanner.nextLine()).append("\n");
                }
                textArea.setText(contentOfFile.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
