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
         
         /*things.add(new Circle(10, 10, 10));
         Vector aPosition = new Vector();
         aPosition.add(21);
         aPosition.add(21);
         boolean test = things.get(0).checkArea(aPosition);
         System.out.println(test);*/
      }
      
      for(PhysicsThing thing: things)
      {
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
   
   
}