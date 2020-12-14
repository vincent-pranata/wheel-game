package view;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private WheelGameFrame frame;
	
	public GameEngineCallbackGUI(WheelGameFrame frame)
	{
		this.frame=frame;
	}

	
	@Override
	public void nextSlot(Slot slot, GameEngine engine) 
	{
		boolean spinning=true;
		SwingUtilities.invokeLater(new Runnable() 
		{
			 public void run() 
			 {
				 //moves the ball to the next slot while still spinning and set 
				 //spinning condition to true
				 frame.getWheelPanel().ballRoll(slot.getPosition(), spinning);
				 //update the status bar
				 frame.getStatusPanel().updateSpinning();
			 }
		 });
	}

	//moves the ball to the next slot once and set 
	//spinning condition to false
	@Override
	public void result(Slot winningSlot, GameEngine engine) 
	{
		//set old points for every players
		frame.getSummaryPanel().setPlayerOldPoints();
		
		new Thread()
		{
			@Override
			public void run()
			{
				//calculate the result for each player
				engine.calculateResult(winningSlot);
				//resets all the bets for every player after result have been calculated
				for(Player player:engine.getAllPlayers())
				{
					player.resetBet();
				}
			}
		}.start();
		
		boolean spinning=false;
		SwingUtilities.invokeLater(new Runnable() 
		{
			 public void run() 
			 {
				 //moves the ball to the next slot while still spinning and set 
				 //spinning condition to true
				 frame.getWheelPanel().ballRoll(winningSlot.getPosition(), spinning);
				 //update the status bar
				 frame.getStatusPanel().updateResult(winningSlot.toString());
				 //update the summary panel
				 frame.getSummaryPanel().updatePlayer();
			 }
		 });
	}
}
