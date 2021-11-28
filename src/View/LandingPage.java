package View;

import Controller.Controller;
import Model.Board;
import Model.IAPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LandingPage extends JFrame {

    private Controller controller;
    private JCheckBox computer = new JCheckBox("Computer");
    private JCheckBox player = new JCheckBox("Other Player");

    public LandingPage(Controller controller){
        super("Welcome to the Tic-Tac-Toe Game");
        this.controller = controller;

        this.setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel g = new JPanel();
        BoxLayout box = new BoxLayout(g, BoxLayout.Y_AXIS);

        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new FlowLayout());


        computer.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(computer.isSelected()){
                    player.setSelected(false);
                    controller.setOpponent(new IAPlayer("computer","O",controller.getBoard()));
                }
            }
        });

        player.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(player.isSelected()){
                    computer.setSelected(false);
                    controller.setOpponent(new IAPlayer("Player2","O",controller.getBoard()));
                }
            }
        });
        checkBoxes.add(computer);
        checkBoxes.add(player);


        g.setLayout(box);
        g.add(welcomeLabel());
        g.add(descriptiveLabel());
        g.add(sizeChoice());
        g.add(checkBoxDescriptiveLabel());
        g.add(checkBoxes);
        g.add(startGame());
        add(g);
        setVisible(true);
    }

    public void closeLandingPage() {
        LandingPage.this.dispatchEvent(
                new java.awt.event.WindowEvent(LandingPage.this, java.awt.event.WindowEvent.WINDOW_CLOSING));
    }

    public JLabel welcomeLabel() {

        JLabel welcome = new JLabel("Welcome to Tic Tac Toe", JLabel.RIGHT);
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        welcome.setFont(new Font("Ink Free", Font.BOLD, 50));
        welcome.setPreferredSize(new Dimension(600, 300));
        welcome.setBackground(new Color(187, 143, 206));
        welcome.setForeground(Color.black);

        return welcome;

    }

    public JLabel descriptiveLabel() {

        JLabel description = new JLabel("Choose the size of your Game Board :");
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setFont(new Font("Ink Free", Font.BOLD, 20));
        description.setForeground(new Color(187, 143, 206));

        return description;

    }

    public JComboBox sizeChoice(){
        String[] size = {"[--Please Select a Board Size--]","3*3", "4*4", "5*5"};

        JComboBox sizeList = new JComboBox(size);
        //sizeList.setSize(new Dimension(00, 100));
        sizeList.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ((e.getStateChange() == ItemEvent.SELECTED)) {
                    int selection = sizeList.getSelectedIndex();
                    switch (selection){
                        case 0:  break;
                        case 1:  controller.setBoard(new Board(3,controller)); break;
                        case 2:  controller.setBoard(new Board(4,controller)); break;
                        case 3:  controller.setBoard(new Board(5,controller)); break;
                    }
                }
            }
        });

        return sizeList;
    }

    public JLabel checkBoxDescriptiveLabel() {

        JLabel dragDrop = new JLabel("Choose the type of player that you want to play against :");
        dragDrop.setAlignmentX(CENTER_ALIGNMENT);
        dragDrop.setFont(new Font("Ink Free", Font.BOLD, 20));
        dragDrop.setForeground(new Color(187, 143, 206));

        return dragDrop;

    }

    public JButton startGame() {

        JButton startGame = new JButton("Start Game");
        startGame.setAlignmentX(CENTER_ALIGNMENT);
        startGame.setSize(600, 200);
        startGame.setPreferredSize(new Dimension(450, 60));
        startGame.setBackground(new Color(93, 173, 226));
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LandingPage.this.dispatchEvent(
                        new java.awt.event.WindowEvent(LandingPage.this, java.awt.event.WindowEvent.WINDOW_CLOSING));
                controller.startGame(computer.isSelected());

            }
        });

        return startGame;
    }


}
