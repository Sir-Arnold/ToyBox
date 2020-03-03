import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;
import java.awt.geom.Area;

public abstract class PhysicsThing
{

   public int[] position;
   public double[] velLinear;
   public double angle;
   public float velAngular;
   public ArrayList<float[]> forces;                    // list of forces acting on this object, which effectively change the velocity of the object
   public ArrayList<int[]> locationsPushed;             // parallel to forces
   double torque;                                       // not sure if I need this or how I would use it
   double mass;
   double restitution;
   String type;
   int[] min;
   int[] max;
   
   public PhysicsThing()
   {
      position = new int[2];
      
      position[0] = 0; position[1] = 0;
      
      velLinear = new double[2];
      
      velLinear[0] = 0; velLinear[1] = 0;
      
      angle = 0f;
      velAngular = 0f;
      
      mass = 1;
      restitution = 0.8;
      
      min = new int[2];
      max = new int[2];
      
      forces = new ArrayList<float[]>();
      
      locationsPushed = new ArrayList<int[]>();
      int[] cm= new int[2];
      
   }
   
   public PhysicsThing(int x, int y)
   {
      position = new int[2];
      
      position[0] = x; position[1] = y;
      
      velLinear = new double[2];
      
      velLinear[0] = 0; velLinear[1] = 0;
      
      angle = 0f;
      velAngular = 0f;
      
      mass = 1; // should pass this as a parameter here
      restitution = 0.8;
      
      min = new int[2];
      max = new int[2];
      
      forces = new ArrayList<float[]>();
      
      locationsPushed = new ArrayList<int[]>();
      int[] cm= new int[2];
   }
  
   public abstract void tick(double delta);
  
   public abstract void render(Graphics2D g2);
   
   public static void runSimpleCollideWalls(PhysicsThing thing)
   {
	   int wallOperand = thing.hittingWall();
	   if(wallOperand == 0)
	   {
		   thing.position[1] = 0;
		   thing.velLinear[1] -= (thing.velLinear[1] * 2) * thing.restitution;
	   }
	   else if(wallOperand == 1)
	   {
		   thing.position[0] = 0;
		   thing.velLinear[0] -= (thing.velLinear[0] * 2) * thing.restitution;
	   }
	   else if(wallOperand == 2)
	   {
		   thing.position[1] = Options.SETTING_HEIGHT - thing.getRadius() - 1;
		   thing.velLinear[1] -= (thing.velLinear[1] * 2) * thing.restitution;
	   }
	   else if(wallOperand == 3)
	   {
		   thing.position[0] = Options.SETTING_WIDTH - thing.getRadius() - 1;
		   thing.velLinear[0] -= (thing.velLinear[0] * 2) * thing.restitution;
	   }
	   else if(wallOperand == -1)
	   {
		   
	   }
   }
   
public abstract int hittingWall();
   
   public abstract int findX(int y);
   
   public abstract int findY(int x);
   
   public abstract float[] getForce();
   
   public abstract Shape getShape();
   
   public abstract int getRadius();
   
   public void setVelLinear(double[] newVel)
   {
      velLinear = newVel;
   }
   
   public void setVelAngular(float rotSec)
   {
	   velAngular = rotSec * ((float) (2.0 * Math.PI));
   }
   
   public void increaseVelX()
   {
	   velLinear[0] += 1.0; 
   }
   
   public void decreaseVelX()
   {
	   velLinear[0] -= 1.0; 
   }
   
   public void increaseVelY()
   {
	   velLinear[1] -= 5.0;
   }
   
   public void decreaseVelY()
   {
	   velLinear[1] += 5.0;
   }
   
   public abstract void setValues(int[] position, double[] velLinear, double angle, float velAngular, double torque);
   
   public void printVelLinear()
   {
	   System.out.println("Linear Velocity: x- " + velLinear[0] + " | y- " + velLinear[1]);
   }
   
   public static Area getIntersection(PhysicsThing a, PhysicsThing b)
   {
	   Area areaA = new Area(a.getShape());
	   Area areaB = new Area(b.getShape());
	   areaA.intersect(areaB);
	   
	   return areaA;
   }
   
   public abstract boolean checkArea(int[] location);
  
   // given that a collision has occurred,
   // using the location of the collision, the direction at which the pusher was moving, and the magnitude of the push
   // both calculates the new velocity of the pushed and returns the magnitude of the reversed pushback
   public void push(PhysicsThing target, int x, int y)
   {
	   target.addForce(x, y, velLinear[0] * mass, velLinear[1] * mass);
   }
   
