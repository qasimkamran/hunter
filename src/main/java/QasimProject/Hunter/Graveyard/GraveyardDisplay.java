package QasimProject.Hunter.Graveyard;

import javafx.scene.layout.Pane;

public class GraveyardDisplay {

	private Graveyard graveyard;
	private Pane root;
	
	public GraveyardDisplay(Graveyard graveyard, Pane root)
	{
		this.graveyard = graveyard;
		this.root = root;
	}
	
	public void displayGraveyard()
	{
		if(!root.getChildren().contains(graveyard.getText()))
			root.getChildren().add(graveyard.getText());
		graveyard.update();
	}
}
