package com.nodomain.mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private static TRex tRex;
    private static ArrayList<Cactus> cactusList;
    private static boolean inGame;
    private static boolean beforeGame;
    private static boolean playedBefore;
    private int time;
    private static int score;
    private double cactusSpeed = 1.5;

    private int[] rnd = {300, 600};


    Board() {
        playedBefore = false;
        initializeBoard();
    }

    private void initializeBoard() {
        addKeyListener(new KeyManager());
        setFocusable(true);
        setBackground(Color.WHITE);
        inGame = false;
        playedBefore = false;
        beforeGame = true;
        setPreferredSize(new Dimension(600, 300));
        newCactusList();
        tRex = new TRex(60, 150);
        timer = new Timer(1, this);
        timer.start();
        time = 0;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawString("Score: " + score, 530, 20);
        g.drawLine(0, 195, 600, 195);
        g.drawImage(tRex.getImage(), (int) tRex.getX(), (int) tRex.getY(), this);

        if (beforeGame) {
            drawGameScreen(g, true);
        }
        if (inGame) {
            drawGame(g);
        }
        if (playedBefore && !inGame) {
            drawGameScreen(g, false);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGame(Graphics g) {

        Iterator cactusIterator = cactusList.iterator();
        while (cactusIterator.hasNext()) {
            Cactus cactus = (Cactus) cactusIterator.next();
            g.drawImage(cactus.getImage(), (int) cactus.getX(), (int) cactus.getY(), this);
        }
    }

    private void drawGameScreen(Graphics g, boolean beforeGame) {
        Font font = new Font("Helvetica", 1, 25);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g.setColor(Color.BLACK);
        g.setFont(font);

        String line;
        String secondLine = null;
        int centerY = 3;
        if (beforeGame) centerY = 2;

        if (beforeGame) line = "Press SPACE to start";
        else {
            line = "GAME OVER";
            secondLine = "Press SPACE to restart";
        }

        g.drawString(line, (600 - fontMetrics.stringWidth(line)) / 2, 300 / centerY);
        if (!beforeGame) {
            g.drawString(secondLine, (600 - fontMetrics.stringWidth(secondLine)) / 2, (300 / 3) * 2 - 20);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (beforeGame) {
            startGame();
        } else {
            if (inGame) {
                updateCactusList();
                updateTRex();
                time++;
                if (time % 20 == 0) {
                    score++;
                    cactusSpeed += 0.0006;
                }
                checkCollision();
            } else {
                gameOver();
            }
        }

        repaint();

    }

    private void checkCollision() {
        Rectangle tRexBounds = tRex.getBounds();

        for (Cactus cactus : cactusList) {
            Rectangle cactusBounds = cactus.getBounds();
            if (tRexBounds.intersects(cactusBounds)) {
                cactus.setVisible(false);
                inGame = false;
                playedBefore = true;
            }
        }
    }

    private void updateCactusList() {
        Random random = new Random();
        int rndNum = random.nextInt(2);
        int currentFrequency = rnd[rndNum];

        if (cactusList.isEmpty()) {
            newCactusList();
        }

        if (time % currentFrequency == 0 && score > 20 && cactusList.get(cactusList.size() - 1).getX() <= 450) {
            cactusList.add(new Cactus(600, 148));
        }

        for (int i = 0; i < cactusList.size(); i++) {
            Cactus cactus = cactusList.get(i);

            if (cactus.getX() < 0 - cactus.getBounds().getWidth()) {
                cactusList.remove(cactus);
            } else if (cactus.isVisible() && time % 2 == 0) {
                cactus.move(cactusSpeed);
            }
        }
    }

    private void newCactusList() {
        cactusList = new ArrayList<>();

        cactusList.add(new Cactus(600, 148));
    }

    private void updateTRex() {
        if (tRex.isVisible()) {
            tRex.getInput();
            tRex.updatePosition();
        }
    }

    private void startGame() {
        boolean keyPressed = KeyManager.getKey();

        if (keyPressed) {
            beforeGame = false;
            inGame = true;
        }
    }

    private void gameOver() {
        boolean keyPressed = KeyManager.getKey();

        if (keyPressed) setDefault();
    }

    private void setDefault() {
        timer.stop();
        score = 0;
        cactusSpeed = 1.5;
        initializeBoard();
    }
}