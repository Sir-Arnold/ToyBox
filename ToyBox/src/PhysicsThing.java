import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;

public abstract class PhysicsThing
{

   public Vector position;
   public Vector velLinear;
   public float angle;
   public float velAngular;
   public Vector forces;
   float torque;
   float mass;
   
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
      
      mass = 1f;
      
      forces = new Vector();
      forces.add(0, createGravity());
      
   }
   
   public PhysicsThing(int x, int y)
   {
      position = new Vector();
      
      position.setSize(2);
      position.add(x); position.add(y);
      
      velLinear = new Vector();
      velLinear.setSize(2);
      velLinear.add(0,0); position.add(1,0);
      
      angle = 0f;
      velAngular = 0f;
      
      mass = 1f; // should pass this as a parameter here
      
      forces = new Vector();
      forces.add(0, createGravity());
   }
  
   public abstract void tick();
  
   public abstract void render(Graphics2D g2);
   
   public abstract boolean checkArea(Vector location);
  
   // given that a collision has occurred,
   // using the location of the collision, the direction at which the pusher was moving, and the magnitude of the push
   // both calculates the new velocity of the pushed and returns the magnitude of the reversed pushback
   public abstract double push(PhysicsThing otherThing, Vector location, Vector direction, double magnitude);
     
   public abstract void pushed(Vector location, Vector direction, double magnitude);
   
   public void decidePushes()
   {
      
   }
   
   public Vector createGravity()
   {
      Vector gravity = new Vector();
      gravity.add((float)((Options.getGravity()) * mass));
      gravity.add((float)((4/3) * Math.PI));
      
      return gravity;
   }
}
