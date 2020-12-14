package view;

/**
 * Assignment user interface for Further Programming providing a dialog 
 * box that allows user to input a bet data for a player 
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DialogCancelButtonActionListener;
import controller.PlaceBetDialogOkButtonActionListener;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlaceBetDialog extends JDialog 
{
	private JTextField bet;
	private JComboBox<BetType> betType;
	private JButton okButton;
	private JButton cancelButton;
	
	public PlaceBetDialog(Frame frame, Player player, GameEngine gameEngine, SummaryPanel summary, StatusBar status)
	{
		//setting the frame to the middle of the screen
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
		JLabel betLabel = new JLabel("Bet: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(betLabel,gbc);
		bet = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 0;
		inputPanel.add(bet, gbc);
		JLabel passwordLabel = new JLabel("Bet Type: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(passwordLabel,gbc);
		BetType betTypeValue[]= {BetType.RED, BetType.BLACK, BetType.ZEROS};
		betType = new JComboBox<BetType>(betTypeValue);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 1;
		inputPanel.add(betType ,gbc);
		JLabel spacer = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(spacer,gbc);
		//creating a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2, 10, 10));
		okButton = new JButton("OK");
		//adding a listener to ok button
		okButton.addActionListener(new PlaceBetDialogOkButtonActionListener(this, player, bet, betType, gameEngine, status, summary));
		gbc.gridx = 0;
		gbc.gridy = 3;
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		//adding a listener to cancel button
		cancelButton.addActionListener(new DialogCancelButtonActionListener(this));
		gbc.gridx = 2;
		gbc.gridy = 3;
		buttonPanel.add(cancelButton);
		panel.add(inputPanel, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(panel);
		pack();
		this.setVisible(true);
	}	
}
