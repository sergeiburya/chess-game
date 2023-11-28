package buria.serhii.models.pieces;

import buria.serhii.models.board.Board;
import buria.serhii.models.board.BoardUtils;

import java.util.List;

public abstract class LongRangePiece extends Piece {
    public LongRangePiece(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected boolean isSquareAvailableToMove(Coordinates coordinates, Board board) {
        boolean result =  super.isSquareAvailableToMove(coordinates, board);

        if (result) {
            return isSquareAvailableToAttack(coordinates, board);
        } else {
            return false;
        }
    }

    @Override
    protected boolean isSquareAvailableToAttack(Coordinates coordinates, Board board) {
        List<Coordinates> coordinatesBetween;
        if (this.coordinates.file == coordinates.file) {
            coordinatesBetween = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates);
        } else if (this.coordinates.rank.equals(coordinates.rank)) {
            coordinatesBetween = BoardUtils.getHorizontalCoordinatesBetween(this.coordinates, coordinates);
        } else {
            coordinatesBetween = BoardUtils.getDiagonalCoordinatesBetween(this.coordinates, coordinates);
        }
        for (Coordinates c : coordinatesBetween) {
            if (!board.isSquareEmpty(c)) {
                return false;
            }
        }
        return true;
    }
}
