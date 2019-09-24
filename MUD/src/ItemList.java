import java.util.*;

public class ItemList {
	ArrayList<Item> items;
	int nextID;
	
	ItemList()
	{
		items = new ArrayList<Item>();
		nextID = 0;
		addItem("", false);
	}
	
	private int getItemIndex(int itemID)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getID() == itemID)
			{
				return(i);
			}
		}
		return 0;
	}
	public ArrayList<Integer> findItemByName(String itemName)
	{
		String name = itemName.toLowerCase();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getName().equals(name))
			{
				a.add(items.get(i).getID());
			}
		}
		return a;
	}
	public int addItem(String itemName, boolean carriable)
	{
		Item n = new Item(itemName, nextID, carriable);
		nextID++;
		items.add(n);
		return n.getID();
	}
	public String getItemName(int itemID)
	{
		int index = getItemIndex(itemID);
		return items.get(index).getName();
	}
	public int getItemContainerID(int itemID)
	{
		int index = getItemIndex(itemID);
		return items.get(index).getContainerID();
	}
	public boolean isItemBeingCarried(int itemID)
	{
		int index = getItemIndex(itemID);
		return items.get(index).isBeingCarried();
	}
	public void setItemContainer(int itemID, boolean beingCarried, int containerID)
	{
		int index = getItemIndex(itemID);
		items.get(index).setContainer(beingCarried, containerID);
	}
}
