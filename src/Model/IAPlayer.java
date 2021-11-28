package Model;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class IAPlayer extends Player{

    private String nickname;
    private Boolean winner = false;
    private String type;
    private Board game;


    /* ************************************ Constructor ************************************ */
    public IAPlayer(String nickname, String type, Board game){
        super(nickname,type,game );
        this.game = game;
    }

    /* ******************************* Getters And Setters ******************************* */

    public String getNickname() {
        return nickname;
    }

    public Boolean getWinner() {
        return winner;
    }

    public String getType() {
        return type;
    }

    public Board getGame() {
        return game;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGame(Board game) {
        this.game = game;
    }

    public void bestSolution(){
        Random rand = new Random();
        int computerMovex;
        int computerMovey;
        Point win = game.getController().playerWillWin("X");
        Point winningPoint = game.getController().playerWillWin("O");
        Point maybe = game.getController().rowContains("0");
        Point maybeX = game.getController().rowContains("X");
        if(game.getGameBoard()[0][0]!= null && game.getGameBoard()[0][0].equals("X") ){
            computerMovex = 2;
            computerMovey = 0;
            if (game.possibleMove(computerMovex, computerMovey)) {
                game.addSymboleBoard(computerMovex,computerMovey,"O");
            }else{
                if(winningPoint.x!=-1) {
                    computerMovex = winningPoint.x;
                    computerMovey = winningPoint.y;
                    game.addSymboleBoard(computerMovex, computerMovey, "O");
                }else if(win.x!=-1){
                    computerMovex = win.x;
                    computerMovey =win.y;
                    game.addSymboleBoard(computerMovex,computerMovey,"O");
                }else if(maybe.x!=-1){
                    computerMovex = maybe.x;
                    computerMovey =maybe.y;
                    game.addSymboleBoard(computerMovex,computerMovey,"O");
                }else if(maybeX.x!=-1) {
                    computerMovex = maybeX.x;
                    computerMovey = maybeX.y;
                    game.addSymboleBoard(computerMovex, computerMovey, "O");
                }else{
                    while (true) {
                        computerMovex = rand.nextInt(game.getSize());
                        computerMovey = rand.nextInt(game.getSize());
                        if (game.possibleMove(computerMovex, computerMovey)) {
                            game.addSymboleBoard(computerMovex,computerMovey,"O");
                            break;
                        }
                    }
                }
            }
        }else{
            if(winningPoint.x!=-1) {
                computerMovex = winningPoint.x;
                computerMovey = winningPoint.y;
                game.addSymboleBoard(computerMovex, computerMovey, "O");
            }else if(win.x!=-1){
                computerMovex = win.x;
                computerMovey =win.y;
                game.addSymboleBoard(computerMovex,computerMovey,"O");
            }else if(maybe.x!=-1){
                computerMovex = maybe.x;
                computerMovey =maybe.y;
                game.addSymboleBoard(computerMovex,computerMovey,"O");
            }else if(maybeX.x!=-1) {
                computerMovex = maybeX.x;
                computerMovey = maybeX.y;
                game.addSymboleBoard(computerMovex, computerMovey, "O");
            }else{
                while (true) {
                    computerMovex = rand.nextInt(game.getSize());
                    computerMovey = rand.nextInt(game.getSize());
                    if (game.possibleMove(computerMovex, computerMovey)) {
                        game.addSymboleBoard(computerMovex,computerMovey,"O");
                        break;
                    }
                }
            }
        }




        game.getController().PrintMoveOnBoard(computerMovex, computerMovey);

    }
}
