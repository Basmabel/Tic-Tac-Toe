package Model;

import Controller.Controller;

public class Board {

    private int size;
    private String [][] gameBoard;
    private Controller controller;

    public Board(){
        super();
    }

    public Board(int size, Controller controller){
        this.size = size;
        gameBoard = new String[size][size];
        this.controller = controller;
    }

    public void addSymboleBoard(int x, int y, String type){
        gameBoard[x][y]= type;
    }

    public int getSize() {
        return size;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public Controller getController() {
        return controller;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean possibleMove(int computerMovex, int computerMovey){
        if(gameBoard[computerMovex][computerMovey]== null)
            return  true;
        else
            return false;
    }
}
