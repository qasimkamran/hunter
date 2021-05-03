package QasimProject.Hunter;

import QasimProject.Hunter.Card.Card;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/*
 * models a log of all activity in the game through a text area object
 */

public class Log {

	private TextArea gameLog;
	private String layoutBlankSpace = "    ";
	
	public Log(String openingMessage)
	{
		gameLog = new TextArea(layoutBlankSpace+openingMessage);
		addDrawText();
		setGameLogBackground();
		setGameLogDimensions();
		setGameLogPosition();
		setToReadOnly();
	}
	
	//adds the text area object to the pane
	public void addToRoot(Pane root)
	{
		if(!root.getChildren().contains(gameLog))
			root.getChildren().add(gameLog);
	}
	
	//the log must not be editable by the user
	public void setToReadOnly()
	{
		gameLog.setEditable(false);
	}
	
	//is called when a card is removed from play
	public void addRemoveText(Card card)
	{
		String result = card.getCardName() + " has been removed from play!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	//is called when cards are dealt in the draw phase
	public void addDrawText()
	{
		gameLog.appendText("\n"+layoutBlankSpace+"Both players are dealt three cards.");
	}
	
	//is called when either player sets a card into a placeholder 
	public void addSetText(Card card)
	{
		String result = card.getOwner() + " has set " + card.getCardName() + " to the field!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	//is called when either player equips a card to another set card
	public void addEquipText(Card animalCard, Card equipCard)
	{
		String result = animalCard.getOwner() + " has equipped " + equipCard.getCardName() + " to " + animalCard.getCardName() + "!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	//is called when a victor has been decided through an engagement
	public void addEngageText(Card victor, Card loser)
	{
		String result = victor.getCardName() + " has engaged with " + loser.getCardName() + " and won!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	//is called when there has been no victor decided in an engagement step
	public void addNoEngagementText() 
	{
		gameLog.appendText("\n"+layoutBlankSpace+"No engagement between any of the animals.");
	}
	
	public TextArea getGameLog()
	{
		return gameLog;
	}
	
	public void setGameLogPosition()
	{
		gameLog.setLayoutX(-30);
		gameLog.setLayoutY(20);
	}
	
	public void setGameLogDimensions()
	{
		gameLog.setMinHeight(200);
		gameLog.setMinWidth(395);
	}
	
	public void setGameLogBackground()
	{
		gameLog.setStyle("-fx-control-inner-background:#D3D3D3; -fx-font-family: Consolas; -fx-font-size: 15; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #000000; ");
		gameLog.setOpacity(0.6);
	}

	public void addFatigue(Card card, int fatigueFactor) 
	{
		String severence =  " ";
		if(fatigueFactor > 4)
			severence = " severely ";
		String result = card.getCardName() + " is"+severence+"fatigued!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
}
