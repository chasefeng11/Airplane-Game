/** Creates static Panel object for the background 
 * Need to make it scrolling
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel{
    private Image background;

    public void paintComponent (Graphics g){
        super.paintComponent(g);

        //Paints background image
        background = Toolkit.getDefaultToolkit().getImage("Background.png");
        g.drawImage(background, 0, 0, 1800, 900, this);

    }

    public Dimension getPreferredSize() {
        return new Dimension(2000, 2000);
    }
}
