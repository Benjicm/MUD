import java.util.*;

public class Character {
	String name;
	int ID;
	int currentRoom;
	ArrayList<Integer> inventory;
	
	public Character()
	{
		name = "";
		ID = 0;
		currentRoom = 0;
		inventory = new ArrayList<Integer>();
	}
	public Character(String name, int ID)
	{
		this.name = name;
		this.ID = ID;
		currentRoom = 0;
		inventory = new ArrayList<Integer>();
	}
	public Character(String name, int ID, int startingRoom)
	{
		this.name = name;
		this.ID = ID;
		currentRoom = startingRoom;
		inventory = new ArrayList<Integer>();
	}
	
	/** Gets the name of this character
	 * @return the character's name
	 */
	public String getName()
	{
		return name;
	}
	/** Gets the ID of this character 
	 * @return the character's ID
	 */
	public int getID()
	{
		return ID;
	}
	/** Gets the ID of the room this character currently occupies
	 * @return the ID of the room this character is in
	 */
	public int getRoom()
	{
		return currentRoom;
	}
	/** Gets the IDs of all the items in this character's inventory
	 * @return ArrayList containing the ID of every item in the character's inventory
	 */
	public ArrayList<Integer> getInv()
	{
		return inventory;
	}
	public boolean holdingItem(int itemID)
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i) == itemID)
			{
				return true;
			}
		}
		return false;
	}
	public void addItem(int itemID)
	{
		if(!holdingItem(itemID))
		{
			inventory.add(itemID);
		}
	}
	public void removeItem(int itemID)
	{
		for(int i = 0; i < inventory.size(); i++)
		{
			if(inventory.get(i) == itemID)
			{
				inventory.remove(i);
				return;
			}
		}
	}
	public void setRoom(int roomID)
	{
		currentRoom = roomID;
	}
}
