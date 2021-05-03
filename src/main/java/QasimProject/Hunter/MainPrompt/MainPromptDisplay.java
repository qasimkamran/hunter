package QasimProject.Hunter.MainPrompt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainPromptDisplay {

	private Pane root;
	private MainPrompt prompt;
	private int arrowIndex;
	
	public MainPromptDisplay(Pane root, MainPrompt prompt)
	{
		this.root = root;
		this.prompt = prompt;
	}
	
	public void displayMainPrompt()
	{
		/*xpos = rect.getX();
		
		rect.setStyle("-fx-fill: olive; -fx-stroke: springgreen; -fx-stroke-width: 5;");
		
		root.getChildren().add(rect);
		mainIndex = root.getChildren().size()-1;*/
		prompt.update();
	}
	
	/*public void displayPromptText(String displayMessage)
	{
		text = new Text();
		text.setFont(new Font(24));
		text.setText(displayMessage);
		text.setLayoutX(xpos +60);
		text.setLayoutY(368);
		text.setFill(Color.SPRINGGREEN);
		root.getChildren().add(text);
		textIndex = root.getChildren().size()-1;
	}*/
	
	public void displayNextArrow(Rectangle rect, Image next)
	{
		rect.setFill(Color.TRANSPARENT);
		root.getChildren().add(rect);
		arrowIndex = root.getChildren().size()-2;
	}
	
	public void emptyPreviousArrow()
	{
		root.getChildren().remove(arrowIndex-1);
	}
}
