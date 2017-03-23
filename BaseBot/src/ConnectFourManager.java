import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;

public class ConnectFourManager {
	private static StringPair[] playerList;
	private static ConnectFour[] gameList;
	private int gameCounter = 0;
	private IChannel mostRecentChannel;
	private static ConnectFourManager INSTANCE = null;
	protected ConnectFourManager(){
	}
	public static ConnectFourManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ConnectFourManager();
			playerList = new StringPair[5];
			gameList = new ConnectFour[5];
		}
		
		return INSTANCE;
	}
	
	public void addGame(String playerOne, String playerTwo){
		StringPair newPair = new StringPair(playerOne, playerTwo);
		playerList[gameCounter] = newPair;
		gameList[gameCounter] = new ConnectFour(playerOne, playerTwo);
		gameCounter++;
	}
	
	public ConnectFour getGame(String playerOne, String playerTwo){
		for(int i = 0; i < playerList.length; i++){
			if(playerList[i].isAMatch(playerOne, playerTwo)){
				return this.gameList[i];
			}
		}
		
		return null;
	}
	
	public ConnectFour getGame(String playerOne){
		for(int i = 0; i < playerList.length; i++){
			if(playerList[i].contains(playerOne)){
				return gameList[i];
			}
		}
		return null;
	}
	
	//lets the ConnectFourManager and therefore any ConnectFour Game 
	//know where the command came from (to print game board correctly
	public void assignChannel(IChannel chan){
		this.mostRecentChannel = chan;
	}
}
