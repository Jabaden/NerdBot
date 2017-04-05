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
	
	public String addGame(String playerOne, String playerTwo){
		for(StringPair newPair : playerList){
			if(newPair == null){
				continue;
			}
			if(newPair.contains(playerOne) || newPair.contains(playerTwo)){
				return "One of you is already in a game. Finish that one before starting another! Type %%c4 currentgames to view games in progress.";
			}
			
		}
		
		StringPair newPair = new StringPair(playerOne, playerTwo);
		playerList[gameCounter] = newPair;
		gameList[gameCounter] = new ConnectFour(playerOne, playerTwo);
		gameCounter++;
		return "Game created successfully!";
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
			if(playerList[i] == null){
				continue;
			}
			
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
	
	public String printPlayerList(){
		String output = "";
		boolean isEmpty = true;
		int i = 1;
		for(StringPair sp: ConnectFourManager.playerList){
			if(sp == null){
				continue;
			}
			else{
				output += Integer.toString(i) + ": " + BaseBot.INSTANCE.client.getUserByID(sp.getFirst()).mention()  + " vs " + BaseBot.INSTANCE.client.getUserByID(sp.getSecond()).mention() + "\n";
				isEmpty = false;
			}
		}
		if(isEmpty){return "There aren't any active games!";}
		else{return output;}
	}
	
	public void removeGame(String playerId){
		for(int i = 0 ; i < playerList.length; i++){
			if(playerList[i].contains(playerId)){
				playerList[i] = null;
				gameList[i] = null;
			}
		}
	}
}
