import java.awt.event.KeyEvent; 
import java.util.LinkedList; 
  
public class KeyInput extends java.awt.event.KeyAdapter 
{
   Window window;
   
   public KeyInput(Window window) 
   { 
     System.out.println("Key Input Constructing..."); 
     
     this.window = window; 
     
     System.out.println("Key Input Constructed."); 
   } 
   
   public void keyPressed(KeyEvent e) 
   { 
      int key = e.getKeyCode(); 
   }
}