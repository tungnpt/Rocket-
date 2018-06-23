import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {
    private Random random = new Random();
    int countStar = 0;

    BufferedImage backBuffered;
    Graphics graphics;

    List<Star> stars;
    Enemy enemy = new Enemy();
    Player player = new Player();

    public GameCanvas() {
        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.stars = new ArrayList<>();
        this.enemy = new Enemy(
                random.nextInt(1025),
                random.nextInt(601),
                this.loadImage("resources/images/circle.png"),
                random.nextInt(10),
                random.nextInt(10)
        );
        this.player = new Player(
                random.nextInt(1025),
                random.nextInt(601),
                this.loadImage("resources/images/circle.png"),
                0,
                0
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);

        this.stars.forEach(star -> star.render(graphics));
        this.enemy.render(graphics);
        this.player.render(graphics);

        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.enemy.run(player);
        //this.player.run();
    }

    private void createStar() {
        if (this.countStar == 10) {
            Star star = new Star(
                    1024,
                    this.random.nextInt(600),
                    this.loadImage("resources/images/star.png"),
                    -this.random.nextInt(10) + 1,
                    0
            );

            this.stars.add(star);
            this.countStar = 0;
        } else {
            this.countStar += 1;
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}
