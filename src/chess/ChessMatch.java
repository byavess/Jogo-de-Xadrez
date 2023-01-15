package chess;


import boardGame.Board;
import boardGame.Position;
import chessPieces.King;
import chessPieces.Rook;

//nessa classe possui as regras de xadrez
public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);// Aqui fica a dimensão do tabuleiro
        initalSetup();
    }


    // vai retornar uma matriz de peças de xadrez referente a essa partida Colunas e linhas
    public ChessPiece[][] getPieces() {

        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i<board.getRows(); i++) {
            for (int j=0; j<board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j); // (chess Piece ) foi feito um downCast pra interpretar como peça xadrez enão peça comum
            }
        }
        return mat;
    }

    //chama o board . placePiece
    //coordenadas do xadrez
    private void  placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column,row).toPosition());
    }

    private void initalSetup(){

        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }


}
