public class Options
{
   public static int WIDTH;
   
   public static int HEIGHT;
   
   private static double gravity;
   
   private static double repulsionMagnitude;
   
   private static double playbackSpeed; 
   
   public Options()
   {
      WIDTH = 800;
      HEIGHT = 800;
      
      
      gravity = -9.8;
      
      repulsionMagnitude = 1;
      
      playbackSpeed = 1;
      
   }
   
   public static int getWIDTH()
   {
      return WIDTH;
   }
   
   public static double getGravity()
   {
      return gravity;
   }
   
}