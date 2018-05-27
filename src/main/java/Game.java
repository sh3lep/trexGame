import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame{

    private Game() {
        this.initUI();
    }

    private void initUI() {
        this.add(new Board());
        this.setResizable(false);
        this.pack();
        this.setTitle("T-Rex");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
