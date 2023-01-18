package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    // so tem acesso no mesmo pacotes e subclasses no caso Piece
    //vai ser usados novamete em outras peças //depois vamos nas peças e criamos o movimento singular
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p.getColor() != color;//concluir que e uma peça adversaria
    }
}
