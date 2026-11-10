import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlappyBron extends JPanel implements ActionListener, KeyListener {
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
    int velocityY = -20;
    int gravity = 1;

    Timer gameLoop;

    public FlappyBron() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("./FlappyBronBG.png")).getImage();
        bronImage = new ImageIcon(getClass().getResource("./lebron.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();

        bron = new Bron(bronImage);

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bron.image, bron.x, bron.y, bron.width, bron.height, null);
    }

    public void move() {
        velocityY += gravity;
        bron.y += velocityY;
        bron.y = Math.max(bron.y, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -13;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
