package view;

/**
 * Assignment user interface for Further Programming providing a menu
 * bar that allows user to click on options or use shortcuts
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.ExitActionListener;
import controller.PlaceBetActionListener;
import controller.RemovePlayerActionListener;
import controller.SpinActionListener;
import model.interfaces.GameEngine;

public class MenuBar extends JMenuBar
{
	public MenuBar(GameEngine gameEngine, WheelPanel wheel, SummaryPanel summary, StatusBar status) 
	{
		//creating a drop down menu
		JMenu gameMenu = new JMenu("Menu");
		//setting a shortcut for menu of alt+M
		gameMenu.setMnemonic(KeyEvent.VK_M);
		//adding the item for menu, creating a listener for each items
		//and setting shortcuts for each one of them (alt+first letter of button) 
		JMenuItem addPlayer = new JMenuItem("Add Player", KeyEvent.VK_A);
		addPlayer.addActionListener(new AddPlayerActionListener(gameEngine, summary, status));
		JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_R);
		removePlayer.addActionListener(new RemovePlayerActionListener(gameEngine, summary, status));
		JMenuItem placeBet = new JMenuItem("Place Bet", KeyEvent.VK_P);
		placeBet.addActionListener(new PlaceBetActionListener(gameEngine, summary, status));
		JMenuItem spin = new JMenuItem("Spin", KeyEvent.VK_S);
		spin.addActionListener(new SpinActionListener(gameEngine, wheel));
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
		exit.addActionListener(new ExitActionListener());
		
		gameMenu.add(addPlayer);
		gameMenu.add(removePlayer);
		gameMenu.add(placeBet);
		gameMenu.addSeparator();
		gameMenu.add(spin);
		gameMenu.addSeparator();
		gameMenu.add(exit);
		add(gameMenu);
	}
}
