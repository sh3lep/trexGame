import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {

    private static boolean[] keys;
    private boolean[] pressed;

    public KeyManager() {
        keys = new boolean[1];
        pressed = new boolean[1];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!pressed[0]) {
                keys[0] = true;
                pressed[0] = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[0] = false;
        pressed[0] = false;
    }

    public static boolean getKey() {
        return keys[0];
    }
}