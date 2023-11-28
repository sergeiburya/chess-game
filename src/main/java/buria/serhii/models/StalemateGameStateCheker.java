package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.pieces.Color;
import buria.serhii.models.pieces.Coordinates;
import buria.serhii.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StalemateGameStateCheker extends GameStateCheker {
    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPieceByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableSquareToMove = piece.getAvailableSquareToMove(board);

            if (!availableSquareToMove.isEmpty()) {
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
