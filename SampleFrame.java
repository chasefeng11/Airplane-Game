/** This class will create the frame to be used
 * Creates single Panel object all pictures
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SampleFrame extends JFrame
{
    public static JFrame frame;
    private SamplePanel panel;
    
    public SampleFrame()
    {
        // Creates the frame.
        frame = new JFrame("Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        
        
        
        // Creates the panel.
        panel = new SamplePanel();
        panel.setPreferredSize(new Dimension(1800,960));
        // Adds the panel to the frame.
        frame.getContentPane().add(panel);
        
        
    }
    
   
    // Shows the frame.
    public void display()
    {
        frame.pack();
        
        frame.setVisible(true);
    }
}




