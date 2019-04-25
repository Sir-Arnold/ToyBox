import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Runner extends Canvas implements Runnable
{
   public Graphics g;
   
   Options options;
   
   Setting setting;
   Window window;
   
   public static long timer = 0;
   protected boolean running = false;
   
   private Thread thread;
   
   public static void main(String[] args)
   {
      Runner runner = new Runner();
   }
   
   public synchronized void start()
   {
      thread = new Thread(this);
      thread.start();
      running = true;
   }
   
   public synchronized void stop()
   {
      try{
         thread.join();
         running = false;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public Runner()
   {
      setting = new Setting();
      options = new Options();
      window = new Window(options.WIDTH, options.HEIGHT, "Toy Box", this);
      
      
      this.addKeyListener(new KeyInput(window));
      this.addMouseListener(new MyAdapter(window));
      
      
      //System.out.println(Options.getWIDTH());
   }

   public void run()
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
   
   public void tick(double delta)
   {
      setting.tick();
   }
   
   public void render()
   {
      BufferStrategy bs = this.getBufferStrategy();
      if(bs == null)
      {
         this.createBufferStrategy(3);
         return;
      }
      
      g = bs.getDrawGraphics();
      
      g.setColor(Color.black);
      g.fillRect(0,0, options.WIDTH, options.HEIGHT);
      
      setting.render();
      
      g.dispose();
      bs.show();
   }
}