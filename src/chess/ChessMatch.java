package chess;


import application.UI;
import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chessPieces.King;
import chessPieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//nessa classe possui as regras de xadrez
public class ChessMatch {
    private int turn;
    private Color currentPlay;//jogador atual
    private Board board;
    private boolean check;
    private  boolean checkMate;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();//essa estacia pode ser feita no construtor
    private List<Piece> capturedPieces = new ArrayList<>();

    //inicio da partida
    public ChessMatch() {
        board = new Board(8, 8);// Aqui fica a dimensão do tabuleiro
        turn = 1;
        currentPlay = Color.WHITE;
        check = false;//se quiser infatizar pq ja começa e falso
        initalSetup();
    }
    public  int getTurn(){
        return turn;
    }
    public Color getCurrentPlayer(){
        return currentPlay;
    }
    public boolean getCheck(){
        return check;
    }
    public boolean getcheckMate(){
        return checkMate;
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
        public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
        }//imprime uma posições possiveis a partir de uma posiçã ode origens


    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        //converter pra posição da matriz
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);//responsavel pelo movimento da peça make move

            if(testCheck(currentPlay)){
                undoMove(source, target, capturedPiece);
                throw new ChessException("you can't put yourself in check");
            }
            check = (testCheck(opponent(currentPlay)))? true : false;//condição ternaria //se terte check do opnenet for verdade se nao e false

                if(testCheckMate(opponent(currentPlay))){//testando se opnete ta em checMate
                    checkMate = true;
                }
                else {
                    nextTurn();
                }
        return (ChessPiece)capturedPiece;
    }
    private Piece makeMove(Position source, Position target) { //posição origem e destino
        Piece p = board.removePiece(source);//tira a peça do tabuleiro

        Piece capturedPiece = board.removePiece(target);//possivel peça capturada
        board.placePiece(p, target);//colocar no destino a peça que tava na origem

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);//retira peça do tabuleiro
            capturedPieces.add(capturedPiece);//adicona a lista de peças capturadas
        }

        return (ChessPiece)capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){//desfazer movimento "isso so no caso do rei"
        Piece p = board.removePiece(target);//tira a peça que moveu do destino
//        ChessPiece p = (ChessPiece)board.removePiece(target);
//        p.decreaseMoveCount();
        board.placePiece(p, source);//devolver pra posição de origem
        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);//tira a peça que moveu do destino
            capturedPieces.remove(capturedPiece);       //devolver pra posição de origem
            piecesOnTheBoard.add(capturedPiece);//adiconando de volta ao tabuleiro
        }
    }
        private void validateSourcePosition(Position position){
        if (!board.thereIsApiece(position)){
            throw new ChessException("There is no piece on source positon");//
        }
        if(currentPlay != ((ChessPiece)board.piece(position)).getColor()) {//ChessPiece foi um downcasting
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Ther is no possible moves for the chose piece");
    }
      }

      private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chosen piece can't move to targer position");
        }
      }
        private void nextTurn(){
        turn ++;
        currentPlay = (currentPlay == Color.WHITE)? Color.BLACK : Color.WHITE; // condição ternaria//troca de turno
        }

        private Color opponent(Color color ){
        return (color == Color.WHITE)? Color.BLACK : Color.WHITE;//se a cor do argumento for igual a branco retorna blck caso contrario branco

        }

        private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());

            for (Piece p : list){
                if( p instanceof King){
                    return (ChessPiece)p;//ChessPiece e DownCasting

                }

            }
            throw new IllegalStateException("There is no " + color + "King on the bord");// naõ foi tratada essa exceção não e pra acontecer

    }

    private boolean testCheck(Color color){
        Position kingPositon = king(color).getChessPosition().toPosition();//pega a posição do rei em formato de matriz
        List<Piece> opponentPieces =  piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());//peças do tabuleiro filtrada com as cores do oppnente
            for( Piece p : opponentPieces){
                boolean[][] mat = p.possibleMoves();
                if(mat[kingPositon.getRow()][kingPositon.getColumn()]){
                    return true;
                }
            }
            return false;
    }
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;

        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i<board.getRows(); i++) {
                for (int j=0; j<board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
    return true;
    }
    //chama o board . placePiece
    //coordenadas do xadrez
    private void  placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column,row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initalSetup() {


        placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
      }
    }



