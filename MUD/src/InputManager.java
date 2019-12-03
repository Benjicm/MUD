
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class InputManager extends JPanel implements CharController{



	private static String lineSeparator = System.lineSeparator();

	private Command commandBuffer;
	private JPanel exitList;
	private JPanel inOutTextBox;
	private JPanel roomImageDisplay;
	private JPanel itemList;
	private JPanel roomNameBox;
	private int charID;
	private boolean readyForInput;

	// this boolean toggles whether it should print out info based about the contents of the commands entered.
	private final boolean printCommandInfo = false; 


	public InputManager(BlockingQueue<Command> commandQueue, int charID) {
		super(new BorderLayout());

		JFrame tWindow = new JFrame();
		Container tPane = tWindow.getContentPane();
		tPane.setPreferredSize(new Dimension(500, 300));
		tPane.add(this);
		tWindow.pack();
		tWindow.setLocation(200, 200);
		tWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tWindow.setVisible(true);

		this.charID = charID;

		// container for list of exits
		exitList = new JPanel(new GridBagLayout());

		// container for input and output text boxes
		inOutTextBox = new JPanel(new GridBagLayout());
		JTextArea outBox = new JTextArea();
		outBox.setEditable(false);
		outBox.setPreferredSize(new Dimension(200, 100));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		inOutTextBox.add(outBox,c);

		JTextField inBox = new JTextField(10);
		// code that turns user interaciton into commands
		ActionListener inBoxListener  = (e) -> {
			JTextField f = (JTextField)e.getSource();
			try {
				commandQueue.put(getTextInput(f.getText()));
				f.setText("");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		};
		inBox.addActionListener(inBoxListener);
		c.gridy = 1;
		inOutTextBox.add(inBox,c);
		inBox.setPreferredSize(new Dimension(200, 30));

		// container for room image display 
		roomImageDisplay = new JPanel(new GridLayout());


		// container for inventory display
		itemList = new JPanel(new GridBagLayout());
		JTextField inventoryTitle = new JTextField("Inventory:");
		inventoryTitle.setEditable(false);
		itemList.add(inventoryTitle);

		// container for room name display
		roomNameBox = new JPanel(new GridLayout(1,0));
		JTextField roomName = new JTextField("Room name");
		roomName.setEditable(false);
		roomNameBox.add(roomName);

		// add all pieces to the higher level container
		this.add(exitList, BorderLayout.LINE_START);
		this.add(inOutTextBox,BorderLayout.PAGE_END);
		this.add(roomImageDisplay, BorderLayout.CENTER);
		this.add(itemList,  BorderLayout.LINE_END);
		this.add(roomNameBox, BorderLayout.PAGE_START);

		readyForInput = true;
	}
	public int getCharID()
	{
		return charID;
	}
	public boolean onCall()
	{
		return readyForInput;
	}

	/**
	 * this literally just exists to call updateUI because spaghetti
	 * @param gameStateData
	 */
	public void updateInfo(GameStateInfo gameStateData)
	{
		updateUI(gameStateData);
		readyForInput = true;
	}
	/**
	 * Takes in a parameter containing all the necessary data to display the current state of the game to the user. Then updates the UI
	 * to accurately reflect the state. 
	 * @param gameStateData an array containing arrays of strings that represent the data in this game.
	 */
	public void updateUI(GameStateInfo gameStateData) {

		// ------------------------------- Update Room Name ---------------------------------
		JTextField roomName = (JTextField) roomNameBox.getComponent(0);
		roomName.setText(gameStateData.getRoomName());
		// ----------------------------------------------------------------------------------



		// ------------------------------- Update Console Output ----------------------------
		JTextArea outBox = (JTextArea) inOutTextBox.getComponent(0);
		outBox.setText(gameStateData.getRoomDesc());
		// ----------------------------------------------------------------------------------



		// ------------------------------- Update Exit List ---------------------------------
		ArrayList<String> exits = gameStateData.getExits();
		// clear previous textfields
		exitList.removeAll();

		// deal with formatting
		int curIndex = 0;
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridy = curIndex;

		// create initial textfield for title
		JTextField exitTitle = new JTextField("Exits:");
		exitTitle.setEditable(false);
		exitList.add(exitTitle,c1);
		curIndex++;

		// create and add textfield for each available exit
		for(String s: exits) {
			c1.gridy = curIndex;
			JTextField newExit = new JTextField(s);
			newExit.setEditable(false);
			exitList.add(newExit,c1);
			curIndex++;
		}
		// ----------------------------------------------------------------------------------




		// ------------------------------- Update Inventory ---------------------------------
		ArrayList<String> items = gameStateData.getInv();
		itemList.removeAll();

		curIndex = 0;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridy = curIndex;

		// create initial textfield for title
		JTextField inventoryTitle = new JTextField("Inventory:");
		inventoryTitle.setEditable(false);
		itemList.add(inventoryTitle,c2);
		curIndex++;

		// create and add textfield for each available exit
		for(String s: items) {
			c2.gridy = curIndex;

			JTextField newItem = new JTextField(s);
			newItem.setEditable(false);
			itemList.add(newItem,c2);
			curIndex++;
		}
		// ----------------------------------------------------------------------------------

		// final method run to make sure all the components are updated and ready to be displayed. For some reason this just fixes stuff.
		this.validate();
	}


	/**
	 * Converts what the user entered into the console into an easily usable array of strings that contains the words the user entered.
	 * This method will stop execution temporarily because it relies on the Scanner class' nextLine() method.
	 * @return returns an array of strings where each element is a single word entered by the user into the console.
	 */
	public Command getTextInput(String in) {


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
			System.out.println("Actual Command Count: " + commandCount);
		}
		Command out = createCommand(returnedIn);
		return out;
	}
	
	//TODO hey this is probably going to cause errors
	public Command sendCommand()
	{
		while(commandBuffer == null){}
		Command out = commandBuffer;
		commandBuffer = null;
		readyForInput = false;
		return out;
	}
	public Command createCommand(String[] commandText)
	{
		String action = commandText[0];
		int actionType = interpretAction(action);
		String param = "";
		for(int i = 1; i < commandText.length; i++)
		{
			param += commandText[i];
		}
		if(actionType == Command.MOVE)
		{
			param = interpretDirection(param);
		}
		return new Command(actionType, this.charID, param);
	}
	public int interpretAction(String action)
	{
		action = action.toLowerCase();
		if(action.equals("move"))
		{
			return Command.MOVE;
		}
		else if(action.equals("get") || action.equals("grab"))
		{
			return Command.GET;
		}
		else if(action.equals("drop"))
		{
			return Command.DROP;
		}
		else
		{
			return 3;
		}

	}
	public String interpretDirection(String dir)
	{
		dir = dir.toLowerCase();
		if(dir.equals("north") || dir.equals("n"))
		{
			return Command.NORTH;
		}
		else if(dir.equals("northeast") || dir.equals("ne"))
		{
			return Command.NORTHEAST;
		}
		else if(dir.equals("east") || dir.equals("e"))
		{
			return Command.EAST;
		}
		else if(dir.equals("southeast") || dir.equals("se"))
		{
			return Command.SOUTHEAST;
		}
		else if(dir.equals("south") || dir.equals("s"))
		{
			return Command.SOUTH;
		}
		else if(dir.equals("southwest") || dir.equals("sw"))
		{
			return Command.SOUTHWEST;
		}
		else if(dir.equals("west") || dir.equals("w"))
		{
			return Command.WEST;
		}
		else if(dir.equals("northwest") || dir.equals("nw"))
		{
			return Command.NORTHWEST;
		}
		else
		{
			return null;
		}
	}



}
