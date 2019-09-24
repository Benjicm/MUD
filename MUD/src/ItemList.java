import java.util.*;

public class ItemList {
	ArrayList<Item> items;
	int nextID;
	
	ItemList()
	{
		items = new ArrayList<Item>();
		nextID = 0;
	}
	
<<<<<<< HEAD

	private int getItemIndex(int itemID)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getID() == itemID)
			{
				return i;
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
	// the setupFileReader class will require some code to be added to it, as there is no place to store the
	// data of which item id corresponds to which name. I assume that will be added to this class yeah? - Alex 
	
	
	// things this class needs: 
	// 1. An array list that holds item id's
	// 2.the names of each item
	// 3. and a simple boolean that defines if the item is portable
	
	
=======
	// the setupFileReader class will require some code to be added to it, as there is no place to store the
	// data of which item id corresponds to which name. I assume that will be added to this class yeah? - Alex 
	
	
	// things this class needs: 
	// 1. An array list that holds item id's
	// 2.the names of each item
	// 3. and a simple boolean that defines if the item is portable
	
	
>>>>>>> refs/remotes/origin/eclipse
	
}
