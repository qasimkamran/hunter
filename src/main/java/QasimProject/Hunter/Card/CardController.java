package QasimProject.Hunter.Card;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import QasimProject.Hunter.TurnCounter;
import QasimProject.Hunter.Placeholders.Placeholder;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardController {

	private ArrayList<Card> cards = new ArrayList<>();
	private CardDisplay cardDisplay;
	private TurnCounter turnCounter;

	private Point mousePosInitial = new Point(0, 0, 0, null);
	private Point mousePosFinal = new Point(0, 0, 0, null);

	public CardController(Card card, Card card2, Card card3, CardDisplay cardDisplay, TurnCounter turnCounter) 
	{
		this.cards.add(card);
		this.cards.add(card2);
		this.cards.add(card3);
		this.cardDisplay = cardDisplay;
		this.turnCounter = turnCounter;
	}

	private EventHandler<MouseEvent> cardPressEventHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			mousePosInitial.setX(e.getX());
			mousePosInitial.setY(e.getY());
		}
	};

	private EventHandler<MouseEvent> cardDragEventHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			mousePosFinal.setX(e.getSceneX());
			mousePosFinal.setY(e.getSceneY());

			double deltaX = mousePosFinal.getX() - mousePosInitial.getX();
			double deltaY = mousePosFinal.getY() - mousePosInitial.getY();

			boolean draggable = ((turnCounter.getPhase().equals("Set") || turnCounter.getPhase().equals("Stack")) ? true : false);
			
			for (Card card : cards) 
				if (card.getCardRectangle().getLayoutBounds().contains(new Point2D(e.getX(), e.getY())) && draggable && card.getOwner().equals("P1")) 
					cardDisplay.displayCard(card.getCardRectangle(deltaX, deltaY));
		}
	};

	private EventHandler<MouseEvent> cardReleaseEventHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			boolean isInPlaceHolder = false;
			for (Card card : cards) 
			{
				if(card.getCardRectangle().getLayoutBounds().contains(new Point2D(e.getX(), e.getY()))) 
				{
					for (Placeholder p : PlaceholderController.phAPRectList)
						if (card.getCardRectangle().getBoundsInParent().intersects(p.getCollisionDetectionRectangle().getBoundsInLocal()))
						{
							cardDisplay.displayCard(card.getCardRectangle(
									p.getPlaceholderRectangle().getX() - card.getCardRectangle().getX(),
									p.getPlaceholderRectangle().getY() - card.getCardRectangle().getY()));
							System.out.println("True");
							isInPlaceHolder = true;
							p.setVacancy(!isInPlaceHolder);
							break;
						}
					if(!isInPlaceHolder) 
					{
						System.out.println("False");
						card.snapToOrigin();
						cardDisplay.displayCard(card.getCardRectangle());
					}
				}
			}
		}
	};

	public void initialiseCard() throws FileNotFoundException 
	{
		for (int i = 0; i < cards.size(); i++) 
		{
			cards.get(i).setBackgroundImage();
			cardDisplay.displayCard(cards.get(i).getCardRectangle(), cards.get(i).getBackgroundImage());
		}
	}

	public void addDragableCard() 
	{
		for (Card card : cards)
			card.addDrag(card.getCardRectangle(), cardPressEventHandler, cardDragEventHandler, cardReleaseEventHandler);
	}
}
