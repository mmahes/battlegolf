package battleGolf;

public class game {
	
	//getting a random x-coord
	public static int xChooser(){
		int xVal= (int)(Math.random()*8);
		return xVal;
	}
	
	//getting a random y-coord
	public static int yChooser(){
		int yVal= (int)(Math.random()*8);
		return yVal;
	}
	
	//getting a random direction
	public static String dirChoose(){
		int guess= (int)(Math.random()*4);
		if (guess==0){
			return "up";			
		}
		else if(guess==1){
			return "down";
		}
		else if(guess==2){
			return "right";
		}
		else{
			return "left";
		}
	}
	
	//getting a random ship
	public static strint shipChooser(){
		strint[] ships=new strint[5];
		for (int i=0; i<ships.length; i++){
			ships[i]=new strint();
		}
		ships[0].setName("carrier");
		ships[0].setSpace(5);
		ships[1].setName("battleship");
		ships[1].setSpace(4);
		ships[2].setName("cruiser");
		ships[2].setSpace(3);
		ships[3].setName("submarine");
		ships[3].setSpace(3);
		ships[4].setName("destroyer");
		ships[4].setSpace(2);
		int x= (int)(Math.random()*5);
		if (x==0){
			return ships[0];
		}
		else if (x==1){
			return ships[1];
		}
		else if(x==2){
			return ships[2];
		}
		else if (x==3){
			return ships[3];
		}
		else{
			return ships[4];
		}
	}
	
	//setting up the board
	public static void setup(char[][] board,int count){
		
		//populates the water
		for (int i=0; i<board.length; i++){
			for (int j=0; j<board[0].length; j++){
					board[i][j]='w';
			}
		}
		
		//Populating the ships
		int shipSpaceCounter=count;
		while(shipSpaceCounter>0){
			int x= xChooser();
			int y= yChooser();
			String dir= dirChoose();
			strint ship= shipChooser();
			
			//filling the ships to the right
			if (dir=="right"){
				int delta=x+ship.getSpace();
				while (delta>board.length-1){
					x=xChooser();
					delta=x+ship.getSpace();
				}
				
				for (int i=x; i<=delta; i++){
					board[i][y]='s';
					shipSpaceCounter=shipSpaceCounter-1;
				}
			}
			
			//filling ships to the left
			if (dir=="left"){
				int delta=x-ship.getSpace();
				while (delta<0){
					x=xChooser();
					delta=x+ship.getSpace();
				}
				for (int i=x; i >=0; i--){
					board[i][y]='s';
					shipSpaceCounter=shipSpaceCounter-1;
				}
			}
			
			//filling up the ships downward
			if (dir=="down"){
				int delta=y-ship.getSpace();
				while (delta>board[0].length-1){
					y=yChooser();
					delta=y+ship.getSpace();
				}
				for (int i=y; i<=delta; i++){
					board[x][i]='s';
					shipSpaceCounter=shipSpaceCounter-1;
				}
			}
			
			//filling up the ships upward
			if (dir=="down"){
				int delta=y-ship.getSpace();
				while (delta<0){
					y=yChooser();
					delta=y+ship.getSpace();
				}
				for (int i=y; i >=0; i--){
					board[x][i]='s';
					shipSpaceCounter=shipSpaceCounter-1;
				}
			}	
		}
	}
	
	//code for playing the game
	
	public static void playGame(){
		
		//sets up pre-conditions for the board
		char[][] gameBoard= new char[8][8];
		int shipSpaceCounter=17;
		int totalGuess=0;
		
		//sets up the board
		setup(gameBoard, shipSpaceCounter);
		
		//Welcomes the person to play the game
		System.out.println("Welcome to BattleGolf!");
		
		while(shipSpaceCounter>0){
			
			//Asks to take the shot
			System.out.println("Take a Shot.");
			
			//Asks for an X-coordinate
			System.out.println("Please enter an X coordinate (A-H)");
			char xCoord=TextIO.getlnChar();
			xCoord=Character.toLowerCase(xCoord);
			int xC= (int)(xCoord)-96;
			
			//Checks to see if the X-coordinate is valid
			while(xCoord<97 || xCoord>104){
				System.out.println("Please enter an X coordinate (A-H) ONLY!");
				xCoord=TextIO.getlnChar();
				xCoord=Character.toLowerCase(xCoord);
				xC=(int)(xCoord)-97;
			}
			
			//Asks for a y-coordinate
			System.out.println("Please enter a Y coordinate (1-8)");
			int yCoord=TextIO.getlnInt();
			int yC= yCoord-1;
			
			//Checks to see if the y-coordinate is valid
			while(yCoord<1 || yCoord>8){
				System.out.println("Please enter a Y coordinate (1-8) ONLY!");
				yCoord=TextIO.getlnInt();
				yC=yCoord-1;
			}
			
			//Checks to see if the Guess is a hit or a miss
			if (gameBoard[xC][yC]=='s'){
				System.out.println("HIT!!!");
				gameBoard[xC][yCoord]='h';
				shipSpaceCounter=shipSpaceCounter-1;
				totalGuess=totalGuess+1;
			}
			
			else if(gameBoard[xC][yC]=='w'){
				System.out.println("MISS :(");
				gameBoard[xC][yC]='m';
				totalGuess=totalGuess+1;
			}
			else {
				System.out.println("MISS :(");
				totalGuess=totalGuess+1;
			}
		}
		
		//Game end message
		System.out.println("All ships have been sunk! Total number of shots taken: " + totalGuess);
		
		//Game replay message
		System.out.println("Would you like to play again? [y or n]");
		char answer=TextIO.getlnChar();
		answer=Character.toLowerCase(answer);
		
		//Checks to see if the replay answer is valid
		while(answer!='y' || answer!='n'){
			System.out.println("Please answer with y or n, don't be dumb!");
			answer=TextIO.getlnChar();
			answer=Character.toLowerCase(answer);
		}
		
		//Chooses whether or not to play the game
		if (answer=='y'){
			playGame();
		}
		else{
			System.out.println("BYE!");
			return;
		}

	}
	
	public static void main(String[] args){
		
		//Actually play the game
		playGame();
	}
}
