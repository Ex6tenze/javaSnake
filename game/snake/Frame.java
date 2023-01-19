package snake;

import javax.swing.JFrame;

public class Frame extends JFrame {
    Frame() {
        Panel panel = new Panel();
        this.add(panel);
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}