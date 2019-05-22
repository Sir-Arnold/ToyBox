import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D;

public class Circle extends PhysicsThing implements MyShapes
{
   double delta; 
   
   public Shape shape;
   
   public int radius;
   public Ellipse2D ellipse;
  
   public Circle()
   {
      super(); 
      
      radius = 10;
      type = "circle";
   }
   
   public Circle(int x, int y, int rad)
   {
      super(x,y);
      
      type = "circle";
      
      myPosition = new int[2];
      myPosition[0] = x;
      myPosition[1] = y;
      
      radius = rad;
      ellipse = new Ellipse2D.Double((double) x, (double) y, radius * 2.0, radius * 2.0);
      
      updateShape();
   }
  
   public void tick(double delta)
   {
      this.delta = delta;
      decidePushes(delta);
      move(delta);
      updateShape();
   }
  
   public void render(Graphics2D g2)
   {
      g2.drawOval((int) position[0], (int) position[1], radius * 2, radius * 2);
      g2.fillOval((int) position[0], (int) position[1], radius * 2, radius * 2);  // @param: (x, y, width, height)
   }
   
   public void move(double delta)
   {
	   int newX = (int) (position[0] +  velLinear[0]);
	   int newY = (int) (position[1] +  velLinear[1]);
	      
	   position[0] = newX;
	   position[1] = newY;
   }
   
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
   
   public void setValues(int[] position, double[] velLinear, double angle, float velAngular, double torque)
   {
	   myPosition = position;
	   myVelLinear = velLinear;
	   myAngle = angle;
	   myVelAngular = velAngular;
	   myTorque = torque;
	   
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
