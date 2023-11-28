package buria.serhii.models.pieces;

import buria.serhii.models.CoordinateShift;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinateShift> getPieceMove() {
        return getRookMoves();
    }
}
