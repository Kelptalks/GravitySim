import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class SimRender extends BufferedImage{
    
    private int xRez;
    private int yRez;
    SimRender(int xRez, int yRez){
        super(xRez, yRez, TYPE_4BYTE_ABGR_PRE);
        this.xRez = xRez;
        this.yRez = yRez;
    }

    public void drawFrame(Particle[] particleArray){
        Graphics2D g2D = super.createGraphics();
        //clear the screen
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, xRez ,yRez);

        //draw the particles
        g2D.setColor(Color.green);
        for(int t = 0; t<particleArray.length; t++){
            drawParticle(particleArray[t], g2D);
        }
    }

    private void drawParticle(Particle particle, Graphics2D g2D){
        g2D.drawOval((int)Math.round(particle.xCor/10), (int)Math.round(particle.yCor/10), particle.mass, particle.mass);
    }


}
