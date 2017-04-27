import java.util.List;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
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
		@EventSubscriber
		public void parseTextForCommand(MessageReceivedEvent event){
			//event.getMessage().getChannel().setTypingStatus(true);
			String commandString = "%%";
			IMessage message = event.getMessage();
			IChannel channel = message.getChannel();
			String text = message.getContent();
			String[] words = text.split(" ");
			String botResponse = "";
			if(text.length() <= 1){
				return;
			}
			if(words[0].substring(0,2).equalsIgnoreCase(commandString)){
				System.out.println("Incoming Request");
				words[0] = words[0].substring(2); //gets rid of %%
				System.out.println("Command word is : " + words[0]);
			}
			else{
				return;
			}
			String coreCommand = words[0];
			if(coreCommand.equalsIgnoreCase("twitter")){
				SingleStatusReader ssr = new SingleStatusReader();
				String[] twitterArray = ssr.returnSingleStatus("tolomeo_r", 2, channel);
				for(String element: twitterArray){
					try {
						// Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
						new MessageBuilder(event.getClient()).withChannel(channel).withContent(element).build();//message.getContent()).build();
					} catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
						System.err.print("Sending messages too quickly!");
						e.printStackTrace();
					} catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
						System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
						e.printStackTrace();
					} catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
						System.err.print("Missing permissions for channel!");
						e.printStackTrace();
					}
				}
				return;
			}
			else if(coreCommand.equalsIgnoreCase("delete")){
				BotCleanup botClean = new BotCleanup(channel);
				botClean.deleteMostRecent();
				botResponse = "Cleaned up a little!";
			}
			//------------------------------------------------------------CONNECT FOUR----------------------------------------------------
			else if(coreCommand.equalsIgnoreCase("C4")){
				
				botResponse = ConnectFourManager.getInstance().parseConnectCommand(words, event);
				
			}
			//--------------------------------------------------------HELP-------------------------------------
			
			
			else if(coreCommand.equalsIgnoreCase("help")){
				
				botResponse = BotUtilities.getInstance().parseHelpCommand(words, event);
				
				
				
				
				try{
					new MessageBuilder(event.getClient()).withChannel(  event.getMessage().getAuthor().getOrCreatePMChannel()  ).withContent(botResponse, MessageBuilder.Styles.CODE).build();
				}catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
					System.err.print("Sending messages too quickly!");
					e.printStackTrace();
				} catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
					System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
					e.printStackTrace();
				} catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
					System.err.print("Missing permissions for channel!");
				}
				return;
			}
			else{
				botResponse = "Unidentified Command. Type %%HELP if you need help!";
			}
			//-----------------------------------------------------RESULT--------------------------------------------------------------
			try{
				
				new MessageBuilder(event.getClient()).withChannel(channel).withContent(botResponse/*, MessageBuilder.Styles.CODE*/).build();
				//event.getMessage().getChannel().setTypingStatus(false);
				
				
			}catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
				System.err.print("Sending messages too quickly!");
				e.printStackTrace();
			} catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
				System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
				e.printStackTrace();
			} catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
				System.err.print("Missing permissions for channel!");
			}
		}
	}
}

