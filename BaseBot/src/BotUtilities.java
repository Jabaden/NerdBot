import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

public class BotUtilities {
	protected BotUtilities(){};
	private static BotUtilities INSTANCE = null;
	public static BotUtilities getInstance(){
		if(INSTANCE == null){
			INSTANCE = new BotUtilities();
		}
		return INSTANCE;
	}
	
	//thanks stack overflow :P
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static String parseHelpCommand(String[] input, MessageReceivedEvent event){
		String botResponse = "";
		
		if(input[1].equalsIgnoreCase("c4")){
			botResponse = "C4 commands are all about playing connect four! \n"
					+ " Command List:\n\n"
					+ "				add <column>: adds a piece to the specified column. The first column is 0 the last column is 6. \n"
					+ "				\n"
					+ "             creategame <player1> <player2>: creates a new game \n"
					+ "             currentgames: prints a list of current games. Maximum of 5 at once.";			  
		}
		else if(input[1].equalsIgnoreCase("twitter")){
			botResponse = "Twitter commands are used to fetch the twitter feed of a user!";
		}
		
		return botResponse;
	}
	
	//IUser findUserByName(String name){
		//IDiscordClient client = BaseBot.INSTANCE.client;
		//client.get
	//}
}
