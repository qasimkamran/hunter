package QasimProject.Hunter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tutorial {

	private Pane root;
	
	private Scene mainMenu;
	
	private Stage primaryStage;
	
	private TextArea text = new TextArea();
	
	private Button backButton = new Button();;
	
	private FileInputStream imageInputStream;
		
	public Tutorial(Pane root, Stage primaryStage, Scene mainMenu)
	{
		this.root = root;
		this.primaryStage = primaryStage;
		this.mainMenu = mainMenu;
	}
	
	private EventHandler<MouseEvent> backClickEvent = new EventHandler<MouseEvent>() 
	{
		@Override
		public void handle(MouseEvent e) 
		{
			primaryStage.close();
			primaryStage.setScene(mainMenu);
			primaryStage.show();
		}
	};
	
	public void setBaackground()
	{
		Rectangle rect = new Rectangle(0, 0, 1280, 720);
		rect.setFill(Color.rgb(128, 128, 128));
		if(!root.getChildren().contains(rect))
			root.getChildren().add(rect);
	}
	
	public void setBackButton()
	{
		BackgroundImage image = new BackgroundImage(MainMenu.menuSelection, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(image);
		backButton.setText("Back");
		backButton.setLayoutX(30);
		backButton.setLayoutY(600);
		backButton.setMinHeight(70);
		backButton.setBackground(background);
		backButton.setFont(new Font(24));
		backButton.setTextFill(Color.WHITE);
		backButton.setPadding(new Insets(30));
		if(!root.getChildren().contains(backButton))
			root.getChildren().add(backButton);
		backButton.setOnMouseClicked(backClickEvent);
	}
	
	public void setText()
	{
		String textMessage = "Start a game first. \n\n"
							+ "You can go to the next step of your turn by clicking the green triangle.\n\n"
							+ "When the prompt tells you to, set an animal card onto an empty place. \n\n"
							+ "Go to the next step (the green triangle). \n\n"
							+ "Once you are told to, you can put an equip card on any animal to strengthen it. \n\n"
							+ "Berries can be an equip card to a fox.\n\n"
							+ "Go to the next step. \n\n"
							+ "Your animals will interact with each other when the prompt says engage. \n\n"
							+ "When this is done you can see the updates in the top left corner. \n\n"
							+ "Go and hit next to see the result of your turn. \n\n"
							+ "Be careful of which card you set and which ones you equip, think about the food chain!";
		text.setFont(new Font(24));
		text.setText(textMessage);
		text.setLayoutX(30);
		text.setLayoutY(20);
		text.setOpacity(0.5);
		text.setMinHeight(550);
		text.setEditable(false);
		if(!root.getChildren().contains(text))
			root.getChildren().add(text);
	}
}
