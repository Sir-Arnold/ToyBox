import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D;

public class Circle extends PhysicsThing implements MyShapes
{
   double delta;                            // the time since the circle was last processed
   
   Shape shape;                   // the geometric shape associated with the Circle object
   
   protected int radius;                    // radius value is specific to circle, used in collisions and rendering
   protected Ellipse2D ellipse;             // ellipse 
  
   public Circle()
   {
      super(); 
      
      radius = 10;
      type = "circle";
      ellipse = new Ellipse2D.Double((double) x, (double) y, radius * 2.0, radius * 2.0);
      updateShape();
   }
   
   // @param x location, y location, radius
   public Circle(int x, int y, int rad)
   {
      super(x,y);
      
      type = "circle";
      
      radius = rad;
      ellipse = new Ellipse2D.Double((double) x, (double) y, radius * 2.0, radius * 2.0);
      updateShape();
   }
   
   // given the time since the program last updated, update the circle
   public void tick(double delta)
   {
      this.delta = delta;
      decidePushes(delta);
      move(delta);
      updateShape();
   }
  
   // given what graphics objects to draw the circle with, draw the circle
   public void render(Graphics2D g2)
   {
      g2.drawOval((int) position[0], (int) position[1], radius * 2, radius * 2);  // @param: (x, y, width, height)
      g2.fillOval((int) position[0], (int) position[1], radius * 2, radius * 2);  // arbitrarily fills over the outline
   }
   
   // given that the linear velocity has already been calculated, move the circle to its new x and y
   public void move(double delta)
   {
	   int newX = (int) (position[0] +  velLinear[0]);
	   int newY = (int) (position[1] +  velLinear[1]);
	      
	   position[0] = newX;
	   position[1] = newY;
   }
   
   // not used in the program but determines if a given point location falls within the radius of the circle
   public boolean checkArea(int[] location)
   {
      int h = (int) location[0];
      int k = (int) location[1];
      
      int x = (int) myPosition[0];
      int y = (int) myPosition[1];
      
      boolean inequality = (Math.pow((x - h), 2) + Math.pow((y - k), 2) <= radius);
      return inequality;
   }
   
   public int hittingWall()
   {
	   if(position[1] + radius > Options.SETTING_HEIGHT)
	   {
		   return 2;
	   }
	   else if(position[1] < 0)
	   {
		   return 0;
	   }
	   else if(position[0] < 0)
	   {
		   return 1;
	   }
	   else if(position[0] + radius > Options.SETTING_WIDTH)
	   {
		   return 3;
	   }
	   else
	   {
		   return -1;
	   }
		   
   }
   
   public double[] getVelLinear()
   {
      return myVelLinear;
   }
   
   public void updateShape()
   {
	   ellipse.setFrame((double) position[0], (double) position[1], radius * 2.0, radius * 2.0);
	   min[0] = position[0] - radius;
	   min[1] = position[1] - radius;
	   max[0] = position[0] + radius;
	   max[1] = position[1] + radius;
	   
	   shape = ellipse;
   }
   
   public Shape getShape()
   {
	   return ellipse;
   }
   
   public float[] getForce()
   {
	   return myForce;
   }

   public int getRadius() 
   {
	   return radius;
   }
}
