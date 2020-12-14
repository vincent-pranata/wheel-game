package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player 
{
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private BetType betType;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints)
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
		bet = 0;
	}
	
	@Override
	public String getPlayerName() 
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerId;
	}

	@Override
	public boolean setBet(int bet) 
	{
		// Only set bet if bet > 0 and player has enough points to bet
		if(bet <= points && bet > 0)
		{
			this.bet = bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public void setBetType(BetType betType) 
	{
		this.betType = betType;
	}

	@Override
	public BetType getBetType() 
	{
		return betType;
	}

	@Override
	public void resetBet() 
	{
		bet = 0;
	}
	
	@Override
	public String toString()
	{
		return String.format("Player: id=%s, name=%s, bet=%s, betType=%s, points=%s", playerId, playerName, bet, betType, points);
	}
}
