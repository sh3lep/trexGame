package com.nodomain.mypackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;

class Sprite {

    public double x;
    public double y;
    public int width;
    public int height;
    public boolean visibility;
    private Image image;

    public Sprite(double x, double y) {
        this.x = x;
        this.y = y;
        this.visibility = true;
    }

    public void getImageDimensions() {
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
    }

    public void loadImage(String imageName) {
        URL url = getClass().getResource("/"+imageName);
        ImageIcon ii = new ImageIcon(url);
        this.image = ii.getImage();
    }

    public Image getImage() {
        return this.image;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isVisible() {
        return this.visibility;
    }

    public void setVisible(Boolean visible) {
        this.visibility = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
    }
}