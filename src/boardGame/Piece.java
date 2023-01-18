package boardGame;


//Esse tabuleiro e o tabuleiro interno invisivel
public abstract class Piece {
    protected Position position; // protected posição camada
    private  Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }
    protected Board getBoard() {
        return board;
    }


    //movimento das peças
    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];//hook metodos chama uma possivel implementaçção
    }
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for (int i=0; i<mat.length; i ++){
            for ( int j=0; j<mat.length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

}
