import java.util.List;
import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
	   BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();
		
		int option=0;
		while(option==0)// To run again
		{
			// User Interaction implementation
			Scanner scan=new Scanner(System.in);
			System.out.println("________________________________");
			System.out.println("Welcome to CROWN AND ANCHOR Game");
			System.out.println("________________________________");
			System.out.print("Enter name of player:");// User input for player name
			String playerName=scan.next();
			System.out.print("Enter age of player:");//User input for age
			int age=scan.nextInt();
			System.out.print("Enter amount to play(10 to 200):");//User input for balance amount
			int amount=scan.nextInt();
			System.out.print("Place your bet(1 to 150):");//User input for bet amount
			int bet=scan.nextInt();
			
			Player player = new Player(playerName, amount, age);

			Game game = new Game(d1, d2, d3);
			List<DiceValue> cdv = game.getDiceValues();

			int totalWins = 0;
			int totalLosses = 0;

			while (true)
			{
				int winCount = 0;
				int loseCount = 0;
				
				for (int i = 0; i < 100; i++)
				{
					String name = playerName;
					int balance = amount;
					int limit = 0;
					player = new Player(name, balance, age);
					player.setLimit(limit);

					System.out.println(String.format("Start Game %d: ", i));
					System.out.println(String.format("%s starts with balance %d, limit %d", 
							player.getName(), player.getBalance(), player.getLimit()));

					int turn = 0;
					while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200 && player.getBalance() > 0)
					{
						turn++;                    
						DiceValue pick = DiceValue.getRandom();
					   
						System.out.printf("Turn %d: %s bet %d on %s\n",
								turn, player.getName(), bet, pick); 
						
						int winnings = game.playRound(player, pick, bet);
						cdv = game.getDiceValues();
						
						System.out.printf("Rolled %s, %s, %s\n",
								cdv.get(0), cdv.get(1), cdv.get(2));
						
						if (winnings > 0) {
							System.out.printf("%s won %d, balance now %d\n\n",
									player.getName(), winnings, player.getBalance()+bet);
							winCount++; 
						}
						else {
							System.out.printf("%s lost, balance now %d\n\n",
									player.getName(), player.getBalance());
							loseCount++;
						}
						
					} //while

					System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
					System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));
					
				} //for
				
				System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
				totalWins += winCount;
				totalLosses += loseCount;
				System.out.println("Press q to quit from game.");// Quiting game
				String ans = console.readLine();
				if (ans.equals("q")) break;
			} //while true
			
			System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
			
			System.out.println("Do you want to play again? (Press 0 to continue) (1 for to exit)");// To play again or not
			option=scan.nextInt();
		}
	}

}
