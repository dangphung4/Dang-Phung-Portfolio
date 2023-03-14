import java.util.*;
import java.util.Scanner;
import static java.lang.System.out;


public class BattleShips {
		public static int rows=5;
		public static int columns =5;
		public static int pShips;
		public static int cShips;
		 public static String[][] grid = new String[rows][columns];
		 public static int[][] misses= new int[rows][columns];
		
		 
		public static void createMap(){
			System.out.println();
			//creating grid
			for(int a =0; a<rows; a++ ){
				out.print("  " + a);
			}
			out.println();
			for(int i = 0; i < columns; i++){
				out.print(i);
				for (int j =0; j<rows; j++){
					grid[i][j] = " | ";
					out.print(grid[i][j]);
				}
				out.println();
			
			}
		}
		//reprint map function
		public static void updateMap() {
			for(int a =0; a<rows; a++ ){
				out.print("  " + a);
			}
			out.println();
			for(int i = 0; i < columns; i++){
				out.print(i);
				for (int j =0; j<rows; j++){
					if(grid[i][j] == " 0 "){
						System.out.print(" | ");
					}
					else{
					System.out.print(grid[i][j]);
					}
				}
				out.println();
			}

		}
		
		public static void deployPShips() {
			Scanner sc = new Scanner(System.in);
			out.println("Deploying ships!");
			BattleShips.pShips =4;
			//ask user for inputs
			for(int i =1; i<=BattleShips.pShips;){
				out.print("Enter X coordinate for your " + i + " ship: ");
			int x = sc.nextInt();
			out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = sc.nextInt();
			//checking algorithims
			if((x >=0 && x < rows) && (y >=0 && y<columns) && (grid[y][x]== " | ")){
				grid[y][x] =   " U ";
				i++;
			}
			else if((x >= 0 && x < rows) && (y >= 0 && y < columns) && grid[y][x]== "0")
                System.out.println("You already placed a ship there.");
            else if((x < 0 || x >= rows) || (y < 0 || y >= columns))
                System.out.println("You cannot place ships outside the dimensions of " + rows + " by " + columns);
			}
			
			updateMap();
		}
		
		public static void deployCShips() {
			BattleShips.cShips=4;
			out.println("The AI is deploying ships.");
			//random ships
			for (int i=1; i <=BattleShips.cShips;){
				int x = (int)(Math.random() * rows+1);
            	int y = (int)(Math.random() * columns+1);
				//checking algorithims
				if((x >= 0 && x < rows) && (y >= 0 && y < columns) && (grid[y][x]== " | ")){
					grid[y][x]= " 0 ";
					out.println("AI Ship " + i + " deployed.");
					i++;
				}
				else if((x >= 0 && x < rows) && (y >= 0 && y < columns) && (grid[y][x]== " U ")){
					grid[y][x]= " U ";
				}
			}
			updateMap();

		}
		public static void pTurn() {
			out.println("Your turn to go.");
			int x =-1;
			int y =-1;
			//keep asking user while inputs are incorrect
			while((x < 0 || x >= rows) || (y < 0 || y >= columns)){
				Scanner sc = new Scanner(System.in);
				out.print("Enter X coordinate: ");
            	x = sc.nextInt();
            	out.print("Enter Y coordinate: ");
            	y = sc.nextInt();

				if ((x >= 0 && x < rows) && (y >= 0 && y < columns)){
					if (grid[y][x] == " 0 "){
						out.println("You have hit an AI Ship!");
						grid[y][x] = " X ";
						BattleShips.cShips -=1;
					}
					else if (grid[y][x] == " U ") {
						out.println("You have sunk your own ship...");
						grid[y][x] = " X ";
						BattleShips.pShips -=1;
					}
					else if (grid[y][x] == " | ") {
						out.println("You have missed.");
						grid[y][x] = " - ";
					}
				}
				else if  ((x < 0 || x >= rows) || (y < 0 || y >= columns)){
					out.println("You cannot shoot ships outside the dimensions of " + rows + " by " + columns);
				}
			}
			
		}
		
		public static void cTurn(){
			out.println("Now it's the AI's turn.");
			int x =-1;
			int y =-1;
			//method to guess random coordinates
			while((x < 0 || x >= rows) || (y < 0 || y >= columns)){
				x = (int)(Math.random() * rows);
           		y = (int)(Math.random() * columns);

				if((x >= 0 && x < rows) && (y >= 0 && y < columns)){
					if (grid[y][x] == " U "){
						out.println("The AI has hit your ship!");
						grid[y][x] = " X ";
						BattleShips.pShips -=1;
				}
					else if (grid[y][x]== " 0 ") {
						out.println("The AI has sunk its own ship...");
						grid[y][x]= " X ";
						BattleShips.cShips -=1;
				}
					else if ((grid[y][x] == " | ") || (grid[y][x] == " - ")) {
						out.println("The AI has missed.");
						grid[y][x] = " - ";

				}
					else if ((grid[y][x]==" X ")){
						out.println("The AI has missed.");

					}

			}
		}
			
			
		}
		public static void battle(){
			//taking turns until ships go to zero
			pTurn();
			cTurn();
			updateMap();

			System.out.println();
			System.out.println("Your ships: " + BattleShips.pShips + " | AI ships: " + BattleShips.cShips);
			System.out.println();
		}
		public static void gameOver(){
			if(BattleShips.pShips > 0 && BattleShips.cShips <= 0)
            	System.out.println("YOU WON!!!");
        	else
            	System.out.println("You have lost. You'll get them next time.");
        	System.out.println();
		}
		
		
		
	

	public static void main(String[] args) {
		out.println("Welcome to battleships!");

		createMap();
		deployPShips();
		deployCShips();

		while(BattleShips.pShips>0 && BattleShips.cShips>0){
			battle();
		}

		gameOver();
		
		
	}

}

