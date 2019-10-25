
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InputManager extends JPanel{

	private Scanner s;

	
	private JPanel exitList;
	private JPanel inputTextBox;
	private JPanel roomImageDisplay;
	private JPanel itemList;
	private JPanel mobList;

	// this boolean toggles whether it should print out info based about the contents of the commands entered.
	private final boolean printCommandInfo = false; 


	public InputManager() {
		
		


		// Alright here we go, so lets break this down into pseudo-code
		// First things first, the InputManager is itself a Jpanel, will be a second-from-the-top level container
		// outside of inputManager, it is added to a JFrame window somewhere in the GameMaster's Run method.
		
		// Anyways, Inside of this class, we have one main layout manager, "BorderLayout". 
		// This will contain chunked off sections that contain groups of UI pieces.
		super(new BorderLayout());
		
		// For example. there will be a section that contains a list of all the exits in the current room, 
		exitList = new JPanel(new GridLayout(3,5));
		JButton[] directionButtonList = new JButton[] {
				new JButton("NW"), new JButton("N"), new JButton("NE"), new JButton(""), new JButton("UP"),
				new JButton("W"),  new JButton(""),  new JButton("E"),  new JButton(""), new JButton(""),
				new JButton("SW"), new JButton("S"), new JButton("SE"), new JButton(""), new JButton("D")
		};
		
		for(JButton j : directionButtonList) {
			j.setPreferredSize(new Dimension(31, 5));
			exitList.add(j);
		}
		
		
		
		
		
		
		
		
		// There will be a section that contains the textbox/JTextField that the user inputs commands on
		inputTextBox = new JPanel(new GridLayout());
		JTextField inBox = new JTextField(10);
		inputTextBox.add(inBox);
		inBox.setPreferredSize(new Dimension(200, 100));
		
		// There will be a section that uses an image to display the current room
		// note we might switch this to a graphics pane just for the sake of simplicity.
		roomImageDisplay = new JPanel(new GridLayout());

		
		
		
		// There will be a section that lists the user's inventory
		itemList = new JPanel(new GridLayout(0,1));
		itemList.add(new JButton("Item1"));
		itemList.add(new JButton("Item2"));
		itemList.add(new JButton("Item3"));
		itemList.add(new JButton("Item4"));

		
		// Maybe even a section that lists mobs in the current Room
		mobList = new JPanel(new GridLayout(1,0));
		mobList.add(new JButton("Mob1"));
		mobList.add(new JButton("Mob2"));
		mobList.add(new JButton("Mob3"));
		mobList.add(new JButton("Mob4"));
		
		
	
		// It is important to note that each 'section' described here is its own JPanel of some kind that defines the layout
		
		// Alright! Lets stack up these things so they all fall into place!
		
		this.add(exitList, BorderLayout.LINE_START);
		this.add(inputTextBox,BorderLayout.PAGE_END);
		this.add(roomImageDisplay, BorderLayout.CENTER);
		this.add(itemList,  BorderLayout.LINE_END);
		this.add(mobList, BorderLayout.PAGE_START);
		
		

		s = new Scanner(System.in);

	}



	public String[] getTextInput() {

		String in = s.nextLine();
		char[] chars = new char[in.length()];
		for(int i = 0; i < in.length(); i++) {
			chars[i] = in.charAt(i);
		}
		if(printCommandInfo) {
			for(char c: chars) {
				System.out.println("" + c);
			}
		}
		int commandCount = 1;
		for(int i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				commandCount++;
			}
		}
		if(printCommandInfo) {
			System.out.println("Initial Command count: " + commandCount);
		}
		String[] inputs = new String[commandCount];
		int index = 0;
		for(int i = 0; i < inputs.length; i++) {
			inputs[i] = "";
			while(index < chars.length && chars[index] != ' ') {
				inputs[i] += chars[index];
				index++;
			}
			if(index < chars.length && chars[index] == ' ') {
				index++;
			}
		}

		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i].length() == 0) {
				commandCount--;
			}
		}

		String[] returnedIn = new String[commandCount];
		int addIndex = 0;
		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i].length() != 0) {
				returnedIn[addIndex] = inputs[i];
				addIndex++;
			}
		}
		if(printCommandInfo) {

			for(String s : returnedIn) {
				System.out.println(s);
			}

			System.out.println("Actuall Command Count: " + commandCount);
		}
		return returnedIn;
	}



}
