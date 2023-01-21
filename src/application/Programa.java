package application;


import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        //pra imprimir peças da partida// o metodo vai receber a matriz de peça da partida
//Lembrar que linha da matrix e matrix_row = 8-1 ches_row
        //a coluna 'a' unicode - 'a' unicode = 0 depois 'b' unicode - 'a'unicode =
        // 0 depois 'c'unicode - 'a' = 2 assim por diante
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();


        while (!chessMatch.getcheckMate()) {
                        try {
                UI.clearScreen();//limpa a tela cada vez que for no while
                UI.printMatch(chessMatch,captured);//imprime o tabuleiro na tela
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);//lendo a posição de origem

                            boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                            UI.clearScreen();
                            UI.printBoard(chessMatch.getPieces(), possibleMoves);//colori as possiveis possibilidades
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }
            }
            catch (ChessException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
                        catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }

        }
        //chegou aqui e pq deu check mate
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);//pra ter a visão geral da partida finalizada
    }
}
