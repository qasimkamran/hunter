package QasimProject.Hunter.MainPrompt;

import java.io.FileNotFoundException;

import QasimProject.Hunter.Screen;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainPromptController {
	
	private MainPrompt prompt;
	private MainPromptDisplay promptDisplay;
	private Screen screen;
	
	public MainPromptController(MainPrompt prompt, MainPromptDisplay promptDisplay, Screen screen)
	{
		this.prompt = prompt;
		this.promptDisplay = promptDisplay;
		this.screen = screen;
	}
	
	private EventHandler<MouseEvent> nextEventHandler = new EventHandler<MouseEvent>() 
	{ 
		   @Override 
		   public void handle(MouseEvent e)
		   { 
			   prompt.getTurnCounter().incrementPhaseCount();;
			   try {
				   //promptDisplay.emptyPreviousRectangle();
				   promptDisplay.emptyPreviousArrow();
				   screen.refreshStaticBackground();
				   initialisePrompt();
				   addClickableArrow();
				   screen.refreshCard();
				} 
			   catch (FileNotFoundException e1) {
				   e1.printStackTrace();
				   }
		   }
	};
	
	public void initialisePrompt() throws FileNotFoundException
	{
		prompt.getTurnCounter().setPhase();
		promptDisplay.displayMainPrompt();
		//promptDisplay.displayPromptText(prompt.getDisplayMessage());
		promptDisplay.displayNextArrow(prompt.getNextRectangle(prompt.getDisplayMessageBoundW()), prompt.getNextArrow());
	}
	
	public void addClickableArrow()
	{
		prompt.addClick(nextEventHandler);
	}
	
	
}
