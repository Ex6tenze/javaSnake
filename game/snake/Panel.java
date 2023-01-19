package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    Panel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(217, 109, 109));
        this.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
