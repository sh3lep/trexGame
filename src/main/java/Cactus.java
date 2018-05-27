public class Cactus extends Sprite{

    double speed = 1.5;

    public Cactus(double x, double y){
        super(x, y);
        this.initCactus();
    }

    private void initCactus() {
        this.loadImage("cactus.png");
        this.getImageDimensions();
    }

    public void move() {
        this.x -= speed;
    }
}
