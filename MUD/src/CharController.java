
public interface CharController {
	public int getCharID();
	public Command sendCommand();
	public void updateInfo(GameStateInfo gameStateData);
	public boolean isReady();
	public void setReady(boolean ready);
}
