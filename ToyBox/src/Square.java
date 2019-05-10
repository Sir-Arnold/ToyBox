import java.util.Vector;
import java.awt.*;

public class Square extends PhysicsThing implements MyShapes
{
   public Vector position;
   public Vector velLinear;
   public float angle;
   public float velAngular;
   public Vector force;
   float torque;
   
   public Square()
   {
      super();
   }
   
   public void tick()
   {
   
   }
  
   public void render(Graphics2D g2)
   {
   
   }
   
   public boolean checkArea(Vector location)
   {
      return false;
   }
   
   public double push(PhysicsThing otherThing, Vector location, Vector direction, double magnitude)
   {
      return 0;
   }
  
   public void pushed(Vector location, Vector direction, double magnitude)
   {
   
   }
}
