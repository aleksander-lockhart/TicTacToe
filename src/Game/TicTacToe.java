package Game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

public class TicTacToe extends Application {

    private static ArrayList<Button> cells = new ArrayList<>();
    private static ArrayList<Boolean> filledCells = new ArrayList<>(9);
    private ComputerMove aiLogic;
    private Label statusLabel;
    Pane root2 = new Pane();


    public TicTacToe() {
        statusLabel = new Label("");
        aiLogic = new RandomComputerMove();

        // Pusta lista pol
        for (int i = 0; i < 9; i++) {
            filledCells.add(false);
        }
        clearGridArray();

        // Show Rules
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeight(800);
        alert.setWidth(800);
        alert.setTitle("TicTacToe rules");
        alert.setHeaderText(null);
        alert.setContentText("1. The game is played on a grid that's 3 squares by 3 squares.\n \n" +
                "2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.\n \n" +
                "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n \n" +
                "4. When all 9 squares are full, the game is over.");
        alert.showAndWait();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Grid for game
        GridPane board = new GridPane() {{
            ColumnConstraints column1 = new ColumnConstraints(110, 75, 110);
            RowConstraints row1 = new RowConstraints(110, 75, 110);

            getColumnConstraints().addAll(column1, column1, column1);
            getRowConstraints().addAll(row1, row1, row1);
            for (int index = 0; index < 9; index++) {
                add(createCell(), index % 3, index / 3);
            }
            setHgap(12);
            setVgap(12);
        }};

        Button clearButton = new Button("Clear board") {{
            setFont(new Font(16));
            setOnAction(ae -> clearGrid());
        }};


        // layout on scene
        BorderPane root = new BorderPane() {{

            VBox bottomPanel = new VBox(5, statusLabel, clearButton);
            bottomPanel.setAlignment(Pos.CENTER);
            BorderPane.setMargin(bottomPanel, new Insets(20));
            setCenter(board);
            setBottom(bottomPanel);
            setMargin(board, new Insets(25));
            setMaxWidth(500);
            setMaxHeight(500);
        }};

        // scene create and show
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setMaxWidth(410);
        primaryStage.setMaxHeight(550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TIC TAC TOE");
        primaryStage.show();
    }

    // single cell builder and action caller
    private Button createCell() {
        Button cell = new Button("") {{
            setPrefHeight(110);
            setPrefWidth(110);
            setFont(new Font(24));
        }};

        // cell actions
        cell.setOnAction(ae -> {
            int clickedCellIndex = cells.indexOf(cell);
            if (filledCells.get(clickedCellIndex)) {
                statusLabel.setText("Click on the empty cell!");
                return;
            } else {
                fillCell("X", clickedCellIndex);
                statusLabel.setText("");
            }

            if (whoWins("X")) {
                statusLabel.setText("Player have won");
                disableGrid();
                return;
            }
            if (isGridFull()) {
                statusLabel.setText("Draw!");
                disableGrid();
                return;
            }

            // Comp move
            int aiIndex = aiLogic.nextMove();
            fillCell("O", aiIndex);

            if (whoWins("O")) {
                statusLabel.setText("Computer has won");
                disableGrid();
            }
        });

        cells.add(cell);
        return cell;
    }

    private static void fillCell(String player, int index) {
        cells.get(index).setText(player);
        filledCells.set(index, true);
    }

    private static void clearGridArray() {
        for (int i = 0; i < 9; i++) {
            filledCells.set(i, false);
        }
        enableGrid();
    }

    private void clearGrid() {
        cells.forEach(cell -> cell.setText(""));
        clearGridArray();
        statusLabel.setText("");
    }


    private static void disableGrid() {
        cells.forEach(cell -> cell.setDisable(true));
    }

    private static void enableGrid() {
        cells.forEach(cell -> cell.setDisable(false));
    }

    private static boolean isGridFull() {
        for (boolean cellFilled : filledCells) {
            if (!cellFilled)
                return false;
        }
        return true;
    }

    private static boolean whoWins(String playerString) {
        // horizontal check
        for (int r = 0; r < 3; r++) {
            if (cells.get(r * 3).getText().equals(playerString) &&
                    cells.get(r * 3 + 1).getText().equals(playerString) &&
                    cells.get(r * 3 + 2).getText().equals(playerString))
                return true;
        }

        // vertical check
        for (int c = 0; c < 3; c++) {
            if (cells.get(c).getText().equals(playerString) &&
                    cells.get(3 + c).getText().equals(playerString) &&
                    cells.get(6 + c).getText().equals(playerString))
                return true;
        }

        // left-to-right down diagonal
        if (cells.get(0).getText().equals(playerString) &&
                cells.get(4).getText().equals(playerString) &&
                cells.get(8).getText().equals(playerString))
            return true;

        // right-to-left down diagonal
        if (cells.get(2).getText().equals(playerString) &&
                cells.get(4).getText().equals(playerString) &&
                cells.get(6).getText().equals(playerString))
            return true;

        return false;
    }

    // Comp pseudo "logic" - stupid as hell
    class RandomComputerMove implements ComputerMove {
        @Override
        public int nextMove() {
            int aiIndex = (int)Math.floor(Math.random() * 9);
            while (filledCells.get(aiIndex)) {
                aiIndex = (int)Math.floor(Math.random() * 9);
            }
            return aiIndex;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}