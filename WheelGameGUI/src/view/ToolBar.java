package view;

/**
 * Assignment user interface for Further Programming providing a tool
 * bar that allows user to click on buttons to choose what to do
 * 
 * @author Vincent Pranata
 * 
 */

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.AddPlayerActionListener;
import controller.ExitActionListener;
import controller.PlaceBetActionListener;
import controller.RemovePlayerActionListener;
import controller.SpinActionListener;
import model.interfaces.GameEngine;

public class ToolBar extends JToolBar
{
	public ToolBar(GameEngine gameEngine, WheelPanel wheel, SummaryPanel summary, StatusBar status)
	{
		//create the button for each functions and the listener for each buttons
		JButton addPlayer = new JButton("Add Player");
		addPlayer.addActionListener(new AddPlayerActionListener(gameEngine, summary, status));
		JButton removePlayer = new JButton("Remove Player");
		removePlayer.addActionListener(new RemovePlayerActionListener(gameEngine, summary, status));
		JButton placeBet = new JButton("Place Bet");
		placeBet.addActionListener(new PlaceBetActionListener(gameEngine, summary, status));
		JButton spin = new JButton("Spin");
		spin.addActionListener(new SpinActionListener(gameEngine, wheel));
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ExitActionListener());
		
		add(addPlayer);
		add(removePlayer);
		add(placeBet);
		add(spin);
		add(exit);
	}
}
