import sx.blah.discord.api.IDiscordClient;

public class ConnectFourManager {
	private StringPair[] playerList;
	private ConnectFour[] gameList;
	private int gameCounter = 0;
	private IDiscordClient client;
	public ConnectFourManager(IDiscordClient client){
		playerList = new StringPair[5];
		gameList = new ConnectFour[5];
		this.client = client;
	}
	
	
	public void addGame(String playerOne, String playerTwo){
		StringPair newPair = new StringPair(playerOne, playerTwo);
		playerList[gameCounter] = newPair;
		gameList[gameCounter] = new ConnectFour(playerOne, playerTwo, this.client);
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
	
}
