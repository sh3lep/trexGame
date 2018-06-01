package com.nodomain.mypackage;

class Cactus extends Sprite{

    Cactus(double x, double y){
        super(x, y);
        this.initCactus();
    }

    private void initCactus() {
        this.loadImage("cactus.png");
        this.getImageDimensions();
    }

    void move(double speed) {
        this.x -= speed;
    }
}
