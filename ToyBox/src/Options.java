public class Options
{
   public static int WIDTH;
   
   public static int HEIGHT;
   
   private static double gravity;
   
   private static double repulsionMagnitude;
   
   private static double playbackSpeed; 
   
   public Options()
   {
      WIDTH = 1000;
      HEIGHT = 1000;
      
      gravity = -9.8;
      
      repulsionMagnitude = 1;
      
      playbackSpeed = 1;
      
   }
   
   public static int getWIDTH()
   {
      return WIDTH;
   }
   
   
}