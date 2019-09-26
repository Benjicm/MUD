import java.util.*;

public class CharTester {
	public static void main(String[] args)
	{
		GameMaster gm = new GameMaster();
		gm.rlist.addRoom("You are in a dark, musty prison cell.");
		gm.rlist.addExitToRoom(1, 2, Exit.NORTH);
		gm.ilist.addItem("key", true);
		gm.ilist.addItem("sword", true);
		gm.rlist.addItem(1, 1);
		gm.rlist.addItem(1, 2);
		System.out.println(gm.getRoomDesc(1));
	}
}
