package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine 
{
	private List<Player> players = new ArrayList<Player>(); 
	private List<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private List<Slot> slots = new ArrayList<Slot>();

	public GameEngineImpl()
	{
		createSlots();
	}

	private void createSlots()
	{
		// Numbers on the wheels where the index of array = position of slot
		final int[] NUMBERS = {0, 27, 10, 25, 29, 12, 8, 19, 31, 18, 6, 21, 33, 16, 4, 23, 35, 
				14, 2, 0, 28, 9, 26, 30, 11, 7, 20, 32, 17, 5, 22, 34, 15, 3, 24, 36, 13, 1};
		final int POSITION_GREEN00 = 0; // Position of GREEN00
		final int POSITION_GREEN0 = 19; // Position of GREEN0

		for(int i = 0; i < Slot.WHEEL_SIZE; i++)
		{
			if(i == POSITION_GREEN00)
			{
				slots.add(new SlotImpl(i, Color.GREEN00, NUMBERS[i]));
			}
			else if(i == POSITION_GREEN0)
			{
				slots.add(new SlotImpl(i, Color.GREEN0, NUMBERS[i]));
			}
			else
			{
				// Odd positions are red
				if(i%2 == 1)
				{
					slots.add(new SlotImpl(i, Color.RED, NUMBERS[i]));
				}
				// Even positions are black
				else
				{
					slots.add(new SlotImpl(i, Color.BLACK, NUMBERS[i]));
				}
			}
		}
	}

	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) 
	{

		int position = (int)(Math.random()*Slot.WHEEL_SIZE);
		// Produces a random number between 0 to WHEEL_SIZE-1
		int delay = initialDelay;
		do
		{
			for(GameEngineCallback gameEngineCallback:gameEngineCallbacks)
			{
				gameEngineCallback.nextSlot(slots.get(position), this);
			}

			// Increments the position but resets to 0 if the position is on the last slot
			// so it acts like a wheel
			if(position >= Slot.WHEEL_SIZE-1)
			{
				position = 0;
			}
			else
			{
				position++;
			}

			delayProgram(delay);

			// Increase the delay so it acts like a spinning wheel which is slowing down
			delay += delayIncrement;
		}
		while(delay < finalDelay);

		// Outputs result
		Slot resultSlot = slots.get(position);
		for(GameEngineCallback gameEngineCallback:gameEngineCallbacks)
		{
			gameEngineCallback.result(resultSlot, this);
		}

	}

	// Delays the program for a certain amount of time (milliseconds)
	private void delayProgram(int time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e) {}
	}

	@Override
	public void calculateResult(Slot winningSlot) 
	{
		// Applies result to each player
		for(Player player:players)
		{
			if(player.getBetType()!=null)
			{
				// Result = RED
				if(winningSlot.getColor().equals(Color.RED))
				{
					BetType.RED.applyWinLoss(player, winningSlot);
				}
				// RESULT = BLACK
				else if(winningSlot.getColor().equals(Color.BLACK))
				{
					BetType.BLACK.applyWinLoss(player, winningSlot);
				}
				// RESULT = GREEN
				else
				{
					BetType.ZEROS.applyWinLoss(player, winningSlot);
				}
			}
		}
	}

	@Override
	public void addPlayer(Player player) 
	{
		// Checks if player exists
		if(getPlayer(player.getPlayerId()) == null)
		{
			players.add(player);
		}
		else
		{
			// Removes old player if playerId already exists 
			removePlayer(getPlayer(player.getPlayerId()));
			players.add(player);
		}
	}

	@Override
	public Player getPlayer(String id) 
	{
		for(Player player:players)
		{
			if(player.getPlayerId().equals(id))
			{
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) 
	{
		int index = players.indexOf(player);
		if(index > -1)
		{
			players.remove(player);
			return true;
		}

		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		int index = gameEngineCallbacks.indexOf(gameEngineCallback);
		if(index > -1)
		{
			gameEngineCallbacks.remove(index);
			return true;
		}

		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() 
	{
		return Collections.unmodifiableCollection(players);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) 
	{
		if(player.setBet(bet))
		{
			// Only sets bet type if player is entered a valid bet
			player.setBetType(betType);
			return true;
		}
		else
		{
			player.resetBet();
			player.setBetType(null);
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots() 
	{
		return Collections.unmodifiableCollection(slots);
	}

}
