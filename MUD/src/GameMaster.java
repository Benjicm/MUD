import java.awt.Container;
import java.awt.Dimension;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

public class GameMaster {

	RoomList rlist;
	ItemList ilist;
	CharList clist;
	private BlockingQueue<Command> commandQueue;
	
	boolean running = true;


	public GameMaster() {
		rlist = new RoomList();
		ilist = new ItemList();
		clist = new CharList();
		commandQueue = new ArrayBlockingQueue<Command>(100);
	}

	public void setup() {
		// this reads in data from the text files provided in the SetupFiles folder
		rlist = SetupFileReader.setupRooms("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Roomlist.txt");
		SetupFileReader.setupExits("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Exitlist.txt", rlist);
		//SetupFileReader.setupItems("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Itemlist.txt", rlist);
		
		
		
		
		// the setup() method still needs something to initialize the player
		
		// test stuff: 
		clist.addChar("Testman");
		//rlist.addRoom("Room 1","You are in the first room.");
		//rlist.addRoom("Room 2","You are in the second room.");
		ilist.addItem("potion", true);
		
		
		//rlist.addExitToRoom(1, 2, Exit.NORTH);
		//rlist.addExitToRoom(2, 1, Exit.SOUTH);
		// how to add items?
		clist.moveChar(1, 2);
		rlist.addChar(2, 1);
		rlist.addItem(2, 1);
		ilist.setItemContainer(1, false, 2);
		
		
		
		
		
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
	private String getMovementDesc(String dir)
	{
		if(dir.equals(Exit.NORTH))
		{
			return "You take the exit to the north.\n";
		}
		else if(dir.equals(Exit.NORTHEAST))
		{
			return "You take the exit to the northeast.\n";
		}
		else if(dir.equals(Exit.EAST))
		{
			return "You take the exit to the east.\n";
		}
		else if(dir.equals(Exit.SOUTHEAST))
		{
			return "You take the exit to the southeast.\n";
		}
		else if(dir.equals(Exit.SOUTH))
		{
			return "You take the exit to the south.\n";
		}
		else if(dir.equals(Exit.SOUTHWEST))
		{
			return "You take the exit to the southwest.\n";
		}
		else if(dir.equals(Exit.WEST))
		{
			return "You take the exit to the west.\n";
		}
		else if(dir.equals(Exit.NORTHWEST))
		{
			return "You take the exit to the northwest.\n";
		}
		else if(dir.equals(Exit.UP))
		{
			return "You go up the stairs.\n";
		}
		else if(dir.equals(Exit.DOWN))
		{
			return "You go down the stairs.\n";
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
	public String getInv(int charID)
	{
		String output = "You have these items:\n";
		ArrayList<Integer> inv = clist.getCharInv(charID);
		for(int i = 0; i < inv.size(); i++)
		{
			String itemName = ilist.getItemName(inv.get(i));
			String itemDesc = "A " + itemName + "\n";
			output = output + itemDesc;
		}
		return output;
	}
	public GameStateInfo pickUpItem(int charID, String itemName)
	{
		int roomID = clist.getCharLocation(charID);
		ArrayList<Integer> items = ilist.findItemByName(itemName);
		int itemID = 0;
		for(int i = 0; i < items.size(); i++)
		{
			if(rlist.containsItem(roomID, items.get(i)))
			{
				itemID = items.get(i);
			}
		}
		if(itemID == 0)
		{
			return null;
		}
		if(!ilist.isItemCarriable(itemID))
		{
			return null;
		}
		rlist.removeItem(roomID, itemID);
		clist.addItem(charID, itemID);
		ilist.setItemContainer(itemID, true, roomID);
		return createInfo(charID, roomID);
	}
	public GameStateInfo dropItem(int charID, String itemName)
	{
		int roomID = clist.getCharLocation(charID);
		ArrayList<Integer> items = ilist.findItemByName(itemName);
		int itemID = 0;
		for(int i = 0; i < items.size(); i++)
		{
			if(clist.holdingItem(charID, items.get(i)))
			{
				itemID = items.get(i);
			}
		}
		if(itemID == 0)
		{
			return null;
		}
		clist.removeItem(charID, itemID);
		rlist.addItem(roomID, itemID);
		ilist.setItemContainer(itemID, false, roomID);
		return createInfo(charID, roomID);
	}
	public GameStateInfo moveChar(int charID, String dir)
	{
		int roomID = clist.getCharLocation(charID);
		if(!rlist.doesExitExist(roomID, dir))
		{
			return null;
		}
		int newRoomID = rlist.moveChar(roomID, charID, dir);
		clist.moveChar(charID, newRoomID);
		return createInfo(charID, newRoomID);
	}
	public GameStateInfo interpretCommand(Command c)
	{
		if(c.getAction() == Command.MOVE)
		{
			return this.moveChar(c.getCharID(), c.getParam());
		}
		if(c.getAction() == Command.GET)
		{
			return this.pickUpItem(c.getCharID(), c.getParam());
		}
		if(c.getAction() == Command.DROP)
		{
			return this.dropItem(c.getCharID(), c.getParam());
		}
		return null;
	}
	private GameStateInfo createInfo(int charID, int roomID)
	{
		String roomName = rlist.getRoomName(roomID);
		String roomDesc = rlist.getRoomDesc(roomID);
		ArrayList<String> inventory = new ArrayList<String>();
		ArrayList<Integer> invIDs = clist.getCharInv(charID);
		ArrayList<Exit> exitIDs = rlist.getRoomExits(roomID);
		ArrayList<String> exits = new ArrayList<String>();
		ArrayList<Integer> roomItemIDs = rlist.getRoomItems(roomID);
		ArrayList<String> roomItems = new ArrayList<String>();
		ArrayList<Integer> charIDs = rlist.getRoomChars(roomID);
		ArrayList<String> charNames = new ArrayList<String>();
		for(int i = 0; i < invIDs.size(); i++)
		{
			String itemName = ilist.getItemName(invIDs.get(i));
			inventory.add(itemName);
		}
		for(int i = 0; i < exitIDs.size(); i++)
		{
			String exitDir = exitIDs.get(i).getDir();
			exits.add(exitDir);
		}
		for(int i = 0; i < roomItemIDs.size(); i++)
		{
			String itemName = ilist.getItemName(roomItemIDs.get(i));
			roomItems.add(itemName);
		}
		for(int i = 0; i < charIDs.size(); i++)
		{
			String charName = clist.getCharName(charIDs.get(i));
		}
		GameStateInfo out = new GameStateInfo(charID, roomName, roomDesc, inventory, exits, roomItems, charNames);
		return out;
	}
	public void run() {
		
		InputManager in = new InputManager(commandQueue);
		JFrame tWindow = new JFrame();
		Container tPane = tWindow.getContentPane();
		tPane.setPreferredSize(new Dimension(500, 300));
		tPane.add(in);
		
		tWindow.pack();
		tWindow.setLocation(200, 200);
		tWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tWindow.setVisible(true);
		in.updateUI(createInfo(1, 1));
		while(running) {
			
		
			
			// inputCommands is an array of strings containing all the words that the user inputed. 
			// The first element in it is the first word inputed, second element is the second word. etc. 
			// Its size is based off of how many words the user inputed. If the user just hits enter 
			// without typing any commands, inputCommands should be an array of size 0
		
			Command currentCommand;
			
			try {
				currentCommand = commandQueue.take();
				GameStateInfo newGameState = interpretCommand(currentCommand);
				
				
				in.updateUI(newGameState);
				
				
				
				System.out.println("Command recieved!");
				System.out.println("Command info:  ActionID: " + currentCommand.getAction() + " Parameter: " + currentCommand.getParam() + " CharID: " + currentCommand.getCharID());
				System.out.println(getDescOfLocation(currentCommand.getCharID()));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
			
			// all game logic should happen here
			// eg: if the user said a direction, try to go in that direction
			// if the user said "get" the next element in inputCommands should be the name of an item
			
			// makes sure they enter something
			
			
			
			
		}
		
		
	}

}
