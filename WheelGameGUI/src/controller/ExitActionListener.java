package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * an exit button from menubar or toolbar is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ExitActionListener implements ActionListener
{
	//exit the frame when pressed
	public void actionPerformed(ActionEvent e)
	{
		System.exit(JFrame.EXIT_ON_CLOSE);
	}

}
