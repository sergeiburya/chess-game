package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.board.BoardFactory;
import buria.serhii.models.board.Move;
import buria.serhii.models.pieces.*;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input() {
        while (true) {
            System.out.println("Please enter coordinates your move ( ex. b1 or B1)");
            String line = scanner.nextLine();
            if (line.length() !=2) {
                System.out.println("Invalid format");
                continue;
            }
            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);
            if (!Character.isLetter(fileChar)) {
                System.out.println("Invalid format");
                continue;
            }
            if (!Character.isDigit(rankChar)) {
                System.out.println("Invalid format");
                continue;
            }
            int rank = Character.getNumericValue(rankChar);
            if (rank < 1 || rank > 8) {
                System.out.println("Invalid format");
                continue;
            }
            File file = File.fromChar(fileChar);
            if (file == null) {
                System.out.println("Invalid format");
                continue;
            }
            return new Coordinates(file, rank);
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {
        while (true) {
            Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinateForColor(
                    color, board);

            Piece piece = board.getPiece(sourceCoordinates);

            Set<Coordinates> availableSquareToMove = piece.getAvailableSquareToMove(board);

            renderer.render(board, piece);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableSquareToMove);
            Move move = new Move(sourceCoordinates, targetCoordinates);
            if (checkingKingNotUnderCheckmateAfterMove(board, color, move)) {
                System.out.println("Your King is under Attack");
                continue;
            }
            return move;
        }
    }

    public static Coordinates inputPieceCoordinateForColor(Color color, Board board) {
        while (true) {
            System.out.println("Enter coordinates for piece to move:");
            Coordinates coordinates = input();
            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Empty square");
                continue;
            }
            Piece piece = board.getPiece(coordinates);
            if (piece.color != color) {
                System.out.println("Wronge color");
                continue;
            }
            Set<Coordinates> availableSquareToMove = piece.getAvailableSquareToMove(board);
            if (availableSquareToMove.isEmpty()) {
                System.out.println("Piece blocked");
                continue;
            }
            return coordinates;
        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {
        while (true) {
            System.out.println("Enter your move for a selected piece:");
            Coordinates input = input();
            if (!coordinates.contains(input)) {
                System.out.println("Non-available square.");
                continue;
            }
            return input;
        }
    }

    private static boolean checkingKingNotUnderCheckmateAfterMove(Board board, Color color, Move move) {
        Board copyBoard = new BoardFactory().copyBoard(board);
        copyBoard.makeMove(move);

        Piece king = copyBoard.getPieceByColor(color)
                .stream().filter(piece -> piece instanceof King)
                .findFirst()
                .get();
        return copyBoard.isSquareAttackedByColor(king.coordinates, color.opposite());
    }
}
