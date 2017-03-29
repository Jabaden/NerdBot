import sx.blah.discord.api.IDiscordClient;
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
	
	//IUser findUserByName(String name){
		//IDiscordClient client = BaseBot.INSTANCE.client;
		//client.get
	//}
}
