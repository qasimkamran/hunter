package QasimProject.Hunter.Placeholders;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Placeholder {
	public static final double CARD_HEIGHT = (0.26 * 720);
	public static final double CARD_WIDTH = (0.115 * 1280);
	public static final double XPOS_START_CPU = (640 + ((0.025*1280) + (CARD_WIDTH / 2)));
	public static final double XPOS_START_P1 = (640 - (CARD_WIDTH + (0.025*1280) + (CARD_WIDTH / 2)));
	public abstract Rectangle getPlaceholderRectangle();
	public abstract boolean isVacant();
	public abstract boolean setVacancy(boolean vacancy);
	public abstract void setCollisionDetectionRectangle();
	public abstract Rectangle getCollisionDetectionRectangle();
}
