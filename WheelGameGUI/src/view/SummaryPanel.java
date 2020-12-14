
package view;

/**
 * Assignment user interface for Further Programming creating the frame
 * and adding all the panels created to the frame where users can interacts
 * and view all the updates that happening to the game
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import modelview.CalculateWinLoss;

public class SummaryPanel extends JPanel
{
	private ListModel<String> model = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(model);
	private Map<String, String> playerList = new HashMap<String, String>();
	private CalculateWinLoss winLoss;
	private GameEngine gameEngine;
	public SummaryPanel(GameEngine gameEngine, CalculateWinLoss winLoss)
	{
		this.gameEngine=gameEngine;
		this.winLoss=winLoss;
		setLayout(new BorderLayout());
		JLabel label= new JLabel("Player List", JLabel.CENTER);
		add(label, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(list);
		add(scroll);
		//setting the hard coded player if there is any
		for(Player player:gameEngine.getAllPlayers())
		{
			addPlayer(player);
		}
	}
	
	//updating the list when a player is added
	public void addPlayer(Player player)
	{
		if(playerList.get(player.getPlayerId())!=null)
		{
			removePlayer(player);
		}
		playerList.put(player.getPlayerId(), ("ID: "+player.getPlayerId()+" ,Name: "+player.getPlayerName()+" ,Points; "+player.getPoints()+" ,Bet: "+player.getBet()+" ,Bet Type: "+player.getBetType()+" ,Win/Loss: "+winLoss.getWinLoss(player.getPlayerId())));
		((DefaultListModel<String>)model).addElement(playerList.get(player.getPlayerId()));
	}
	
	//updating the player list when a player is removed
	public void removePlayer(Player player)
	{
		((DefaultListModel<String>)model).removeElement(playerList.get(player.getPlayerId()));
		playerList.remove(player.getPlayerId());
	}
	
	//updating the list after spinning
	public void updatePlayer()
	{
		for(Player player:gameEngine.getAllPlayers())
		{
			//set the new points to the points after calculated
			winLoss.setNewPoints(player.getPlayerId(), player.getPoints());
			removePlayer(player);
			playerList.put(player.getPlayerId(), ("ID: "+player.getPlayerId()+" ,Name: "+player.getPlayerName()+" ,Points; "+player.getPoints()+" ,Bet: "+player.getBet()+" ,Bet Type: "+player.getBetType()+" ,Win/Loss: "+winLoss.getWinLoss(player.getPlayerId())));
			((DefaultListModel<String>)model).addElement(playerList.get(player.getPlayerId()));
		}
	}
	
	//updating the list after a player makes a bet
	public void updatePlayerBet()
	{
		for(Player player:gameEngine.getAllPlayers())
		{
			removePlayer(player);
			playerList.put(player.getPlayerId(), ("ID: "+player.getPlayerId()+" ,Name: "+player.getPlayerName()+" ,Points; "+player.getPoints()+" ,Bet: "+player.getBet()+" ,Bet Type: "+player.getBetType()+" ,Win/Loss: "+winLoss.getWinLoss(player.getPlayerId())));
			((DefaultListModel<String>)model).addElement(playerList.get(player.getPlayerId()));
		}
	}
	
	public void setPlayerOldPoints()
	{
		//set every players' old points to the points after calculated 
		for(Player player:gameEngine.getAllPlayers())
		{
			winLoss.setOldPoints(player.getPlayerId(), player.getPoints());
		}
	}
	
	//setting the size of the summary panel based on the frame	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(getParent().getWidth()/3, getParent().getHeight());
		
	}
	//get the list and sends it
	public JList<String> getList()
	{
		return list;
	}
}
