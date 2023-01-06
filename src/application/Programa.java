package application;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;

public class Programa {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();


//pra imprimir peças da partida// o metodo vai receber a matriz de peça da partida

        UI.printBoard(chessMatch.getPieces());


    }
}
