public class Runner
{
   public static long timer = 0;
   
   public static void main(String[] args)
   {
   
   }

   public static void run()
   {
      boolean running = true;
      int time = 0;
      long lastTime = System.nanoTime();
      double amountOfTicks = 30.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0; 
      long timer = System.currentTimeMillis();
      int frames = 0;
      int targetFPS = 30;
      
      while(running)
      {
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         
         //System.out.println(delta);
         
         while(delta >= 1)
         {
           frames += 1;
           tick(delta);
           //System.out.println(">");
           render();
           timer += 1/30;
           delta--;
         }

         if(frames >= amountOfTicks)
         {
            frames = 0;
            time += 1;
            System.out.println(time);
         }

      }
   }
   
   public static void tick(double delta)
   {
   
   }
   
   public static void render()
   {
   
   }
}