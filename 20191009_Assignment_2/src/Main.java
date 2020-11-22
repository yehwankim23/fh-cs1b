import java.util.Random;
import java.util.Scanner;

// Assignment 2 - Adding a Deck
public class Main
{
   public static void main(String[] args)
   {
      // Test of Phase 1: The Deck Class

      /* -----------------------------------------------------------------------
      Deck deck = new Deck(2);

      String report = "";

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         report += deck.dealCard().toString();

         if (i > 0)
         {
            report += " / ";
         }
      }

      System.out.println(report + "\n");

      deck.init(2);
      deck.shuffle();

      report = "";

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         report += deck.dealCard().toString();

         if (i > 0)
         {
            report += " / ";
         }
      }

      System.out.println(report + "\n");

      deck.init(1);

      report = "";

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         report += deck.dealCard().toString();

         if (i > 0)
         {
            report += " / ";
         }
      }

      System.out.println(report + "\n");

      deck.init(1);
      deck.shuffle();

      report = "";

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         report += deck.dealCard().toString();

         if (i > 0)
         {
            report += " / ";
         }
      }

      System.out.println(report + "\n");
      End of Test ----------------------------------------------------------- */

      // Test of Phase 2: The Deck and Hand Classes

      final int MAX_HANDS = 10;
      final int MIN_HANDS = 2;

      int numHands = -1;

      Deck deck = new Deck(1);
      Hand[] hands = new Hand[MAX_HANDS];

      Scanner scanner = new Scanner(System.in);

      boolean isValid = false;

      while (!isValid)
      {
         System.out.print("How many hands? (1 - 10, please): ");
         int tempNumHands;

         try
         {
            tempNumHands = Integer.parseInt(scanner.next());

            if (tempNumHands < MIN_HANDS || tempNumHands > MAX_HANDS)
            {
               System.out.println("Error! Input out of range.\n");
            } else
            {
               numHands = tempNumHands;
               isValid = true;

               scanner.close();
            }
         } catch (NumberFormatException error)
         {
            System.out.println("Error! Non-numeric input.\n");
         }
      }

      for (int i = 0; i <= numHands - 1; i++)
      {
         hands[i] = new Hand();
         System.out.println(hands[i].toString());
      }

      int whichHand; // for cycling between hands

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         whichHand = i % numHands;

         hands[whichHand].takeCard(deck.inspectCard(i));
         deck.dealCard();
      }

      System.out.println("\n" + "Here are our hands, from unshuffled deck:");

      for (int i = 0; i < numHands; i++)
      {
         System.out.println(hands[i].toString() + "\n");
      }

      for (int i = 0; i < numHands; i++)
      {
         hands[i].resetHand();
      }

      deck.init(1);
      deck.shuffle();

      for (int i = deck.getTopCard() - 1; i >= 0; i--)
      {
         whichHand = i % numHands;

         hands[whichHand].takeCard(deck.inspectCard(i));
         deck.dealCard();
      }

      System.out.println("\n" + "Here are our hands, from SHUFFLED deck:");

      for (int i = 0; i < numHands; i++)
      {
         System.out.println(hands[i].toString() + "\n");
      }
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
         retVal = String.valueOf(value) + " of " + suit;
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
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
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

class Deck
{
   // private static class constants
   private static int MAX_PACKS = 6;
   private static int MIN_PACKS = 1;
   private static int NUM_CARDS_PER_PACK = 52;
   private static int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;
   private static int DEFAULT_NUM_PACKS = 1;

   // private static member data
   private static Card[] masterPack = new Card[NUM_CARDS_PER_PACK];
   private static boolean firstTime = true;

   // private member data
   private Card[] cards;
   private int topCard;
   private int numPacks;

   // public methods
   // constructors
   public Deck(int numPacks)
   {
      allocateMasterPack();

      // instantiate array elements
      cards = new Card[MAX_CARDS_PER_DECK];

      for (int i = 0; i < cards.length; i++)
      {
         cards[i] = new Card();
      }

      // assign card objects
      if (!init(numPacks))
      {
         init(DEFAULT_NUM_PACKS);
      }
   }

   public Deck()
   {
      this(DEFAULT_NUM_PACKS);
   }

