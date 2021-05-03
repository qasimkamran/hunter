package QasimProject.Hunter;

/*
 * This class defines all constants used through the applications as static fields
 * These fields are constants that will remain the same through all instances of the game
 */

public class Constants {
	
	public static final double CARD_HEIGHT = (0.26 * 720);
	
	public static final double CARD_WIDTH = (0.115 * 1280);
	
	public static final double HAND_YPOS_P1 =  720 - (CARD_HEIGHT/2);

	public static final double HAND_YPOS_CPU =  0 - (CARD_HEIGHT/2);

	public static final double HAND_XPOS_LEFTMOST = 640 - (CARD_WIDTH + (0.025*1280) + (CARD_WIDTH / 2));
	
	public static final double HAND_XPOS_MIDDLE = 640 - (CARD_WIDTH/2);
	
	public static final double HAND_XPOS_RIGHTMOST = 640 + ((0.025*1280) + (CARD_WIDTH / 2));
	
	public static final double XPOS_START_CPU = (640 + ((0.025*1280) + (CARD_WIDTH / 2)));
	
	public static final double XPOS_START_P1 = (640 - (CARD_WIDTH + (0.025*1280) + (CARD_WIDTH / 2)));
	
	public static final double SPACING = 0.14 * 1280;
	
	public static final String ABSOLUTE_PATH = System.getProperty("user.dir")+"/src/main/java/QasimProject/Hunter/GameAssets/";
}
