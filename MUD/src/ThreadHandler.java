import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;

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
	
	public void run()
	{
		ExecutorService service = Executors.newCachedThreadPool();
	}
	private Callable<Command> getCommand(int controllerIndex)
	{
		return () -> {
			CharController c = controllers.get(controllerIndex);
			try {
				Command out = c.sendCommand();
				controllers.get(controllerIndex).setReady(false);
				return out;
			}
			catch(NullPointerException e) {
				return null;
			}
		};
	}
}
