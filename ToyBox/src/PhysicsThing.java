import java.util.ArrayList;
import java.util.Vector;

public abstract class PhysicsThing
{

   public Vector position;
   public Vector velLinear;
   public float angle;
   public float velAngular;
   public Vector force;
   float torque;
   
   public PhysicsThing()
   {
      position = new Vector();
      
      position.setSize(2);
      position.add(0,0); position.add(1,0);
      
      velLinear = new Vector();
      velLinear.setSize(2);
      velLinear.add(0,0); position.add(1,0);
      
      angle = 0f;
      velAngular = 0f;
      
      force = new Vector();
      force.setSize(2);
      force.add(0,0); force.add(1,0);
      
   }
  
   public abstract void tick();
  
   public abstract void render();
   
   public abstract boolean checkArea(Vector location);
  
   // given that a collision has occurred,
   // using the location of the collision, the direction at which the pusher was moving, and the magnitude of the push
   // both calculates the new velocity of the pushed and returns the magnitude of the reversed pushback
   public abstract double push(PhysicsThing otherThing, Vector location, Vector direction, double magnitude);
     
   public abstract void pushed(Vector location, Vector direction, double magnitude);
}
