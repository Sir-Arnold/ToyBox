import java.util.Vector;

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
      myPosition = new Vector();
      myPosition.add(x);
      myPosition.add(y);
      
      radius = rad;
   }
  
   public void tick()
   {
      
   }
  
   public void render()
   {
   
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