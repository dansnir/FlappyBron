import java.awt.*;
import javax.swing.*;

public class FlappyBron extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImage;
    Image bronImage;
    Image topPipeImage;
    Image bottomPipeImage;

    int bronX = boardWidth / 8;
    int bronY = boardHeight / 2;
    int bronWidth = 68;
    int bronHeight = 48;

    public class Bron {
        int x = bronX;
        int y = bronY;
        int width = bronWidth;
        int height = bronHeight;
        Image image;

        public Bron(Image image) {
            this.image = image;
        }
    }

    Bron bron;

    public FlappyBron() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        backgroundImage = new ImageIcon(getClass().getResource("./FlappyBronBG.png")).getImage();
        bronImage = new ImageIcon(getClass().getResource("./lebron.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();

        bron = new Bron(bronImage);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bron.image, bron.x, bron.y, bron.width, bron.height, null);
    }
    
}
