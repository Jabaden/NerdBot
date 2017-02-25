import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
//0607dc242c370306d3353a2331408057
public class NSFWGetter {
	public void sendNSFWGet() throws Exception{
		String url = "http://www.google.com/search?q=mkyong";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//default is Get
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "NerdBot (by NerdBird on e621)");
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader( 
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
	
	public void sendPost() throws Exception {

		String url = "https://rule34.xxx/index.php?page=post&s=view&id=2291047";
		//String url = "https://rule34.xxx/index.php?page=dapi&s=post&q=index&tags=gay&limit=1"; THIS IS FOR RULE34.XXX API SEE BOOKMARKS
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "NerdBot (by NerdBird on e621)");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "/post/index.xml?limit=1";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		//wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	
	
}
