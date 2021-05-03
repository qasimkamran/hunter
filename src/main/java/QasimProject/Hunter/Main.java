package QasimProject.Hunter;

import javafx.application.Application;
import javafx.event.EventHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import QasimProject.Hunter.Card.AnimalCard;
import QasimProject.Hunter.Card.Card;
import QasimProject.Hunter.Card.CardController;
import QasimProject.Hunter.Card.CardDisplay;
import QasimProject.Hunter.Card.CardFactory;
import QasimProject.Hunter.Card.EquipCard;
import QasimProject.Hunter.Card.FaceDownCard;
import QasimProject.Hunter.Graveyard.Graveyard;
import QasimProject.Hunter.Graveyard.GraveyardController;
import QasimProject.Hunter.Graveyard.GraveyardDisplay;
import QasimProject.Hunter.MainPrompt.MainPrompt;
import QasimProject.Hunter.MainPrompt.MainPromptController;
import QasimProject.Hunter.MainPrompt.MainPromptDisplay;
import QasimProject.Hunter.Placeholders.Placeholder;
import QasimProject.Hunter.Placeholders.PlaceholderController;
import QasimProject.Hunter.Placeholders.PlaceholderDisplay;
import QasimProject.Hunter.Placeholders.PlaceholderFactory;
import QasimProject.Hunter.PlayField.Biome;
import QasimProject.Hunter.PlayField.PlayField;
import QasimProject.Hunter.PlayField.PlayFieldDisplay;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Pane root = new Pane();
	private Pane root2 = new Pane();
	private Pane root3 = new Pane();
	
	private Scene scene = new Scene(root, 1280, 720);
	private Scene scene2 = new Scene(root2, 1280, 720);
	private Scene scene3 = new Scene(root3, 1280, 720);
	
	private Canvas canvas = new Canvas(1280, 720);
	
	private Screen screen = new Screen();
	
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	
	private Biome biome = new Biome();
	private PlayFieldDisplay pfd = new PlayFieldDisplay(biome, gc);
	private PlayField playFieldController = new PlayField(biome, pfd);
	
	private TurnCounter turnCounter = new TurnCounter();
	
	private Log log = new Log("Welcome to hunter!");
	
	private PlaceholderFactory placeholderFactoryCPU = new PlaceholderFactory(gc, "CPU");
	private PlaceholderFactory placeholderFactoryP1 = new PlaceholderFactory(gc, "P1");
	
	private PlaceholderDisplay placeholderDisplay = new PlaceholderDisplay(root);
	private PlaceholderController placeholderController = new PlaceholderController(placeholderFactoryCPU, placeholderFactoryP1, placeholderDisplay);
	
	private MainPrompt prompt = new MainPrompt(gc, turnCounter);
	private MainPromptDisplay promptDisplay = new MainPromptDisplay(root, prompt);
	private MainPromptController promptController = new MainPromptController(prompt, promptDisplay, log, screen);
	
	private CardFactory cardFactoryP1 = new CardFactory(gc, "P1");
	private CardFactory cardFactoryCPU = new CardFactory(gc, "CPU");
	
	private Card p1Card = cardFactoryP1.initialiseCard("Animal", "BabyDeer", 1);
	private Card p1Card2 = cardFactoryP1.initialiseCard("Equip", "LargeWingSpan", 2);
	private Card p1Card3 = cardFactoryP1.initialiseCard("Animal", "SnowyOwl", 3);
	
	private Card cpuCard = cardFactoryCPU.initialiseCard("Facedown", "", 1);
	private Card cpuCard2 = cardFactoryCPU.initialiseCard("Animal", "ArcticFox", 2);
	private Card cpuCard3 = cardFactoryCPU.initialiseCard("Facedown", "", 3);
	
	private Hand p1Hand = new Hand("P1", p1Card, p1Card2, p1Card3);
	private Hand cpuHand = new Hand("CPU", cpuCard, cpuCard2, cpuCard3);
	
	private CardDisplay cardDisplay = new CardDisplay(root, p1Hand);
	private CardController cardController = new CardController(p1Hand, cardDisplay, log, turnCounter, screen);
	
	private CardDisplay cardDisplay2 = new CardDisplay(root, cpuHand);
	private CardController cardController2 = new CardController(cpuHand, cardDisplay2, log, turnCounter, screen);
	
	private Graveyard graveyardP1 = new Graveyard(gc, 0, 0, "P1");
	private Graveyard graveyardCPU = new Graveyard(gc, 0, 0, "CPU");
	
	private GraveyardDisplay graveyardDisplay = new GraveyardDisplay(graveyardP1, root);
	private GraveyardController graveyardController = new GraveyardController(graveyardP1, graveyardDisplay);
	
	private GraveyardDisplay graveyardDisplay2 = new GraveyardDisplay(graveyardCPU, root);
	private GraveyardController graveyardController2 = new GraveyardController(graveyardCPU, graveyardDisplay2);
	
	public static void main(String[] args) 
	{
		launch(args);
	}  
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		screen.setCardController(cardController);
		screen.setCardController2(cardController2);
		screen.setPlaceholderController(placeholderController);
		screen.setPlayFieldController(playFieldController);
		screen.setPromptController(promptController);
		screen.setGraveyardController(graveyardController);
		screen.setGraveyardController2(graveyardController2);
				
		MainMenu mainScreen = new MainMenu(root2, primaryStage, scene, scene3);	
		
		Tutorial howToPlay = new Tutorial(root3, primaryStage, scene2);	
		
		primaryStage.setScene(scene2);
		
		primaryStage.setTitle("Hunter");
		
		primaryStage.show();
		
		root.getChildren().add(canvas);
			
		mainScreen.setBackground();
		
		mainScreen.setButtons();
		
		mainScreen.setClickEvents();
		
		howToPlay.setBaackground();
		
		howToPlay.setBackButton();
		
		howToPlay.setText();
		
		cardController.readCardInfo();
		
		cardController2.readCardInfo();
		
		playFieldController.startDisplay();
		
		playFieldController.initialiseBackground();
		
		placeholderController.initialisePlaceholderList();
		
		placeholderController.initialiseEmptyPlaceholders();
		
		promptController.initialisePrompt();
		
		promptController.addClickableArrow();
		
		cardController.initialiseCard();
		
		cardController.addDragableCard();
		
		cardController.setFieldCard(p1Card, 1, "P1", 0);
		
		cardController2.initialiseCard();
		
		cardController2.setFieldCard(cpuCard2, 1, "CPU", 0);
		
		graveyardController.initialiseGraveyard();
		
		graveyardController2.initialiseGraveyard();
		
		log.addToRoot(root);
	}

}
