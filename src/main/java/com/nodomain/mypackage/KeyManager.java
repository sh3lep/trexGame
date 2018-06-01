package com.nodomain.mypackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {

    private static boolean key;

    KeyManager() {
        key = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!key) {
                key = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = false;
    }

    static boolean getKey() {
        return key;
    }
}