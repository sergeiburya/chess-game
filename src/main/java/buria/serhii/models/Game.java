package buria.serhii.models;

import buria.serhii.models.board.Board;
import buria.serhii.models.board.Move;
import buria.serhii.models.pieces.Color;

import java.util.Collections;
import java.util.List;

public class Game {
    private final Board board;
    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();
    private final List<GameStateCheker> chekers = List.of(
            new StalemateGameStateCheker(),
            new CheckmateGameStateCheker()
    );

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        Color colorToMove =  Color.WHITE;

        GameState gameState = determineGameState(board, colorToMove);

        while (gameState == GameState.ONGOING) {
            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");
            }

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            board.makeMove(move);
            colorToMove = colorToMove.opposite();

           gameState =  determineGameState(board, colorToMove);
        }
        renderer.render(board);
        System.out.println("Game ended with state: - " + gameState);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateCheker cheker : chekers) {
            GameState gameState = cheker.check(board, color);
            if (gameState != GameState.ONGOING) {
                return gameState;
            }
        }
        return GameState.ONGOING;
    }
}
