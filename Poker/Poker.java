public class Poker {
	private static Deck deck = new Deck();
	private static Hands hand = new Hands();
	private static Card [] hand1=new Card[5];
	private static Card [] hand2=new Card[5];
	private static int score;//variable used for checking two pairs for hand1
	private static int score2;//variable used for checking two pairs for hand2
	private static int score3;//variable used for checking one pair for hand1
	private static int score4;//variable used for checking one pair for hand1

	//give each rank a number
	//lowercase and uppercase is included for StringDemo.java purpose
	private int number; 
	public static int getNumber(Card[] hand, int i) {
		if ((hand[i].getRank().equals("A")) || (hand[i].getRank().equals("a"))) {
			return 14;
		}
		if ((hand[i].getRank().equals("K")) || (hand[i].getRank().equals("k"))) {
			return 13;
		}
		if ((hand[i].getRank().equals("Q")) || (hand[i].getRank().equals("q"))) {
			return 12;
		}
		if ((hand[i].getRank().equals("J")) || (hand[i].getRank().equals("j"))) {
			return 11;
		}
		return Integer.parseInt(hand[i].getRank());	
	}

	//sort each card in the order from least to greatest rank using bubble sort algorithm 
	public static void sort(Card[] hand) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4 - i; j++) {
				if (getNumber(hand, j) > getNumber(hand, j+1)) {
					Card temp = hand[j];
					hand[j] = hand[j+1];
					hand[j+1] = temp;
				}
			}
		}
		//for debugging purpose (print out the sorted items)
