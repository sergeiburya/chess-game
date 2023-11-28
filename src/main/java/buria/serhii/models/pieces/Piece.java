package buria.serhii.models.pieces;

import buria.serhii.models.board.Board;
import buria.serhii.models.CoordinateShift;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Set<Coordinates> getAvailableSquareToMove(Board board) {
        Set<Coordinates> result = new HashSet<>();
        for (CoordinateShift shift : getPieceMove()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isSquareAvailableToMove(newCoordinates, board)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    public Set<Coordinates> getAttackedSquare(Board board) {
        Set<CoordinateShift> pieceAttacks = getPieceAttacks();
        Set<Coordinates> result = new HashSet<>();
        for (CoordinateShift pieceAttack : pieceAttacks) {
            if (coordinates.canShift(pieceAttack)) {
                Coordinates shiftedCoordinates = coordinates.shift(pieceAttack);
                if (isSquareAvailableToAttack(shiftedCoordinates, board)) {
                    result.add(shiftedCoordinates);
                }
            }
        }
        return result;
    }
    protected boolean isSquareAvailableToMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }

    protected abstract Set<CoordinateShift> getPieceMove();

    protected Set<CoordinateShift> getPieceAttacks() {
        return getPieceMove();
    }

    protected boolean isSquareAvailableToAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
