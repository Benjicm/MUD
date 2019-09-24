
public class Command {

	public static final int GET = 0;
	public static final int DROP = 1;
	public static final int MOVE = 2;
	public static final String NORTH = "N";
	public static final String NORTHEAST = "NE";
	public static final String EAST = "E";
	public static final String SOUTHEAST = "SE";
	public static final String SOUTH = "S";
	public static final String SOUTHWEST = "SW";
	public static final String WEST = "W";
	public static final String NORTHWEST = "NW";
	public static final String UP = "U";
	public static final String DOWN = "D";
	public static final String OTHER = "O";
	
	int action; // the action to be carried out, either getting an item (GET), dropping an item (DROP), or moving in a direction (MOVE).
	
	int charID; // the ID of the character this command was intended for.
	
	String param; // the parameter for the action. Either a direction, if the action is to move, or the name of the item, if the action is picking up or dropping an item.

	
	
	
	
	
	// Methods
	
	// returns action
	public int getAction() {
		return action; 
	}
	
	// returns charID
	public int getCharID() {
		return charID;
	} 
	
	// returns param
	public String getParam() {
		return param;
	}
	
	
}
