public class Cactus extends Sprite{

    public Cactus(double x, double y){
        super(x, y);
        this.initCactus();
    }

    private void initCactus() {
        this.loadImage("cactus.png");
        this.getImageDimensions();
    }

    public void move(double speed) {
        
        this.x -= speed;
    }
}
