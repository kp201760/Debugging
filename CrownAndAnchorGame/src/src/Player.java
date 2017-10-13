
public class Player {
	private String name;
	private int balance;
	int age;
	private int limit;
	
	public Player(String name, int balance, int age) {
		if (name == null || name .isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
		if (balance < 0 && balance > 200) throw new IllegalArgumentException("Balance cannot be negative and more than 200");// Setting limit between more than zero and 200
		if (age < 18) throw new IllegalArgumentException("Age cannot be less than 18");// Throw exception if age is less than 18
		this.name = name;
		this.balance = balance;
		this.age=age;
		this.limit = 0;
	}
		
	public String getName() { return name; }
	public int getBalance() { return balance; }
	public int getLimit() { return limit; }
	
	public void setLimit(int limit) {
		if (limit < 0) throw new IllegalArgumentException("Limit cannot be negative.");
		if (limit > balance)  throw new IllegalArgumentException("Limit cannot be greater than balance.");
		this.limit = limit;
	}

	public boolean balanceExceedsLimit() {
		return (balance > limit);
	}
	
	public boolean balanceExceedsLimitBy(int amount) {
		return (balance - amount > limit);
	}
	
	public void takeBet(int bet) {
		if (bet < 0 && bet > 150 ) throw new IllegalArgumentException("Bet cannot be negative.");//Setting limit for bet
		if (!balanceExceedsLimitBy(bet)) throw new IllegalArgumentException("Placing bet would go below limit.");
		balance = balance - bet;
	}
	
	public void receiveWinnings(int winnings) {
		if (winnings < 0) throw new IllegalArgumentException("Winnings cannot be negative.");
		balance = balance + winnings;		
	}
	
	public String toString() {
		return String.format("Player: %s, Balance: %d, Limit: %d", name, balance, limit);
	}
}
