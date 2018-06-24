import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    public int x;
    public int y;
    public BufferedImage image;
    public int velocityX;
    public int velocityY;

    public Enemy(int x, int y, BufferedImage image, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public Enemy() {

    }

    public void run(Player player) {
//        this.x += this.velocityX;
//        this.y += this.velocityY;
//
//        if (this.x < 0 || this.x > 1024 - 20)
//            this.velocityX = -this.velocityX;
//
//        if (this.y < 0 || this.y > 600 - 20)
//            this.velocityY = -this.velocityY;

        int _velocityX = this.velocityX;
        int _velocityY = this.velocityY;
        if(player.x<this.x){
            _velocityX = -_velocityX;
        }
        if(player.y<this.y){
            _velocityY = -_velocityY;
        }
        int subX = this.x + _velocityX;
        int subY = this.y + _velocityY;
        Double pytagoX = Math.sqrt(Math.pow(this.x+_velocityX-player.x,2) + Math.pow(this.y-player.y,2));
        double minPytago = pytagoX;
        Double pytagoY = Math.sqrt(Math.pow(this.x-player.x,2) + Math.pow(this.y+_velocityY-player.y,2));
        if(pytagoY<minPytago){
            minPytago=pytagoY;
        }
        Double pytagoXY = Math.sqrt(Math.pow(this.x+_velocityX-player.x,2)+ Math.pow(this.y+_velocityY-player.y,2));
        if(pytagoXY<minPytago){
            minPytago=pytagoXY;
        }
        if(pytagoX==minPytago){
            this.x += _velocityX;
        }
        else if (pytagoY==minPytago) {
            this.y+=_velocityY;
        }
        else
        {
            this.x += _velocityX;
            this.y+=_velocityY;
        }

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, 15, 15, null);
    }
}