   public void pushedByWall(int wallOperand)
   {
	   int[] targetVertice = new int[2];
	   if(wallOperand == 0)
	   {
		   targetVertice[0] = findXWithMinY();
		   targetVertice[1] = min[1];
		   if(angle % (Math.PI/4) == 0)
		   {
			   addForce(position[0], position[1], 0, velLinear[1] * mass * 1);
		   }
		   else
		   {
			   addForce(targetVertice[0], targetVertice[1], 0, velLinear[1] * mass * 1); 
		   }
	   }
	   else if(wallOperand == 1)
	   {
		   targetVertice[0] = min[0];
		   targetVertice[1] = findYWithMinX();
		   
		   if(angle % (Math.PI/4) == 0)
		   {
			   addForce(position[0], position[1], velLinear[0] * mass * 1, 0);
		   }
		   else
		   {
			   addForce(targetVertice[0], targetVertice[1], velLinear[0] * mass * 1, 0); 
		   }
	   }
	   else if(wallOperand == 2)
	   {
		   targetVertice[0] = findXWithMaxY();
		   targetVertice[1] = max[1];
		   
		   if(angle % (Math.PI/4) == 0)
		   {
			   addForce(position[0], position[1], 0, velLinear[1] * mass * -1);
		   }
		   else
		   {
			   addForce(targetVertice[0], targetVertice[1], 0, velLinear[1] * mass * -1);
		   }
	   }
	   else if(wallOperand == 3)
	   {
		   targetVertice[0] = max[0];
		   targetVertice[1] = findYWithMaxX();
		   
		   if(angle % (Math.PI/4) == 0)
		   {
			   addForce(position[0], position[1], velLinear[0] * mass * -1, 0);
		   }
		   else
		   {
			   addForce(targetVertice[0], targetVertice[1], velLinear[0] * mass * -1, 0); 
		   }
	   }
   }
   
   protected abstract int findYWithMaxX();

   protected abstract int findXWithMaxY();

   protected abstract int findYWithMinX();

   protected abstract int findXWithMinY();

   public void decidePushes(double delta)
   {   
	  float xForce = 0;
      float yForce = 0;
      
      for(int i = 0; i < forces.size(); i++)
      {
         float[] force = forces.get(i);
         if(i == 0)
         {
        	 xForce += force[0];
             yForce += force[1];
         }
         
         
      }
      
      double forceAngle = Math.atan(xForce / yForce);
      
      double xAccel = xForce / mass;
      double yAccel = (yForce / mass) + Options.getGravity();
      
      double[] aVelLinear = new double[2];
      aVelLinear[0] = velLinear[0];
      aVelLinear[1] = velLinear[1];
      
      double newVelX = (aVelLinear[0]) + xAccel * delta;
      double newVelY = (aVelLinear[1]) + yAccel * delta;
      
      //System.out.println(newVelY);
      
      double[] newVelLinear = new double[2];
      newVelLinear[0] = newVelX;
      newVelLinear[1] = newVelY;
      
      velLinear[0] = newVelLinear[0];
      velLinear[1] = newVelLinear[1];
      //printVelLinear();
      
      forces.clear();
      
      locationsPushed.clear();
      
      
   }
   
   public static boolean testIntersection(PhysicsThing a, PhysicsThing b)
   {
	   Area areaA = new Area(a.getShape());
	   Area areaB = new Area(b.getShape());
	   areaA.intersect(areaB);
	   
	   if(areaA.isEmpty())
		   return false;
	   else
		   return true;
   }
   
   public static void runCollideWalls(PhysicsThing thing)
   {
	   int wallOperand = thing.hittingWall();
	   
	   if(wallOperand == 0)
	   {
		   thing.pushedByWall(0);
	   }
	   else if(wallOperand == 1)
	   {
		   thing.pushedByWall(1);
	   }
	   else if(wallOperand == 2)
	   {
		   thing.pushedByWall(2);
	   }
	   else if(wallOperand == 3)
	   {
		   thing.pushedByWall(3);
	   }
	   else if(wallOperand == -1)
	   {
		   
	   }
   }
   
   
   
   public float[] createGravity()
   {
      float[] gravity = new float[2];
      gravity[0] = 0;
      gravity[1] = (float) (Options.getGravity() * mass);
      
      return gravity;
   }
   
   public double[] getVelLinear()
   {
	   return velLinear;
   }
   
   public int[] getPosition()
   {
	   return position;
   }
   
   public void addForce(int x, int y, double magnitudeX, double magnitudeY)
   {
	   float[] newForce = new float[2];
	   newForce[0] = (float) magnitudeX; newForce[1] = (float) magnitudeY;
	   forces.add(newForce);
	   
	   int[] newForceLocation = new int[2];
	   newForceLocation[0] = x; newForceLocation[1] = y;
	   locationsPushed.add(newForceLocation);
	   
   }
   
   
   
}