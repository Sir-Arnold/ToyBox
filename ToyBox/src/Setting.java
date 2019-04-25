import java.util.Vector;
import java.util.ArrayList;

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
   }
   
   public void render()
   {
   
   }
   
   public void addObject(PhysicsThing thing)
   {
      things.add(thing);
   }
   
   public void clear()
   {
   
   }
   
   
}