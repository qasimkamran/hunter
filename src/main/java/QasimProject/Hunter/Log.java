package QasimProject.Hunter;

import QasimProject.Hunter.Card.Card;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Log {

	private TextArea gameLog;
	private String layoutBlankSpace = "    ";
	
	public Log(String openingMessage)
	{
		gameLog = new TextArea(layoutBlankSpace+openingMessage);
		setGameLogBackground();
		setGameLogDimensions();
		setGameLogPosition();
		setToReadOnly();
	}
	
	public void addToRoot(Pane root)
	{
		if(!root.getChildren().contains(gameLog))
			root.getChildren().add(gameLog);
	}
	
	public void setToReadOnly()
	{
		gameLog.setEditable(false);
	}
	
	public void addRemoveText(Card card)
	{
		String result = card.getCardName() + " has been removed from play!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	public void addDrawText()
	{
		gameLog.appendText("\n"+layoutBlankSpace+"Both players are dealt three cards.");
	}
	
	public void addSetText(Card card)
	{
		String result = card.getOwner() + " has set " + card.getCardName() + " to the field!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	public void addEquipText(Card animalCard, Card equipCard)
	{
		String result = animalCard.getOwner() + " has equipped " + equipCard.getCardName() + " to " + animalCard.getCardName() + "!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
	}
	
	public void addEngageText(Card victor, Card loser)
	{
		String result = victor.getCardName() + " has engaged with " + loser.getCardName() + " and won!";
		gameLog.appendText("\n"+layoutBlankSpace+result);
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
}
