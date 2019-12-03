import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadHandler {
	GameMaster gm;
	ArrayList<CharController> controllers;
	
	public ThreadHandler() {
		RoomList rlist = SetupFileReader.setupRooms("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Roomlist.txt");
		SetupFileReader.setupExits("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Exitlist.txt", rlist);
		ItemList ilist = new ItemList();
		CharList clist = new CharList();
		
		gm = new GameMaster(rlist,ilist,clist);
		BlockingQueue<Command> commandQueue = new ArrayBlockingQueue<Command>(100);	
		InputManager in = new InputManager(commandQueue,1);
		
		
	}
}
