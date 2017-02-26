
import java.util.Iterator;
import java.util.List;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SingleStatusReader {
	public String[] returnSingleStatus(String user, int numOfTweets, IChannel chan){
		
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		    .setOAuthConsumerKey(SensitiveString.TWITTER_CONSUMER_KEY)
		    .setOAuthConsumerSecret(SensitiveString.TWITTER_SECRET_KEY)
		    .setOAuthAccessToken(SensitiveString.TWITTER_ACCESS_TOKEN)
		    .setOAuthAccessTokenSecret(SensitiveString.TWITTER_ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		String[] tweetArray = new String[4];
		try{
			//Status status = twitter.showStatus(Long.parseLong("12345"));
			//System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			Query userSearch = new Query(user);
			//userSearch.setResultType(Query.RECENT);
			//userSearch.setCount(20);
			//userSearch.
			QueryResult userSearchResult;
			int tweetsRequested = 4;
			int count = 0;
			do{
				userSearchResult = twitter.search(userSearch);
				//System.out.println("result is" + userSearchResult.getCount()); 
				List<Status> tweets = userSearchResult.getTweets();
				//for(Status tweet : tweets){
				for(Iterator<Status> iter = tweets.iterator(); iter.hasNext() && count < tweetsRequested;){
					Status tweet = iter.next();
					if(tweet.getUser().getScreenName().equalsIgnoreCase("Tolomeo_r")){
						
						//String tString = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
						//System.out.println(tString /*+ " - " + tweet.getId()*/);
						//tweetArray[tweetElement] = tString;
						//tweetElement++;
						String tString = "https://twitter.com/" + "Tolomeo_r" + "/status/" + tweet.getId();
						System.out.println(tString);
						tweetArray[count] = tString;
						count++;
						//if(count >= tweetsRequested){
							//break;
						//}
					}
				}
			}while ((userSearch = userSearchResult.nextQuery()) != null && count < tweetsRequested );
			
			
			
			
			
			
			/*userSearchResult = twitter.search(userSearch);
			List<Status> tweets = userSearchResult.getTweets();
			for(Status tweet: tweets){
				String test = tweet.getUser().getScreenName();
				if(test.equalsIgnoreCase("tolomeo_r")){
					System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
				}
				userSearch = userSearchResult.nextQuery();
			}
			*/

		}
		catch (TwitterException te){
			te.printStackTrace();
			System.out.println("Failed to show status: " + te.getMessage());
			System.exit(-1);
		}
		
		return tweetArray;
	}
}

/*
IMessage message = event.getMessage(); // Gets the message from the event object NOTE: This is not the content of the message, but the object itself
IChannel channel = message.getChannel(); // Gets the channel in which this message was sent.
try {
	// Builds (sends) and new message in the channel that the original message was sent with the content of the original message.
	new MessageBuilder(this.client).withChannel(channel).withContent(message.getContent()).build();
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
*/