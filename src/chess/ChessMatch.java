package chess;


import boardGame.Board;

//nessa classe possui as regras de xadrez
public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);// Aqui fica a dimensão do tabuleiro
    }


    // vai retornar uma matriz de peças de xadrez referente a essa partida Colunas e linhas
    public ChessPiece[][] getPieces() {

        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j); // (chess Piece ) foi feito um downCast pra interpretar como peça xadrez enão peça comum
            }
        }
        return mat;
    }


}
