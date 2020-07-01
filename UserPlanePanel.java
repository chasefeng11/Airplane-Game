
/** Creates seperate Panel object for User's Plane
 * Plane currently does not move -- still working on repainting MasterPanel object
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserPlanePanel extends JPanel implements ActionListener, KeyListener {
    final private int locx = 300;
    private int locy = 300;
    private int changeX = 0;
    private int changeY = 0;
    private Image plane;

    /**
     * Constructor for objects of class UserPlane
     */
    public UserPlanePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);

        //Paints user's plane
        plane = Toolkit.getDefaultToolkit().getImage("Allied_Plane.png");
        g.drawImage(plane, locx, locy, 1200, 500, this);
    }

    public void actionPerformed (ActionEvent e){
        locy += changeY;
        repaint();
    }

    public void keyPressed (KeyEvent e){
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_UP){
            changeY = -2;
        }
        if (c == KeyEvent.VK_DOWN){
            changeY = 2;
        }
    }

    public void keyReleased (KeyEvent e){
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_UP || c == KeyEvent.VK_DOWN){
            changeY = 0;
        }
    }

    public void keyTyped (KeyEvent e){

    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
}
