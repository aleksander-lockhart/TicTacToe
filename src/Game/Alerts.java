package Game;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class Alerts {

    public void showRules() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("alert-class.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-class");


        alert.setHeight(800);
        alert.setWidth(1000);
        alert.setTitle("TicTacToe rules");
        alert.setHeaderText(null);
        alert.setContentText("1. The game is played on a grid that's 3 squares by 3 squares.\n \n" +
                "2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.\n \n" +
                "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n \n" +
                "4. When all 9 squares are full, the game is over.");
        alert.showAndWait();
    }
}
