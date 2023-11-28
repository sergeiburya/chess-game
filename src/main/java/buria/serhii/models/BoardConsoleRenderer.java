package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.pieces.Color;
import buria.serhii.models.pieces.Coordinates;
import buria.serhii.models.pieces.File;
import buria.serhii.models.pieces.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR ="\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableSquareToMove = emptySet();
        if (pieceToMove != null) {
            availableSquareToMove = pieceToMove.getAvailableSquareToMove(board);
        }
        System.out.println("   A   B   C  D   E   F  G   H");
        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder line = new StringBuilder();
            line.append(rank).append(" ");
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHightLight = availableSquareToMove.contains(coordinates);
                if (board.isSquareEmpty(coordinates)) {
                    line.append(getSpriteForEmptySquare(coordinates, isHightLight));
                } else {
                    line.append(getPieceSprite(board.getPiece(coordinates), isHightLight));
                }
            }
            line.append(ANSI_RESET);
            line.append(" ").append(rank);
            System.out.println(line);
        }
        System.out.println("   A   B   C  D   E   F  G   H");
    }

    public void render(Board board) {
        render(board, null);

    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "Pawn" : return "♟";
            case "Bishop" : return "♝";
            case "King" : return "♚";
            case "Knight" : return "♞";
            case "Rook" : return "♜";
            case "Queen" : return "♛";
        }
        return "";
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHightLighted) {
        String result = sprite;
        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHightLighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHightLight) {
        return colorizeSprite("   ", Color.WHITE, Board.isSquareDark(coordinates), isHightLight);
    }

    private String getPieceSprite(Piece piece, boolean isHightLighted) {
        return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates), isHightLighted);
    }
}
