import javax.swing.Renderer;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        myFrame myFrame = new myFrame(1920, 1080);
        Random random = new Random();
        
        Particle[] particleArray = new Particle[500];

        //generate particles
        for(int x = 0; x<particleArray.length; x++){
            particleArray[x] = new Particle(6,50, random.nextInt(1920*5+5*1920), random.nextInt(1080*5+5*1080));
        }

        while(true){
            Thread.sleep(20);
            myFrame.renderFrame(particleArray);
            double gravity;
            double angle;

            for(int a = 0; a<particleArray.length; a++){
                for(int b = 0; b<particleArray.length; b++){
                    if (a != b){
                        //System.out.println(particleArray[a].getGravity(particleArray[b]));
                        angle = particleArray[b].getRelitiveAngle(particleArray[a]);
                        particleArray[a].addVector(0.5, angle);
                    }
                    
                }
            }

            for(int a = 0; a<particleArray.length; a++){
                particleArray[a].move();
            }

            
        }

    }
}

//First step
//based of the angle of its I need to calculate the x and y lengths of a triangle. The hyoitanuse is the magnitude of the vecotr
