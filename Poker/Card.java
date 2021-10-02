//define the Card class
public class Card {
	//declare String variables
	private String suit;
	private String rank;
	
	//Card constructors overload
	public Card() {
		this.suit=null;
		this.rank=null;
	}
	
	//Card construtors
	public Card(String rank, String suit) {
		this.suit = suit;
		this.rank = rank;
	}
	//getters for the suit
	public String getSuit() {
		return suit;
	}
	//setters for the suit
	public void setSuit(String suit) {
		this.suit = suit;
	}
	//getter for the rank
	public String getRank() {
		return rank;
	}
	//setter for the rank
	public void setRank(String rank) {
		this.rank = rank;
	}
	//toString method to print out the cards information
	public String toString() {
		return rank +  " of  "+suit;
	}
}

