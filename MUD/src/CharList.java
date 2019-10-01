import java.util.*;

public class CharList {
	
	ArrayList<Character> chars;
	int nextID;
	
	public CharList()
	{
		chars = new ArrayList<Character>();
		nextID = 0;
		addChar("");
	}
	/** Finds a given character's location in the list
	 * @param charID The ID of the character to located
	 * @return the index at which the character with ID charID is located
	 */
	private int getCharIndex(int charID)
	{
		for(int i = 0; i < chars.size(); i++)
		{
			if(chars.get(i).getID() == charID)
			{
				return(i);
			}
		}
		return 0;
	}
	/** Creates a new character and adds it to the list.
	 * @param charName the name of the new character
	 * @return the ID of the new character
	 */
	public int addChar(String charName)
	{
		Character c = new Character(charName, nextID);
		nextID++;
		chars.add(c);
		return c.getID();
	}
	/** Gets the name of the character
	 * @param charID the ID of the character whose name you want to know
	 * @return the name of the character with ID charID
	 */
	public String getCharName(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getName();
	}
	/** Finds the IDs of every character with a certain name
	 * @param charName the name to search for
	 * @return an ArrayList containing the IDs of every character with name charName
	 */
	public ArrayList<Integer> findCharByName(String charName)
	{
		String name = charName.toLowerCase();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < chars.size(); i++)
		{
			if(chars.get(i).getName().equals(name))
			{
				a.add(chars.get(i).getID());
			}
		}
		return a;
	}
	/** Gets the room in which a character is located
	 * @param charID the ID of the character
	 * @return the ID of the room in which the character with ID charID is contained
	 */
	public int getCharLocation(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getRoom();
	}
	/** Gets the inventory of a character
	 * @param charID the ID of the character
	 * @return an ArrayList containing the IDs of every item in the inventory of the character with ID charID
	 */
	public ArrayList<Integer> getCharInv(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getInv();
	}
	/** Checks if a character is holding an item
	 * @param charID the ID of the character in question
	 * @param itemID the ID of the item in question
	 * @return true if the character with ID charID is holding the item with ID itemID, false if not
	 */
	public boolean holdingItem(int charID, int itemID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).holdingItem(itemID);
	}
	public void addItem(int charID, int itemID)
	{
		int index = getCharIndex(charID);
		if(!holdingItem(charID,itemID))
		{
			chars.get(index).addItem(itemID);
		}
	}
	public void removeItem(int charID, int itemID)
	{
		int index = getCharIndex(charID);
		if(holdingItem(charID, itemID))
		{
			chars.get(index).removeItem(itemID);
		}
	}
	public void moveChar(int charID, int roomID)
	{
		int index = getCharIndex(charID);
		chars.get(index).setRoom(roomID);
	}
}
