
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SingleStatusReader {
	private static final String TWITTER_CONSUMER_KEY = "rTkGw97D0eblLnSOJviskDoNv";
	private static final String TWITTER_SECRET_KEY = "CJDWoPs15U4ssNC84nKLfvXtZ9HIiPfqQ58D1F1W2w5CEAFmII";
	private static final String TWITTER_ACCESS_TOKEN = "15495467-rmmrakf4zeRJ71VM69WkEqhV8o87BtQ6i2xm7hs3Q";
	private static final String TWITTER_ACCESS_TOKEN_SECRET = "f3nzXd34NV7dormih1wHbdGcQV30JM8XqtcxT0ojNggCH";
	public void returnSingleStatus(String user){
		

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		    .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
		    .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
		    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
		    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try{
			//Status status = twitter.showStatus(Long.parseLong("12345"));
			//System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			Query userSearch = new Query(user);
			//userSearch.setResultType(Query.RECENT);
			//userSearch.setCount(20);
			//userSearch.
			QueryResult userSearchResult;
			int tweetsRequested = 10;
			int count = 0;
			do{
				userSearchResult = twitter.search(userSearch);
				//System.out.println("result is" + userSearchResult.getCount()); 
				List<Status> tweets = userSearchResult.getTweets();
				
				for(Status tweet : tweets){
					if(tweet.getUser().getScreenName().equalsIgnoreCase("Tolomeo_r")){
						System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() /*+ " - " + tweet.getId()*/);
						System.out.println("The URL for this tweet is: https://twitter.com/" + "Tolomeo_r" + "/status/" + tweet.getId());
						count++;
					}
				}
			}while ((userSearch = userSearchResult.nextQuery()) != null && count != tweetsRequested );
			
			
			
			
			
			
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

			
			System.exit(0);
			
		}
		catch (TwitterException te){
			te.printStackTrace();
			System.out.println("Failed to show status: " + te.getMessage());
			System.exit(-1);
		}
	}
}
