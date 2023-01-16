package notepadapp;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MenuEventsHandling {

    public MenuEventsHandling() {
    }

    Set<KeyCode> extended;
    FilesHandling filesHandling;
    File file;
    StringBuffer textOfOpenedFile, lastTextSaved, currentText;
    boolean firstSave = true;
    FXMLNotepadBase fxmlNotepadBase;
    Stage stage;

    public MenuEventsHandling(FXMLNotepadBase fxmlNotepadBase, Stage stage) {
        this.stage = stage;
        this.fxmlNotepadBase = fxmlNotepadBase;
        filesHandling = new FilesHandling();
        lastTextSaved = new StringBuffer();
        currentText = new StringBuffer();
        textOfOpenedFile = new StringBuffer();
        extended = new HashSet<>();
        createExtendedKeysSet();
    }

    public void save() {
        fxmlNotepadBase.saveFileItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (firstSave) {
                        FileChooser fileChooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                        fileChooser.getExtensionFilters().add(extFilter);
                        file = fileChooser.showSaveDialog(stage);
                        if (file != null) {
                            filesHandling.saveFileForFirstTime(file, fxmlNotepadBase.textArea.getText());
                            firstSave = false;
                            stage.setTitle(file.getName().replace(".txt", ""));
                        }
                    } else {
                        filesHandling.saveFile(file, fxmlNotepadBase.textArea.getText());
                    }
                    if (stage.getTitle().charAt(0) == '*') {
                        stage.setTitle(stage.getTitle().substring(1));
                    }
                    
                    filesHandling.editSettingsFile(file.getPath(), fxmlNotepadBase.fontsComp.getValue().toString(), fxmlNotepadBase.sizeSlider.getValue()
                            + "", fxmlNotepadBase.colorPicker.getValue().toString().substring(2));
                    
                    lastTextSaved = new StringBuffer(fxmlNotepadBase.textArea.getText());     //to check if file is saved when clicking on new or exit options
                    
                    fxmlNotepadBase.renameItem.setDisable(false);
                } catch (IOException ex) {
                }
            }
        });
    }

    public void open() {
        fxmlNotepadBase.openFileItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    textOfOpenedFile = filesHandling.openFile(file);
                    fxmlNotepadBase.textArea.setText(filesHandling.openFile(file).toString());
                    currentText = new StringBuffer(textOfOpenedFile);
                    lastTextSaved = new StringBuffer(textOfOpenedFile);
                    firstSave = false;
                    stage.setTitle(file.getName().replace(".txt", ""));
                    fxmlNotepadBase.renameItem.setDisable(false);
                    countWordsAndLetters(fxmlNotepadBase.textArea.getText());
                    if (filesHandling.getSettingsOfFile(file) != null) {
                        applyEdits(filesHandling.getSettingsOfFile(file));
                    } else {               //file settings not exist so the following statements set it to the default settings
                        fxmlNotepadBase.textArea.setStyle("-fx-font-family: \"Arial Black\"; -fx-text-fill: black; -fx-font-size: 20;");
                        fxmlNotepadBase.colorPicker.setValue(Color.BLACK);
                        fxmlNotepadBase.fontsComp.setValue("Arial Black");
                        fxmlNotepadBase.sizeSlider.setValue(20);

                    }
                }
            }
        });
    }

    public void applyEdits(String[] fileEdits) {
        fxmlNotepadBase.fontsComp.setValue(fileEdits[1]);
        fxmlNotepadBase.sizeSlider.setValue(Double.parseDouble(fileEdits[2]));
        fxmlNotepadBase.colorPicker.setValue(Color.web("#" + fileEdits[3]));
        fxmlNotepadBase.textArea.setStyle("-fx-font-family: \"" + fileEdits[1] + "\";" + "\n" + "-fx-text-fill: #"
                + fileEdits[3] + ";" + "-fx-font-size: " + Double.parseDouble(fileEdits[2]) + ";");

    }

    public void editText() {
        fxmlNotepadBase.textArea.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!extended.contains(event.getCode()) && !event.isControlDown()) {
//                    System.out.println(event);
                    countWordsAndLetters(fxmlNotepadBase.textArea.getText());
                    currentText = new StringBuffer(fxmlNotepadBase.textArea.getText());
                    if (stage.getTitle().charAt(0) != '*') {
                        stage.setTitle("*" + stage.getTitle());
                    }
                }
            }
        });
    }

    public void newFile() {
        fxmlNotepadBase.NewFileItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (lastTextSaved.toString().equals(currentText.toString())) {
                    openNewFile();

                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to save changes?", "Alert", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.NO_OPTION) {
                        openNewFile();

                    }
                    else if (choice == JOptionPane.YES_OPTION) {
                        fxmlNotepadBase.saveFileItem.fire();
                        openNewFile();
                    }
                }
            }
        });
    }

    private void openNewFile() {
        fxmlNotepadBase.textArea.setText("");
        stage.setTitle("new document");
        firstSave = true;
        fxmlNotepadBase.renameItem.setDisable(true);
    }

    public void undo() {
        fxmlNotepadBase.undoItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.undo();
            }
        });
    }

    public void redo() {
        fxmlNotepadBase.redoItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.redo();

            }
        });
    }

    public void cut() {
        fxmlNotepadBase.cutItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.cut();
            }
        });
    }

    public void copy() {
        fxmlNotepadBase.copyItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.copy();
            }
        });
    }

    public void selectAll() {
        fxmlNotepadBase.selectItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.selectAll();
            }
        });
    }

    public void paste() {
        fxmlNotepadBase.pasteItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.paste();
            }
        });
    }

    public void delete() {
        fxmlNotepadBase.deleteItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.setText("");
            }
        });
    }

    public void exit() {
        fxmlNotepadBase.exitItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (lastTextSaved.toString().equals(currentText.toString())) {
                    stage.close();
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to save changes?", "Alert", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.NO_OPTION) {
                        stage.close();
                    }
                    else if (choice == JOptionPane.YES_OPTION) {
                        fxmlNotepadBase.saveFileItem.fire();
                        stage.close();
                    }
                }
            }
        });
    }

    public void rename() {
        fxmlNotepadBase.renameItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(null, "Enter File Name");
                if (fileName != null) {
                    if (stage.getTitle().charAt(0) == '*') {
                        stage.setTitle("*" + fileName);
                    } else {
                        stage.setTitle(fileName);
                    }

                    File temp = new File(file.getPath().replace(file.getName(), fileName) + ".txt");
                    file.delete();
                    file= new File(temp.getPath());
                    temp.delete();
                    fxmlNotepadBase.saveFileItem.fire();
                    
                    try {
                        filesHandling.renameDocInSettingsFile(file, fileName);
                    } catch (IOException ex) {
                    }
                   
                }
            }
        });

    }
    
    
     public void help() {
        fxmlNotepadBase.helpMenu.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                JOptionPane.showMessageDialog( null,  "My Notepad by Martina Naeem",  "About", JOptionPane.INFORMATION_MESSAGE); 
              
            }
        });

    }
    

    private void countWordsAndLetters(String text) {
        fxmlNotepadBase.wordsNum.setText("Words: " + text.trim().split("\\s+").length);
        fxmlNotepadBase.lettersNum.setText("letters: " + text.replaceAll("\n|\\s", "").length());
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public final void createExtendedKeysSet() {
        extended.add(CAPS);
        extended.add(UP);
        extended.add(DOWN);
        extended.add(LEFT);
        extended.add(RIGHT);
        extended.add(TAB);
        extended.add(CONTROL);
        extended.add(ESCAPE);
        extended.add(HOME);
        extended.add(END);
        extended.add(UNDEFINED);
        extended.add(SHIFT);
        extended.add(PAGE_UP);
        extended.add(PAGE_DOWN);
        extended.add(ALT_GRAPH);
        extended.add(WINDOWS);
        extended.add(F1);
        extended.add(F2);
        extended.add(F3);
        extended.add(F4);
        extended.add(F5);
        extended.add(F6);
        extended.add(F7);
        extended.add(F8);
        extended.add(F9);
        extended.add(F10);
        extended.add(F11);
        extended.add(F12);

    }

}
