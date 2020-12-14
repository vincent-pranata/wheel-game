package modelview;

/**
 * Assignment modelview for Further Programming providing the winloss
 * of every player last round
 * 
 * @author Vincent Pranata
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class CalculateWinLoss 
{
	private Map<String, Integer> playerOldPoints = new HashMap<String, Integer>();
	private Map<String, Integer> playerNewPoints = new HashMap<String, Integer>();
	//setting the points before the bet
	public void setOldPoints(String id,int points)
	{
		playerOldPoints.put(id, points);
	}
	
	//setting the points after the bet
	public void setNewPoints(String id,int points)
	{
		playerNewPoints.put(id, points);
	}
	
	//calculating the win loss of each round for each player
	public int getWinLoss(String id)
	{
		int winLoss=0;
		//check if old point and new point for a certain player is not null
		if(playerNewPoints.get(id)!=null && playerOldPoints.get(id)!=null)
		{
			winLoss=playerNewPoints.get(id)-playerOldPoints.get(id);
		}
		return winLoss;
	}
}
