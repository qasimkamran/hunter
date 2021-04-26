package QasimProject.Hunter.Card;

import java.util.ArrayList;

import QasimProject.Hunter.Hand;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CardDisplay {
	
	private Pane root;
	private ArrayList<Card> cards = new ArrayList<>();
	
	private int cardIndex;
	
	public CardDisplay(Pane root, Hand hand)
	{
		this.root = root;
		this.cards = hand.getCards();
	}
	
	public void displayCard()
	{
		for(Card c : cards)
		{	
			c.setText();
			c.getCardRectangle().setFill(Color.TRANSPARENT);
			if(!root.getChildren().contains(c.getCardRectangle()))
				root.getChildren().add(c.getCardRectangle());
			if(!root.getChildren().contains(c.getText()))
				root.getChildren().add(c.getText());
			cardIndex = root.getChildren().size()-1;
			c.update();
		}
	}
	
	public void removeRectangle(Rectangle rect)
	{
		root.getChildren().remove(rect);
	}
	
	public void removeText(Text text)
	{
		root.getChildren().remove(text);
	}
}
