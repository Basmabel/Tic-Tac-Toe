package Model;

public class Player {

    private String nickname;
    private Boolean winner = false;
    private String type;
    private Board game;


/* ************************************ Constructor ************************************ */
    public Player() {

    }

    public Player(String nickname, String type, Board game){
        this.nickname =nickname;
        this.type= type;
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

    public void bestSolution(){}
}
