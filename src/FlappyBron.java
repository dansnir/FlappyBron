import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

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

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    public class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image image;
        boolean passed = false;

        public Pipe(Image image) {
            this.image = image;
        }
    }

    Bron bron;
    int velocityX = -4;
    int velocityY = -20;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;

    public FlappyBron() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("./FlappyBronBG.png")).getImage();
        bronImage = new ImageIcon(getClass().getResource("./lebron.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./walls.png")).getImage();

        bron = new Bron(bronImage);
        pipes = new ArrayList<Pipe>();

        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipeTimer.start();


        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void placePipes() {
        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random() * pipeHeight/2);
        int openingSpace = boardHeight / 4;
        Pipe topPipe = new Pipe(topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImage);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bron.image, bron.x, bron.y, bron.width, bron.height, null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void move() {
        velocityY += gravity;
        bron.y += velocityY;
        bron.y = Math.max(bron.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }
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
