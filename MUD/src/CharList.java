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
	public int addChar(String charName)
	{
		Character c = new Character(charName, nextID);
		nextID++;
		chars.add(c);
		return c.getID();
	}
	public String getCharName(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getName();
	}
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
	public int getCharLocation(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getRoom();
	}
	public ArrayList<Integer> getCharInv(int charID)
	{
		int index = getCharIndex(charID);
		return chars.get(index).getInv();
	}
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
