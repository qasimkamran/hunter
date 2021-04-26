package QasimProject.Hunter;

import java.io.FileNotFoundException;

import QasimProject.Hunter.Card.Card;
import QasimProject.Hunter.Card.CardController;
import QasimProject.Hunter.Graveyard.GraveyardController;
import QasimProject.Hunter.MainPrompt.MainPromptController;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import QasimProject.Hunter.PlayField.PlayField;

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
	
	public void refreshStaticBackground() throws FileNotFoundException
	{
		playFieldController.startDisplay();
		playFieldController.refreshBackground();
		placeholderController.initialiseEmptyPlaceholders();
		graveyardController.initialiseGraveyard();
		graveyardController2.initialiseGraveyard();
	}
	
	public void refreshPrompt() throws FileNotFoundException
	{
		promptController.initialisePrompt();
		promptController.addClickableArrow();
	}
	
	public void refreshCard() throws FileNotFoundException
	{
		cardController.initialiseCard();
		cardController.addDragableCard();
	}
	
	public void refreshCPUCard() throws FileNotFoundException
	{
		cardController2.initialiseCard();
	}
	
	public void engage()
	{
		Card cardForRemoval  = engage.getVictim(engage.getStrongestCard());
		if(cardForRemoval!=null)
			if(cardForRemoval.getOwner().equals("CPU"))
				cardController2.removeCard(cardForRemoval);
			else
				cardController.removeCard(cardForRemoval);
	}
}
