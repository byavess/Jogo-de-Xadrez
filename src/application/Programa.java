package application;


import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        //pra imprimir peças da partida// o metodo vai receber a matriz de peça da partida
//Lembrar que linha da matrix e matrix_row = 8-1 ches_row
        //a coluna 'a' unicode - 'a' unicode = 0 depois 'b' unicode - 'a'unicode =
        // 0 depois 'c'unicode - 'a' = 2 assim por diante
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();


        while (true) {
                        try {
                 UI.clearScreen();//limpa a tela cada vez que for no while

                UI.printBoard(chessMatch.getPieces());//imprime o tabuleiro na tela
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);//lendo a posição de origem

                System.out.println();

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            }
            catch (ChessException e ){
                System.out.println(e.getMessage());
                sc.nextLine();

            }
            catch (InputMismatchException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
