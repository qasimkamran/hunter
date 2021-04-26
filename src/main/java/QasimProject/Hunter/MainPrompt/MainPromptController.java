package QasimProject.Hunter.MainPrompt;

import java.io.FileNotFoundException;

import QasimProject.Hunter.Engage;
import QasimProject.Hunter.Log;
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
	private Screen gameScreen;
	private Log log;
	
	public MainPromptController(MainPrompt prompt, MainPromptDisplay promptDisplay, Log log, Screen screen)
	{
		this.prompt = prompt;
		this.promptDisplay = promptDisplay;
		this.log = log;
		this.gameScreen = screen;
	}
	
	private EventHandler<MouseEvent> nextEventHandler = new EventHandler<MouseEvent>() 
	{ 
		   @Override 
		   public void handle(MouseEvent e)
		   { 
			   prompt.getTurnCounter().incrementPhaseCount();;
			   promptDisplay.emptyPreviousArrow();
			   try {
				   gameScreen.refreshStaticBackground();
				   initialisePrompt();
				   addClickableArrow();
				   if(prompt.getTurnCounter().getPhase().equals("Engage"))
				   {
					   Engage engage = new Engage(log);
					   gameScreen.setEngage(engage);
					   gameScreen.engage();
				   }
				   gameScreen.refreshCard();
				   gameScreen.refreshCPUCard();
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
		promptDisplay.displayNextArrow(prompt.getNextRectangle(prompt.getDisplayMessageBoundW()), prompt.getNextArrow());
	}
	
	public void addClickableArrow()
	{
		prompt.addClick(nextEventHandler);
	}
	
}
