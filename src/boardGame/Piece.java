package boardGame;


//Esse tabuleiro e o tabuleiro interno invisivel
public class Piece {
    protected Position position; // protected posição camada omvosoveç
    private  Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }
    protected Board getBoard() {
        return board;
    }

}
