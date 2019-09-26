import java.util.*;

public class CharTester {
	public static void main(String[] args)
	{
		GameMaster gm = new GameMaster();
		gm.clist.addChar("Testman");
		gm.ilist.addItem("potion", true);
		gm.ilist.addItem("key", true);
		gm.ilist.addItem("sword", true);
		gm.clist.addItem(1, 1);
		gm.clist.addItem(1, 2);
		gm.clist.addItem(1, 3);
		System.out.println(gm.getInv(1));
	}
}
