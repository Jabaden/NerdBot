import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class ConnectFour { //7 wide 6 tall
	public enum color{RED, YELLOW, WHITE};
	private CFSquare[][] gameboard;
	private String pOneName = ""; //RED
	private String pTwoName = ""; //YELLOW
	ConnectFour(String playerOne, String playerTwo){
		pOneName = playerOne;
		pTwoName = playerTwo;
		
		gameboard = new CFSquare[7][6];
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++){
				gameboard[i][j] = new CFSquare(i, j);
				//count++;
				//System.out.println(count);
			}
		}
	}
	public boolean checkVerticalWinCon(){
		ConnectFour.color currentColor = gameboard[0][0].getColor();
		int consecutiveColors = 0;
		//int i = 0;
		int j = 0;
		for(int i = 0; i < 7; i++){
			j = 0;
			consecutiveColors = 0;
			currentColor = gameboard[i][j].getColor();
			
			for(; j < 6; j++){
				if(gameboard[i][j].getColor() != currentColor ){
					currentColor = gameboard[i][j].getColor();
					consecutiveColors = 1;
					continue;
				}
				if(currentColor != ConnectFour.color.WHITE){
					consecutiveColors++;
				}
				if(consecutiveColors == 4){
					//we got a winner boys
					return true;
				}
			}
		}
		return false;
		
	}
	boolean checkHorizontalWinCon(){
		ConnectFour.color currentColor = gameboard[0][0].getColor();
		int consecutiveColors = 0;
		int i = 0;
		int j = 0;
		
		for(; j < 6; j++){
			consecutiveColors = 0;
			for(i = 0; i < 7; i++){
				if(gameboard[i][j].getColor() != currentColor ){
					currentColor = gameboard[i][j].getColor();
					consecutiveColors = 1;
					continue;
				}
				if(currentColor != ConnectFour.color.WHITE){
					consecutiveColors++;
				}
				if(consecutiveColors == 4){
					//we got a winner boys
					return true;
				}
			}
		}
		return false;
	}
	
	//checks diagonally like ->>> "/"
	boolean checkDiagonalWinConPositive(){
		int consecutiveColors = 0;
		boolean match = true;
		
		//used to keep track of the size of j's loop (j starts at 3 and reaches its max at 5
		int startingJ = 3;
		int startingI = 0;
		int i = startingI;
		int j = startingJ;
		ConnectFour.color currentColor = gameboard[0][3].getColor();
		ConnectFour.color currentTileColor = currentColor;
		while(startingI < 4){
			currentTileColor = gameboard[i][j].getColor();
			if(currentTileColor != currentColor || currentColor == ConnectFour.color.WHITE ){
				match = false;
				currentColor = gameboard[i][j].getColor();
				
			}
			if(match){
				consecutiveColors++;
				if(consecutiveColors >= 4){
					//winnar
					return true;
				}
			}
			match = true;
			
			i++;
			j--;
			
			
			if(startingI > 0){
				if(j == (startingI - 1)){
					startingI++;
					i = startingI;
					j = startingJ;
					consecutiveColors = 1;
					currentColor = gameboard[i][j].getColor();
					currentTileColor = currentColor;
					continue;
				}
			}
			else if(j < 0){
				if(startingJ < 5){
					startingJ++;
				}
				else if(startingJ >= 5){
					startingI++;
				}
				j = startingJ;
				i = startingI;
				consecutiveColors = 0;
				currentColor = gameboard[i][j].getColor();
				currentTileColor = currentColor;
				continue;
			}	
			currentTileColor = gameboard[i][j].getColor();
		}
		//keep going!
		return false;
	}
	
	//checks diagonally like ->>> "\"
	boolean checkDiagonalWinConNegative(){
		int consecutiveColors = 0;
		boolean match = true;
		
		//used to keep track of the size of j's loop (j starts at 3 and reaches its max at 5
		int startingJ = 2;
		int startingI = 0;
		int i = startingI;
		int j = startingJ;
		ConnectFour.color currentColor = gameboard[0][2].getColor();
		ConnectFour.color currentTileColor = currentColor;
		while(startingI < 4){
			currentTileColor = gameboard[i][j].getColor();
			if(currentTileColor != currentColor || currentColor == ConnectFour.color.WHITE){
				consecutiveColors = 1;
				match = false;
				currentColor = gameboard[i][j].getColor();
				
			}
			if(match){
				consecutiveColors++;
				if(consecutiveColors >= 4){
					//winnar
					return true;
				}
			}
			match = true;
			
			i++;
			j++;
			
			
			if(startingI > 0){
				//starts at 1 then 2 then 3
				//ends at 5 then 4 then 3
				if(i == 6){
					startingI++;
					i = startingI;
					j = startingJ;
					consecutiveColors = 0;
					currentColor = gameboard[i][j].getColor();
					currentTileColor = currentColor;
					continue;
				}
			}
			else if(j > 5){
				if(startingJ > 0){
					startingJ--;
				}
				else if(startingJ <= 0){
					startingI++;
				}
				j = startingJ;
				i = startingI;
				consecutiveColors = 0;
				currentColor = gameboard[i][j].getColor();
				currentTileColor = currentColor;
				continue;
			}	
			currentTileColor = gameboard[i][j].getColor();
		}
		//keep going!
		return false;
	}
	
	boolean checkForWinner(){
		if(this.checkDiagonalWinConNegative() 
				|| this.checkDiagonalWinConPositive() 
				|| this.checkHorizontalWinCon()
				|| this.checkVerticalWinCon()){
			System.out.println("WE HAVE A WINNER BOISSSSSSSSSSSS");
			return true;
		}
		System.out.println("No winners biblethump");
		return false;
	}
	
	public CFSquare[][] getBoard(){
		return gameboard;
	}
	
	//converts a collumn character into its corresponding integer value.
	int getTrueValue(char collumn){
		char[] charArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		
		for(int i = 0; i < 7; i++){
			if(charArray[i] == collumn){
				return i;
			}
		}
		System.err.println("Not a value between A-G");
		return -1;
	}
	
	ConnectFour.color getPlayerColor(String name){
		if(this.pOneName == name){
			return ConnectFour.color.RED;
		}
		return ConnectFour.color.YELLOW;
	}
	
	void printBoard(){
		boolean firstPass = true;
		char color = 'a';
		String output = "";
		
		for(int j = 0; j < 6; j++){
			if(!firstPass){
				//System.out.println();
				output += "\n";
			}
			for(int i = 0; i < 7; i++ ){
				//System.out.print("|");
				output += "|";
				
				if(this.gameboard[i][j].squareColor == ConnectFour.color.RED){
					color = 'R';
				}
				else if(this.gameboard[i][j].squareColor == ConnectFour.color.YELLOW){
					color = 'Y';
				}
				else{
					color = 'W';
				}
				
				//System.out.print(color + "|");
				output += color + "|";
			}
			firstPass = false;
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
		IChannel channel;
		
		
		System.out.println(output);
	}
	
	
	void addPiece(int x, ConnectFour.color clr){
		//ABCDEFG
	  /* 1
	   * 2
	   * 3
	   * 4
	   * 5
	   * 6
	   */
		
		CFSquare tempSquare = this.gameboard[x][0];
		
		if(tempSquare.squareColor == ConnectFour.color.RED ||  tempSquare.squareColor == ConnectFour.color.YELLOW){
			System.out.println("Collumn is full, choose another");
			return;
		}
		
		if(tempSquare.getLower(tempSquare.getXPos(), tempSquare.getYPos()) == null){
			this.gameboard[tempSquare.getXPos()][tempSquare.getYPos()].squareColor = clr;
			return;
		}
		
		CFSquare pointer;
		
		while(tempSquare != null){
			pointer = tempSquare;
			tempSquare = tempSquare.getLower(tempSquare.xPos, tempSquare.yPos );
			if( tempSquare == null){
				this.gameboard[pointer.getXPos()][pointer.getYPos()].squareColor = clr;
				return;
			}
			if(tempSquare.getColor() != ConnectFour.color.WHITE){
				this.gameboard[pointer.getXPos()][pointer.getYPos()].squareColor = clr;
				return;
			}
		}
		
	}
	
	public class CFSquare{
		private ConnectFour.color squareColor;
		private int xPos;
		private int yPos;
		
		CFSquare(int xPos, int yPos){
			squareColor = ConnectFour.color.WHITE;
			this.xPos = xPos;
			this.yPos = yPos;
		}
		
		//gets the piece below this one
		CFSquare getLower(int x, int y){
			if(y >= 5){
				return null;
			}
		return getBoard()[x][y+1];
		}
		
		ConnectFour.color getColor(){
			return this.squareColor;
		}
		
		int getXPos(){
			return this.xPos;
		}
		
		int getYPos(){
			return this.yPos;
		}
		
	}
	
	
	
}
