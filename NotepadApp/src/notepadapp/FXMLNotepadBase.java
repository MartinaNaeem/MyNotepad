package notepadapp;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXMLNotepadBase extends BorderPane {

    protected final MenuBar menubar;
    protected final Menu fileMenu;
    protected final MenuItem NewFileItem;
    protected final MenuItem openFileItem;
    protected final MenuItem saveFileItem;
    protected final MenuItem exitItem;
    protected final Menu editMenu;
    protected final MenuItem undoItem;
    protected final MenuItem redoItem;
    protected final MenuItem cutItem;
    protected final MenuItem copyItem;
    protected final MenuItem pasteItem;
    protected final MenuItem deleteItem;
    protected final MenuItem selectItem;
    protected final MenuItem renameItem;
    protected final Menu helpMenu;
    protected final MenuItem aboutItem;
    protected final TextArea textArea;
    protected final Pane countsPane;
    protected final Label lettersNum;
    protected final Label wordsNum;
    protected final Pane settingsPane;
    protected final Label label;
    protected final Slider sizeSlider;
    protected final Label label0;
    protected final ColorPicker colorPicker;
    protected final ComboBox fontsComp;
    MenuEventsHandling menuEventsHandling;
    TextSettingEventsHandling textSettingEventsHandling;

    public FXMLNotepadBase(Stage primaryStage) {

        menubar = new MenuBar();
        fileMenu = new Menu();
        NewFileItem = new MenuItem();
        openFileItem = new MenuItem();
        saveFileItem = new MenuItem();
        exitItem = new MenuItem();
        editMenu = new Menu();
        undoItem = new MenuItem();
        redoItem = new MenuItem();
        cutItem = new MenuItem();
        copyItem = new MenuItem();
        pasteItem = new MenuItem();
        deleteItem = new MenuItem();
        selectItem = new MenuItem();
        renameItem = new MenuItem();
        helpMenu = new Menu();
        aboutItem = new MenuItem();
        textArea = new TextArea();
        countsPane = new Pane();
        lettersNum = new Label();
        wordsNum = new Label();
        settingsPane = new Pane();
        label = new Label();
        sizeSlider = new Slider();
        label0 = new Label();
        colorPicker = new ColorPicker();
        fontsComp = new ComboBox();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menubar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        NewFileItem.setMnemonicParsing(false);
        NewFileItem.setText("New");
        NewFileItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        openFileItem.setMnemonicParsing(false);
        openFileItem.setText("Open");
        openFileItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        saveFileItem.setMnemonicParsing(false);
        saveFileItem.setText("Save");
        saveFileItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        exitItem.setMnemonicParsing(false);
        exitItem.setText("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.CONTROL_DOWN));

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        undoItem.setMnemonicParsing(false);
        undoItem.setText("Undo");

        redoItem.setMnemonicParsing(false);
        redoItem.setText("Redo");

        cutItem.setMnemonicParsing(false);
        cutItem.setText("Cut");

        copyItem.setMnemonicParsing(false);
        copyItem.setText("Copy");

        pasteItem.setMnemonicParsing(false);
        pasteItem.setText("Paste");

        deleteItem.setMnemonicParsing(false);
        deleteItem.setText("Delete");

        selectItem.setMnemonicParsing(false);
        selectItem.setText("Select All");

        renameItem.setMnemonicParsing(false);
        renameItem.setText("Rename");
        renameItem.setDisable(true);

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        aboutItem.setMnemonicParsing(false);
        aboutItem.setText("About");
        setTop(menubar);

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(375.0);
        textArea.setPrefWidth(403.0);
        setCenter(textArea);

        BorderPane.setAlignment(countsPane, javafx.geometry.Pos.CENTER);
        countsPane.setPrefHeight(27.0);
        countsPane.setPrefWidth(600.0);

        lettersNum.setLayoutX(183.0);
        lettersNum.setLayoutY(4.0);
        lettersNum.setText("Letters: 0");
        lettersNum.setFont(new Font(14.0));

        wordsNum.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        wordsNum.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);
        wordsNum.setLayoutX(10.0);
        wordsNum.setLayoutY(4.0);
        wordsNum.setText("Words: 0");
        wordsNum.setFont(new Font(14.0));
        setBottom(countsPane);

        BorderPane.setAlignment(settingsPane, javafx.geometry.Pos.CENTER);
        settingsPane.setPrefHeight(375.0);
        settingsPane.setPrefWidth(139.0);

        label.setLayoutX(1.0);
        label.setLayoutY(4.0);
        label.setText("Font size");
        label.setFont(new Font(14.0));

        sizeSlider.setLayoutX(3.0);
        sizeSlider.setLayoutY(33.0);
        sizeSlider.setMajorTickUnit(10.0);
        sizeSlider.setMax(50.0);
        sizeSlider.setMin(10.0);
        sizeSlider.setPrefHeight(37.0);
        sizeSlider.setPrefWidth(129.0);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setValue(10.0);

        label0.setLayoutX(3.0);
        label0.setLayoutY(90.0);
        label0.setText("Font color");
        label0.setFont(new Font(14.0));

        colorPicker.setEditable(true);
        colorPicker.setLayoutX(2.0);
        colorPicker.setLayoutY(122.0);
        colorPicker.setValue(Color.BLACK);

        fontsComp.setEditable(true);
        fontsComp.setLayoutX(1.0);
        fontsComp.setLayoutY(175.0);
        fontsComp.setPrefHeight(25.0);
        fontsComp.setPrefWidth(136.0);
        fontsComp.setPromptText("Fonts");
        fontsComp.setVisibleRowCount(5);
        fontsComp.setOpaqueInsets(new Insets(0.0));
        fontsComp.getItems().addAll("Arial Black", "Brush Script MT", "Verdana", "Cambria",
                "Garamond", "Consolas", "Tahoma", "Courier New", "French Script MT", "Arabic Typesetting");
        fontsComp.setValue("Arial Black");
        setRight(settingsPane);

        fileMenu.getItems().add(NewFileItem);
        fileMenu.getItems().add(openFileItem);
        fileMenu.getItems().add(saveFileItem);
        fileMenu.getItems().add(exitItem);
        menubar.getMenus().add(fileMenu);
        editMenu.getItems().add(undoItem);
        editMenu.getItems().add(redoItem);
        editMenu.getItems().add(cutItem);
        editMenu.getItems().add(copyItem);
        editMenu.getItems().add(pasteItem);
        editMenu.getItems().add(deleteItem);
        editMenu.getItems().add(selectItem);
        editMenu.getItems().add(renameItem);
        menubar.getMenus().add(editMenu);
        helpMenu.getItems().add(aboutItem);
        menubar.getMenus().add(helpMenu);
        countsPane.getChildren().add(lettersNum);
        countsPane.getChildren().add(wordsNum);
        settingsPane.getChildren().add(label);
        settingsPane.getChildren().add(sizeSlider);
        settingsPane.getChildren().add(label0);
        settingsPane.getChildren().add(colorPicker);
        settingsPane.getChildren().add(fontsComp);

        menuEventsHandling = new MenuEventsHandling(this, primaryStage);
        textSettingEventsHandling = new TextSettingEventsHandling(this, primaryStage);
        menuEventsHandling.copy();
        menuEventsHandling.cut();
        menuEventsHandling.paste();
        menuEventsHandling.save();
        menuEventsHandling.delete();
        menuEventsHandling.editText();
        menuEventsHandling.undo();
        menuEventsHandling.selectAll();
        menuEventsHandling.redo();
        menuEventsHandling.open();
        menuEventsHandling.newFile();
        menuEventsHandling.exit();
        menuEventsHandling.rename();
        menuEventsHandling.help();

        textSettingEventsHandling.changeColor();
        textSettingEventsHandling.changeFont();
        textSettingEventsHandling.changeSize();

    }

}
