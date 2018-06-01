package com.nodomain.mypackage;

class TRex extends Sprite {


    private double velocityY = 0.0;
    private boolean onGround = true;

    TRex(int x, int y){
        super(x, y);
        initTRex();
    }

    private void initTRex() {
        this.loadImage("TRex.gif");
        this.getImageDimensions();
    }

    private void startJump() {
        if (onGround) {
            this.loadImage("TRexStanding.png");
            velocityY = -2.5;
            onGround = false;
        }
    }

    void updatePosition() {
        double gravity = 0.025;
        velocityY += gravity;
        y += velocityY;

        if (y > 150.0)
        {
            y = 150.0;
            velocityY = 0.0;
            onGround = true;
            this.loadImage("TRex.gif");
        }
    }

    void getInput() {
        boolean key = KeyManager.getKey();
        if (key) {
            startJump();
        }
    }
}
