package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * ok button from the add player dialog box is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.interfaces.Player;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.StatusBar;
import view.SummaryPanel;

public class AddPlayerDialogOkButtonActionListener implements ActionListener 
{
	private AddPlayerDialog addPlayerDialog;
	private JTextField id;
	private JTextField name;
	private JTextField points;
	private GameEngine gameEngine;
	private String detail="";
	private StatusBar status;
	private SummaryPanel summary;
	private int type=JOptionPane.ERROR_MESSAGE;
	
	public AddPlayerDialogOkButtonActionListener(AddPlayerDialog addPlayerDialog, JTextField id, JTextField name, JTextField points, GameEngine gameEngine, StatusBar status, SummaryPanel summary)
	{
		this.addPlayerDialog=addPlayerDialog;
		this.name=name;
		this.id=id;
		this.points=points;
		this.gameEngine=gameEngine;
		this.status=status;
		this.summary=summary;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		//check if id is valid
		if(!id.getText().isEmpty())
		{
			//check if name is valid
			if(!name.getText().isEmpty())
			{
				//try to change string to int if it valid
				try
				{
					int pointValue=Integer.parseInt(points.getText());
					//checks if point is a positive value
					if(pointValue>0)
					{
						//create a new player
						Player player = new SimplePlayer(id.getText(), name.getText(), pointValue);
						new Thread()
						{
							@Override 
							public void run()
							{
								//add a player
					      		gameEngine.addPlayer(player);
							}
						}.start();
			      		detail="ID: "+id.getText()+" ,Name: "+name.getText()+" ,Points: "+pointValue;
			      		//update status and summary 
			      		status.updateAddPlayer(player);
			      		summary.addPlayer(player);
			      		//close the dialog box
			      		addPlayerDialog.dispose();
			      		type = JOptionPane.PLAIN_MESSAGE;
			      	}
					else
					{
						detail="Please input a positive value for point";
					}
				}
				catch(Exception e)
				{
					detail="Please input a valid value for points";
				}
			}
			else
			{
				detail="Please input a valid value for name";
			}
		}
		else
		{
			detail="Please input a valid value for id";
		}
		//a pop up message printing the detail  
		JOptionPane.showMessageDialog(null, detail,  null, type);
	}
}	
