import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;

public abstract class PhysicsThing
{

   public Vector position;
   public Vector velLinear;
   public float angle;
   public float velAngular;
   public ArrayList<Vector> forces;
   float torque;
   float mass;
   
   public PhysicsThing()
   {
      position = new Vector();
      
      position.setSize(2);
      position.add(0,0); position.add(1,0);
      
      velLinear = new Vector();
      velLinear.setSize(2);
      velLinear.add(0,0.0); position.add(1,0.0);
      
      angle = 0f;
      velAngular = 0f;
      
      mass = 1f;
      
      forces = new ArrayList<Vector>();
      forces.add(0, createGravity());
      
   }
   
   public PhysicsThing(int x, int y)
   {
      position = new Vector();
      
      position.setSize(2);
      position.add(x); position.add(y);
      
      velLinear = new Vector();
      velLinear.setSize(2);
      velLinear.add(0,0.0); position.add(1,0.0);
      
      angle = 0f;
      velAngular = 0f;
      
      mass = 1f; // should pass this as a parameter here
      
      forces = new ArrayList<Vector>();
      forces.add(0, createGravity());
   }
  
   public abstract void tick(double delta);
  
   public abstract void render(Graphics2D g2);
   
   public abstract boolean checkArea(Vector location);
  
   // given that a collision has occurred,
   // using the location of the collision, the direction at which the pusher was moving, and the magnitude of the push
   // both calculates the new velocity of the pushed and returns the magnitude of the reversed pushback
   public abstract double push(float xForce, float yForce);
     
   public abstract void pushed(float xForce, float yForce);
   
   public void decidePushes(double delta)
   {
      float xForce = 0;
      float yForce = 0;
      
      for(int i = 0; i < forces.size(); i++)
      {
         Vector force = forces.get(i);
         
         xForce += (float) force.get(0);
         yForce += (float) force.get(1);
      }
      
      double forceAngle = Math.atan(xForce / yForce);
      
      double xAccel = xForce / mass;
      double yAccel = yForce / mass;
      
      Vector aVelLinear = new Vector();
      
      aVelLinear.add(0, this.getVelLinear().get(0));
      aVelLinear.add(1, this.getVelLinear().get(1));
      
      double newVelX = ((double) aVelLinear.get(0)) + xAccel * delta;
      double newVelY = ((double) aVelLinear.get(0)) + yAccel * delta;
      
      Vector newVelLinear = new Vector();
      newVelLinear.add(0, newVelX);
      newVelLinear.add(1, newVelY);
      
      this.setVelLinear(newVelLinear);
      
      
   }
   
   public Vector createGravity()
   {
      Vector gravity = new Vector();
      gravity.add(0f);
      gravity.add((float) ((float)(Options.getGravity()) * mass));
      
      return gravity;
   }
   
   public abstract Vector getVelLinear();
   
   public abstract void setVelLinear(Vector newVel);
}
