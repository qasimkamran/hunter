package QasimProject.Hunter;

import java.util.ArrayList;

import QasimProject.Hunter.Card.AnimalCard;
import QasimProject.Hunter.Card.Card;
import QasimProject.Hunter.Placeholders.Placeholder;
import QasimProject.Hunter.Placeholders.PlaceholderController;

/*
 * This class handles interaction between animals at the 'Engage' step
 */

public class Engage {
	
	private ArrayList<AnimalCard> cpuCards = new ArrayList<>();;
	private ArrayList<AnimalCard> p1Cards = new ArrayList<>();;
	
	private String ownerOfStrongest = null;
	
	//This field is to allow altering the log with every change
	private Log log;
	
	//All cards set into placeholders are stores in their respective ArrayList variables
	public Engage(Log log)
	{
		for(Placeholder p : PlaceholderController.placeholders)
			if(p.getCard()!=null)
				if(p.getOwner().equals("CPU"))
					cpuCards.add(p.getCard());
				else
					p1Cards.add(p.getCard());
		this.log = log;
		sortCards();
	}
	
	public void noEngagement()
	{
		log.addNoEngagementText();
	}
	
	//Sorts the two ArrayLists in ascending order of card strength
	public void sortCards()
	{
		cpuCards.sort(new StrengthSorter());
		p1Cards.sort(new StrengthSorter());
	}

	//Loops through all cards in each ArrayList and returns the strongest available card
	public AnimalCard getStrongestCard()
	{
		int finalElementIndexCPU = cpuCards.size()-1;
		int finalElementIndexP1 = p1Cards.size()-1;
		AnimalCard strongestCard = null;
		if(cpuCards.isEmpty() || p1Cards.isEmpty())
			return strongestCard = null;
		else
		{
			strongestCard = (cpuCards.get(finalElementIndexCPU).getFinalStrength() > p1Cards.get(finalElementIndexP1).getFinalStrength()) ? cpuCards.get(finalElementIndexCPU) : p1Cards.get(finalElementIndexP1);
			ownerOfStrongest = strongestCard.getOwner();
			return strongestCard;
		}
	}
	
	/*
	 * Iterates through the ArrayList that doesn't correspond with the owner of the strongest card.
	 * If their exist prey for the card in that ArrayList.
	 * 		it returns one of the preys for removal.
	 * if their exist no prey for the strongest card chosen.
	 * It recursively calls this method after removing that strongest card from each iteration.
	 * if it does not contain prey, until all possible cards run out or a victor is reached.
	 * The log is updated accordingly.
	 */
	public Card getVictim(AnimalCard strongestCard)
	{
		ArrayList<AnimalCard> potentialVictims = new ArrayList<>();
		ArrayList<AnimalCard> preyCheck = null;
		Card cardForRemoval = null;
		if(strongestCard!=null)
		{
			if(ownerOfStrongest.equals("CPU"))
				preyCheck = p1Cards;
			else
				preyCheck = cpuCards;
			for(AnimalCard ac : preyCheck)
			{
				if(strongestCard.getPrey().contains(ac.getCardName()))
					potentialVictims.add(ac);
			}
			if(potentialVictims.size()!=0)
			{
				potentialVictims.sort(new StrengthSorter());
				cardForRemoval = potentialVictims.get(potentialVictims.size()-1);
				log.addEngageText(strongestCard, cardForRemoval);
				return cardForRemoval;
			} 
			else
			{
				if(ownerOfStrongest.equals("CPU"))
				{
					cpuCards.remove(strongestCard);
					return getVictim(getStrongestCard());
				}
				else
				{
					p1Cards.remove(strongestCard);
					return getVictim(getStrongestCard());
				}
			}
		}
		else
			return null;
	}
}
