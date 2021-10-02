public class DeckDemo {
	//execute the program
	public static void main(String[] args) {
		//create a new inordered deck
		Deck deck=new Deck();
		//print every card in the deck
		System.out.println("Cards in the deck: ");
		for (int i=0;i<52;i++) {
			System.out.println(deck.dealCard());
		}
		//shuffle the deck
		deck.shuffle();
		//print out the deck after shuffling
		System.out.println("");
		System.out.println("Deck after shuffling: ");
		for (int i=0;i<52;i++) {
			System.out.println(deck.dealCard());
		}
	
		//make a new hand of cards
		Hands hand = new Hands();
		Card[] hand1=new Card[5];
		Card[] hand2=new Card[5];
		//print and generate two different hands of card
		System.out.println("");
		System.out.println("Hand 1:");
		hand.generate(hand1);
		System.out.println("");
		System.out.println("Hand 2:");
		hand.generate(hand2);

				//royal flush
//						hand1[0]=deck.getDeck()[12];
//						hand1[1]=deck.getDeck()[11];
//						hand1[2]=deck.getDeck()[10];
//						hand1[3]=deck.getDeck()[9];
//						hand1[4]=deck.getDeck()[8];
//		
//						hand2[0]=deck.getDeck()[12];
//						hand2[1]=deck.getDeck()[11];
//						hand2[2]=deck.getDeck()[10];
//						hand2[3]=deck.getDeck()[9];
//						hand2[4]=deck.getDeck()[8];
//						
				//straight flush
//						hand2[0]=deck.getDeck()[4];
//						hand2[1]=deck.getDeck()[5];
//						hand2[2]=deck.getDeck()[6];
//						hand2[3]=deck.getDeck()[7];
//						hand2[4]=deck.getDeck()[8];
//						

				//Four of a kind
				//		hand1[0]=deck.getDeck()[4];
				//		hand1[1]=deck.getDeck()[5];
				//		hand1[2]=deck.getDeck()[6];
				//		hand1[3]=deck.getDeck()[7];
				//		hand1[4]=deck.getDeck()[8];

				//Full house
				//		hand1[0]=deck.getDeck()[0];
				//		hand1[1]=deck.getDeck()[0];
				//		hand1[2]=deck.getDeck()[0];
				//		hand1[3]=deck.getDeck()[1];
				//		hand1[4]=deck.getDeck()[1];

				//Flush
//				hand1[0]=deck.getDeck()[2];
//				hand1[1]=deck.getDeck()[6];
//				hand1[2]=deck.getDeck()[8];
//				hand1[3]=deck.getDeck()[9];
//				hand1[4]=deck.getDeck()[11];

				//Straight
//				hand1[0]=deck.getDeck()[4];
//				hand1[1]=deck.getDeck()[17];
//				hand1[2]=deck.getDeck()[6];
//				hand1[3]=deck.getDeck()[7];
//				hand1[4]=deck.getDeck()[8];
				
				//Three of a kind
//				hand1[0]=deck.getDeck()[3];
//				hand1[1]=deck.getDeck()[3];
//				hand1[2]=deck.getDeck()[3];
//				hand1[3]=deck.getDeck()[20];
//				hand1[4]=deck.getDeck()[4];
				
				//Two pair
//				hand1[0]=deck.getDeck()[7];
//				hand1[1]=deck.getDeck()[7];
//				hand1[2]=deck.getDeck()[47];
//				hand1[3]=deck.getDeck()[24];
//				hand1[4]=deck.getDeck()[24];
//				
//				hand2[0]=deck.getDeck()[42];
//				hand2[1]=deck.getDeck()[42];
//				hand2[2]=deck.getDeck()[4];
//				hand2[3]=deck.getDeck()[19];
//				hand2[4]=deck.getDeck()[19];
				
				//One pair
//				hand1[0]=deck.getDeck()[1];
//				hand1[1]=deck.getDeck()[1];
//				hand1[2]=deck.getDeck()[3];
//				hand1[3]=deck.getDeck()[9];
//				hand1[4]=deck.getDeck()[33];
//				
//				hand2[0]=deck.getDeck()[1];
//				hand2[1]=deck.getDeck()[0];
//				hand2[2]=deck.getDeck()[3];
//				hand2[3]=deck.getDeck()[33];
//				hand2[4]=deck.getDeck()[33];
				
				//high card
//				hand1[0]=deck.getDeck()[1];
//				hand1[1]=deck.getDeck()[50];
//				hand1[2]=deck.getDeck()[3];
//				hand1[3]=deck.getDeck()[9];
//				hand1[4]=deck.getDeck()[33];
//				
//				hand2[0]=deck.getDeck()[1];
//				hand2[1]=deck.getDeck()[0];
//				hand2[2]=deck.getDeck()[3];
//				hand2[3]=deck.getDeck()[33];
//				hand2[4]=deck.getDeck()[19];
						
		//make a new poker class
		Poker poker = new Poker();
		poker.sort(hand1);
		poker.sort(hand2);
		System.out.println("");
		System.out.print("Hand 1 is a ");
		poker.classification(hand1);
		System.out.print("Hand 2 is a ");
		poker.classification(hand2);
//		System.out.println(poker.checkHand(hand1));
//		System.out.println(poker.checkHand(hand2));
		poker.compareHand(hand1, hand2);
	}

}
