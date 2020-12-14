package view;
  
/**
 * Assignment user interface for Further Programming providing the wheel 
 * game and also the ball that will spin whenever the spin button is successfully clicked
 * 
 * @author Vincent Pranata
 * 
 */
  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.interfaces.Slot;

public class WheelPanel extends JPanel
{
	//getting the image for the roulette
	private Image image= Toolkit.getDefaultToolkit().getImage("../WheelGame/img/Basic_Roulette_wheel_1024x1024.png"); 
	//getting the first random position from gameEngineImpl
	private int position=0;
	private int BALL_DIAMETER=0;
	//creating the angle of each slots
	private double theta = 2*Math.PI/Slot.WHEEL_SIZE;
	//the condition of the ball
	private boolean spinning=false;
	//the ratio for wheel and ball so the ball is in the model of the roulette wheel
	private final double BALL_RATIO=0.93;
	
	//drawing the image and the ball
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		int width = getWidth();  
		int height = getHeight();
		int x=0;
		int y=0;
		//if statement that make the roulette wheel stays circle so its not oval
		//and place it in the middle of the panel
		if(width>height)
		{
			width=height;
			x=getWidth()/2-getHeight()/2;
		} 
		else
		{
			height=width;
			y=getHeight()/2-getWidth()/2;
		}
	    g.drawImage(image, x, y, width, height, this); 
	    drawBall(g2d);
	}
	
	//draw the ball
	public void drawBall(Graphics2D g) 
	{
		int r=0;
		//setting the ball diameter based on the height/ width of panel
		//and creating the radius for the roulette wheel
		if(getWidth()>getHeight())
		{
			BALL_DIAMETER=getHeight()/30;
			r=getHeight()/2;
		} 
		else
		{
			BALL_DIAMETER=getWidth()/30;
			r=getWidth()/2;
		}
		//the position of the ball in each slots so it stays in the middle
		int	x=(int)(r*BALL_RATIO*(Math.cos((theta*position)-Math.PI/2)))+getWidth()/2;
		int	y=(int)(r*BALL_RATIO*(Math.sin((theta*position)-Math.PI/2)))+getHeight()/2;
		g.setColor(Color.YELLOW);
		g.fillOval(x-BALL_DIAMETER/2, y-BALL_DIAMETER/2, BALL_DIAMETER, BALL_DIAMETER);	
	}
	
	//a method where it is called to repaint the ball when it is spinning
	public void ballRoll(int position, boolean spinning)
	{
		setPosition(position);
		setCondition(spinning);
		SwingUtilities.invokeLater(new Runnable() 
		{
			 public void run() 
			 {
				 repaint();
			 }
		 });
	}
	
	//set the position of the ball
	private void setPosition(int position) 
	{
		this.position=position;
	}
	
	//set the condition of the ball if it is spinning or not
	private void setCondition(boolean spinning)
	{
		this.spinning=spinning;
	}
	
	//get the condition of the ball
	public boolean getCondition()
	{
		return spinning;
	}
}


