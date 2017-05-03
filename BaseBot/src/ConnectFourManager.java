import java.util.List;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

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
	
	public String parseConnectCommand(String[] input, MessageReceivedEvent event){
		String botResponse = "";
		

			if(input.length < 2){
				botResponse = "Command must be followed by an additional sub-command. Please type %%HELP for help!";
			}
			
			else if(input[1].equalsIgnoreCase("add")){
				if(input.length < 3){
					botResponse = "Incomplete Command. Please type %%C4 for information on playing Connect Four!";
				}
				else{
					
					String author = event.getMessage().getAuthor().getName();
					String a_author = event.getMessage().getAuthor().getID();
					System.out.println("The original sender is seen as is " + author);
					System.out.println("The id is " + a_author);
					System.out.println("the bot sees this user as..." + BaseBot.INSTANCE.client.getUserByID(a_author).getName() );
					ConnectFour tempGame = ConnectFourManager.getInstance().getGame(a_author);
					ConnectFourManager.getInstance().assignChannel(event.getMessage().getChannel());
					if(tempGame != null){
						ConnectFour.color color = tempGame.getPlayerColor(a_author);
						if(BotUtilities.getInstance().isInteger(input[2])){
							
							if(a_author != tempGame.getCurrentPlayer()){
								botResponse = "It's not your turn! Be patient!";
							}
							
							else if(Integer.parseInt(input[2]) < 7 && Integer.parseInt(input[2]) >= 0){
								tempGame.addPiece(Integer.parseInt(input[2]), color);
								botResponse = tempGame.printBoard();
								if( tempGame.checkForWinner() ){
									botResponse += "\n" + event.getMessage().getAuthor().getName() + " Is the Winner!";
								}
							}
							
							else{
								botResponse = "Please imput a valid integer";
							}
							
						}
						else{
							botResponse = "Please imput a valid integer";
						}
					}
					else{
						botResponse = "You're not in any games! Type %%help C4 for help playing Connect Four!";
					}
				}
			}
			
			else if (input[1].equals("creategame")){
				if(input.length < 4 ){
					botResponse = "Please input two players!";
				}
				else{
					List<IUser> iList = event.getMessage().getMentions();
					//String playerOne = words[2];
					//String playerTwo = words[3];
					if(iList.size() == 2){
						String playerOne = iList.get(0).getID();
						String playerTwo = iList.get(1).getID();
						botResponse = ConnectFourManager.getInstance().addGame(playerOne, playerTwo);
					}
					else{
						botResponse = "Please mention two users!";
					}
					
					
					 
				}
			}
			
			else if(input[1].equalsIgnoreCase("currentgames")){
				botResponse = ConnectFourManager.getInstance().printPlayerList();
			}
			else{
				botResponse = "Subcommand does not exist with primary command C4. Please type \"%%help C4\" for help!";
			}
			

		
		return botResponse;
	}
}
