/* Tic Tac Toe Game Application
 * The MenusController Class is responsable for controlling button events in Menu Scenes
*/
import java.io.IOException;

import core.AudioCore;
import core.resourceLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import game.*;
public class MenusController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public char p1,p2; 
    @FXML
    Button CheckButton;
    Button NxtSceneBtn;
    @FXML
    TextField TextField1;
    @FXML
    TextField TextField2;
    @FXML
    Label Label1;
    @FXML
    Label Label2;
    @FXML
    RadioButton RadioButton1,RadioButton2,RadioButton3,RadioButton4;
    @FXML
    Label menuCheckLabel;
    public static Player player1,player2;

    //check for errors in entered values
    public boolean checkValues(){
        if(RadioButton1.isSelected()&& RadioButton3.isSelected()){
            menuCheckLabel.setText("Select the correct piece");
            return false;
        }
        if(RadioButton2.isSelected()&& RadioButton4.isSelected()) {
            menuCheckLabel.setText("Select the correct piece");
            return false;
        }
        else if (TextField1.getText().isBlank() || TextField2.getText().isBlank()) {
            menuCheckLabel.setText("Name Should Not Be Empty");
            return false;
        }
        else if(((RadioButton1.isSelected()&&RadioButton4.isSelected())
        || (RadioButton2.isSelected()&&RadioButton3.isSelected())) && 
        (!(TextField1.getText().isBlank() || TextField2.getText().isBlank())))
        {
            menuCheckLabel.setText("");
            return true;
        }
        else {
            return false;
        }
    }

    //changes the scene to load the Game UI
    public void changeScene(ActionEvent event) throws IOException {
        AudioCore.playSound(resourceLoader.getAsset("POP.wav"));
        if (checkValues()) {
            if(RadioButton2.isSelected()&& RadioButton3.isSelected()){
                p1 = 'O';
                p2 = 'X';
               }
            else if(RadioButton1.isSelected()&&RadioButton4.isSelected()){
                p1 = 'X';
                p2 = 'O';
            }
            player1 = new Player(TextField1.getText(),Cell.getCellFromStr(p1));
            player2 = new Player(TextField2.getText(),Cell.getCellFromStr(p2));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/MainGame.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String CSS = this.getClass().getResource("css/style.css").toExternalForm();
            scene.getStylesheets().add(CSS);
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("Please Enter Correct Values before Going to Next Scene");
        }
    }
}
