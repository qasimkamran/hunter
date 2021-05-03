package QasimProject.Hunter.Card;

import QasimProject.Hunter.Constants;
import QasimProject.Hunter.GameObject;
import javafx.scene.canvas.GraphicsContext;

/*
 * Factory of card objects that returns a card through its methods 
 * based on the owner, type, and its zone
 */

public class CardFactory implements CardFactoryIF{

	private String owner;
	
	private Card card = null;
	
	private GraphicsContext gc;
	
	private double xpos, ypos;
	
	public CardFactory(GraphicsContext gc, String owner)
	{
		this.gc = gc;
		this.owner = owner;
		if(owner.equals("P1"))
			ypos = Constants.HAND_YPOS_P1;
		else
			ypos = Constants.HAND_YPOS_CPU;
	}
	
	@Override
	public Card initialiseCard(String cardType, String cardName, int zone) 
	{
		switch(zone)
		{
			case 1:
				xpos = Constants.HAND_XPOS_LEFTMOST;
				break;
			case 2:
				xpos = Constants.HAND_XPOS_MIDDLE;
				break;
			case 3:
				xpos = Constants.HAND_XPOS_RIGHTMOST;
				break;
		}
		if(cardType.equals("Animal"))
			return card = new AnimalCard(gc, xpos, ypos, owner, cardName);
		else if(cardType.equals("Equip"))
				return card = new EquipCard(gc, xpos, ypos, owner, cardName);
			else
				return card = new FaceDownCard(gc, xpos, ypos, owner);
	}
	
}
