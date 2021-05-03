package QasimProject.Hunter;

import java.io.FileNotFoundException;

import QasimProject.Hunter.Card.Card;
import QasimProject.Hunter.Card.CardController;
import QasimProject.Hunter.Graveyard.GraveyardController;
import QasimProject.Hunter.MainPrompt.MainPromptController;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import QasimProject.Hunter.PlayField.PlayField;

/*
 * models the refresh of drawable objects on any one update.
 * it also acts as a hub giving access to some controllers through it.
 */

public class Screen {
	
	private PlayField playFieldController;
	private CardController cardController;
	private PlaceholderController placeholderController;
	private MainPromptController promptController;
	private CardController cardController2;
	private GraveyardController graveyardController;
	private GraveyardController graveyardController2;
	private Engage engage;
	
	public Screen()
	{
		
	}
	
	public void setPlayFieldController(PlayField playFieldController) {
		this.playFieldController = playFieldController;
	}

	public void setCardController(CardController cardController) {
		this.cardController = cardController;
	}

	public void setPlaceholderController(PlaceholderController placeholderController) {
		this.placeholderController = placeholderController;
	}

	public void setPromptController(MainPromptController promptController) {
		this.promptController = promptController;
	}

	public void setCardController2(CardController cardController2) {
		this.cardController2 = cardController2;
	}

	public void setGraveyardController(GraveyardController graveyardController){
		this.graveyardController = graveyardController;
	}
	
	public void setGraveyardController2(GraveyardController graveyardController2){
		this.graveyardController2 = graveyardController2;
	}
	
	public void setEngage(Engage engage){
		this.engage = engage;
	}
	
	//refreshes all drawable objects that remain static after the first initialisation
	public void refreshStaticBackground() throws FileNotFoundException
	{
		playFieldController.startDisplay();
		playFieldController.refreshBackground();
		placeholderController.initialiseEmptyPlaceholders();
		graveyardController.initialiseGraveyard();
		graveyardController2.initialiseGraveyard();
	}
	
	//refreshes the prompt and clickable arrow involved with each message
	public void refreshPrompt() throws FileNotFoundException
	{
		promptController.initialisePrompt();
		promptController.addClickableArrow();
	}
	
	//refreshes all p1 cards
	public void refreshCard() throws FileNotFoundException
	{
		cardController.initialiseCard();
		cardController.addDragableCard();
	}
	
	//refreshes all cpu cards
	public void refreshCPUCard() throws FileNotFoundException
	{
		cardController2.initialiseCard();
	}
	
	public void incrementGraveyard()
	{
		graveyardController.incrementGraveCounter();
	}
	
	public void incrementGraveyard2()
	{
		graveyardController2.incrementGraveCounter();
	}
	
	//calls remove card from card controller based on the result of an engagement
	//updates the grave counter on the corresponding side of the field
	public void engage()
	{
		Card cardForRemoval  = engage.getVictim(engage.getStrongestCard());
		if(cardForRemoval!=null)
			if(cardForRemoval.getOwner().equals("CPU"))
			{
				graveyardController2.incrementGraveCounter();
				cardController2.removeCard(cardForRemoval);	
			}
			else
			{
				graveyardController.incrementGraveCounter();
				cardController.removeCard(cardForRemoval);		
			}
		else
			engage.noEngagement();
	}

	//returns true if win condition has been met and false otherwise
	public boolean getWinCondition()
	{
		if(graveyardController2.getCount()>0)
			return true;
		else
			return false;
	}
}