   public boolean init(int numPacks)
   {
      boolean retVal; // return value

      // give the client a chance to change the number of packs in the deck
      this.numPacks = numPacks;

      if (this.numPacks < MIN_PACKS || this.numPacks > MAX_PACKS)
      {
         // numPacks is out of range
         retVal = false;
      } else
      {
         // re-populate cards[]
         for (int i = 0; i < this.numPacks; i++)
         {
            for (int j = 0; j < NUM_CARDS_PER_PACK; j++)
            {
               // reference the masterPack[] objects
               cards[NUM_CARDS_PER_PACK * i + j].set(masterPack[j].getValue(),
                     masterPack[j].getSuit());
            }
         }

         // reset topCard
         topCard = this.numPacks * NUM_CARDS_PER_PACK;

         retVal = true;
      }

      return retVal;
   }

   public void shuffle()
   {
      Random random = new Random();

      Card tempCard = new Card();
      int randomPosition;

      for (int i = 0; i < numPacks * NUM_CARDS_PER_PACK; i++)
      {
         randomPosition = random.nextInt(numPacks * NUM_CARDS_PER_PACK);

         tempCard = cards[i];
         cards[i] = cards[randomPosition];
         cards[randomPosition] = tempCard;
      }
   }

   public Card dealCard()
   {
      Card tempCard = new Card(); // return value

      if (cards.length <= 0)
      {
         // cards[] is empty
         tempCard.set('1', Card.Suit.spades);
      } else
      {
         tempCard.set(cards[topCard - 1].getValue(),
               cards[topCard - 1].getSuit());
         topCard--;
      }

      return tempCard;
   }

   // accessors
   public int getTopCard()
   {
      return topCard;
   }

   public Card inspectCard(int k)
   {
      // return copy of the card at position k
      Card tempCard = new Card(); // return value

      if (k < 0 || k > topCard - 1)
      {
         // k is out of range
         tempCard.set('1', Card.Suit.spades);
      } else
      {
         tempCard.set(cards[k].getValue(), cards[k].getSuit());
      }

      return tempCard;
   }

