package boardGame;


//TABULEIRO
public class Piece {
    protected Position position; // protected posição invisivel
    private  Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }
    protected Board getBoard() {
        return board;
    }

}
