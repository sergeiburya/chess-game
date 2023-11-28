package buria.serhii.models.pieces;

import buria.serhii.models.CoordinateShift;

import java.util.Set;

public class Queen extends LongRangePiece implements IRook, IBishop {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinateShift> getPieceMove() {
        Set<CoordinateShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());
        return moves;
    }
}
