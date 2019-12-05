import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;

public class ThreadHandler {
	GameMaster gm;
	ArrayList<CharController> controllers;
	BlockingQueue<Command> commandQueue;
	boolean run;
	
	public ThreadHandler() {
		RoomList rlist = SetupFileReader.setupRooms("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Roomlist.txt");
		SetupFileReader.setupExits("MUD"+ SetupFileReader.fileSeparator + "SetupFiles" + SetupFileReader.fileSeparator + "Exitlist.txt", rlist);
		ItemList ilist = new ItemList();
		CharList clist = new CharList();
		run = true;
		
		gm = new GameMaster(rlist,ilist,clist);
		commandQueue = new ArrayBlockingQueue<Command>(100);
		InputManager in = new InputManager(commandQueue,1);
		
		
	}
	
	public void run()
	{
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<Future<Command>> futures = new ArrayList<Future<Command>>();
		while(run)
		{
			//take all the commands from the controllers
			for(int i = 0; i <controllers.size(); i++)
			{
				if(controllers.get(i).isReady())
				{
					controllers.get(i).setReady(false);
					Future<Command> f = service.submit(getCommand(i));
					futures.add(f);
				}
			}
			//put them in a queue
			for(int i = 0; i < futures.size(); i++)
			{
				if(futures.get(i).isDone())
				{
					try
					{
						if(futures.get(i).get() != null)
						{
							Command c = futures.get(i).get();
							futures.remove(i);
							commandQueue.put(c);
							i--;
						}
						else
						{
							futures.remove(i);
							i--;
						}
					}
					catch(Exception e)
					{
						
					}
				}
			}
			//send commands from the queue to GameMaster
			if(!commandQueue.isEmpty())
			{
				try {
					GameStateInfo info = gm.interpretCommand(commandQueue.take());
					//send GameStateInfo objects from the GM to the controllers
					int index = findControllerIndex(info.getIssuerID());
					controllers.get(index).updateInfo(info);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		service.shutdown();
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
				controllers.get(controllerIndex).setReady(true);
				return null;
			}
		};
	}
	private int findControllerIndex(int charID)
	{
		for(int i = 0; i < controllers.size(); i++)
		{
			if(controllers.get(i).getCharID() == charID)
			{
				return i;
			}
		}
		throw new NullPointerException();
	}
}
