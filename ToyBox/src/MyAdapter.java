import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyAdapter extends MouseAdapter 
{
    Window window;
    
    public MyAdapter(Window window)
    {
      this.window = window;
    }
    
    public void mouseClicked(MouseEvent event) 
    {

      System.out.println(event.getComponent());
    }
    
}