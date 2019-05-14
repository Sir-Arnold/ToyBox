import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class Window implements ActionListener, ItemListener
{
   protected JFrame frame;
   
   public static final Color LUMA_BLUE = new Color(173, 255, 255);
   public int borderSize;
   
   Runner runner; // extends Canvas, and you can add a canvas to a Content Pane
   JPanel runnerPanel;
   Setting setting;
   
   JTextArea jtAreaOutput;
   JScrollPane jspPane;
   
   public String title;
   public int width, height;
   
   public JPanel canvasHolder;
   
   public Color bottomPanelBG;
   
   public JPanel bottomBar;
   public int canvasHeight, bottomBarWidth, bottomBarHeight;
   
   
   
   public int MainMenuState;   // controls set to 0 to start, 1 if in setting, reset to 0 when user goes back to menu

  
   public Window(int width, int height, String title, Runner runner)
   {  
      borderSize = 20;
	   
	  this.title = title;
      this.runner = runner;
      
      this.width = width;
      this.height = height;
      
      bottomPanelBG = LUMA_BLUE;
      
      bottomBarWidth = this.width;
      bottomBarHeight = 100;
      
      canvasHeight = height;
      
      createGUI();
      
      
      runner.start();
   }
   
   public JPanel createCanvasHolder()
   {
	   JPanel canvasHolder = new JPanel(new BorderLayout());
	   
	   canvasHolder.setBounds(0,0, width, canvasHeight);
	   
	   canvasHolder.add(runner, BorderLayout.CENTER);
	   
	   return canvasHolder;
	   
   }
   
   
   public JPanel createBottomBar()
   {
       JPanel bottomPanel = new JPanel(new FlowLayout());
      
       JButton button1 = new JButton("Button 1");
      
       bottomPanel.setBounds(0, 0, bottomBarWidth, bottomBarHeight);
       bottomPanel.setBackground(bottomPanelBG);
      
       button1.setActionCommand("button1");
       button1.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e){  
               System.out.println(button1.getActionCommand() + " has been pressed"); 
    	   }
       });
      
       bottomPanel.add(button1);
      
       return bottomPanel;
   }
   
   public JMenuBar createJMenuBar() 
   {
	   JMenuBar mainMenuBar;
       JMenu menu1;
       JMenuItem toMenu;
       JMenuItem close;
      
       mainMenuBar = new JMenuBar();
       menu1 = new JMenu("Menu");
       mainMenuBar.add(menu1);
      
       close = new JMenuItem("Close Window");
       close.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e){  
               System.out.println("Close Window has been pressed"); 
    	   }
       });
       
       toMenu = new JMenuItem("To Main Menu");
       toMenu.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e){  
               System.out.println("To Menu has been pressed"); 
    	   }
       });
	   
       menu1.add(toMenu);
       menu1.add(close);
      
       return mainMenuBar;
   }

   
   private void createGUI() 
   {
	   JFrame.setDefaultLookAndFeelDecorated(true);
	      
	   canvasHolder = createCanvasHolder();
	   bottomBar = createBottomBar();
	   
	   // Create and set up the window.
	   JFrame frame = new JFrame(title);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setLayout(new BorderLayout());
	   
       Window app = this;
      
       frame.setPreferredSize(new Dimension(width, height));
       frame.setMaximumSize(new Dimension(width, height));
	   frame.setMinimumSize(new Dimension(width, height));
	   frame.setResizable(false);
	   frame.setLocationRelativeTo(null);
	   
	   frame.setJMenuBar(app.createJMenuBar());
	   frame.getContentPane().add(app.createContentPane());
	   
	   frame.setVisible(true);
   }
      
   public Container createContentPane() 
   {
	   // Create the content-pane-to-be.
	   JPanel jplContentPane = new JPanel(new BorderLayout(10,10));
	   jplContentPane.setOpaque(true);
       jplContentPane.setPreferredSize(new Dimension(width + borderSize, height + borderSize));
       jplContentPane.setMaximumSize(new Dimension(width + borderSize, height + borderSize ));
       jplContentPane.setMinimumSize(new Dimension(width + borderSize, height + borderSize));
	   jplContentPane.setBackground(Color.DARK_GRAY);
	   
	   jplContentPane.add(canvasHolder, BorderLayout.CENTER);
	   jplContentPane.add(bottomBar, BorderLayout.PAGE_END);
      
	   return jplContentPane;
  }
  
  public void actionPerformed(ActionEvent e) 
  {
	  /*
	  JMenuItem menuSource;
      JButton buttonSource;
      
      JMenuItem menuItemReference = new JMenuItem();
      JButton buttonReference = new JButton();
      
      if(e.getClass().getName().equals(menuItemReference.getClass().getName()))
      {
         menuSource = (JMenuItem) (e.getSource());
		   String menuString = "Menu Item source: " + menuSource.getText()
				+ " (an instance of " + getClassName(menuSource) + ")";
		   System.out.println(menuString);
      }
      else if(e.getClass().getName().equals(buttonReference.getClass().getName()))
      {      
         buttonSource = (JButton) (e.getSource());
         System.out.println("hello");
         String buttonString = buttonSource.getActionCommand();
         System.out.println(buttonString + " has been pressed");
      }
      */
	}
   
   public void itemStateChanged(ItemEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Menu Item source: "
				+ source.getText()
				+ " (an instance of "
				+ getClassName(source)
				+ ")"
				+ "\n"
				+ "    State of check Box: "
				+ ((e.getStateChange() == ItemEvent.SELECTED) ?
                                   "selected" : "unselected");
		jtAreaOutput.append(s + "\n");
		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
				.getLength());
	}
   
   protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex + 1); // Returns only Class name
	}
   
   public int getBottomBarHeight()
   {
      return bottomBarHeight;
   }
   
}