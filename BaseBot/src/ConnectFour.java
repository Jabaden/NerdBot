
public class ConnectFour { //7 wide 6 tall
	public enum color{RED, YELLOW, WHITE};
	private static CFSquare[][] gameboard;
	private String pOneName = "";
	private String pTwoName = "";
	ConnectFour(String playerOne, String playerTwo){
		pOneName = playerOne;
		pTwoName = playerTwo;
		
		gameboard = new CFSquare[7][6];
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 6; j++){
				gameboard[i][j] = new CFSquare(i, j);
			}
		}
	}
	public boolean checkVerticalWinCon(){
		ConnectFour.color currentColor = gameboard[0][0].getColor();
		int consecutiveColors = 0;
		int i = 0;
		int j = 0;
		for(; i < 7; i++){
			
			consecutiveColors = 0;
			currentColor = gameboard[i][j].getColor();
			
			for(; j < 6; j++){
				if(gameboard[i][j].getColor() != currentColor ){
					currentColor = gameboard[i][j].getColor();
					consecutiveColors = 1;
					continue;
				}
				consecutiveColors++;
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
			for(; j < 7; i++){
				if(gameboard[i][j].getColor() != currentColor ){
					currentColor = gameboard[i][j].getColor();
					consecutiveColors = 1;
					continue;
				}
				consecutiveColors++;
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
		while(startingI < 4){
			
			if(gameboard[i][j].getColor() != currentColor ){
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
					//consecutiveColors = 0;
					currentColor = gameboard[i][j].getColor();
					continue;
				}
			}
			else if(j <=0){
				if(startingJ <5){
					startingJ++;
				}
				else if(startingJ >= 5){
					startingI++;
				}
				j = startingJ;
				i = startingI;
				consecutiveColors = 0;
				currentColor = gameboard[i][j].getColor();
				continue;
			}	
			currentColor = gameboard[i][j].getColor();
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
		ConnectFour.color currentColor = gameboard[0][3].getColor();
		while(startingI < 4){
			
			if(gameboard[i][j].getColor() != currentColor ){
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
				if(j == (startingI - 1)){
					startingI++;
					i = startingI;
					j = startingJ;
					//consecutiveColors = 0;
					currentColor = gameboard[i][j].getColor();
					continue;
				}
			}
			else if(j >= 5){
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
				continue;
			}	
			currentColor = gameboard[i][j].getColor();
		}
		//keep going!
		return false;
	}
	
	boolean checkForWinner(){
		return true;
	}
	public static CFSquare[][] getBoard(){
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
	
	void printBoard(){
		boolean firstPass = true;
		char color = 'a';
		for(int j = 0; j < 7; j++){
			if(!firstPass){
				System.out.println();
			}
			for(int i = 0; i < 6; i++ ){
				System.out.print("|" );
				if(ConnectFour.gameboard[i][j].squareColor == ConnectFour.color.RED){
					color = 'R';
				}
				else if(ConnectFour.gameboard[i][j].squareColor == ConnectFour.color.YELLOW){
					color = 'Y';
				}
				else{
					color = 'W';
				}
				
				System.out.print(color + "|");
			}
			firstPass = false;
		}
	}
	
	
	void addPiece(int x, int y, ConnectFour.color clr){
		//ABCDEFG
	  /* 1
	   * 2
	   * 3
	   * 4
	   * 5
	   * 6
	   */
		
		CFSquare tempSquare = ConnectFour.gameboard[x][y];
		
		if(tempSquare.squareColor == ConnectFour.color.RED ||  tempSquare.squareColor == ConnectFour.color.RED){
			System.out.println("Collumn is full, choose another");
			return;
		}
		
		if(tempSquare.getLower(x, y) == null){
			ConnectFour.gameboard[x][y].squareColor = clr;
			return;
		}
		
		CFSquare pointer;
		
		while(tempSquare != null){
			pointer = tempSquare;
			tempSquare = tempSquare.getLower(tempSquare.xPos, tempSquare.yPos );
			if( tempSquare == null){
				ConnectFour.gameboard[x][y] = pointer;
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
			if(y > 5){
				return null;
			}
		return ConnectFour.getBoard()[x][y+1];
		}
		
		ConnectFour.color getColor(){
			return this.squareColor;
		}
		
	}
	
	
	
}