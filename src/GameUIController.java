/* Tic Tac Toe Game Application
 * The GameUIController Class is responsable for controlling The UI Events of Game Scene
*/
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import core.AudioCore;
import core.resourceLoader;
import game.Cell;
public class GameUIController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private game.Board gameBoard = new game.Board();

    private enum PlayerChance {
        PLAYER1,
        PLAYER2,
        NONE
    }

    @FXML
    Button button00, button01, button02, button10, button11, button12, button20, button21, button22;
    @FXML
    private Line LineD1, LineD2, LineV1, LineV2, LineV3, LineH1, LineH2, LineH3;
    public Label TurnLabel;
    private ImageView imageView;
    private File XImage = new File(resourceLoader.getAsset("image/Ximage.png"));
    private File OImage = new File(resourceLoader.getAsset("image/Oimage.png"));
    private File EmptyImage = new File(resourceLoader.getAsset("image/EmptyImage.png"));
    private PlayerChance playerChance = PlayerChance.PLAYER1;
    
    @FXML
    private void initialize() {
        // This code will run when the FXML file is loaded
        TurnLabel.setText(MenusController.player1.getName()+"'s Turn");
        button00.setUserData("00");
        button01.setUserData("01");
        button02.setUserData("02");
        button10.setUserData("10");
        button11.setUserData("11");
        button12.setUserData("12");
        button20.setUserData("20");
        button21.setUserData("21");
        button22.setUserData("22");

        // Create and start AnimationTimer for simulatoing update. its created for making update function
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Code here will run on every frame
                update();
            }
        };
        timer.start();
    }

    //calls every frame aka Update function
    public void update() {
        if (!(gameBoard.didSomeoneWin())) {
            if (playerChance == PlayerChance.PLAYER1) {
                TurnLabel.setText(MenusController.player1.getName()+"'s Turn");
            }
            else if (playerChance == PlayerChance.PLAYER2) {
                TurnLabel.setText(MenusController.player2.getName()+"'s Turn");
            }
            else if (playerChance == PlayerChance.NONE) {
                TurnLabel.setText("Draw!! No One Won");
            }   
        }
        else {
            if(gameBoard.didSomeoneWin()) {
                results();
            }
        }

        WinningLines();
    }

    //will disable all buttons
    public void disableAllBtn() {
        button00.setDisable(true);
        button01.setDisable(true);
        button02.setDisable(true);
        button10.setDisable(true);
        button11.setDisable(true);
        button12.setDisable(true);
        button20.setDisable(true);
        button21.setDisable(true);
        button22.setDisable(true);
    }

    //the Button Click Event Function
    public void btnClick(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        System.out.println(clickedButton.getGraphic());
        int row = Character.getNumericValue(clickedButton.getUserData().toString().charAt(0));
        int column = Character.getNumericValue(clickedButton.getUserData().toString().charAt(1));
        AudioCore.playSound(resourceLoader.getAsset("POP.wav"));
        if (playerChance == PlayerChance.PLAYER1) {
            if (MenusController.player1.getSymboll() == Cell.X) {
                imageView = (ImageView) clickedButton.getGraphic();
                imageView.setImage(new Image(XImage.toURI().toString()));
                gameBoard.changeSymboll(row, column, MenusController.player1.getSymboll());
                playerChance = PlayerChance.PLAYER2;   
            }
            else {
                imageView = (ImageView) clickedButton.getGraphic();
                imageView.setImage(new Image(OImage.toURI().toString()));
                gameBoard.changeSymboll(row, column, MenusController.player1.getSymboll());
                playerChance = PlayerChance.PLAYER2; 
            }
        }
        else {
            if (MenusController.player2.getSymboll() == Cell.X) {
                imageView = (ImageView) clickedButton.getGraphic();
                imageView.setImage(new Image(XImage.toURI().toString()));
                gameBoard.changeSymboll(row, column, MenusController.player2.getSymboll());
                playerChance = PlayerChance.PLAYER1;   
            }
            else {
                imageView = (ImageView) clickedButton.getGraphic();
                imageView.setImage(new Image(OImage.toURI().toString()));
                gameBoard.changeSymboll(row, column, MenusController.player2.getSymboll());
                playerChance = PlayerChance.PLAYER1;
            }
        }

        if (gameBoard.didSomeoneWin()) {
            disableAllBtn();   
        }
        else if (!(gameBoard.didSomeoneWin())) {
            clickedButton.setDisable(true);
            if (button00.isDisabled() && button01.isDisabled() && button02.isDisabled()
            && button10.isDisabled() && button11.isDisabled() && button12.isDisabled()
            && button20.isDisabled() && button21.isDisabled() && button22.isDisabled()) {
                playerChance = PlayerChance.NONE;
            }
        }
    }

    //calculate results
    public void results() {
        if ((gameBoard.whoWon == Cell.X && MenusController.player1.getSymboll() == Cell.X)
        || (gameBoard.whoWon == Cell.O && MenusController.player1.getSymboll() == Cell.O)) {
            TurnLabel.setText(MenusController.player1.getName()+" Won");   
        }
        else if ((gameBoard.whoWon == Cell.X && MenusController.player2.getSymboll() == Cell.X)
        || (gameBoard.whoWon == Cell.O && MenusController.player2.getSymboll()== Cell.O)) {
            TurnLabel.setText(MenusController.player2.getName()+" Won");   
        }
    }

    //for Replay Button
    public void replay(ActionEvent event) {
        AudioCore.playSound(resourceLoader.getAsset("POP.wav"));
        button00.setDisable(false);
        button01.setDisable(false);
        button02.setDisable(false);
        button10.setDisable(false);
        button11.setDisable(false);
        button12.setDisable(false);
        button20.setDisable(false);
        button21.setDisable(false);
        button22.setDisable(false);

        LineD1.setVisible(false);
        LineD2.setVisible(false);
        LineV1.setVisible(false);
        LineV2.setVisible(false);
        LineV3.setVisible(false);
        LineH1.setVisible(false);
        LineH2.setVisible(false);
        LineH3.setVisible(false);

        imageView = (ImageView) button00.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button01.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button02.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button10.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button11.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button12.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button20.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button21.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        imageView = (ImageView) button22.getGraphic();
        imageView.setImage(new Image(EmptyImage.toURI().toString()));
        gameBoard.emptyBoard();
        playerChance = PlayerChance.PLAYER1;
    }
    
    //For Back Button
    public void back(ActionEvent event) throws IOException {
        AudioCore.playSound(resourceLoader.getAsset("POP.wav"));
        replay(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/Menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String CSS = this.getClass().getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(CSS);
        stage.setScene(scene);
        stage.show();
    }

    void WinningLines() {
        //Daigonal 1
        if((gameBoard.cellState[0][0] == Cell.X && gameBoard.cellState[1][1] == Cell.X && gameBoard.cellState[2][2] == Cell.X)
        || (gameBoard.cellState[0][0] == Cell.O && gameBoard.cellState[1][1] == Cell.O && gameBoard.cellState[2][2] == Cell.O)) {
            LineD1.setVisible(true);
        }
        //Daigonal 2
        else if ((gameBoard.cellState[0][2] == Cell.X && gameBoard.cellState[1][1] == Cell.X && gameBoard.cellState[2][0] == Cell.X)
        || (gameBoard.cellState[0][2] == Cell.O && gameBoard.cellState[1][1] == Cell.O && gameBoard.cellState[2][0] == Cell.O)) {
            LineD2.setVisible(true);
        }
        //Collumn 1
        else if ((gameBoard.cellState[0][0] == Cell.X && gameBoard.cellState[1][0] == Cell.X && gameBoard.cellState[2][0] == Cell.X)
        || (gameBoard.cellState[0][0] == Cell.O && gameBoard.cellState[1][0] == Cell.O && gameBoard.cellState[2][0] == Cell.O)) {
            LineV1.setVisible(true);
        }
        //Collumn 2
        else if ((gameBoard.cellState[0][1] == Cell.X && gameBoard.cellState[1][1] == Cell.X && gameBoard.cellState[2][1] == Cell.X)
        || (gameBoard.cellState[0][1] == Cell.O && gameBoard.cellState[1][1] == Cell.O && gameBoard.cellState[2][1] == Cell.O)) {
            LineV2.setVisible(true);
        }
        //Collumn 3
        else if ((gameBoard.cellState[0][2] == Cell.X && gameBoard.cellState[1][2] == Cell.X && gameBoard.cellState[2][2] == Cell.X)
        || (gameBoard.cellState[0][2] == Cell.O && gameBoard.cellState[1][2] == Cell.O && gameBoard.cellState[2][2] == Cell.O)) {
            LineV3.setVisible(true);
        }
        //Row 1
        else if ((gameBoard.cellState[0][0] == Cell.X && gameBoard.cellState[0][1] == Cell.X && gameBoard.cellState[0][2] == Cell.X)
        || (gameBoard.cellState[0][0] == Cell.O && gameBoard.cellState[0][1] == Cell.O && gameBoard.cellState[0][2] == Cell.O)) {
            LineH1.setVisible(true);
        }
        //Row 2
        else if ((gameBoard.cellState[1][0] == Cell.X && gameBoard.cellState[1][1] == Cell.X && gameBoard.cellState[1][2] == Cell.X)
        || (gameBoard.cellState[1][0] == Cell.O && gameBoard.cellState[1][1] == Cell.O && gameBoard.cellState[1][2] == Cell.O)) {
            LineH2.setVisible(true);
        }
        //Row 3
        else if ((gameBoard.cellState[2][0] == Cell.X && gameBoard.cellState[2][1] == Cell.X && gameBoard.cellState[2][2] == Cell.X)
        || (gameBoard.cellState[2][0] == Cell.O && gameBoard.cellState[2][1] == Cell.O && gameBoard.cellState[2][2] == Cell.O)) {
            LineH3.setVisible(true);
        }
    }
}


