package QasimProject.Hunter.MainPrompt;

import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainPromptController {
	
	private MainPrompt prompt;
	private MainPromptDisplay promptDisplay;
	
	public MainPromptController(MainPrompt prompt, MainPromptDisplay promptDisplay)
	{
		this.prompt = prompt;
		this.promptDisplay = promptDisplay;
	}
	
	private EventHandler<MouseEvent> nextEventHandler = new EventHandler<MouseEvent>() 
	{ 
		   @Override 
		   public void handle(MouseEvent e)
		   { 
			   prompt.getTurnCounter().incrementPhaseCount();;
			   try {
				   promptDisplay.emptyPreviousRectangle();
				   initialisePrompt();
				   addClickableArrow();
				} 
			   catch (FileNotFoundException e1) {
				   e1.printStackTrace();
				   }
		   }
	};
	
	public void initialisePrompt() throws FileNotFoundException
	{
		prompt.getTurnCounter().setPhase();
		prompt.outputMessage();
		promptDisplay.displayMainPrompt(prompt.getPromptRectangle(prompt.getDisplayMessageBoundW()));
		promptDisplay.displayPromptText(prompt.getDisplayMessage());
		promptDisplay.displayNextArrow(prompt.getNextRectangle(prompt.getDisplayMessageBoundW()), prompt.getNextArrow());
	}
	
	public void addClickableArrow()
	{
		prompt.addClick(nextEventHandler);
	}
}
