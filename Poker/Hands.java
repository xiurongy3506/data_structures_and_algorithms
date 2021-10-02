import java.security.SecureRandom;
public class Hands {
	public Card [] hand1;//=new Card[5];
	public  Card [] hand2;//=new Card[5];
	private static final SecureRandom randomNumbers = new SecureRandom();
	//public static Card[] deck = new Card[52];
	private static Deck deck = new Deck();
	
	//give each hand 5 cards
	public Hands() {
		hand1 = new Card[5];
		hand2 = new Card[5];
		for (int i = 0; i <5; i++) {
			hand1[i]= new Card();
			hand2[i] = new Card();
		}
	}

	public static void generate(Card[] hand) {

		for (int i=0; i<5;i++) {
			//generate random integer within the range 52
			int index= randomNumbers.nextInt(52);
			//if the index is null, regenerate the number
			while (deck.getDeck()[index] == null) {
				index= randomNumbers.nextInt(52);
			} 
//			set the card in hand to a random card from deck
			hand[i]=deck.getDeck()[index];
			System.out.println("This hand has "+ hand[i]);	
//			once the card is picked, set it to null to make sure the card doesn't repeat
			deck.getDeck()[index] = null;
		}


	}
	
	public static void main(String [] args) {
		//make a new hand of cards
		Hands hand = new Hands();
		//shuffle the deck
		deck.shuffle();
		//print and generate two different hands of card
		System.out.println("Hand 1:");
		generate(hand.hand1);
		System.out.println("Hand 2:");
		generate(hand.hand2);
		
//		print the rest of the 42 cards
		System.out.println(" ");
		System.out.println("The rest of the cards are : ");
		for (int i=0;i<52;i++) {
			if (deck.getDeck()[i] != null) {
				System.out.println(deck.getDeck()[i]);
			}
		}
		
		
	}
}

