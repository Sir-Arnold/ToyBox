import java.awt.event.KeyEvent; 
import java.util.LinkedList; 
  
public class KeyInput extends java.awt.event.KeyAdapter 
{
    Setting setting;
	Window window;
   
    public KeyInput(Window window, Setting setting) 
    { 
    	System.out.println("Key Input Constructing..."); 
     
    	this.setting = setting;
    	this.window = window; 
     
    	System.out.println("Key Input Constructed."); 
    } 
   
    public void keyPressed(KeyEvent e) 
    { 
    	int key = e.getKeyCode(); 
        if(key == KeyEvent.VK_W) 
        { 
        	setting.increaseVelY();
        } 
        else if(key == KeyEvent.VK_D)
        {
        	setting.increaseVelX();
        }
        else if(key == KeyEvent.VK_A)
        {
        	setting.decreaseVelX();
        }
        else if(key == KeyEvent.VK_S)
        {
        	setting.decreaseVelY();
        }
      
    }
    
    
}