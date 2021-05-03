package QasimProject.Hunter;

import java.util.ArrayList;

import QasimProject.Hunter.Card.AnimalCard;
import QasimProject.Hunter.Card.Card;
import QasimProject.Hunter.Card.EquipCard;
import QasimProject.Hunter.Card.FaceDownCard;
import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;

/*
 * This class models the Hand in trading card games.
 * All interactions from hand and to hand are implemented.
 */

public class Hand {
	
	private String owner;
	private int length = 3;
	
	private ArrayList<Card> cards = new ArrayList<>();
	
	public Hand(String owner, Card card, Card card2, Card card3)
	{
		this.owner = owner;
		this.cards.add(card);
		this.cards.add(card2);
		this.cards.add(card3);
	}
	
	//length is decremented if a card is removed from hand
	public void remove()
	{
		length--;
	}
	
	//length is decremented if a card is added to hand (drawn)
	public void draw()
	{
		length++;
	}
	
	//a card object may be added to the ArrayList of cards in Hand
	public void addToHand(Card card)
	{
		cards.add(card);
	}

	//a card object may be removed from the ArrayList of cards in Hand
	public void removeFromHand(Card card)
	{
		cards.remove(card);
	}
	
	//all cards in hand are of type 'Card' initiallty, this method casts them to their respective types
	public void setCardTypes()
	{
		for(Card c : cards)
			switch(c.getCardType())
			{
				case "Animal":
					c = (AnimalCard)c;
					break;
				case "Equip":
					c = (EquipCard)c;
					break;
				case "FaceDown":
					c = (FaceDownCard)c;
					break;
			}
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public String getOwner() {
		return owner;
	}

	public int getLength() {
		return length;
	}
}
