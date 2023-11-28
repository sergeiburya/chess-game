package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.board.BoardFactory;
import buria.serhii.models.board.Move;
import buria.serhii.models.pieces.Color;
import buria.serhii.models.pieces.Coordinates;
import buria.serhii.models.pieces.King;
import buria.serhii.models.pieces.Piece;

import java.util.List;
import java.util.Set;

public class CheckmateGameStateCheker extends GameStateCheker {
    @Override
    public GameState check(Board board, Color color) {
        Piece king = board.getPieceByColor(color)
                .stream().filter(piece -> piece instanceof King)
                .findFirst()
                .get();
        if (!board.isSquareAttackedByColor(king.coordinates, color.opposite())) {
            return GameState.ONGOING;
        }
        List<Piece> pieces = board.getPieceByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableSquareToMove = piece.getAvailableSquareToMove(board);
            for (Coordinates coordinates : availableSquareToMove) {
                Board copyBoard = new BoardFactory().copyBoard(board);
                copyBoard.makeMove(new Move(piece.coordinates, coordinates));
                Piece copyKing = copyBoard.getPieceByColor(color)
                        .stream().filter(p -> p instanceof King)
                        .findFirst()
                        .get();
                if (!copyBoard.isSquareAttackedByColor(copyKing.coordinates, color.opposite())) {
                    return GameState.ONGOING;
                }
            }
        }
        if (color == Color.WHITE) {
            return GameState.CHECKMATE_TO_WHITE_KING;
        } else {
            return GameState.CHECKMATE_TO_BLACK_KING;
        }
    }
}
