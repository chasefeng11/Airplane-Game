import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewFrame extends JFrame {
    private JFrame frame;

    private JPanel masterPanel;
    private BackgroundPanel backgroundImage;
    private UserPlanePanel userPlane;

    public NewFrame(){

        // Creates the frame.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        masterPanel = new JPanel();
        masterPanel.setLayout( new OverlayLayout(masterPanel) );

        masterPanel.setPreferredSize(new Dimension(4000,4000));
        init();

        frame.getContentPane().add(masterPanel);
    }
    public void init(){
        backgroundImage = new BackgroundPanel();
        userPlane = new UserPlanePanel();
        userPlane.setOpaque(false);
        backgroundImage.setOpaque(false);

        masterPanel.add(userPlane);
        masterPanel.add(backgroundImage);

        //frame.getContentPane().add(userPlane);
        //frame.getContentPane().add(backgroundImage);

    }

    // Shows the frame.
    public void display(){
        frame.pack();
        frame.setVisible(true);
    }
}