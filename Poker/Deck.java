//import random method
import java.security.SecureRandom;
//declare the deck class
public class Deck {
	//declare the array of deck with type Card
	private Card[] deck = new Card[52];
	
	//getter and setters for deck
	public Card[] getDeck() {
		return deck;
	}
	
	public void setDeck(Card[] deck) {
		this.deck = deck;
	}

	//declare variables currentCard to keep track of number.
	int currentCard;
	//declare variables to generate random number
	private static final SecureRandom randomNumbers = new SecureRandom();
	//the constructor for the deck
	public Deck() {
		//specify the String suit and rank
		String[] suit = {"Diamond", "Heart", "Club", "Spade"};
		String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10","j", "q", "k", "A"};
		//initialize the currentCard
		currentCard=0;
		//get a new in-ordered deck of card
		for (int count = 0; count < deck.length; count++)
			//give each card a different rank and suit
			deck[count] =new Card(rank[count % 13], suit[count / 13]);
	}
	//the shuffle class put the card into random order
	public void shuffle() {
		//initialize the card to 0
		currentCard=0;
		//looping through every card in the deck
		for (int first = 0; first < deck.length; first++) {
			int second;
			//declare another variable to store the random number
			second= randomNumbers.nextInt(52);
			//use another variable to help the reassignment
			Card temp=deck[first];
			deck[first]=deck[second];
			deck[second]=temp;	
			
		}
	}	
		//this class is used to print the information of the card from the toString method
		public Card dealCard () {
			if (currentCard<deck.length) {
				return deck[currentCard++];
				
			} else {
				return null;
			}
		}
		
	}



