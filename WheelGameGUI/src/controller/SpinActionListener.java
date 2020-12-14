package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * a spin button button from menubar or toolbar is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.WheelPanel;

public class SpinActionListener implements ActionListener 
{
	private GameEngine gameEngine;
	private WheelPanel wheel;
	
	public SpinActionListener(GameEngine gameEngine, WheelPanel wheel)
	{
		this.gameEngine=gameEngine;
		this.wheel=wheel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//if wheel condition is spinning
		if(wheel.getCondition())
		{
			JOptionPane.showMessageDialog(null, "Ball has been spun. Please wait until it stops.", null, JOptionPane.ERROR_MESSAGE);
		}
		//if not spinning
		else
		{
			if(!gameEngine.getAllPlayers().isEmpty())
			{
				int counter=0;
				for(Player player:gameEngine.getAllPlayers())
				{
					if(player.getPoints()>0 && player.getBet()>0)
					{
						counter++;
					}
				}
				if(counter>=1)
				{
					new Thread()
					{
						@Override 
						public void run()
						{
				      		gameEngine.spin(1, 500, 25);
		
						}
					}.start();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No players have made a bet. Please make a bet", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "There are no players added. Please add a player first.", null, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
