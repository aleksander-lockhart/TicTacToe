package Game;

import java.util.ArrayList;
import javafx.scene.control.Button;

public class SmartCompMove implements ComputerMove {

    @Override
    public int nextMove(ArrayList<Boolean> filledCells, ArrayList<Button> cells) {

        int aiIndex;

        // check for win move
        aiIndex = checkForTheRightLine("o", cells);
        if (aiIndex != -1) {
            return aiIndex;
        }

        // smart move check
        aiIndex = checkForTheRightLine("x", cells);
        if (aiIndex != -1) {
            return aiIndex;
        }
        // if not above - random move
        aiIndex = (int)Math.floor(Math.random() * 9);
        while (filledCells.get(aiIndex)) {
            aiIndex = (int)Math.floor(Math.random() * 9);
        }
        return aiIndex;
    }

    // Comp AI - check for counter and win moves
    private int checkForTheRightLine(String cellString, ArrayList<Button> cells) {
        // check counter move - horizontal
        for (int r = 0; r < 3; r++) {
            if (cells.get(r * 3).getText().equals(cellString) &&
                    cells.get(r * 3 + 1).getText().equals(cellString) &&
                    cells.get(r * 3 + 2).getText().equals(""))
                return (r * 3 + 2);
            if (cells.get(r * 3).getText().equals(cellString) &&
                    cells.get(r * 3 + 1).getText().equals("") &&
                    cells.get(r * 3 + 2).getText().equals(cellString))
                return (r * 3 + 1);
            if (cells.get(r * 3).getText().equals("") &&
                    cells.get(r * 3 + 1).getText().equals(cellString) &&
                    cells.get(r * 3 + 2).getText().equals(cellString))
                return (r * 3);
        }

        // check counter move - vertical
        for (int c = 0; c < 3; c++) {
            if (cells.get(c).getText().equals(cellString) &&
                    cells.get(3 + c).getText().equals(cellString) &&
                    cells.get(6 + c).getText().equals(""))
                return (6 + c);
            if (cells.get(c).getText().equals(cellString) &&
                    cells.get(3 + c).getText().equals("") &&
                    cells.get(6 + c).getText().equals(cellString))
                return (3 + c);
            if (cells.get(c).getText().equals("") &&
                    cells.get(3 + c).getText().equals(cellString) &&
                    cells.get(6 + c).getText().equals(cellString))
                return (c);
        }

        // check counter move - left-to-right down diagonal
        if (cells.get(0).getText().equals(cellString) &&
                cells.get(4).getText().equals(cellString) &&
                cells.get(8).getText().equals(""))
            return 8;
        if (cells.get(0).getText().equals(cellString) &&
                cells.get(4).getText().equals("") &&
                cells.get(8).getText().equals(cellString))
            return 4;
        if (cells.get(0).getText().equals("") &&
                cells.get(4).getText().equals(cellString) &&
                cells.get(8).getText().equals(cellString))
            return 0;

        // check counter move - right-to-left down diagonal
        if (cells.get(2).getText().equals(cellString) &&
                cells.get(4).getText().equals(cellString) &&
                cells.get(6).getText().equals(""))
            return 6;
        if (cells.get(2).getText().equals(cellString) &&
                cells.get(4).getText().equals("") &&
                cells.get(6).getText().equals(cellString))
            return 4;
        if (cells.get(2).getText().equals("") &&
                cells.get(4).getText().equals(cellString) &&
                cells.get(6).getText().equals(cellString))
            return 2;
        return -1;
    }
}

