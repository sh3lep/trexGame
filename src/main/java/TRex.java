public class TRex extends Sprite {


    double velocityY = 0.0;
    double gravity = 0.025;
    boolean onGround = true;

    public TRex(int x, int y){
        super(x, y);
        this.initTRex();
    }

    private void initTRex() {
            this.loadImage("TRex.gif");
        this.getImageDimensions();
    }

    private void startJump() {
        if (onGround) {
            velocityY = -2.5;
            onGround = false;
        }
    }

    public void update() {
        velocityY += gravity;
        this.y += velocityY;

        if (this.y > 150.0)
        {
            this.y = 150.0;
            velocityY = 0.0;
            onGround = true;
        }
    }

    public void getInput() {
        boolean key = KeyManager.getKey();
        if (key && Board.getingame()) {
            startJump();
        }
    }
}
