package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.pieces.Color;

public abstract class GameStateCheker {
    public abstract GameState check(Board board, Color color);
}
