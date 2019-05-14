import java.util.Vector;
import java.util.ArrayList;
import java.awt.*;

public class Setting
{
   public boolean pauseState;
   
   public ArrayList<PhysicsThing> things;
   
   
   public Setting()
   {
      things = new ArrayList<PhysicsThing>();
   }
   
   public void tick()
   {
      for(PhysicsThing thing: things)
         thing.tick();
         
      if(things.size() == 0)
      {
         addObject(new Circle(100, 100, 50));
      }
      
      for(PhysicsThing thing: things)
      {
         if(findCollisions() == false)
            thing.tick();
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
   
   public boolean findCollisions()
   {
      return false;
   }
   
   
}