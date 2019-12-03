import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SetupFileReader {
	
	
	private static final String ints = "-0123456789";
	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String lineSeparator = System.getProperty("line.separator");
	
	
	public SetupFileReader() {
			
	}

	public static RoomList setupRooms(String inputFilename) {
	
		FileReader fReader = null;
		BufferedReader buffedReader = null;

		char[] chars = null;
		RoomList rlist = new RoomList();
		try {
			fReader = new FileReader(inputFilename);
			buffedReader = new BufferedReader(fReader);

			
			// begin while loop for reading in the room list
			while(buffedReader.ready()) {
				chars = buffedReader.readLine().toCharArray();
				
				//code that initializes the rooms array
				
				// extract data from the line by looking for commas
				String line = new String(chars);
				
				int first = 0;
				first = line.indexOf(',');
				
				// makes sure the line actually contains a comma
				if(first == -1) {
				
				}
				
				// checks the first char to see if its actually a number
				//else if(ints.indexOf(line.charAt(0)) == -1) {}
				
				// Room id code
				String rName = line.substring(0, first);
				
				// Room description Code
				String description = line.substring(first+1);
				rlist.addRoom(rName,description);
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buffedReader.close();
			}
			catch(IOException e) {
				e.printStackTrace();	
			}
		}
		return rlist;
		
	}
	
	public static void setupExits(String inputFilename, RoomList rlist) {
		
		FileReader fReader = null;
		BufferedReader buffedReader = null;

		char[] chars = null;
		try {
			fReader = new FileReader(inputFilename);
			buffedReader = new BufferedReader(fReader);

			
			// begin while loop for reading in the room list
			while(buffedReader.ready()) {
				chars = buffedReader.readLine().toCharArray();
				
				//code that initializes the rooms array
				
				// extract data from the line by looking for commas
				String line = new String(chars);
				
				int first = 0;
				int second = 0;
				first = line.indexOf(',');
				
				
				// makes sure the line actually contains a comma
				if(first == -1) {
				
				}
				
				// checks the first char to see if its actually a number
				else if(ints.indexOf(line.charAt(0)) == -1) {

				}
				
				// Room id code of where the exit is
				int roomID = Integer.parseInt(line.substring(0, first));
				line = line.substring(first+1);
				
				// Room id code of where the exit leads
				second = line.indexOf(',');
				int leadsToRoomID = Integer.parseInt(line.substring(0,second));
				line = line.substring(second+1);
				
				// exit direction code
				String direction = line;
				
				rlist.addExitToRoom(roomID, leadsToRoomID, direction);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buffedReader.close();
			}
			catch(IOException e) {
				e.printStackTrace();	
			}
		}

		
	}

public static void setupItems(String inputFilename, RoomList rlist) {
		
		FileReader fReader = null;
		BufferedReader buffedReader = null;

		char[] chars = null;
		try {
			fReader = new FileReader(inputFilename);
			buffedReader = new BufferedReader(fReader);

			
			// begin while loop for reading in the room list
			while(buffedReader.ready()) {
				chars = buffedReader.readLine().toCharArray();
				
				//code that initializes the rooms array
				
				// extract data from the line by looking for commas
				String line = new String(chars);
				
				int first = 0;
				int second = 0;
				int third = 0;
				first = line.indexOf(',');
				
				
				// makes sure the line actually contains a comma
				if(first == -1) {
				
				}
				
				// checks the first char to see if its actually a number
				else if(ints.indexOf(line.charAt(0)) == -1) {

				}
				
				// id code of the item itself
				int itemID = Integer.parseInt(line.substring(0, first));
				line = line.substring(first+1);
				
				// Room id code of where the item is
				second = line.indexOf(',');
				int existsInRoomID = Integer.parseInt(line.substring(0,second));
				line = line.substring(second+1);
				
				
				
				third = line.indexOf(',');
				boolean isPortable = (Integer.parseInt(line.substring(0,third)) == 1);
				line = line.substring(third+1);
				
				//item name 
				String name = line;
				
				rlist.addItem(existsInRoomID, itemID);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buffedReader.close();
			}
			catch(IOException e) {
				e.printStackTrace();	
			}
		}

		
	}
	
}
