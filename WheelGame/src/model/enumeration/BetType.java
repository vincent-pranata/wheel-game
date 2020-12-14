package model.enumeration;

import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */
public enum BetType
{
   RED
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot)
	   {
		   if(player.getBetType().equals(RED))
		   {
			   // Adds the players bet to their points if their bet type is RED
			   player.setPoints(player.getPoints() + player.getBet());
		   }
		   else
		   {
			   player.setPoints(player.getPoints() - player.getBet());
		   }
	   }
   },
   
   BLACK
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) 
	   {
		   if(player.getBetType().equals(BLACK))
		   {
			   // Adds the players bet to their points if their bet type is BLACK
			   player.setPoints(player.getPoints() + player.getBet());
		   }
		   else
		   {
			   player.setPoints(player.getPoints() - player.getBet());
		   }
	   }
	   
	   
   },
   
   ZEROS
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) 
	   {
		   if(player.getBetType().equals(ZEROS))
		   {
			   // Adds (WHEEL_SIZE/2 - 1)x of the players bet to their points if their bet type is ZEROS
			   player.setPoints(player.getPoints() + player.getBet()*(Slot.WHEEL_SIZE/2 - 1));
		   }
		   else
		   {
			   player.setPoints(player.getPoints() - player.getBet());
		   }
	   }
   };
 
   /**
    * This method is to be overridden for each bet type<br>
    * see assignment specification for betting odds and how win/loss is applied
    * 
    * @param player - the player to apply the win/loss points balance adjustment
    * @param winSlot - the winning slot the ball landed on
    */
   public abstract void applyWinLoss(Player player, Slot winSlot);
}