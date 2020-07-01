
//Creates the menu for the game
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Menu
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Menu"); //Creates Frame
        frame.setVisible(true);//Makes is visible
        frame.setSize(1800,960);//Sets size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();//Creates Panel
        frame.add(panel);
        JButton button1 = new JButton("Play"); //Display first button "play"
        panel.add(button1);
        button1.addActionListener(new Action());//Implements ActionListener
        JButton button2 = new JButton("Instructions");//Same algorithm for each button
        panel.add(button2);
        button2.addActionListener(new Action2());
        JButton button3 = new JButton("Credits");
        panel.add(button3);
        button3.addActionListener(new Action3());
        JButton button4 = new JButton("Quit");
        panel.add(button4);
        button4.addActionListener(new Action4());
        
    }
    static class Action implements ActionListener //Creates action listener
    {
        public void actionPerformed(ActionEvent e)
        {
            SampleFrame s = new SampleFrame(); //Invokes SampleFrame class
            
            s.display(); //Displays class
        }
    }
    static class Action2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Instructions: ");
            System.out.println("Use up and down arrows to go up and down");
            System.out.println("Hit the spacebar to drop bombs on enemy bases and factories");
            System.out.println("Dodge enemy bullets and destroy their bases!");
            System.out.println("Your success as a bomber will be measured by your score. ");
            System.out.println("Hitting enemy bases and factories will increase your score,");
            System.out.println("and staying alive for longer will also increase your score");
            System.out.println("and hitting civilian homes will decrease your score.");
            System.out.println("Get ready for takeoff!");
        }

    }
    static class Action3 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Credits (in alphabetical order):"); 
            System.out.println("Chase Feng");
            System.out.println("Dillon Bardhi");
            System.out.println("Matthew Sadowski");
        }

    }
    static class Action4 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0); //closes program
        }

    }
}

