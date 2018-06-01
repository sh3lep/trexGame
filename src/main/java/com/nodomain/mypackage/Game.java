package com.nodomain.mypackage;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame{

    private Game() {
        this.initUI();
    }

    private void initUI() {
        add(new Board());
        setResizable(false);
        pack();
        setTitle("T-Rex");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(
                () -> {
                    Game ex = new Game();
                    ex.setVisible(true);
                }
        );
    }
}
