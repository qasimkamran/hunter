package QasimProject.Hunter.Card;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import QasimProject.Hunter.Hand;
import QasimProject.Hunter.Log;
import QasimProject.Hunter.Screen;
import QasimProject.Hunter.TurnCounter;
import QasimProject.Hunter.Placeholders.AnimalPlaceholder;
import QasimProject.Hunter.Placeholders.Placeholder;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CardController {

	private ArrayList<Card> cards = new ArrayList<>();
	private CardDisplay cardDisplay;
	private TurnCounter turnCounter;
	private Hand hand;
	private Log log;
	
	private Point mousePosInitial = new Point(0, 0, 0, null);
	private Point mousePosFinal = new Point(0, 0, 0, null);
	private Screen gameScreen;

	public CardController(Hand hand, CardDisplay cardDisplay, Log log, TurnCounter turnCounter, Screen gameScreen) 
	{
		this.hand = hand;
		this.cards = hand.getCards();
		this.cardDisplay = cardDisplay;
		this.log =  log;
		this.turnCounter = turnCounter;
		this.gameScreen = gameScreen;
	}

	/*
	 * click event that activates when a card is selected (clicked on)
	 * assigns the initial position of the mouse at that instance to the related fields.
	 */
	private EventHandler<MouseEvent> cardPressEventHandler = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			mousePosInitial.setX(e.getX());
			mousePosInitial.setY(e.getY());
			System.out.println("Clicked");
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
				if (card.getCardRectangle().getLayoutBounds().contains(new Point2D(e.getX(), e.getY())) && ((turnCounter.getPhase().equals("Set") && card.getCardType().equals("Animal")) || (turnCounter.getPhase().equals("Stack") && card.getCardType().equals("Equip"))) && card.getOwner().equals("P1")) 
				{
					try {
						gameScreen.refreshStaticBackground();
						gameScreen.refreshPrompt();
						gameScreen.refreshCPUCard();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					card.getCardRectangle(deltaX, deltaY);
					card.getText(deltaX, deltaY);
					card.setImageTranslation(card.getCardRectangle().getX() + deltaX, card.getCardRectangle().getY() + deltaY);
					cardDisplay.displayCard();
					try {
						gameScreen.refreshCard();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
				boolean snapable = (card.getCardType().equals("Animal")) ? true : false;
				
				if(card.getCardRectangle().getLayoutBounds().contains(new Point2D(e.getX(), e.getY()))) 
				{
					for (Placeholder p : PlaceholderController.placeholders)
						if (card.getCardRectangle().getBoundsInParent().intersects(p.getCollisionDetectionRectangle().getBoundsInLocal()) && snapable)
						{
							try {
								gameScreen.refreshStaticBackground();
								gameScreen.refreshPrompt();
								gameScreen.refreshCPUCard();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							card.setInPlay();
							card.setCard();
							card.getCardRectangle(
									p.getPlaceholderRectangle().getX() - card.getCardRectangle().getX(),
									p.getPlaceholderRectangle().getY() - card.getCardRectangle().getY());
							card.getText(
									p.getPlaceholderRectangle().getX() - card.getCardRectangle().getX(),
									p.getPlaceholderRectangle().getY() - card.getCardRectangle().getY());
							card.setImageTranslation(p.getPlaceholderRectangle().getX(),
									p.getPlaceholderRectangle().getY());
							log.addSetText(card);
							cardDisplay.displayCard();
							try {
								gameScreen.refreshCard();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							isInPlaceHolder = true;
							p.setVacancy(!isInPlaceHolder);
							if(!p.isVacant())
								p.setCard((AnimalCard)card);
							break;
						}
						else if(card.getCardRectangle().getBoundsInParent().intersects(p.getCollisionDetectionRectangle().getBoundsInLocal()) && card.getCardType().equals("Equip"))
						{
							EquipCard equip = (EquipCard) card;
							if(!p.isVacant())
							{
								if(p.getCard().getEquips().contains(equip.getCardName()))
								{
									p.getCard().addEquip(equip.getPositiveBonus());
									log.addEquipText(p.getCard(), equip);
									removeCard(card);
								}
								//cardDisplay.displayCard();
							}
						}
					if(!isInPlaceHolder) 
					{
						try {
							gameScreen.refreshStaticBackground();
							gameScreen.refreshPrompt();
							gameScreen.refreshCPUCard();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("False");
						card.setInHand();
						card.snapToOrigin();
						card.getCardRectangle();
						card.getText();
						card.setImageTranslation(card.getCardRectangle().getX(),
								card.getCardRectangle().getY());
						cardDisplay.displayCard();
						try {
							gameScreen.refreshCard();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		}
	};
	
	
	public void initialiseCard() throws FileNotFoundException 
	{
		for (int i=0; i<cards.size(); i++) 
		{
			if(cards.get(i).getCardImage()==null)
				cards.get(i).setCardImage();
			cardDisplay.displayCard();
		}
	}
	
	public void readCardInfo()
	{
		for(Card c : cards)
			c.readCardInfo();
	}

	public void addDragableCard() 
	{
		for (Card card : cards)
			card.addDrag(card.getCardRectangle(), cardPressEventHandler, cardDragEventHandler, cardReleaseEventHandler);
	}
	
	public void removeEventHandlers()
	{
		for(Card card : cards)
			card.removeDrag(card.getCardRectangle(), cardPressEventHandler, cardDragEventHandler, cardReleaseEventHandler);
	}
	
	public void displayEquipables()
	{
		ArrayList<Card> cardsInHand = new ArrayList<>();
		ArrayList<AnimalCard> cardsInPlay = new ArrayList<>();
		
		if(turnCounter.getPhase().equals("Equip"))
		{
			for(int i=0; i<cardsInHand.size(); i++)
				for(int j=0; j<cardsInPlay.size(); j++)
					if(cardsInPlay.get(j).getEquips().contains(cardsInHand.get(i).getCardName()))
					{
						System.out.println("Equip Available!");
					}
		}
	}
	
	public void removeCard(Card card)
	{
		for(Placeholder p : PlaceholderController.placeholders)
			if(p.getCard()!=null && p.getCard().equals(card))
				p.setCard(null);
		cardDisplay.removeRectangle(card.getCardRectangle());
		cardDisplay.removeText(card.getText());
		hand.removeFromHand(card);
	}
	
	public void setFieldCard(Card card, int zone, String owner, int fatigueFactor)
	{
		log.addSetText(card);
		if(fatigueFactor>0)
			log.addFatigue(card, fatigueFactor);
		Placeholder p = null;
		for(int i=0; i<PlaceholderController.placeholders.size(); i++)
			if(PlaceholderController.placeholders.get(i).getZone() == zone && PlaceholderController.placeholders.get(i).getOwner().equals(owner))
				p = PlaceholderController.placeholders.get(i);
		try {
			gameScreen.refreshStaticBackground();
			gameScreen.refreshPrompt();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		card.setInPlay();
		card.setCard();
		card.getCardRectangle(
				p.getPlaceholderRectangle().getX() - card.getCardRectangle().getX(),
				p.getPlaceholderRectangle().getY() - card.getCardRectangle().getY());
		card.getText(
				p.getPlaceholderRectangle().getX() - card.getCardRectangle().getX(),
				p.getPlaceholderRectangle().getY() - card.getCardRectangle().getY());
		card.setImageTranslation(p.getPlaceholderRectangle().getX(),
				p.getPlaceholderRectangle().getY());
		cardDisplay.displayCard();
		try {
			gameScreen.refreshCard();
			gameScreen.refreshCPUCard();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//isInPlaceHolder = true;
		p.setVacancy(false);
		if(!p.isVacant())
		{
			p.setCard((AnimalCard)card);		
			p.getCard().addFatigue(fatigueFactor);
		}
		//if(!p.isVacant())
			//p.setCard(card);
	}
}
