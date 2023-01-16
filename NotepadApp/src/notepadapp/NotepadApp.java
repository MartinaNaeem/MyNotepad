
package notepadapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NotepadApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLNotepadBase root = new FXMLNotepadBase(primaryStage);
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("new document");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
