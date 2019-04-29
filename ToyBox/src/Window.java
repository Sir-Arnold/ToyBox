import java.awt.*;
import java.awt.event.*;
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
   
   Runner runner; // extends Canvas, and you can add a canvas to a Content Pane
   Setting setting;
   
   JTextArea jtAreaOutput;
   JScrollPane jspPane;
  
   public int MainMenuState;   // controls set to 0 to start, 1 if in setting, reset to 0 when user goes back to menu
   
   //JMenuBar menuBar;
   //JMenu menu;
   //JMenuItem menuItem;
  
   public Window(int width, int height, String title, Runner runner)
	{
		/*
      frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
      frame.getContentPane().add(runner);
		frame.setVisible(true);
      */
      this.runner = runner;
      
      createGUI(width, height);
      
      runner.start();
   }
   
   public JMenuBar createJMenuBar() 
   {
		JMenuBar mainMenuBar;
      JMenu menu1;
      JMenuItem plainTextMenuItem;
      
      mainMenuBar = new JMenuBar();
      menu1 = new JMenu("Menu");
      mainMenuBar.add(menu1);
      
      plainTextMenuItem = new JMenuItem("To Main Menu");
      plainTextMenuItem.addActionListener(this);
		menu1.add(plainTextMenuItem);
      
      return mainMenuBar;
   }

   
   private void createGUI(int width, int height) 
   {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		JFrame frame = new JFrame("Physics Thing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window app = this;
		frame.setJMenuBar(app.createJMenuBar());
		frame.setContentPane(app.createContentPane());
		frame.setSize(500, 300);
		frame.setVisible(true);
   }
      
   public Container createContentPane() 
   {
		// Create the content-pane-to-be.
		JPanel jplContentPane = new JPanel(new BorderLayout());
		jplContentPane.setLayout(new BorderLayout());
		jplContentPane.setOpaque(true);
		jtAreaOutput = new JTextArea(5, 30);
		jtAreaOutput.setEditable(false);
		jspPane = new JScrollPane(jtAreaOutput);
		// Add the text area to the content pane.
		
      //jplContentPane.add(jspPane, BorderLayout.CENTER);
      jplContentPane.add(runner);
		return jplContentPane;
  }
  
  public void actionPerformed(ActionEvent e) 
  {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Menu Item source: " + source.getText()
				+ " (an instance of " + getClassName(source) + ")";
		jtAreaOutput.append(s + "\n");
		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
				.getLength());
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
   
   
}