//		System.out.println(hand[0]);
//		System.out.println(hand[1]);
//		System.out.println(hand[2]);
//		System.out.println(hand[3]);
//		System.out.println(hand[4]);
	}

	//check the card 
	public static int checkHand(Card[] hand) {
		//to check for royal flush
		if (hand[0].getSuit().equals(hand[1].getSuit())) { 
			if (hand[1].getSuit().equals(hand[2].getSuit())) {
				if (hand[2].getSuit().equals(hand[3].getSuit())) {
					if (hand[3].getSuit().equals(hand[4].getSuit())) {
						if ((getNumber(hand, 0) == 10) && (getNumber(hand, 1) == 11) && (getNumber(hand, 2)== 12) && (getNumber(hand, 3)== 13) && (getNumber(hand, 4) == 14)) {
							return 10000;
						}
					}
				}
			}
		} 
		//to check for straight
		boolean straight = false;
		if (hand[0].getSuit().equals(hand[1].getSuit())) {
			if (hand[1].getSuit().equals(hand[2].getSuit())) {
				if (hand[2].getSuit().equals(hand[3].getSuit())) {
					if (hand[3].getSuit().equals(hand[4].getSuit())) {
						for (int i = 0; i < 5; i++) {
							if (getNumber(hand, 0) == getNumber(hand, i) - i ) {
								straight = true;
							} else {
								straight = false;
								break;
							}
						}
					}
				}
			}
		} 
		if (straight) {
			return 9000;
		}
		// to check for Four of a kind
		if(getNumber(hand, 1) == getNumber(hand, 2)) {
			if (getNumber(hand, 2) == getNumber(hand, 3)) {
				if ((getNumber(hand, 3) == getNumber(hand, 4)) || (getNumber(hand, 3) == getNumber(hand, 0))) {
					return 8000;
				}
			}
		}
		//check for full house
		if ((getNumber(hand, 0) == getNumber(hand, 1)) && (getNumber(hand, 1) == getNumber(hand, 2))) {
			if ((getNumber(hand, 3) == getNumber(hand, 4))) {
				return 7000;
			}
		} else if ((getNumber(hand, 2) == getNumber(hand, 3)) && (getNumber(hand, 3) == getNumber(hand, 4))) {
			if ((getNumber(hand, 0) == getNumber(hand, 1))) {
				return 7000;
			}
		}
		//check for flush
		if (hand[0].getSuit().equals(hand[1].getSuit())) { 
			if (hand[1].getSuit().equals(hand[2].getSuit())) {
				if (hand[2].getSuit().equals(hand[3].getSuit())) {
					if (hand[3].getSuit().equals(hand[4].getSuit())) {
						return 6000;	
					}
				}
			}
		} 
		//check for straight 
		for (int i = 0; i < 5; i++) {
			if (getNumber(hand, 0) == getNumber(hand, i) - i ) {
				straight = true;
			} else {
				straight = false;
				break;
			}
		}
		if (straight) {
			return 5000;
		}
		//check for three of a kind
		if(getNumber(hand, 0) == getNumber(hand, 1)) {
			if (getNumber(hand, 1) == getNumber(hand, 2)) {
				return 4000;
			}
		}else if (getNumber(hand, 3) == getNumber(hand, 4)) {
			if (getNumber(hand, 2) == getNumber(hand, 3)) {
				return 4000;
			}
		}else if (getNumber(hand, 1) == getNumber(hand, 2)) {
			if (getNumber(hand, 2) == getNumber(hand, 3)) {
				return 4000;
			}
		}
		//check for two pair
		if (((getNumber(hand, 0) == (getNumber(hand, 1)))) && ((getNumber(hand, 2) == (getNumber(hand, 3)))) || ((getNumber(hand, 1) == (getNumber(hand, 2)))) && ((getNumber(hand, 3) == (getNumber(hand, 4)))) || 
				((getNumber(hand, 0) == (getNumber(hand, 1)))) && ((getNumber(hand, 3) == (getNumber(hand, 4))))) {

				return 3000;
		}
		
		//check for one pair 
		if (((getNumber(hand, 0) == (getNumber(hand, 1)))) || ((getNumber(hand, 1) == (getNumber(hand, 2)))) || ((getNumber(hand, 2) == (getNumber(hand, 3)))) || ((getNumber(hand, 3) == (getNumber(hand, 4))))) {
			return 2000;
		}
		//high card
		return 1000;
	}
	
	//method to print the classification of player’s hand.
	public static void classification(Card[] hand) {
		if (checkHand(hand) == 10000) {
			System.out.println("Royal Flush");
		}else if (checkHand(hand) == 9000) {
			System.out.println("Straight Flush");
		}else if (checkHand(hand) == 8000) {
			System.out.println("Four of a kind");
		}else if (checkHand(hand) == 7000) {
			System.out.println("Full House");
		}else if (checkHand(hand) == 6000) {
			System.out.println("Flush");
		}else if (checkHand(hand) == 5000) {
			System.out.println("Straight");
		}else if (checkHand(hand) == 4000) {
			System.out.println("Three of a kind");
		}else if (checkHand(hand) == 3000) {
			System.out.println("Two pair");
		}else if (checkHand(hand) == 2000) {
			System.out.println("One pair");
		}else {
			System.out.println("High Card");
		}
	}
	
	//identify win or loss or draw
	public static void compareHand(Card[] hand1, Card[] hand2) {
		if (checkHand(hand1) > (checkHand(hand2))) {
			System.out.println("Hand 1 wins");
		}else if (checkHand(hand1) < (checkHand(hand2))) {
			System.out.println("Hand 2 wins");
		}else {
			//when two player has the same classification
			if (checkHand(hand1) == 10000) {//royal flush
				System.out.println("Draw");
			}else if (checkHand(hand1) == 9000) { //straight flush
				if ((getNumber(hand1, 0)) > (getNumber(hand2, 0))) {
					System.out.println("Hand 1 win");
				} else {
					System.out.println("Hand 2 win");
				}
			}else if (checkHand(hand1) == 8000) { //four of a kind
				//check the middle index
				if ((getNumber(hand1, 2)) > (getNumber(hand2, 2))) {
					System.out.println("Hand 1 win");
				} else {
					System.out.println("Hand 2 win");
				}
			}else if (checkHand(hand1) == 7000) {//full house
				if ((getNumber(hand1, 2)) > (getNumber(hand2, 2))) {
					System.out.println("Hand 1 win");
				} else {
					System.out.println("Hand 2 win");
				}
			}else if (checkHand(hand1) == 6000) {//flush
				if ((getNumber(hand1, 4)> getNumber(hand2, 4))) {
					System.out.println("Hand 1 win");
				} else if ((getNumber(hand1, 4) < getNumber(hand2, 4))) {
					System.out.println("Hand 2 win");
				} else {
					System.out.println("Draw"); 
				}
			}else if (checkHand(hand1) == 4000) {//three of a kind
				if ((getNumber(hand1, 0)) > (getNumber(hand2, 0))) {
					System.out.println("Hand 1 win");
				}else {
					System.out.println("Hand 2 win");
				}
			}else if(checkHand(hand1) ==3000) { //two pair
				//check for hand 1
				if (((getNumber(hand1, 0) == (getNumber(hand1, 1)))) && ((getNumber(hand1, 2) == (getNumber(hand1, 3))))){
					score=getNumber(hand1,3);
				}else if(((getNumber(hand1, 1) == (getNumber(hand1, 2)))) && ((getNumber(hand1, 3) == (getNumber(hand1, 4))))){
					score=getNumber(hand1,4);
				}else if (((getNumber(hand1, 0) == (getNumber(hand1, 1)))) && ((getNumber(hand1, 3) == (getNumber(hand1, 4))))) {
					score=getNumber(hand1,4);
				}
				//check for hand 2
				if (((getNumber(hand2, 0) == (getNumber(hand2, 1)))) && ((getNumber(hand2, 2) == (getNumber(hand2, 3))))){
					score2=getNumber(hand2,3);
				}else if(((getNumber(hand2, 1) == (getNumber(hand2, 2)))) && ((getNumber(hand2, 3) == (getNumber(hand2, 4))))) {
					score2=getNumber(hand2,4);
				}else if (((getNumber(hand2, 0) == (getNumber(hand2, 1)))) && ((getNumber(hand2, 3) == (getNumber(hand2, 4))))) {
					score2=getNumber(hand2,4);
				}
				//return who wins
				if(score>score2) {
					System.out.println("Hand 1 win");
				}else if (score<score2) {
					System.out.println("Hand 2 win");
				}else {
					System.out.println("Draw");
				}
					
			}else if (checkHand(hand1) == 2000) { //one pair - score increase by the bigger the index because the card is sort from least to greatest
				if (((getNumber(hand1, 0) == (getNumber(hand1, 1))))){
					score3=getNumber(hand1,1);
				}
				if (((getNumber(hand1, 2) == (getNumber(hand1, 1))))){
					score3=getNumber(hand1,2);
				}
				if (((getNumber(hand1, 2) == (getNumber(hand1, 3))))){
					score3=getNumber(hand1,3);
				}
				if (((getNumber(hand1, 3) == (getNumber(hand1, 4))))){
					score3=getNumber(hand1,4);
				}
				if (((getNumber(hand2, 0) == (getNumber(hand2, 1))))){
					score4=getNumber(hand2,1);
				}
				if (((getNumber(hand2, 2) == (getNumber(hand2, 1))))){
					score4=getNumber(hand2,2);
				}
				if (((getNumber(hand2, 2) == (getNumber(hand2, 3))))){
					score4=getNumber(hand2,3);
				}
				if (((getNumber(hand2, 3) == (getNumber(hand2, 4))))){
					score4=getNumber(hand2,4);
				}
				
				if(score3>score4) {
					System.out.println("Hand 1 win");
				}else if (score3<score4) {
					System.out.println("Hand 2 win");
				}else {
					System.out.println("Draw");
				}
				
			} else {//high hand
				if(((getNumber(hand1, 4) >(getNumber(hand2, 4))))){//compare the largest hand in the high hand
					System.out.println("Hand 1 win");
				}else if (((getNumber(hand1, 4) <(getNumber(hand2, 4))))) {
					System.out.println("Hand 2 win");
				}else if (((getNumber(hand1, 3) >(getNumber(hand2, 3))))) {//compare the second largest hand in the high hand
					System.out.println("Hand 1 win");
				}else if (((getNumber(hand1, 3) <(getNumber(hand2, 3))))) {
					System.out.println("Hand 2 win");
				}else if (((getNumber(hand1, 2) >(getNumber(hand2, 2))))) {//compare the middle hand in the high hand
					System.out.println("Hand 1 win");
				}else if (((getNumber(hand1, 2) <(getNumber(hand2, 2))))) {
					System.out.println("Hand 2 win");
				}else if (((getNumber(hand1, 1) >(getNumber(hand2, 1))))) {//compare the second smallest hand in the high hand
					System.out.println("Hand 1 win");
				}else if (((getNumber(hand1, 1) <(getNumber(hand2, 1))))) {
					System.out.println("Hand 2 win");
				}else if (((getNumber(hand1, 0) >(getNumber(hand2, 0))))) {//compare the smallest hand in the high hand
					System.out.println("Hand 1 win");
				}else if (((getNumber(hand1, 0) <(getNumber(hand2, 0))))) {
					System.out.println("Hand 2 win");
				}else {				
					System.out.println("Draw");
				}
			}
		}
	}
}
