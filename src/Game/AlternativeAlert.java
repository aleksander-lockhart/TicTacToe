package Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Window;

public class AlternativeAlert {
    
    protected Alert createAlert(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("alert-class.css").toExternalForm());
        dialogPane.getStyleClass().add("alert-class");

        alert.initModality(Modality.APPLICATION_MODAL);

        alert.getDialogPane().setContentText(
                "1. The game is played on a grid that's 3 squares by 3 squares.\n \n" +
                        "2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.\n \n" +
                        "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n \n" +
                        "4. When all 9 squares are full, the game is over.");

        DialogPane dialogPane2 = alert.getDialogPane();
        GridPane grid = new GridPane();
        ColumnConstraints graphicColumn = new ColumnConstraints();
        graphicColumn.setFillWidth(false);
        graphicColumn.setHgrow(Priority.NEVER);
        ColumnConstraints textColumn = new ColumnConstraints();
        textColumn.setFillWidth(true);
        textColumn.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().setAll(graphicColumn, textColumn);
        grid.setHgap(200);
        grid.setPadding(new Insets(15));

        Image image1 = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Tic_tac_toe.svg/300px-Tic_tac_toe.svg.png");
        ImageView imageView = new ImageView(image1);
        imageView.setFitWidth(164);
        imageView.setFitHeight(164);
        StackPane stackPane = new StackPane(imageView);
        stackPane.setAlignment(Pos.CENTER);
        grid.add(stackPane, 0, 0);

        Label headerLabel = new Label("TIC TAC TOE Game");
        headerLabel.setWrapText(true);
        headerLabel.setAlignment(Pos.CENTER_LEFT);
        headerLabel.setMaxWidth(1500);
        headerLabel.setMaxHeight(Double.MAX_VALUE);
        headerLabel.setStyle("-fx-font-size: 22");
        grid.add(headerLabel, 1, 0);

        dialogPane2.setHeader(grid);
        dialogPane2.setGraphic(null);

        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.out.println("The alert was approved"));

        return alert;
    }
    
}
