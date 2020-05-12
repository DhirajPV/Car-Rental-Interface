package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageComponent extends JComponent {
    private final BufferedImage img;

    public ImageComponent(URL url) throws IOException {
        img = ImageIO.read(url);
        setPreferredSize(new Dimension(400, 300));

    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 400, 300, this);

    }

    public void main(ImageComponent img) {
        JFrame frame = new JFrame("Test");
        frame.add(new JScrollPane(img));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(425, 350);
        frame.setVisible(true);
    }
}
