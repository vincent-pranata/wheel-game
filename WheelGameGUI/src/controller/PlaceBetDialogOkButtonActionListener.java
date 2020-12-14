package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * an ok button from place bet dialog box is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.interfaces.Player;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import view.PlaceBetDialog;
import view.StatusBar;
import view.SummaryPanel;

public class PlaceBetDialogOkButtonActionListener implements ActionListener 
{
	private PlaceBetDialog placeBetDialog;
	private Player player;
	private JTextField bet;
	private JComboBox<BetType> betType;
	private GameEngine gameEngine;
	private String detail="";
	private StatusBar status;
	private SummaryPanel summary;
	private int validCounter=0;
	private int invalidCounter=0;
	private int type=JOptionPane.ERROR_MESSAGE;
	
	public PlaceBetDialogOkButtonActionListener(PlaceBetDialog placeBetDialog, Player player, JTextField bet, JComboBox<BetType> betType, GameEngine gameEngine, StatusBar status, SummaryPanel summary)
	{
		this.placeBetDialog=placeBetDialog;
		this.player=player;
		this.bet=bet;
		this.betType=betType;
		this.gameEngine=gameEngine;
		this.status=status;
		this.summary=summary;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//try change string to int
		try
		{
			int betValue=Integer.parseInt(bet.getText());
			//checks if player points more than 0
			if(player.getPoints()>0)
			{
				//checks if points is more or equal to bet
				if(player.getPoints()>=betValue)
				{		
					//get selected bet type from JComboBox
					BetType betTypeValue=(BetType)betType.getSelectedItem();
					new Thread()
					{
						@Override 
						public void run()
						{
							//place bet for player
				      		gameEngine.placeBet(player, betValue, betTypeValue);
						}
					}.start();
					//dispose place bet dialog box
					placeBetDialog.dispose();
					detail="ID: "+player.getPlayerId()+" ,Name: "+player.getPlayerName()+" has make a bet of "+betValue+" for type "+betTypeValue;
					//updates summary and status 
					summary.updatePlayerBet();
					status.updateMakeBet(player);
					for(Player player:gameEngine.getAllPlayers())
					{
						//checks if player have more than 0 points and have make a bet
						if(player.getBet()>0 && player.getPoints()>0)
						{
				      		validCounter++;
						}
						//checks if player have 0 point
						else if(player.getPoints()==0)
						{
				      		invalidCounter++;
						}
					}
					//checks if every player have make a bet
					if(validCounter==gameEngine.getAllPlayers().size()-invalidCounter)
					{
						new Thread()
						{
							@Override 
							public void run()
							{
								//spins the ball if all player makes bet
					      		gameEngine.spin(1, 500, 25);
							}
						}.start();
					}
					type=JOptionPane.PLAIN_MESSAGE;
				}
				else
				{
					detail="Player do not have enough points to make the bet";
				}
			}
			else
			{
				detail="Player do not have any point remaining";
				placeBetDialog.dispose();
			}
		}
		catch(Exception e)
		{
			detail="Please input a valid bet";
		}
		//shows a pop up messages
		JOptionPane.showMessageDialog(null, detail, null, type);
	}
}
