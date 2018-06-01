package com.nodomain.mypackage;

public class TRex extends Sprite {


    private double velocityY = 0.0;
    private boolean onGround = true;

    public TRex(int x, int y){
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

    public void updatePosition() {
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

    public void getInput() {
        boolean key = KeyManager.getKey();
        if (key) {
            startJump();
        }
    }
}
