public class Options
{
   public static int WIDTH;                    // Width of whole window
   public static int SETTING_WIDTH;            // Width of setting on its own
   public static int HEIGHT;                   // Height of whole window
   public static int SETTING_HEIGHT;           // Height of setting on its own
   public static double INIT_VELX;             // Initial X component of the velocity of the ball
   public static double INIT_VELY;             // Initial Y component of the veolcity of the ball
   private static double gravity;
   
   public Options()
   {
      WIDTH = 800;
      HEIGHT = 800;
      
      SETTING_WIDTH = WIDTH - 50;
      SETTING_HEIGHT = HEIGHT - 160;
      
      INIT_VELX = Runner.initVelX;
      INIT_VELY = Runner.initVelY;
      
      gravity = 9.8;
   }
   
   public static int getWIDTH()
   {
      return WIDTH;
   }
   
   public static double getGravity()
   {
      return gravity;
   }
   
   public static double getINIT_VELX()
   {
      return INIT_VELX;
   }
   
   public static double getINIT_VELY()
   {
	   return INIT_VELY;
   }
}