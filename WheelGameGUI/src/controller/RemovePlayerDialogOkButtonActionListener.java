package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * an ok button from remove player dialog box is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.Player;
import model.interfaces.GameEngine;
import view.RemovePlayerDialog;
import view.StatusBar;
import view.SummaryPanel;

public class RemovePlayerDialogOkButtonActionListener implements ActionListener 
{
	private RemovePlayerDialog removePlayerDialog;
	private Player player;
	private GameEngine gameEngine;
	private String detail="";
	private StatusBar status;
	private SummaryPanel summary;
	
	public RemovePlayerDialogOkButtonActionListener(RemovePlayerDialog removePlayerDialog, Player player,  GameEngine gameEngine, StatusBar status, SummaryPanel summary)
	{
		this.removePlayerDialog=removePlayerDialog;
		this.player=player;
		this.gameEngine=gameEngine;
		this.status=status;
		this.summary=summary;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		new Thread()
		{
			@Override 
			public void run()
			{
				//remove player from the player lists
	      		gameEngine.removePlayer(player);
			}
		}.start();
		//dispose the dialog box
		removePlayerDialog.dispose();
		detail=player.getPlayerName()+" has been removed";
		//updates the summary and status
		summary.removePlayer(player);
		status.updateRemovePlayer(player);
		JOptionPane.showMessageDialog(null, detail, null, JOptionPane.PLAIN_MESSAGE);
	}
}	
