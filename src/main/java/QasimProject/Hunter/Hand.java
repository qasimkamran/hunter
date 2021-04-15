package QasimProject.Hunter;

import QasimProject.Hunter.Placeholders.Placeholder;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;

public class Hand {
	
	private String owner;
	private int length = 3;
	
	private final double HAND_YPOS_P1 =  720 - (Placeholder.CARD_HEIGHT/2);
	private final double HAND_YPOS_CPU =  0 - (Placeholder.CARD_HEIGHT/2);
	private Point leftMost, middle, rightMost;

	public Hand(String owner)
	{
		this.owner = owner;
		if(owner.equals("P1"))
		{
			this.leftMost = new Point(640 - (Placeholder.CARD_WIDTH + (0.025*1280) + (Placeholder.CARD_WIDTH / 2)), HAND_YPOS_P1, 0, null);
			this.middle = new Point(640 - (Placeholder.CARD_WIDTH/2), HAND_YPOS_P1, 0,null);
			this.rightMost = new Point(640 + ((0.025*1280) + (Placeholder.CARD_WIDTH / 2)), HAND_YPOS_P1, 0, null);
		}
		else
		{
			this.leftMost = new Point(640 - (Placeholder.CARD_WIDTH + (0.025*1280) + (Placeholder.CARD_WIDTH / 2)), HAND_YPOS_CPU, 0, null);
			this.middle = new Point(640 - (Placeholder.CARD_WIDTH/2), HAND_YPOS_CPU, 0,null);
			this.rightMost = new Point(640 + ((0.025*1280) + (Placeholder.CARD_WIDTH / 2)), HAND_YPOS_CPU, 0, null);	
		}
	}
	
	public void remove()
	{
		length--;
	}
	
	public void draw()
	{
		length++;
	}

	public double getHAND_YPOS_P1() {
		return HAND_YPOS_P1;
	}

	public double getHAND_YPOS_CPU() {
		return HAND_YPOS_CPU;
	}
	
	public String getOwner() {
		return owner;
	}

	public int getLength() {
		return length;
	}
	
	public Point getLeftMost() {
		return leftMost;
	}

	public Point getMiddle() {
		return middle;
	}

	public Point getRightMost() {
		return rightMost;
	}
}
