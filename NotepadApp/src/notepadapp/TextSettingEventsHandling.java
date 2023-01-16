
package notepadapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class TextSettingEventsHandling {

    FXMLNotepadBase fxmlNotepadBase;
    Stage stage;

    public TextSettingEventsHandling() {
    }

    public TextSettingEventsHandling(FXMLNotepadBase fxmlNotepadBase, Stage stage) {
        this.fxmlNotepadBase = fxmlNotepadBase;
        this.stage = stage;

    }

    public void changeFont() {
        fxmlNotepadBase.fontsComp.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                fxmlNotepadBase.textArea.setStyle("-fx-font-size: " + fxmlNotepadBase.sizeSlider.getValue() + ";" + "-fx-text-fill: #"
                        + fxmlNotepadBase.colorPicker.getValue().toString().substring(2) + ";" + "-fx-font-family: \""
                        + fxmlNotepadBase.fontsComp.getValue().toString() + "\";");

                
            }
        });
    }

    public void changeColor() {
        fxmlNotepadBase.colorPicker.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                fxmlNotepadBase.textArea.setStyle("-fx-font-size: " + fxmlNotepadBase.sizeSlider.getValue() + ";" + "-fx-text-fill: #"
                        + fxmlNotepadBase.colorPicker.getValue().toString().substring(2) + ";" + "-fx-font-family: \""
                        + fxmlNotepadBase.fontsComp.getValue().toString() + "\";");
                
            }
        });

    }

    public void changeSize() {
        fxmlNotepadBase.sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fxmlNotepadBase.textArea.setStyle("-fx-font-size: " + newValue + ";" + "-fx-text-fill: #"
                        + fxmlNotepadBase.colorPicker.getValue().toString().substring(2)
                        + ";" + "-fx-font-family: \"" + fxmlNotepadBase.fontsComp.getValue().toString() + "\";");
              
            }
        });

    }
}
