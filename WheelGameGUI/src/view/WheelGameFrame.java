package view;

/**
 * Assignment user interface for Further Programming providing the list of players are shown 
 * and updating the list whenever a player is added, removed, placing a bet and after the ball
 * has stopped spinning
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.interfaces.GameEngine;
import modelview.CalculateWinLoss;

public class WheelGameFrame extends JFrame
{
	private CalculateWinLoss winLoss = new CalculateWinLoss();
	private WheelPanel wheel;
	private SummaryPanel summary;
	private StatusBar status;
	private ToolBar tool;
	
	public WheelGameFrame(GameEngine gameEngine)
	{
		wheel = new WheelPanel(); 
		summary = new SummaryPanel(gameEngine, winLoss); 
		status = new StatusBar();
		tool = new ToolBar(gameEngine, wheel, summary, status);
		
		//getting and setting the frame size so it will be half
		//the width and height  of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension minimumSize = new Dimension((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
		setMinimumSize(minimumSize);
		//setting the frame to the middle of the screen
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//setting a menubar
		setJMenuBar(new MenuBar(gameEngine, wheel, summary, status));
		//setting the toolbar so its not movable
		tool.setFloatable(false);
		add(tool, BorderLayout.NORTH);
		add(wheel, BorderLayout.CENTER);
		add(summary, BorderLayout.EAST);
		add(status, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public SummaryPanel getSummaryPanel()
	{
		return summary;
	}
	
	public StatusBar getStatusPanel()
	{
		return status;
	}
	
	public WheelPanel getWheelPanel()
	{
		return wheel;
	}
}