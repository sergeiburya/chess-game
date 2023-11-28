package buria.serhii.models.board;

import buria.serhii.models.PieceFactory;
import buria.serhii.models.board.Board;
import buria.serhii.models.pieces.Coordinates;
import buria.serhii.models.pieces.File;

public class BoardFactory {
    private PieceFactory pieceFactory = new PieceFactory();
    public Board boardFromFen(String fen) {
        Board board = new Board(fen);

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];

        String[] fenRows = piecePositions.split("/");
        for (int i = 0; i < fenRows.length; i++) {
            String row = fenRows[i];
            int rank = 8 - i;
            int fileIndex = 0;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);
                if (Character.isDigit(fenChar)) {
                    fileIndex += Character.getNumericValue(fenChar);
                } else {
                    File file = File.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(file, rank);

                    board.setPieces(coordinates, pieceFactory.pieceFromFen(fenChar, coordinates));
                    fileIndex++;
                }
            }
        }
        return board;
    }

    public Board copyBoard(Board sourceBoard) {
        Board copyBoard = boardFromFen(sourceBoard.startingFen);
        for (Move move : sourceBoard.moves) {
            copyBoard.makeMove(move);
        }
        return copyBoard;
    }
}
