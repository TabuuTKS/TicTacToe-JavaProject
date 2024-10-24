/* Tic Tac Toe Game Application
 * The Main Class which initialiezes things like the Stage with the Main Scene.
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import core.*;

public class App extends Application {

    //Main Function Which Runs The JavaFX Application
    @Override public void start(Stage primaryStage) {
        AudioCore.playBGM("audio/BGM.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String CSS = this.getClass().getResource("css/style.css").toExternalForm();
            scene.getStylesheets().add(CSS);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Main Function of Core Java Application
    public static void main(String[] args) {
        resourceLoader.setProjectPath(System.getProperty("user.dir"));
        launch(args);
    }
}
