import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("FlappyBron");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlappyBron flappyBron = new FlappyBron();
        frame.add(flappyBron);
        frame.pack();
        flappyBron.requestFocus();
        frame.setVisible(true);

    }
}
