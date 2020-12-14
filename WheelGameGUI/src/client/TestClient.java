package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.WheelGameFrame;

public class TestClient 
{
	public static void main(String args[])
	{
		GameEngine gameEngine = new GameEngineImpl();
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			 public void run() 
			 {
				 WheelGameFrame frame=new WheelGameFrame(gameEngine);
				 gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(frame));
				 gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
			 }
		 });
	}
}
