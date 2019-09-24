
public class GameManager {

	RoomList rlist;
	ItemList ilist;
	boolean running = true;


	public GameManager() {



	}

	public void setup() {
		// this reads in data from the text files provided in the SetupFiles folder
		rlist = SetupFileReader.setupRooms("SetupFiles" + SetupFileReader.fileSeparator + "Roomlist.txt");
		SetupFileReader.setupExits("SetupFiles" + SetupFileReader.fileSeparator + "Exitlist.txt", rlist);
		SetupFileReader.setupItems("SetupFiles" + SetupFileReader.fileSeparator + "Itemlist.txt", rlist);
		
		// the setup() method still needs something to initialize the player
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
