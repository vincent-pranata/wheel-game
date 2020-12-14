package controller;

/**
 * Assignment listener for Further Programming providing what to do when
 * cancel button from any dialog box is clicked
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class DialogCancelButtonActionListener implements ActionListener 
{
	private JDialog dialog;
	public DialogCancelButtonActionListener(JDialog dialog) 
	{
		this.dialog=dialog;
	}

	//dispose dialog box
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		dialog.dispose();
	}

}
