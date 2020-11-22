// Assignment 1 - Game Basics: Cards and Hands
public class Main
{
   public static void main(String[] args)
   {
      // Test of Phase 1: The Card Class

      /*
      Card card1, card2, card3;

      card1 = new Card();
      card2 = new Card('1');
      card3 = new Card('J', Card.Suit.clubs);

      card1.set('A', Card.Suit.spades);

      String report = card1.toString() + "\n" + card2.toString() + "\n"
            + card3.toString() + "\n";

      System.out.println(report);

      card1.set('1', Card.Suit.spades);
      card2.set('Q', Card.Suit.spades);

      report = card1.toString() + "\n" + card2.toString() + "\n"
            + card3.toString() + "\n";

      System.out.println(report);
       */

      // Test of Phase 2: The Hand Class

      Card card1, card2, card3, card4;

      card1 = new Card('A', Card.Suit.spades);
      card2 = new Card('5', Card.Suit.hearts);
      card3 = new Card('9', Card.Suit.clubs);
      card4 = new Card('K', Card.Suit.diamonds);

      Hand hand;

      hand = new Hand();

      for (int i = 0; i < Hand.MAX_CARDS_PER_HAND; i++)
      {
         switch (i % 4)
         {
         case 0:
            hand.takeCard(card1);
            break;
         case 1:
            hand.takeCard(card2);
            break;
         case 2:
            hand.takeCard(card3);
            break;
         case 3:
            hand.takeCard(card4);
            break;
         }

         if (i == Hand.MAX_CARDS_PER_HAND - 1)
         {
            System.out.println("Hand full");
         }
      }

      System.out.println();

      System.out.println("After deal");

      String report = hand.toString();

      System.out.println(report);

      System.out.println();

      System.out.println("Testing inspectedCard()");

      report = hand.inspectCard(-1).toString();

      System.out.println(report);

      report = hand.inspectCard(1).toString();

      System.out.println(report);

      System.out.println();

      for (int i = hand.getNumCards(); i > 0; i--)
      {
         System.out.println("Playing " + hand.inspectCard(i));
         hand.playCard();
      }

      System.out.println();

      System.out.println("After playing all cards");

      report = hand.toString();

      System.out.println(report);
   }
}

class Card
{
   // static public constants
   public static final char DEFAULT_VAL = 'A';
   public static final Suit DEFAULT_SUIT = Suit.spades;

   // a public, nested enum type
   public enum Suit
   {
      spades, hearts, clubs, diamonds
   }

   // private member data
   private char value;
   private Suit suit;
   private boolean errorFlag;

   // public methods
   // constructors
   public Card()
   {
      this(DEFAULT_VAL, DEFAULT_SUIT);
   }

   public Card(char value)
   {
      this(value, DEFAULT_SUIT);
   }

   public Card(Suit suit)
   {
      this(DEFAULT_VAL, suit);
   }

   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   // stringizer
   public String toString()
   {
      String retVal; // return value
      if (errorFlag)
      {
         retVal = String.valueOf(this.value) + " of " + this.suit;
      } else
      {
         retVal = "** illegal **";
      }
      return retVal;
   }

   // mutator
   public boolean set(char value, Suit suit)
   {
      char upVal; // for upcasing char
      boolean valid; // return value

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      // check for validity
      if (isValid(upVal, suit))
      {
         valid = true;
         this.value = upVal;
         this.suit = suit;
         errorFlag = true;
      } else
      {
         valid = false;
         this.value = 'A';
         this.suit = suit;
         errorFlag = false;
      }
      return valid;
   }

   // accessors
   public char getValue()
   {
      return this.value;
   }

   public Suit getSuit()
   {
      return this.suit;
   }

   public boolean getErrorFlag()
   {
      return this.errorFlag;
   }

   // comparator
   public boolean equals(Card card)
   {
      boolean retVal = true; // return value

      if (this.value != card.value || this.suit != card.suit
            || this.errorFlag != card.errorFlag)
      {
         retVal = false;
      }

      return retVal;
   }

