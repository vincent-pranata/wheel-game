package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * a remove player button from menubar or toolbar is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.RemovePlayerDialog;
import view.StatusBar;
import view.SummaryPanel;

public class RemovePlayerActionListener implements ActionListener 
{
	private GameEngine gameEngine;
	private SummaryPanel summary;
	private StatusBar status;
	private int type=JOptionPane.ERROR_MESSAGE;
	
	public RemovePlayerActionListener(GameEngine gameEngine, SummaryPanel summary, StatusBar status)
	{
		this.gameEngine=gameEngine;
		this.summary=summary;
		this.status=status;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//if player list is not empty
		if(!gameEngine.getAllPlayers().isEmpty())
		{
			//if a player is selected
			if(summary.getList().getSelectedValue()!=null)
			{
				//split the string to ID: x, Name: x,...
				String data[]=summary.getList().getSelectedValue().split(" ,");
				//split ID: x to ID and x
				String playerId[]=data[0].split(": ");
				new RemovePlayerDialog(new Frame(), gameEngine.getPlayer(playerId[1]), gameEngine, summary, status);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a player to remove", null, type);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "There are no players added", null, type);
		}
	}

}
