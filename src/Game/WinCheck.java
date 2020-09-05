package Game;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class WinCheck {

    public static boolean whoWins(String playerString, ArrayList<Button> cells) {
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
}
