import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private TRex tRex;
    private ArrayList<Cactus> cacti;
    private static boolean ingame;
    private static boolean inGameOver;
    private boolean beforeGame;
    private boolean playedBefore;
    private int time;
    private int score;
    private double speed = 1.5;

    private int[] rnd = {300, 600};



    public Board() {
        this.playedBefore = false;
        this.initBoard();
    }

    private void initBoard() {
        this.addKeyListener(new KeyManager());
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        this.ingame = false;
        this.playedBefore = false;
        this.inGameOver = false;
        this.beforeGame = true;
        this.initCacti();
        this.setPreferredSize(new Dimension(600, 300));
        this.tRex = new TRex(60, 150);
        this.timer = new Timer(1, this);
        this.timer.start();
        this.time = 0;
    }

    public static boolean getingame() {
        return ingame;
    }

    private void checkCollision() {
        Rectangle rex = this.tRex.getBounds();

        for (Cactus cactus : this.cacti) {
            Rectangle cactusR = cactus.getBounds();
            if (rex.intersects(cactusR)) {
                cactus.setVisible(false);
                this.ingame = false;
                this.playedBefore = true;
                this.inGameOver = true;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Score: " + this.score, 540, 20);
        g.drawLine(0, 195, 600, 195);
        g.drawImage(tRex.getImage(), (int)tRex.getX(), (int)tRex.getY(), this);
        if (beforeGame) {
            this.drawBeforeGame(g);
        }
        if (ingame) {
            this.drawGame(g);
        }
        if (playedBefore && inGameOver) {
            drawGameOver(g);
        }
    }

    private void drawBeforeGame(Graphics g) {
        String msg = "Press SPACE to start";

        Font small = new Font("Helvetica", 1, 25);
        FontMetrics fm = this.getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);

        g.drawString(msg, (600 - fm.stringWidth(msg)) / 2, 300 / 2);
    }

    private void drawGame(Graphics g) {
        Iterator cactusIterator = cacti.iterator();
        while (cactusIterator.hasNext()) {
            Cactus cactus = (Cactus) cactusIterator.next();
            g.drawImage(cactus.getImage(), (int)cactus.getX(), (int)cactus.getY(), this);
        }
    }

    private void drawGameOver(Graphics g) {
        String msg = "GAME OVER";
        String msg2 = "Press SPACE to restart";

        Font small = new Font("Helvetica", 1, 25);
        FontMetrics fm = this.getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);

        g.drawString(msg, (600 - fm.stringWidth(msg)) / 2, 300 / 3);
        g.drawString(msg2, (600 - fm.stringWidth(msg2)) / 2, (300 / 3) * 2 - 20);
    }

    private void initCacti() {
        this.cacti = new ArrayList<>();

        this.cacti.add(new Cactus(600, 148));
    }

    private void updateCacti() {
        Random rand = new Random();
        int value = rand.nextInt(2);
        int curfr = rnd[value];

        if (this.cacti.isEmpty()) {
            this.initCacti();
        }



        if (this.time % curfr == 0 && score > 20) {
            this.cacti.add(new Cactus(600, 148));
        }

        for (int i = 0; i < this.cacti.size(); i++) {
            Cactus cactus = this.cacti.get(i);

            if (cactus.getX() < 0 - cactus.getBounds().getWidth()) {
                this.cacti.remove(cactus);
            } else if (cactus.isVisible() && this.time % 2 == 0) {
                cactus.move(speed);
            }
        }
    }


    private void start() {
        boolean key = KeyManager.getKey();

        if (key) {
            this.beforeGame = false;
            this.ingame = true;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (beforeGame) {
            this.start();
        } else {
            if (ingame && !inGameOver) {

                this.updateCacti();
                this.updateTRex();
                time++;
                if (time % 20 == 0) {
                    this.score++;
                    speed += 0.00006;
                }
                this.checkCollision();

                tRex.update();

            } else {
                if (!ingame && inGameOver) {
                    gameOver();
                }
            }
        }
    }

    private void gameOver() {
        boolean key = KeyManager.getKey();

        if (key) {
            this.timer.stop();
            score = 0;
            speed = 1.5;
            this.initBoard();
        }
    }

    private void updateTRex() {
        if (this.tRex.isVisible()) {
            this.tRex.getInput();
        }
    }
}