package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot
{
	private int position;
	private Color color;
	private int number;
	
	public SlotImpl(int position, Color color, int number)
	{
		this.position = position;
		this.color = color;
		this.number = number;
	}
	
	@Override
	public int getPosition() 
	{
		return position;
	}

	@Override
	public int getNumber() 
	{
		return number;
	}

	@Override
	public Color getColor() 
	{
		return color;
	}

	@Override
	public String toString()
	{
		// Lowers the case of the letters besides the first letter
		// eg. BLACK -> Black
		String colourUpperCase = color.toString();
		String colourLowerCase = colourUpperCase.substring(0,1) + colourUpperCase.substring(1).toLowerCase();
		
		return String.format("Position: %s, Color: %s, Number: %s", position, colourLowerCase, number);
	}

	@Override
	public boolean equals(Slot slot) 
	{
		// Is equal if number and colour are the same
		if(slot.getNumber() == number && slot.getColor() == color)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Slot)
		{
			return equals((Slot) obj);
		}
		return false;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 17;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		result = prime * result + position;
		return result;
	}
}
