import java.util.Vector;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Setting
{
   public boolean pauseState;
   private double delta;
   public ArrayList<PhysicsThing> things;
   
   Runner runner;
   
   double increaseVelX;
   double decreaseVelX;
   double increaseVelY;
   double decreaseVelY;
   
   
   public Setting(Runner runner)
   {
      things = new ArrayList<PhysicsThing>();
      runner = runner;
      
      increaseVelX = 0.0;
      decreaseVelX = 0.0;
      increaseVelY = 0.0;
      decreaseVelY = 0.0;
   }
   
   public void tick(double delta)
   {
      this.delta = delta;
      
      if(things.size() == 0)
      {
         addObject(new Circle(100, 200, 50));
         //addObject(new Square(100, 500, 50));
         
         
         things.get(0).setVelLinear(new double[] {Runner.initVelX, Runner.initVelY});
         
      }
      
      for(int i = 0; i < things.size(); i++)
      {
    	  runCollisions(i);
    	  
    	  // interpreting what to do about user input
    	  if(increaseVelX == 1.0)
    	  {
    		  things.get(i).increaseVelX();
    		  increaseVelX = 0;
    	  }
    	  else if(decreaseVelX == 1.0)
    	  {
    		  things.get(i).decreaseVelX();
    		  decreaseVelX = 0;
    	  }
    	  else if(increaseVelY == 1.0)
    	  {
    		  things.get(i).increaseVelY();
    		  increaseVelY = 0;
    	  }
    	  else if(decreaseVelY == 1.0)
    	  {
    		  things.get(i).decreaseVelY();
    		  decreaseVelY = 0;
    	  }
    	  
    	  things.get(i).tick(delta);
      }
         
      
   }
   
   public void render(Graphics2D g2)
   {
      for(PhysicsThing thing: things)
      {
         thing.render(g2);
      }
   }
   
   public void addObject(PhysicsThing thing)
   {
      things.add(thing);
   }
   
   public void clear()
   {
      
   }
   
   public void runCollisions(int i)
   {
	   if(things.size() > 0)
	   {
		   PhysicsThing.runSimpleCollideWalls(things.get(i));                                         // this is the only part of the method that actually does anything
		   
		   for(int j = 0; j < things.size(); j ++)
		   {
			   if(i != j)
			   {
				   if(PhysicsThing.testIntersection(things.get(i), things.get(j)))                      // returns true as it should                    
				   {
					   Area intersection = PhysicsThing.getIntersection(things.get(i), things.get(j));
					   Rectangle rect = intersection.getBounds();
					   
					   things.get(i).push(things.get(j), (int) rect.getX(), (int) rect.getY());
					   
				   }
			   }   
		   }
	   }
   }
   
   public static void reset(Setting setting)
   {
	   setting.reset();
   }
   
   public void reset()
   {
	   things.clear();
	   addObject(new Circle(100, 200, 50));
   }
   
   public void increaseVelX()
   {
	   increaseVelX = 1.0;
   }
   
   public void decreaseVelX()
   {
	   decreaseVelX = 1.0;
   }
   
   public void increaseVelY()
   {
	   increaseVelY = 1.0;
   }
   
   public void decreaseVelY()
   {
	   decreaseVelY = 1.0;
   }
}
