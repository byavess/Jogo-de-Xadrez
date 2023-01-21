package chessPieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King  extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString(){
        return "K";
    }
    private boolean canMove(Position position){ //se o rei pode mover pra uma determidada posição
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor()!= getColor();

    }

    @Override
    public boolean[][] possibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

       Position p = new Position(0,0);
       //above
        p.setValues(position.getRow() -1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValues(position.getRow() +1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //left
        p.setValues(position.getRow() , position.getColumn() -1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //right
        p.setValues(position.getRow() , position.getColumn() +1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NW  noroeste diagonal esquerda
        p.setValues(position.getRow() -1 , position.getColumn() -1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //NW  nordeste diagonal direita
        p.setValues(position.getRow() -1 , position.getColumn() +1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SW sudoeste diagonal esquerda baixo
        p.setValues(position.getRow() +1 , position.getColumn() -1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //SW sudeste diagonal direita baixo
        p.setValues(position.getRow() +1 , position.getColumn() +1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }
}
