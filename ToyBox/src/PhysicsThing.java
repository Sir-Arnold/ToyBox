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

  public PhysicsThing();
  
  public void tick();
  
  public void render();
}
