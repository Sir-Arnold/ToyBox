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
   
   double delta;
   
   public Square()
   {
      super();
   }
   
   public void tick(double delta)
   {
      this.delta = delta;
   }
  
   public void render(Graphics2D g2)
   {
   
   }
   
   public boolean checkArea(Vector location)
   {
      return false;
   }
   
   public double push(float xForce, float yForce)
   {
      return 0;
   }
  
   public void pushed(float xForce, float yForce)
   {
   
   }
}
