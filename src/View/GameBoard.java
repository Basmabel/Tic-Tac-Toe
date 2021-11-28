package View;

import Controller.Controller;
import Model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {

    private Controller controller = new Controller();
    private Board board;
    private JButton[] box;
    private JPanel boxPannel;
    private JPanel presentation = new JPanel();
    private Boolean turn = false;
    private Boolean isAi = false;
    private Boolean Won = false;
    private String elem = "X";

    public GameBoard(Controller controller, Boolean isAi){
        super("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        getContentPane().setBackground(new Color(50, 50, 50));
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        setVisible(true);

        this.isAi = isAi;
        this.controller = controller;
        this.board = controller.getBoard();
        this.box = new JButton[board.getSize()*board.getSize()];

        this.boxPannel = new JPanel(new GridLayout(board.getSize(), board.getSize()));
        boxPannel.setBackground(new Color(187, 143, 206));

        initializeButton();

        presentation.setLayout(new BorderLayout());
        presentation.setBounds(0, 0, 800, 100);

        presentation.add(initializeLabel());
        add(presentation, BorderLayout.NORTH);
        add(boxPannel);


    }

    public JButton[] getBox() {
        return box;
    }

    public void setButton(int index, String text){
        box[index].setText(text);
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public void setWon(Boolean won) {
        Won = won;
    }

    public void initializeButton(){
        for (int i = 0; i < box.length; i++) {
            box[i] = new JButton();// creating object for each button element of array
            boxPannel.add(box[i]);// adding each button to the pannel for buttons
            box[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            box[i].setFocusable(false);
            int finalI = i;
            box[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(!turn && !Won){
                        turn = true;
                        box[finalI].setText(elem);

                        controller.getBoard().addSymboleBoard((int)((finalI)/controller.getBoard().getSize()),(finalI)%controller.getBoard().getSize(),elem);

                        if(controller.gameFinished()){
                            if(controller.gameWon()){
                                JOptionPane.showConfirmDialog(null, "You have Won this game");
                            }else{
                                JOptionPane.showConfirmDialog(null, "It's a Tie");
                            }

                        }else if(controller.gameWon()){
                            JOptionPane.showConfirmDialog(null, "You have Won this game");
                            Won = true;
                        }
                        else{
                            if(isAi){
                                controller.startOpponent();
                            }else{
                                if(elem.equals("X")){
                                    elem = "O";
                                } else if(elem.equals("O")){
                                    elem = "X";
                                }
                                turn=false;
                                Won =false;
                            }
                        }

                    }
                }
            });
        }
    }

    public JLabel initializeLabel(){
        JLabel welcome = new JLabel("TIC-TAC-TOE");
        welcome.setBackground(new Color(93, 173, 226));
        welcome.setForeground(new Color(155, 89, 182));
        welcome.setFont(new Font("Ink Free", Font.BOLD, 75));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setText("Tic Tac Toe");
        welcome.setOpaque(true);

        return welcome;
    }



}
