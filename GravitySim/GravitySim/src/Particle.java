public class Particle {
    public double xCor = 250;
    public double yCor = 250;
    private double magnitude;
    private double angle;
    public int mass = 5;

    Particle(double magnitude, double angle, int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.magnitude = magnitude;
        this.angle = angle;
    }

    public void move(){

        //Get the particles vector
        double pRaidian = Math.toRadians(this.angle);
        double pVectorX = 0;
        double pVectorY = 0;

        pVectorX = this.magnitude * (Math.cos(pRaidian));
        pVectorY = this.magnitude * (Math.sin(pRaidian));

        this.xCor = xCor+pVectorX;
        this.yCor = yCor+pVectorY;
    }

    //Notes : I when I implement realistic gravity I need to messure the distance I want bodys to interact, because
    //having all particles interct with echother would cause massive preformance losses. This can be acheaved by
    //creating a force threshold where if a force is has too small an affect on an particle then limmit it, I could
    //also implement a system that automaicly determines the max amount of interactions that can take place, and limmit
    //each particles interactions based off that number only taking into account the closest particles.

    public void addVector(double forceMagnitude, double forceAngle){

        //calclate the x,y vector cords
        double[] fVector = calculateVectors(forceMagnitude/this.mass, forceAngle);
        double[] pVector = calculateVectors(magnitude, this.angle);

        //Add the vectors
        double cVectorX = fVector[0]+pVector[0];
        double cVectorY = fVector[1]+pVector[1];
        
        //get the new vectors magnitude(This needs to change based off distance using the gravity function)
        this.magnitude = Math.hypot(cVectorY, cVectorX);

        //Get the new vectors angle
        this.angle = Math.toDegrees(Math.atan2(cVectorY, cVectorX));
    }

    public double[] calculateVectors(double forceMagnitude, double forceAngle){
        //Get the forces vector
        double fRaidian = Math.toRadians(forceAngle);
        double fVectorX = 0;
        double fVectorY = 0;

        fVectorX = forceMagnitude * (Math.cos(fRaidian));
        fVectorY = forceMagnitude * (Math.sin(fRaidian));

        double vector[] = {fVectorX, fVectorY};
        return vector;
    }

    public double getGravity(Particle particle){
        //find distance between particle
        double xDiff =this.xCor-particle.xCor;
        double yDiff = this.yCor-particle.yCor;
        double distance = Math.hypot(yDiff, xDiff);
        
        //Multiply the masses of each
        double mass = this.mass*particle.mass;
        
        if (distance>particle.mass){
        //calculate gravity based off falloff
        double gravitationalMagnitude = (100*mass)/((distance*distance));
        return gravitationalMagnitude;
        }
        else{
            return 0;
        }
    }

    public double getRelitiveAngle(Particle particle){
        //create a magnitude and angle based off inputed particle.
        double xDistance = this.xCor-particle.xCor;
        double yDistance = this.yCor-particle.yCor;
        
        double angle_radians = Math.atan2(yDistance, xDistance);
        double angle_degrees = Math.toDegrees(angle_radians);

        return angle_degrees;
    }

    //add particles mass and delete old particle for colision
    public void combineMass(Particle particle){
        this.mass = particle.mass+this.mass;
        System.out.println(this.mass);
    }

}
