package Controller;

import Model.Board;
import Model.Player;
import View.GameBoard;
import View.LandingPage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Controller {

    private Board board;
    private GameBoard game;
    private Player opponent;
    private Player player;
    private LandingPage landing;

    public Controller(){
        super();
        this.landing = new LandingPage(this);
        this.board = new Board();
        this.player = new Player("Player1","X",board);
    }

/* ******************************* Getters And Setters ******************************* */
    public Board getBoard() {
        return board;
    }

    public GameBoard getGame() {
        return game;
    }

    public Player getOpponent() {
        return opponent;
    }

    public LandingPage getLanding() {
        return landing;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setGame(GameBoard game) {
        this.game = game;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void setLanding(LandingPage landing) {
        this.landing = landing;
    }
    /* **************************** End of Getters And Setters **************************** */

    public void startGame(Boolean type){
        landing.closeLandingPage();
        this.game = new GameBoard(this,type);

    }

    public void startOpponent(){
        opponent.bestSolution();
    }

    public void PrintMoveOnBoard(int x, int y){
        game.setButton(x*board.getSize()+y,"O");
        game.setTurn(false);
        if(gameFinished()) {
            if (gameWon()) {
                JOptionPane.showConfirmDialog(null, "Computer have Won this game");
            } else {
                JOptionPane.showConfirmDialog(null, "It's a Tie");
            }
            game.setWon(true);
        }else if(gameWon()){
            JOptionPane.showConfirmDialog(null, "Computer have Won this game");
            game.setWon(true);
        }

    }

    public Boolean gameFinished(){
        for(int i=0; i< board.getSize(); i++){
            for(int j=0; j< board.getSize(); j++){
                if(board.possibleMove(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean gameWon(){
        String [] diagLeft = new String[board.getSize()];
        String [] diagRight = new String[board.getSize()];
        for(int i=0; i<board.getSize(); i++){
            String [] column = getColumn(board.getGameBoard(), i);
            int finalI = i;
            if( Arrays.asList(board.getGameBoard()[i]).stream().allMatch(t -> t == board.getGameBoard()[finalI][0]) && board.getGameBoard()[finalI][0]!= null){
                return true;
            } else if ( Arrays.asList(column).stream().allMatch(t -> t == column[0]) && column[0]!= null){
                return true;
            }
            diagLeft[i]= board.getGameBoard()[i][i];
            diagRight[i]= board.getGameBoard()[i][board.getSize()-i-1];
        }
        if( Arrays.asList(diagLeft).stream().allMatch(t -> t == diagLeft[0]) && diagLeft[0]!=null){
            return true;
        } else if ( Arrays.asList(diagRight).stream().allMatch(t -> t == diagRight[0]) && diagRight[0]!=null){
            return true;
        }

        return false;
    }

    public String[] getColumn(String[][] array, int index){
        String[] column = new String[array[0].length]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        return column;
    }

    public int numberOfOccurences( String[] table, String character){
        int result =0;
        for(String s : table){
            if(s!= null && s.equals(character)){
                result++;
            }
        }
        return result;
    }

    public Point playerWillWin(String elem){
        String [] diagLeft = new String[board.getSize()];
        String [] diagRight = new String[board.getSize()];
        for(int i=0; i<board.getSize(); i++){
            String [] column = getColumn(board.getGameBoard(), i);
            int finalI = i;
            if( numberOfOccurences(board.getGameBoard()[i],elem)==board.getSize()-1){
                for(int j=0; j<board.getSize(); j++){
                    if(board.getGameBoard()[i][j]== null){
                        return new Point(i,j);
                    }
                }
            } else if ( numberOfOccurences(column,elem)==board.getSize()-1){
                for(int j=0; j< column.length; j++){
                    if(column[j]== null){
                        return new Point(j,i);
                    }
                }
            }
            diagLeft[i]= board.getGameBoard()[i][i];
            diagRight[i]= board.getGameBoard()[i][board.getSize()-i-1];
        }
        if(  numberOfOccurences(diagLeft,elem)==diagLeft.length-1){
            for(int j=0; j< diagLeft.length; j++){
                if(diagLeft[j]== null){
                    return new Point(j,j);
                }
            }
        } else if (numberOfOccurences(diagRight,elem)==diagRight.length-1){
            for(int j=0; j< diagRight.length; j++){
                if(diagRight[j]== null){
                    return new Point(diagRight.length-1-j,j);
                }
            }
        }

        return  new Point(-1,-1);
    }

    public Point rowContains( String elem){
        String [] diagLeft = new String[board.getSize()];
        String [] diagRight = new String[board.getSize()];
        for(int i=0; i<board.getSize(); i++){
            String [] column = getColumn(board.getGameBoard(), i);
            int finalI = i;
            if( Arrays.asList(board.getGameBoard()[i]).stream().anyMatch(t -> t == elem) ){
                for(int j=0; j<board.getSize(); j++){
                    if(board.getGameBoard()[i][j]== null){
                        return new Point(i,j);
                    }
                }
            } else if ( Arrays.asList(column).stream().allMatch(t -> t == elem)){
                for(int j=0; j< column.length; j++){
                    if(column[j]== null){
                        return new Point(j,i);
                    }
                }
            }
            diagLeft[i]= board.getGameBoard()[i][i];
            diagRight[i]= board.getGameBoard()[i][board.getSize()-i-1];
        }
        if( Arrays.asList(diagLeft).stream().anyMatch(t -> t == elem)){
            for(int j=0; j< diagLeft.length; j++){
                if(diagLeft[j]== null){
                    return new Point(j,j);
                }
            }
        } else if ( Arrays.asList(diagRight).stream().anyMatch(t -> t == elem)){
            for(int j=0; j< diagRight.length; j++){
                if(diagRight[j]== null){
                    return new Point(diagRight.length-1-j,j);
                }
            }
        }

        return  new Point(-1,-1);
    }
}
