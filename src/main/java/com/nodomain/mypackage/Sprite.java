package com.nodomain.mypackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

class Sprite {

    double x;
    double y;
    private int width;
    private int height;
    private boolean visibility;
    private Image image;

    Sprite(double x, double y) {
        this.x = x;
        this.y = y;
        this.visibility = true;
    }

    void getImageDimensions() {
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
    }

    void loadImage(String imageName) {
        URL url = getClass().getResource("/"+imageName);
        ImageIcon ii = new ImageIcon(url);
        this.image = ii.getImage();
    }

    Image getImage() {
        return this.image;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    boolean isVisible() {
        return this.visibility;
    }

    void setVisible(Boolean visible) {
        this.visibility = visible;
    }

    Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
    }
}