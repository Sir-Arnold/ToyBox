import java.util.ArrayList;
import java.util.Vector;

public abstract class PhysicsThing
{

  public Vector position;
  public Vector velLinear;
  public float angle;
  public float velAngular;
  public Vector force;
  float torque;
  MyShapes shape;
  
  
  
  public void tick();
  
  public void render();
  
  
  // given that a collision has occurred,
  // using the location of the collision, the direction at which the pusher was moving, and the magnitude of the push
  // both calculates the new velocity of the pushed and returns the magnitude of the reversed pushback
  public double push(Vector location, Vector direction, double magnitude);
  
  public void pushed(Vector location, Vector direction, double magnitude);
}
