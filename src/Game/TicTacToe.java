package Game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;


public class TicTacToe extends Application {

    private final static ArrayList<Boolean> filledCells = new ArrayList<>(9);
    private final static ArrayList<Button> cells = new ArrayList<>();
    private final ComputerMove aiLogic;
    private final Label statusLabel;
    AlternativeAlert showStatus = new AlternativeAlert();


    public TicTacToe() {
        statusLabel = new Label("");
        statusLabel.setStyle("-fx-font-size: 22px;");
        aiLogic = new SmartCompMove();

        // Create empty grid
        for (int i = 0; i < 9; i++) {
            filledCells.add(false);
        }
        clearGridArray();

        // Show Rules
        showStatus.showRules();
    }

    @Override
    public void start(Stage primaryStage) {

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
        Scene scene = new Scene(root, 500, 550);
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

            if (WinCheck.whoWins("X", cells)) {
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
            int aiIndex = aiLogic.nextMove(filledCells, cells);
            fillCell("O", aiIndex);

            if (WinCheck.whoWins("O", cells)) {
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

    // REMOVED FOR OTHER AI CLASS

//    // Comp pseudo "logic" - stupid as hell
//    class RandomComputerMove implements ComputerMove {
//        @Override
//        public int nextMove() {
//            int aiIndex = (int)Math.floor(Math.random() * 9);
//            while (filledCells.get(aiIndex)) {
//                aiIndex = (int)Math.floor(Math.random() * 9);
//            }
//            return aiIndex;
//        }
//    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}