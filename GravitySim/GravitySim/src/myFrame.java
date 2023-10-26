import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class myFrame extends JFrame {
    private BufferedImage image;
    private SimRender simRender;

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }

    private ImagePanel imagePanel;

    myFrame(int xRez, int yRez) {
        super();
        setSize(xRez, yRez);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simRender = new SimRender(xRez, yRez);
        imagePanel = new ImagePanel();
        add(imagePanel);
        setVisible(true);
    }

    public void renderFrame(Particle[] particleArray) {
        simRender.drawFrame(particleArray);
        setImage(simRender);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        SwingUtilities.invokeLater(() -> imagePanel.repaint());
    }
}