package buria.serhii.models.pieces;

import buria.serhii.models.board.Board;
import buria.serhii.models.CoordinateShift;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinateShift> getPieceMove() {
        Set<CoordinateShift> result = new HashSet<>();
        for (int fileShift = -1; fileShift <=1 ; fileShift++) {
            for (int rankShift = -1; rankShift <=1 ; rankShift++) {
                if ((fileShift == 0) && (rankShift == 0)) {
                    continue;
                }
                result.add(new CoordinateShift(fileShift, rankShift));
            }
        }
        return result;
    }

    @Override
    protected boolean isSquareAvailableToMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableToMove(coordinates, board);
        if (result) {
           return !board.isSquareAttackedByColor(coordinates, color.opposite());
        }
        return false;
    }
}
