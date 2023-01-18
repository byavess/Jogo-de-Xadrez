package boardGame;

public class Board {

    private int rows;
    private int columns;
    private  Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns <1){
            throw new BoardException("Erro ao cria o tabuleiro: e necessario uma linha e uma coluna");
        }

        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

      public Piece piece (int row, int column) {
        if(!positinonExists(row,column)){
            throw new BoardException("Posição não existe");
        }
        return pieces[row][column];
    }
    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posição não existe");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    public void placePiece (Piece piece,Position position ) {
        if(thereIsApiece(position)){
            throw new BoardException("There is already a piece on positon "+ position);
        }

        pieces[position.getRow()][position.getColumn()] = piece; // na posição da da e atribuir a ela a posição informada
        piece.position = position;
    }
    public Piece removePiece(Position positon) {
        if(!positionExists(positon)){
            throw  new BoardException("Position not on the borad");
        }
        if(piece (positon) == null){
            return null;
        }
        Piece aux = piece(positon); //peça retirada
        aux.position = null;
        pieces [positon.getRow()][positon.getColumn()]= null;
        return aux;
    }

    //ate onde pode ir as posições
    private boolean positinonExists(int row, int column){
      return   row >= 0 && row < rows && columns >= 0 && column < columns;
    }
    public boolean positionExists(Position position){
        return  positinonExists(position.getRow(), position.getColumn());

    }
    public boolean thereIsApiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posição não existe");
        }
       return piece(position) != null;
    }


}
