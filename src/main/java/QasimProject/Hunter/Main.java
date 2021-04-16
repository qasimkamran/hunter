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
import QasimProject.Hunter.MainPrompt.MainPrompt;
import QasimProject.Hunter.MainPrompt.MainPromptController;
import QasimProject.Hunter.MainPrompt.MainPromptDisplay;
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
	
	private Hand p1Hand = new Hand("P1");
	private Hand cpuHand = new Hand("CPU");
	
	private PlaceholderFactory factoryCPU = new PlaceholderFactory("CPU");
	private PlaceholderFactory factoryP1 = new PlaceholderFactory("P1");
	private PlaceholderDisplay placeholderDisplay = new PlaceholderDisplay(canvas, root);
	private PlaceholderController placeholderController = new PlaceholderController(factoryCPU, factoryP1, placeholderDisplay);
	
	private MainPrompt prompt = new MainPrompt(gc, turnCounter);
	private MainPromptDisplay promptDisplay = new MainPromptDisplay(root, prompt);
	private MainPromptController promptController = new MainPromptController(prompt, promptDisplay, screen);
	
	private Card p1Card = new AnimalCard(p1Hand.getLeftMost().getX(), p1Hand.getHAND_YPOS_P1(),"P1", "Carnivore");
	private Card p1Card2 = new EquipCard(p1Hand.getMiddle().getX(), p1Hand.getHAND_YPOS_P1(), "P1");
	private Card p1Card3 = new AnimalCard(p1Hand.getRightMost().getX(), p1Hand.getHAND_YPOS_P1(), "P1", "Herbivore");
	
	private Card cpuCard = new FaceDownCard(cpuHand.getLeftMost().getX(), cpuHand.getHAND_YPOS_CPU(), "CPU");
	private Card cpuCard2 = new FaceDownCard(cpuHand.getMiddle().getX(), cpuHand.getHAND_YPOS_CPU(), "CPU");
	private Card cpuCard3 = new FaceDownCard(cpuHand.getRightMost().getX(), cpuHand.getHAND_YPOS_CPU(), "CPU");
	
	private CardDisplay cardDisplay = new CardDisplay(canvas, root);
	private CardController cardController = new CardController(p1Card, p1Card2, p1Card3, cardDisplay, turnCounter);
	private CardController cardController2 = new CardController(cpuCard, cpuCard2, cpuCard3, cardDisplay, turnCounter);
	
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
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		root.getChildren().add(canvas);
		
		playFieldController.startDisplay();
		
		playFieldController.initialiseBackground();
		
		placeholderController.initialiseEmptyPlaceholders();
		
		promptController.initialisePrompt();
		
		promptController.addClickableArrow();
		
		cardController.initialiseCard();
		
		cardController.addDragableCard();
		
		cardController2.initialiseCard();
		
		cardController2.addDragableCard();
	}

}
