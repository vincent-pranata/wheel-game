package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * an add player button from menubar or toolbar is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.StatusBar;
import view.SummaryPanel;


public class AddPlayerActionListener implements ActionListener 
{
	private GameEngine gameEngine;
	private SummaryPanel summary;
	private StatusBar status;
	
	public AddPlayerActionListener(GameEngine gameEngine, SummaryPanel summary, StatusBar status)
	{
		this.gameEngine=gameEngine;
		this.summary=summary;
		this.status=status;
	}
	
	//creating add player dialog
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		new AddPlayerDialog(new Frame(), gameEngine, summary, status);
	}

}
