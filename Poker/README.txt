Xiurong Yu and Ruolan Lin

Files
Card.java, Deck.java, and DeckDemo.java (for testing)
Hand.java (The driver's program is in the main method of Hand.java, so simply run Hand.java)
Poker.java, DeckDemo.java (for testing)
StringDemo.java

We defined a Card class that models the suit and rank and defines a Deck class which models a deck of all 52 cards using an array. To do so, we created a Card class in Card.java that will take in a suit and a rank and created getters and setters for it. For the deck class, we created two arrays to store all the suit and rank that will allow us to print out all the 52 cards. Specifically, each suit will be printed with each of the 13 ranks, which will result in all 52 cards being printed. With all the 52 cards printed, we can shuffle the order using randomness and temporary variable commented in Deck.Java. 

We have to deal with some hands. In Hands.java, we created two hands, each with 5 cards. To sample without replacement – the same card cannot
appear twice, we set the card to null once the card is picked. In addition, we created a while loop that will regenerate the card if the card is null. 

We give each rank a number, so that it is easier to sort the cards from least to greatest (for the purpose of deciding the winner), and comparing hands. Then we created a checkHand method that will return an integer in which indicates the value of the classification. For instance, a royal flush will return a value of 10000 in comparison to 9000 of a straight flush because the royal flush is bigger, and so on. To do so, we listed all the possibilities in our condition in which a person's hand might be a type of classification. The classification method prints out the type of classification the hand is and the compareHand method will compare the two hand and print who wins. 

We first import the scanner class and then ask the user to input the cards for hand1. Then we use s1 to store the value. Then repeat the same process for hand2. After that, we use the substring method to extract the information for each of the cards for hand1 and hand2 and store them using the matrix and corresponding index. After that, we use the checkHand and compareHand method to classify each of the hand and prints out who wins. 

Running the DeckDemo.java, a new deck will be created and printed, then the deck will be shuffled and printed again. Next, two hands of 5 cards in each will be generated and printed in the console. Then each hand will be sorted (not printed) in the order from the least value card to the greatest value card. Then, the console will print out the type of classification hand 1 and hand 2 is. By comparing the type of classification and the cards the user has, the program will print out who wins in the console. 

To test out different cases, you can create your own hand by commenting out deck.shuffle();
On line 12, line 26, hand.generate(hand1); on line 27, line 29, and hand.generate(hand2); on line 30. Then you can create your own hand1 and hand2 by doing something like the following by placing it below line 31. The following creates 5 cards for hand 1 and hand 2, the number inside [ ] in the end indicates a specific card in the 52 cards. 

hand1[0]=deck.getDeck()[12];
hand1[1]=deck.getDeck()[11];
hand1[2]=deck.getDeck()[10];
hand1[3]=deck.getDeck()[9];
hand1[4]=deck.getDeck()[8];
	
hand2[0]=deck.getDeck()[12];
hand2[1]=deck.getDeck()[11];
hand2[2]=deck.getDeck()[10];
hand2[3]=deck.getDeck()[9];
hand2[4]=deck.getDeck()[8];

The above will print: 
Hand 1 is a High Card
Hand 2 is a Two pair
Hand 2 wins

To print the card of the hand that you create, uncomment line 41 to line 45 on Poker.java, and the console should include: 

3 of  Diamond
5 of  Diamond
9 of  Club
j of  Diamond
k of  Spade
2 of  Diamond
3 of  Diamond
5 of  Diamond
8 of  Heart
9 of  Club

Hand 1 is a High Card
Hand 2 is a Two pair
Hand 2 wins
