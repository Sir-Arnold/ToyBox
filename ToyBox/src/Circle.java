import java.util.Vector;
import java.awt.*;

public class Circle extends PhysicsThing implements MyShapes
{
   public Vector myPosition;
   public int radius;
   public Vector myVelLinear;
   public float myAngle;
   public float myVelAngular;
   public Vector myForce;
   float myTorque;
  
   public Circle()
   {
      super(); 
   }
   
   public Circle(int x, int y, int rad)
   {
      super(x,y);
      
      myPosition = new Vector();
      myPosition.add(x);
      myPosition.add(y);
      
      radius = rad;
   }
  
   public void tick()
   {
      decidePushes();
   }
  
   public void render(Graphics2D g2)
   {
      g2.drawOval((int) myPosition.get(0), (int) myPosition.get(1), radius * 2, radius * 2);
      g2.fillOval((int) myPosition.get(0), (int) myPosition.get(1), radius * 2, radius * 2);
   }
   
   public boolean checkArea(Vector location)
   {
      int h = (int) location.get(0);
      int k = (int) location.get(1);
      
      int x = (int) myPosition.get(0);
      int y = (int) myPosition.get(1);
      
      boolean inequality = (Math.pow((x - h), 2) + Math.pow((y - k), 2) <= radius);
      return inequality;
   }
   
   public double push(PhysicsThing otherThing, Vector location, Vector direction, double magnitude)
   {
      return 0;
   }
  
   public void pushed(Vector location, Vector direction, double magnitude)
   {
      
   }
   
}