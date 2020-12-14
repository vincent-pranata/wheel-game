package view;

/**
 * Assignment user interface for Further Programming providing a dialog 
 * box that allows user to input data for a new player to added to the game
 * 
 * @author Vincent Pranata
 * 
 */

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerDialogOkButtonActionListener;
import controller.DialogCancelButtonActionListener;
import model.interfaces.GameEngine;

public class AddPlayerDialog extends JDialog 
{
	private JTextField id;
	private JTextField name;
	private JTextField points;
	private JButton okButton;
	private JButton cancelButton;
	
	public AddPlayerDialog(Frame frame, GameEngine gameEngine, SummaryPanel summary, StatusBar status)
	{
		//setting the dialog box to the middle of the screen
		setLocationRelativeTo(null);
		//makes the dialog box unresizable
		setResizable(false);
		//creating panel to contain input panel and button panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//creating the panel to contain all the elements of input
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2,2,2,2);
		JLabel idLabel = new JLabel("ID: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(idLabel,gbc);
		id = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 0;
		inputPanel.add(id,gbc);
		JLabel nameLabel = new JLabel("Name: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(nameLabel,gbc);
		name = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 1;
		inputPanel.add(name,gbc);
		JLabel pointsLabel = new JLabel("Points: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(pointsLabel,gbc);
		points = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 2;
		inputPanel.add(points,gbc);
		JLabel spacer = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		inputPanel.add(spacer,gbc);
		//creating a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2, 10, 10));
		okButton = new JButton("OK");
		//adding a listener to ok button
		okButton.addActionListener(new AddPlayerDialogOkButtonActionListener(this, id, name, points,gameEngine, status, summary));
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		//adding a listener to cancel button
		cancelButton.addActionListener(new DialogCancelButtonActionListener(this));
		buttonPanel.add(cancelButton);
		panel.add(inputPanel, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(panel);
		pack();
		this.setVisible(true);
	}	
}
