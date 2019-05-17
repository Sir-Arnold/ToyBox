import java.util.Vector;
import java.awt.*;

public class Square extends PhysicsThing implements MyShapes
{
   public Vector myPosition;
   public Vector myVelLinear;
   public float angle;
   public float velAngular;
   public Vector force;
   float torque;
   
   int sideLength;
   
   double delta;
   
   public Square()
   {
      super();
   }
   
   public Square(int x, int y, int side)
   {
      super(x, y);
      
      myPosition = new Vector();
      myPosition.add(x);
      myPosition.add(y);
      
      sideLength = side;
   }
   
   public void tick(double delta)
   {
      this.delta = delta;
   }
  
   public void render(Graphics2D g2)
   {
      g2.fill(new Rectangle((int) myPosition.get(0), (int) myPosition.get(1), sideLength, sideLength));
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
   
   public Vector getVelLinear()
   {
      return myVelLinear;
   }
   
   public void setVelLinear(Vector newVel)
   {
      myVelLinear = newVel;
   }
}
