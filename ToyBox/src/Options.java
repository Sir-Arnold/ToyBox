public class Options
{
   public static int WIDTH;
   public static int SETTING_WIDTH;
   public static int HEIGHT;
   public static int SETTING_HEIGHT;
   public static double INIT_VELX;
   public static double INIT_VELY;
   private static double gravity;
   
   public Options()
   {
      WIDTH = 800;
      HEIGHT = 800;
      
      SETTING_WIDTH = WIDTH - 50;
      SETTING_HEIGHT = HEIGHT - 150;
      
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
