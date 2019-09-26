import java.util.*;
public class GameMaster {

	RoomList rlist;
	ItemList ilist;
	CharList clist;
	boolean running = true;


	public GameMaster() {
		rlist = new RoomList();
		ilist = new ItemList();
		clist = new CharList();
	}

	public void setup() {
		// this reads in data from the text files provided in the SetupFiles folder
		rlist = SetupFileReader.setupRooms("SetupFiles" + SetupFileReader.fileSeparator + "Roomlist.txt");
		SetupFileReader.setupExits("SetupFiles" + SetupFileReader.fileSeparator + "Exitlist.txt", rlist);
		SetupFileReader.setupItems("SetupFiles" + SetupFileReader.fileSeparator + "Itemlist.txt", rlist);
		
		// the setup() method still needs something to initialize the player
	}
	private String getExitDesc(String dir)
	{
		if(dir.equals(Exit.NORTH))
		{
			return "There is an exit to the north.\n";
		}
		else if(dir.equals(Exit.NORTHEAST))
		{
			return "There is an exit to the northeast.\n";
		}
		else if(dir.equals(Exit.EAST))
		{
			return "There is an exit to the east.\n";
		}
		else if(dir.equals(Exit.SOUTHEAST))
		{
			return "There is an exit to the southeast.\n";
		}
		else if(dir.equals(Exit.SOUTH))
		{
			return "There is an exit to the south.\n";
		}
		else if(dir.equals(Exit.SOUTHWEST))
		{
			return "There is an exit to the southwest.\n";
		}
		else if(dir.equals(Exit.WEST))
		{
			return "There is an exit to the west.\n";
		}
		else if(dir.equals(Exit.NORTHWEST))
		{
			return "There is an exit to the northwest.\n";
		}
		else if(dir.equals(Exit.UP))
		{
			return "There is a staircase leading upwards.\n";
		}
		else if(dir.equals(Exit.DOWN))
		{
			return "There is a staircase leading downwards.\n";
		}
		return null;
	}
	public String getRoomDesc(int roomID)
	{
		String output = rlist.getRoomDesc(roomID) + "\n";
		ArrayList<Integer> roomItems = rlist.getRoomItems(roomID);
		for(int i = 0; i < roomItems.size(); i++)
		{
			String itemName = ilist.getItemName(roomItems.get(i));
			String itemDesc = "There is a " + itemName + " on the ground.\n";
			output = output + itemDesc;
		}
		ArrayList<Exit> roomExits = rlist.getRoomExits(roomID);
		for(int i = 0; i < roomExits.size(); i++)
		{
			String exitDesc = getExitDesc(roomExits.get(i).getDir());
			output = output + exitDesc;
		}
		return output;
	}
	public String getDescOfLocation(int charID)
	{
		int roomID = clist.getCharLocation(charID);
		return this.getRoomDesc(roomID);
	}
	public void run() {
		
		InputManager in = new InputManager();
		
		while(running) {
			
		
			
			// inputCommands is an array of strings containing all the words that the user inputed. 
			// The first element in it is the first word inputed, second element is the second word. etc. 
			// Its size is based off of how many words the user inputed. If the user just hits enter 
			// without typing any commands, inputCommands should be an array of size 0
			
			String[] inputCommands = in.getInput();
		
			// all game logic should happen here
			// eg: if the user said a direction, try to go in that direction
			// if the user said "get" the next element in inputCommands should be the name of an item
			
			System.out.println("user input recieved");
			
		}
		
		
	}

}