   // private methods
   private static void allocateMasterPack()
   {
      int i, j; // for loop counters
      Card.Suit suit;
      char value;

      if (firstTime == true)
      {
         // instantiate array elements
         for (i = 0; i < masterPack.length; i++)
         {
            masterPack[i] = new Card();
         }

         for (i = 0; i < 4; i++)
         {
            // set suit
            switch (i)
            {
            case 0:
               suit = Card.Suit.spades;
               break;
            case 1:
               suit = Card.Suit.hearts;
               break;
            case 2:
               suit = Card.Suit.clubs;
               break;
            case 3:
               suit = Card.Suit.diamonds;
               break;
            default:
               suit = Card.Suit.spades;
               break;
            }

            // set value
            masterPack[13 * i].set('A', suit);
            for (j = 1, value = '2'; j < 9; j++, value++)
            {
               masterPack[13 * i + j].set(value, suit);
            }
            masterPack[13 * i + 9].set('T', suit);
            masterPack[13 * i + 10].set('J', suit);
            masterPack[13 * i + 11].set('Q', suit);
            masterPack[13 * i + 12].set('K', suit);
         }

         firstTime = false;
      } else
      {
         // return without doing anything
         return;
      }
   }
}

/* Run of Test of Phase 1: The Deck Class --------------------------------------

K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 
8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 
3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of c
lubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clu
bs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K of hearts / Q of hear
ts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of 
hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K
 of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades
 / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of sp
ades / A of spades / K of diamonds / Q of diamonds / J of diamonds / T of diamon
ds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamon
ds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs 
/ Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 
6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K 
of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts 
/ 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hea
rts / A of hearts / K of spades / Q of spades / J of spades / T of spades / 9 of
 spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 
3 of spades / 2 of spades / A of spades

Q of hearts / A of diamonds / 3 of spades / 5 of diamonds / A of clubs / Q of cl
ubs / 9 of hearts / J of hearts / 4 of diamonds / 3 of clubs / 2 of clubs / K of
 clubs / 6 of clubs / T of hearts / 6 of diamonds / 4 of hearts / 5 of hearts / 
J of clubs / 9 of clubs / T of spades / J of diamonds / K of spades / 8 of diamo
nds / 6 of spades / 2 of spades / 4 of diamonds / 5 of spades / T of hearts / 5 
of diamonds / 7 of clubs / 8 of clubs / 3 of clubs / Q of diamonds / T of diamon
ds / J of diamonds / J of spades / 5 of clubs / Q of hearts / T of clubs / A of 
spades / 7 of spades / T of spades / K of hearts / T of diamonds / K of spades /
 3 of spades / 8 of clubs / 7 of hearts / 7 of spades / T of clubs / J of clubs 
/ 2 of diamonds / 4 of spades / A of hearts / 8 of hearts / 6 of hearts / K of d
iamonds / Q of spades / 4 of spades / K of diamonds / 2 of hearts / 9 of clubs /
 3 of hearts / 5 of spades / 2 of clubs / 4 of clubs / 7 of diamonds / 9 of hear
ts / 8 of hearts / 8 of diamonds / A of diamonds / Q of diamonds / 7 of diamonds
 / K of hearts / 8 of spades / 3 of hearts / 7 of hearts / 9 of spades / 5 of cl
ubs / 7 of clubs / 6 of spades / 3 of diamonds / 6 of diamonds / A of clubs / A 
of spades / 2 of spades / 6 of clubs / 9 of diamonds / 4 of clubs / J of spades 
/ 2 of diamonds / 4 of hearts / 8 of spades / 5 of hearts / 2 of hearts / J of h
earts / 6 of hearts / Q of clubs / 3 of diamonds / 9 of spades / K of clubs / A 
of hearts / Q of spades / 9 of diamonds

K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 
8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 
3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of c
lubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clu
bs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K of hearts / Q of hear
ts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of 
hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K
 of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades
 / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of sp
ades / A of spades

Q of spades / 4 of clubs / 4 of hearts / Q of hearts / 7 of hearts / 6 of hearts
 / 9 of spades / 5 of clubs / 7 of spades / 5 of diamonds / 9 of clubs / 9 of di
amonds / 2 of diamonds / 2 of hearts / 2 of spades / J of spades / 9 of hearts /
 6 of clubs / J of clubs / A of spades / K of spades / 8 of clubs / 6 of spades 
/ K of hearts / A of hearts / 3 of diamonds / K of clubs / 7 of diamonds / T of 
diamonds / T of clubs / A of diamonds / 7 of clubs / Q of diamonds / 3 of hearts
 / 6 of diamonds / 5 of spades / 2 of clubs / J of diamonds / J of hearts / 3 of
 clubs / 4 of diamonds / 3 of spades / 5 of hearts / T of spades / 8 of diamonds
 / Q of clubs / T of hearts / 8 of spades / A of clubs / 8 of hearts / K of diam
onds / 4 of spades

End of Run ------------------------------------------------------------------ */

/* Run of Test of Phase 2: The Deck and Hand Classes ---------------------------

How many hands? (1 - 10, please): 6
Hand = (  )
Hand = (  )
Hand = (  )
Hand = (  )
Hand = (  )
Hand = (  )

Here are our hands, from unshuffled deck:
Hand = ( T of diamonds, 4 of diamonds, J of clubs, 5 of clubs, Q of hearts, 6 of
 hearts, K of spades, 7 of spades, A of spades )

Hand = ( J of diamonds, 5 of diamonds, Q of clubs, 6 of clubs, K of hearts, 7 of
 hearts, A of hearts, 8 of spades, 2 of spades )

Hand = ( Q of diamonds, 6 of diamonds, K of clubs, 7 of clubs, A of clubs, 8 of 
hearts, 2 of hearts, 9 of spades, 3 of spades )

Hand = ( K of diamonds, 7 of diamonds, A of diamonds, 8 of clubs, 2 of clubs, 9 
of hearts, 3 of hearts, T of spades, 4 of spades )

Hand = ( 8 of diamonds, 2 of diamonds, 9 of clubs, 3 of clubs, T of hearts, 4 of
 hearts, J of spades, 5 of spades )

Hand = ( 9 of diamonds, 3 of diamonds, T of clubs, 4 of clubs, J of hearts, 5 of
 hearts, Q of spades, 6 of spades )


Here are our hands, from SHUFFLED deck:
Hand = ( Q of hearts, 8 of spades, A of diamonds, 6 of clubs, A of clubs, Q of c
lubs, 5 of hearts, 4 of hearts, A of spades )

Hand = ( Q of diamonds, 8 of diamonds, 7 of hearts, J of spades, J of clubs, T o
f clubs, 9 of hearts, 3 of spades, 9 of clubs )

Hand = ( 5 of diamonds, T of spades, J of hearts, 9 of spades, 2 of diamonds, 2 
of clubs, 4 of clubs, 7 of spades, 7 of clubs )

Hand = ( 6 of diamonds, K of hearts, 5 of spades, 2 of spades, K of spades, K of
 clubs, 3 of hearts, 6 of spades, 7 of diamonds )

Hand = ( J of diamonds, 3 of diamonds, T of hearts, 9 of diamonds, T of diamonds
, A of hearts, 4 of spades, Q of spades )

Hand = ( 6 of hearts, 4 of diamonds, 8 of clubs, 5 of clubs, 3 of clubs, 2 of he
arts, 8 of hearts, K of diamonds )

End of Run ------------------------------------------------------------------ */
