import java.util.*;


public class MobController implements CharController{
	private int charID;
	private ArrayList<String> visibleChars;
	private ArrayList<String> visibleItems;
	private ArrayList<String> visibleExits;
	private ArrayList<String> inventory;
	
	public MobController(int charID)
	{
		this.charID = charID;
		visibleChars = new ArrayList<String>();
		visibleItems = new ArrayList<String>();
		visibleExits = new ArrayList<String>();
		inventory = new ArrayList<String>();
	}
	
	public Command moveMob(String dir)
	{
		return new Command(2, charID, dir);
	}
	public Command getItem(String itemName)
	{
		return new Command(0, charID, itemName);
	}
	public Command dropItem(String itemName)
	{
		return new Command(1, charID, itemName);
	}

	@Override
	public int getCharID() {
		// TODO Auto-generated method stub
		return charID;
	}

	@Override
	public Command sendCommand() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int exit = (int)(Math.random()*visibleExits.size());
		String direction = visibleExits.get(exit);
		return moveMob(direction);
	}

	@Override
	public void updateInfo(GameStateInfo gameStateData) {
		// TODO Auto-generated method stub
		inventory = gameStateData.getInv();
		visibleChars = gameStateData.getChars();
		visibleItems = gameStateData.getItems();
		visibleExits = gameStateData.getExits();
	}
}
