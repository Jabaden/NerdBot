import java.util.Iterator;
import java.util.List;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class BotCleanup {
	private IChannel channel;
	public BotCleanup(IChannel channel){
		this.channel = channel;
	}
	public void deleteMostRecent(){
		
		//IMessage toDelete = messages.getLatestMessage();
		int count = 0;
		int rateLimitPreventer = 0;
		//for( Iterator<IMessage> iter = messages.iterator(); iter.hasNext() && count < 10;count++){
		while(count < 7){
			MessageList messages =  this.channel.getMessages();
			IMessage latest = messages.getLatestMessage();
			//IMessage element = iter.next();
			//if(element.getAuthor().isBot() || element.getAuthor().getName().equalsIgnoreCase("Nerd Bird")){
				try{
					latest.delete();
					rateLimitPreventer++;
					count++;
					if(rateLimitPreventer >= 3){
						rateLimitPreventer = 0;
						Thread.sleep(1000);
					}
				}
				catch(MissingPermissionsException a){
					System.err.print("Missing permissions for channel!");
					a.printStackTrace();
				}
				catch(RateLimitException b){
					System.err.print("Stop sending messages so quickly!");
					b.printStackTrace();
				}
				catch(DiscordException c){
					c.printStackTrace();
				}
				catch(InterruptedException ex){
					Thread.currentThread().interrupt();
				}
			//}
			
		}
	}
}