   // private methods
   private static boolean isValid(char value, Suit suit)
   {
      boolean valid = false; // return value

      // suit is enum, so no test needed: all suits allowed

      // test val: check for validity
      if (value == 'A' || value == 'K' || value == 'Q' || value == 'J'
            || value == 'T' || (value >= '2' && value <= '9'))
      {
         valid = true;
      }
      return valid;
   }
}

class Hand
{
   // static class constants
   public static final int MAX_CARDS_PER_HAND = 50;

   // private member data
   private Card[] myCards = new Card[MAX_CARDS_PER_HAND];
   private int numCards = 0;

   // public methods
   // constructor
   public Hand()
   {

   }

   // remove all cards from the hand
   public void resetHand()
   {
      numCards = 0;
   }

   // add card to the next available position
   public boolean takeCard(Card card)
   {
      Card tempCard = new Card(); // for copying card
      char tempValue; // for copying card
      Card.Suit tempSuit; // for copying card
      boolean retVal = true; // return value

      if (numCards >= MAX_CARDS_PER_HAND)
      {
         retVal = false;
      } else if (card.getErrorFlag())
      {
         tempValue = card.getValue();
         tempSuit = card.getSuit();

         tempCard.set(tempValue, tempSuit);

         myCards[numCards] = tempCard;
         numCards++;
      }

      return retVal;
   }

   // return and remove the card in the top occupied position
   public Card playCard()
   {
      Card tempCard = new Card(); // return value

      if (numCards <= 0)
      {
         tempCard.set('1', Card.Suit.spades);
      } else
      {
         tempCard.set(myCards[numCards - 1].getValue(),
               myCards[numCards - 1].getSuit());
         numCards--;
      }

      return tempCard;
   }

   // stringizer
   public String toString()
   {
      String retVal; // return value

      retVal = "Hand = ( ";

      for (int i = 0; i < numCards; i++)
      {
         retVal += myCards[i].toString();

         if (i < numCards - 1)
         {
            retVal += ", ";
         }
      }

      retVal += " )";

      return retVal;
   }

   // accessors
   public int getNumCards()
   {
      return numCards;
   }

   public Card inspectCard(int k)
   {
      Card tempCard = new Card(); // return value

      if (k < 0 || k > numCards)
      {
         tempCard.set('1', Card.Suit.spades);
      } else
      {
         tempCard.set(myCards[k - 1].getValue(), myCards[k - 1].getSuit());
      }

      return tempCard;
   }
}

/* Run of Test of Phase 1: The Card Class --------------------------------------

A of spades
** illegal **
J of clubs

** illegal **
Q of spades
J of clubs

End of Run */

/* Run of Test of Phase 2: The Hand Class --------------------------------------

Hand full

After deal
Hand = ( A of spades, 5 of hearts, 9 of clubs, K of diamonds, A of spades, 5 of 
hearts, 9 of clubs, K of diamonds, A of spades, 5 of hearts, 9 of clubs, K of di
amonds, A of spades, 5 of hearts, 9 of clubs, K of diamonds, A of spades, 5 of h
earts, 9 of clubs, K of diamonds, A of spades, 5 of hearts, 9 of clubs, K of dia
monds, A of spades, 5 of hearts, 9 of clubs, K of diamonds, A of spades, 5 of he
arts, 9 of clubs, K of diamonds, A of spades, 5 of hearts, 9 of clubs, K of diam
onds, A of spades, 5 of hearts, 9 of clubs, K of diamonds, A of spades, 5 of hea
rts, 9 of clubs, K of diamonds, A of spades, 5 of hearts, 9 of clubs, K of diamo
nds, A of spades, 5 of hearts )

Testing inspectedCard()
** illegal **
A of spades

Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades
Playing K of diamonds
Playing 9 of clubs
Playing 5 of hearts
Playing A of spades

After playing all cards
Hand = (  )

End of Run */
