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
	private Scene scene = new Scene(root, 1280, 720);
	private Canvas canvas = new Canvas(1280, 720);
	
	private Screen screen = new Screen();
	
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	
	private Biome biome = new Biome();
	private PlayFieldDisplay pfd = new PlayFieldDisplay(biome, gc);
	private PlayField playFieldController = new PlayField(biome, pfd);
	
	private TurnCounter turnCounter = new TurnCounter();
	
	private Log gameLog = new Log("Welcome to hunter!");
	
	private PlaceholderFactory factoryCPU = new PlaceholderFactory(gc, "CPU");
	private PlaceholderFactory factoryP1 = new PlaceholderFactory(gc, "P1");
	
	private PlaceholderDisplay placeholderDisplay = new PlaceholderDisplay(root);
	private PlaceholderController placeholderController = new PlaceholderController(factoryCPU, factoryP1, placeholderDisplay);
	
	private MainPrompt prompt = new MainPrompt(gc, turnCounter);
	private MainPromptDisplay promptDisplay = new MainPromptDisplay(root, prompt);
	private MainPromptController promptController = new MainPromptController(prompt, promptDisplay, gameLog, screen);
	
	private Card p1Card = new AnimalCard(gc, Constants.HAND_XPOS_LEFTMOST, Constants.HAND_YPOS_P1,"P1", "GoldenEagle");	
	private Card p1Card2 = new EquipCard(gc, Constants.HAND_XPOS_MIDDLE, Constants.HAND_YPOS_P1, "P1", "LargeWingSpan");
	private Card p1Card3 = new AnimalCard(gc, Constants.HAND_XPOS_RIGHTMOST, Constants.HAND_YPOS_P1, "P1", "SnowyOwl");
	
	private Card cpuCard = new FaceDownCard(gc, Constants.HAND_XPOS_LEFTMOST, Constants.HAND_YPOS_CPU, "CPU");
	private Card cpuCard2 = new AnimalCard(gc, Constants.HAND_XPOS_MIDDLE, Constants.HAND_YPOS_CPU, "CPU", "ArcticFox");
	private Card cpuCard3 = new FaceDownCard(gc, Constants.HAND_XPOS_RIGHTMOST, Constants.HAND_YPOS_CPU, "CPU");
	
	private Hand p1Hand = new Hand("P1", p1Card, p1Card2, p1Card3);
	private Hand cpuHand = new Hand("CPU", cpuCard, cpuCard2, cpuCard3);
	
	private CardDisplay cardDisplay = new CardDisplay(root, p1Hand);
	private CardController cardController = new CardController(p1Hand, cardDisplay, gameLog, turnCounter, screen);
	
	private CardDisplay cardDisplay2 = new CardDisplay(root, cpuHand);
	private CardController cardController2 = new CardController(cpuHand, cardDisplay2, gameLog, turnCounter, screen);
	
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
		
		p1Card.readCardInfo();
		p1Card.printInfo();
		p1Card2.readCardInfo();
		cpuCard2.readCardInfo();
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		root.getChildren().add(canvas);
		
		playFieldController.startDisplay();
		
		playFieldController.initialiseBackground();
		
		placeholderController.initialisePlaceholderList();
		
		placeholderController.initialiseEmptyPlaceholders();
		
		promptController.initialisePrompt();
		
		promptController.addClickableArrow();
		
		cardController.initialiseCard();
		
		cardController.addDragableCard();
		
		cardController2.initialiseCard();
		
		cardController2.setFieldCard(cpuCard2, 1);
		
		graveyardController.initialiseGraveyard();
		
		graveyardController2.initialiseGraveyard();
		
		gameLog.addToRoot(root);
	}

}
