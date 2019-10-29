import java.util.*;

public class GameStateInfo {
	//list of inventory items, name and description of room, list of exits, ID of issuer, list of room items, list of characters in room
	private int issuerID;
	private String roomName;
	private String roomDesc;
	private ArrayList<String> inventory;
	private ArrayList<String> exits;
	private ArrayList<String> roomItems;
	private ArrayList<String> charNames;
	
	public GameStateInfo(int ID, String roomName, String roomDesc, ArrayList<String> inv, ArrayList<String> exits, ArrayList<String> roomItems, ArrayList<String> charNames)
	{
		issuerID = ID;
		this.roomName = roomName;
		this.roomDesc = roomDesc;
		inventory = inv; 
		this.exits = exits;
		this.roomItems = roomItems;
		this.charNames = charNames;
	}
	
	public int getIssuerID()
	{
		return issuerID;
	}
	public String getRoomName()
	{
		return roomName;
	}
	public String getRoomDesc()
	{
		return roomDesc;
	}
	public ArrayList<String> getInv()
	{
		return inventory;
	}
	public ArrayList<String> getExits()
	{
		return exits;
	}
	public ArrayList<String> getItems()
	{
		return roomItems;
	}
	public ArrayList<String> getChars()
	{
		return charNames;
	}
}
