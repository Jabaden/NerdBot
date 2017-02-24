import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
public class ParseBot extends BaseBot{

	public ParseBot(IDiscordClient client){
		super(client);
		EventDispatcher dispatcher = client.getDispatcher();
		dispatcher.registerListener(new TextParser());
	}
	
	public static class TextParser{
		//@TextParser
		public void parseTextForCommand(MessageReceivedEvent event){
			IMessage message = event.getMessage();
			IChannel channel = message.getChannel();
			String text = message.getContent();
			if(text.substring(0,2).equalsIgnoreCase("%%")){
				System.out.println("Incoming Request");
				String command = null;
				if(text.contains(" ")){
					 command = text.substring(2, text.indexOf(" "));
				}
				else{
					command = text.substring(2);
				}
				System.out.println("Command word is : " + command);
			}
		}
	}
}
