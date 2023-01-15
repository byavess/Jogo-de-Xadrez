package application;


import chess.ChessMatch;

public class Programa {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();


//pra imprimir peças da partida// o metodo vai receber a matriz de peça da partida
//Lembrar que linha da matrix e matrix_row = 8-1 ches_row
        //a coluna 'a' unicode - 'a' unicode = 0 depois 'b' unicode - 'a'unicode =
        // 0 depois 'c'unicode - 'a' = 2 assim por diante
        UI.printBoard(chessMatch.getPieces());


    }
}
