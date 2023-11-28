package buria.serhii.models;

import buria.serhii.models.pieces.*;

public class PieceFactory {
    public Piece pieceFromFen(char fenChar, Coordinates coordinates) {
        switch (fenChar) {
            case 'p' : return new Pawn(Color.BLACK, coordinates);
            case 'r' : return new Rook(Color.BLACK, coordinates);
            case 'n' : return new Knight(Color.BLACK, coordinates);
            case 'b' : return new Bishop(Color.BLACK, coordinates);
            case 'q' : return new Queen(Color.BLACK, coordinates);
            case 'k' : return new King(Color.BLACK, coordinates);

            case 'P' : return new Pawn(Color.WHITE, coordinates);
            case 'R' : return new Rook(Color.WHITE, coordinates);
            case 'N' : return new Knight(Color.WHITE, coordinates);
            case 'B' : return new Bishop(Color.WHITE, coordinates);
            case 'Q' : return new Queen(Color.WHITE, coordinates);
            case 'K' : return new King(Color.WHITE, coordinates);
            default: throw new RuntimeException("Unknown FEN char!");
        }
    }
}
