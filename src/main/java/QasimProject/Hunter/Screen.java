package QasimProject.Hunter;

import java.io.FileNotFoundException;

import QasimProject.Hunter.Card.CardController;
import QasimProject.Hunter.MainPrompt.MainPromptController;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import QasimProject.Hunter.PlayField.PlayField;

public class Screen {
	
	private PlayField playFieldController;
	private CardController cardController;
	private PlaceholderController placeholderController;
	private MainPromptController promptController;
	private CardController cardController2;
	
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

	public void refreshStaticBackground() throws FileNotFoundException
	{
		playFieldController.startDisplay();
		playFieldController.refreshBackground();
		placeholderController.initialiseEmptyPlaceholders();
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
		cardController2.initialiseCard();
		cardController2.addDragableCard();
	}
}
