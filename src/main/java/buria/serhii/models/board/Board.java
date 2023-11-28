package buria.serhii.models.board;

import buria.serhii.models.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
    public final String startingFen;
    private Map<Coordinates, Piece> pieces = new HashMap<>();

    public List<Move> moves = new ArrayList<>();

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPieces(Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void makeMove(Move move) {
        Piece piece = getPiece(move.from);
        removePiece(move.from);
        setPieces(move.to, piece);
        moves.add(move);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public void setupDefaultPiecesPositions() {
        for (File file : File.values()) {
            setPieces(new Coordinates(file, 2), new Pawn(Color.WHITE, new Coordinates(file, 2)));
            setPieces(new Coordinates(file, 7), new Pawn(Color.BLACK, new Coordinates(file, 7)));
        }

        setPieces(new Coordinates(File.A, 1), new Rook(Color.WHITE, new Coordinates(File.A, 1)));
        setPieces(new Coordinates(File.H, 1), new Rook(Color.WHITE, new Coordinates(File.H, 1)));
        setPieces(new Coordinates(File.A, 8), new Rook(Color.BLACK, new Coordinates(File.A, 8)));
        setPieces(new Coordinates(File.H, 8), new Rook(Color.BLACK, new Coordinates(File.H, 8)));

        setPieces(new Coordinates(File.B, 1), new Knight(Color.WHITE, new Coordinates(File.B, 1)));
        setPieces(new Coordinates(File.G, 1), new Knight(Color.WHITE, new Coordinates(File.G, 1)));
        setPieces(new Coordinates(File.B, 8), new Knight(Color.BLACK, new Coordinates(File.B, 8)));
        setPieces(new Coordinates(File.G, 8), new Knight(Color.BLACK, new Coordinates(File.G, 8)));

        setPieces(new Coordinates(File.C, 1), new Bishop(Color.WHITE, new Coordinates(File.C, 1)));
        setPieces(new Coordinates(File.F, 1), new Bishop(Color.WHITE, new Coordinates(File.F, 1)));
        setPieces(new Coordinates(File.C, 8), new Bishop(Color.BLACK, new Coordinates(File.C, 8)));
        setPieces(new Coordinates(File.F, 8), new Bishop(Color.BLACK, new Coordinates(File.F, 8)));

        setPieces(new Coordinates(File.D, 1), new Queen(Color.WHITE, new Coordinates(File.D, 1)));
        setPieces(new Coordinates(File.D, 8), new Queen(Color.BLACK, new Coordinates(File.D, 8)));

        setPieces(new Coordinates(File.E, 1), new King(Color.WHITE, new Coordinates(File.E, 1)));
        setPieces(new Coordinates(File.E, 8), new King(Color.BLACK, new Coordinates(File.E, 8)));
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) % 2) == 0;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPieceByColor(color);

        for (Piece piece : pieces) {
            Set<Coordinates> attackedSquare = piece.getAttackedSquare(this);
            if (attackedSquare.contains(coordinates)) {
                return true;
            }
        }
        return false;
    }

    public List<Piece> getPieceByColor(Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }
        return  result;
    }
}
