
public class ConnectFourManager {
	private static StringPair[] playerList;
	private static ConnectFour[] gameList;
	private static int gameCounter = 0;
	public ConnectFourManager(){
		playerList = new StringPair[5];
		gameList = new ConnectFour[5];
	}
	
	
	public void addGame(String playerOne, String playerTwo){
		StringPair newPair = new StringPair(playerOne, playerTwo);
		playerList[gameCounter] = newPair;
		gameList[gameCounter] = new ConnectFour(playerOne, playerTwo);
		gameCounter++;
	}
	
}
