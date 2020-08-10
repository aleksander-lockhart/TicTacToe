package Game;

import javafx.scene.control.Button;

import java.util.ArrayList;

public interface ComputerMove {
    int nextMove(ArrayList<Boolean> filledCells, ArrayList<Button> cells);
}
