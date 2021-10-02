//import the scanner
import java.util.Scanner;
public class StringDemo {

	public static void main(String[] args) {
		//ask the user to input the cards for hand1
	System.out.print("Enter the hand1 in RSRSRSRSRS form where R can be any one of the\r\n" + 
			"set (2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a)\r\n"+"and S can be any one of the set (C,c,H,h,S,s,D,d): ");
	//store the string to the variable
	Scanner s=new Scanner(System.in);
	//String s1=s.next();
	String s1=(String) s.next();
	//ask the user to input the cards for hand2
	System.out.print("Enter the hand2 in RSRSRSRSRS form where R can be any one of the\r\n" + 
			"set (2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a)\r\n"+"and S can be any one of the set (C,c,H,h,S,s,D,d): ");
	//store the string to the variable
	String s2=s.next();
	s.close();
	//create hands object
	Hands hand=new Hands();
	//extract the each card from the string and store them to the hand object. 
	for (int i = 0; i<hand.hand1.length; i++) {
		//get the rank from the string to hand1
		hand.hand1[i].setRank(s1.substring(2*i,2*i+1));
		//get the suit from the string to hand1
		hand.hand1[i].setSuit(s1.substring(2*i+1,2*i+2));
		//get the rank from the string to hand2
		hand.hand2[i].setRank(s2.substring(2*i,2*i+1));
		//get the suit from the string to hand1
		hand.hand2[i].setSuit(s2.substring(2*i+1,2*i+2));
		//System.out.println(hand.hand1[i].getRank());
		//System.out.println(hand.hand1[i].getSuit());
	}
	
	Poker.sort(hand.hand1);
	Poker.sort(hand.hand2);
	//check the classification of hand1
	Poker.checkHand(hand.hand1);
	//check the classification of hand2
	Poker.checkHand(hand.hand2);
	//print the classification
	System.out.print("Hand 1 is a ");
	Poker.classification(hand.hand1);
	System.out.print("Hand 2 is a ");
	Poker.classification(hand.hand2);
	//compare the hand 1 and hand2 to see which hand wins
	Poker.compareHand(hand.hand1, hand.hand2);
	}

}


