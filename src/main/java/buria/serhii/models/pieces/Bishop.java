package buria.serhii.models.pieces;

import buria.serhii.models.CoordinateShift;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop {
    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinateShift> getPieceMove() {
        return getBishopMoves();
    }
}
