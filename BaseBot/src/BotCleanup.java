import java.util.Iterator;
import java.util.List;

//https://gyazo.com/d09a379c39dbb307fb11f4b4d23f2442
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class BotCleanup {
	private IChannel channel;
	public BotCleanup(IChannel channel){
		this.channel = channel;
	}
	public void deleteMostRecent(){
		MessageHistory messages = null;
		messages = this.channel.getMessageHistory(15);
		for(Iterator<IMessage> iter = messages.iterator(); iter.hasNext();){
			IMessage element = iter.next();
			System.out.println( element.getContent());
		}
		
		try {
			messages.bulkDelete();
		} catch (DiscordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//channel.getMessages().stream().filter
		//RequestBuffer.
/*
		int count = 0;
		int rateLimitPreventer = 0;
		for( Iterator<IMessage> iter = messages.iterator(); iter.hasNext() && count < 10;count++){
			IMessage element = iter.next();
			//if(element.getAuthor().isBot() || element.getAuthor().getName().equalsIgnoreCase("Nerd Bird")){
				try{
					element.delete();
					rateLimitPreventer++;
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
		*/
	}
	
	public void deleteLastXMessages(int numberOfMessages, MessageReceivedEvent event){
		//MessageList messages = event.getMessage().getChannel().getMessages();
		//MessageList trimmedMessages;
		
		//if(numberOfMessages > messages.size())
		//int size = messages.size();
	}
}
