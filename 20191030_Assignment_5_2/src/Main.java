import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Assignment 5 - GUI Cards
// original main() provided by Professor
public class Main
{
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

   // static method
   static Card generateRandomCard()
   {
      Card card = new Card();
      int randomValue, randomSuit;
      char value;
      Card.Suit suit;

      randomValue = (int) (13 * Math.random());
      randomSuit = (int) (4 * Math.random());

      value = GUICard.turnIntIntoCardValueChar(randomValue);
      suit = GUICard.turnIntIntoSuit(randomSuit);

      card.set(value, suit);

      return card;
   }

   public static void main(String[] args)
   {
      // Option A: A JFrame of Card Icons
      // Test of Phase 2: Encapsulating Layout and Icons into CardTable and
      // GUICard Classes
      // -----------------------------------------------------------------------
      int k; // for loop counter
      Icon tempIcon;

      // establish main frame in which program will run
      CardTable myCardTable = new CardTable("CS 1B CardTable",
            NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // create labels
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         tempIcon = GUICard.getBackCardIcon();
         computerLabels[k] = new JLabel(tempIcon);
      }

      for (k = 0; k < NUM_PLAYERS; k++)
      {
         tempIcon = GUICard.getIcon(generateRandomCard());
         playedCardLabels[k] = new JLabel(tempIcon);
      }

      playLabelText[0] = new JLabel("Computer", JLabel.CENTER);
      playLabelText[1] = new JLabel("You", JLabel.CENTER);

      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         tempIcon = GUICard.getIcon(generateRandomCard());
         humanLabels[k] = new JLabel(tempIcon);
      }

      // add labels to panels
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         myCardTable.pnlTop.add(computerLabels[k]);
      }

      for (k = 0; k < NUM_PLAYERS; k++)
      {
         myCardTable.pnlMiddle.add(playedCardLabels[k]);
      }

      for (k = 0; k < NUM_PLAYERS; k++)
      {
         myCardTable.pnlMiddle.add(playLabelText[k]);
      }

      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
         myCardTable.pnlBottom.add(humanLabels[k]);
      }

      // show everything to the user
      myCardTable.setVisible(true);
   }
}

// controls the positioning of the panels and cards of the GUI
// -----------------------------------------------------------------------------
@SuppressWarnings("serial")
class CardTable extends JFrame
{
   // members
   static int MIN_CARDS_PER_HAND = 1;
   static int MAX_CARDS_PER_HAND = 57;
   static int MIN_PLAYERS = 2;
   static int MAX_PLAYERS = 2; // for now, we only allow 2 person games

   private int numCardsPerHand;
   private int numPlayers;

   JPanel pnlTop, pnlMiddle, pnlBottom;

   // public instance methods
   // constructor
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      super(title);

      if (numCardsPerHand < MIN_CARDS_PER_HAND
            || numCardsPerHand > MAX_CARDS_PER_HAND)
      {
         numCardsPerHand = MAX_CARDS_PER_HAND;
      }

      if (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS)
      {
         numPlayers = MAX_PLAYERS;
      }

      setLayout(new BorderLayout(10, 10));

      pnlTop = new JPanel(new GridLayout(1, numCardsPerHand, 10, 10));
      pnlMiddle = new JPanel(new GridLayout(2, numPlayers, 10, 10));
      pnlBottom = new JPanel(
            new GridLayout(numPlayers - 1, numCardsPerHand, 10, 10));

      pnlTop.setBorder(new TitledBorder("Computer Hand"));
      pnlMiddle.setBorder(new TitledBorder("Playing Area"));
      pnlBottom.setBorder(new TitledBorder("Your Hand"));

      add(pnlTop, BorderLayout.NORTH);
      add(pnlMiddle, BorderLayout.CENTER);
      add(pnlBottom, BorderLayout.SOUTH);
   }

   // accessors
   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return numPlayers;
   }

}

// manages the reading and building of the card image Icons
// -----------------------------------------------------------------------------
class GUICard
{
   // static members
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K (+
   // joker optional)
   private static Icon iconBack;
   private static boolean iconsLoaded = false;

   // helper static arrays
   private static String cardlValsConvertAssist = "A23456789TJQKX";
   private static String suitValsConvertAssist = "CDHS";
   private static Card.Suit suitConvertAssist[] =
   { Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts, Card.Suit.spades };

   // static methods
   static void loadCardIcons()
   {
      String imageFileName;
      int intSuit, intVal;

      if (!iconsLoaded)
      {
         for (intSuit = 0; intSuit < 4; intSuit++)
         {
            for (intVal = 0; intVal < 14; intVal++)
            {
               // card image files stored in /images folder with names
               // like "AC.gif", "3H.gif","XD.gif", etc.
               imageFileName = "images/" + turnIntIntoCardValueChar(intVal)
                     + turnIntIntoCardSuitChar(intSuit) + ".gif";
               iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
            }
         }

         imageFileName = "images/BK.gif";
         iconBack = new ImageIcon(imageFileName);

         iconsLoaded = true;
      }
   }

   static public Icon getIcon(Card card)
   {
      loadCardIcons(); // will not load twice, so no worries.

      return iconCards[valueAsInt(card.getValue())][suitAsInt(card.getSuit())];
   }

   static public Icon getBackCardIcon()
   {
      loadCardIcons(); // will not load twice, so no worries.

      return iconBack;
   }

   // helpers
   // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
   public static char turnIntIntoCardValueChar(int k)
   {
      char retVal; // return value

      if (k < 0 || k > 13)
      {
         retVal = '?';
      }

      retVal = cardlValsConvertAssist.charAt(k);

      return retVal;
   }

   // turns 0 - 3 into 'C', 'D', 'H', 'S'
   static char turnIntIntoCardSuitChar(int k)
   {
      if (k < 0 || k > 3)
      {
         return '?';
      }

      return suitValsConvertAssist.charAt(k);
   }

   // turns 0 - 3 into Card.Suit.clubs - Card.Suit.spades
   public static Card.Suit turnIntIntoSuit(int k)
   {
      Card.Suit retVal; // return value

      if (k < 0 || k > 3)
      {
         retVal = Card.Suit.spades;
      }

      retVal = suitConvertAssist[k];

      return retVal;
   }

   // turns 'A', '2', '3', ... 'Q', 'K', 'X' into 0 - 13
   private static int valueAsInt(char value)
   {
      int i; // for loop counter
      int retVal = -1; // return value

      for (i = 0; i < cardlValsConvertAssist.length(); i++)
      {
         if (cardlValsConvertAssist.charAt(i) == value)
         {
            retVal = i;
         }
      }

      return retVal;
   }

   // turns 'C', 'D', 'H', 'S' into 0 - 3
   private static int suitAsInt(Card.Suit suit)
   {
      int i; // for loop counter
      int retVal = -1; // return value

      for (i = 0; i < suitConvertAssist.length; i++)
      {
         if (suitConvertAssist[i] == suit)
         {
            retVal = i;
         }
      }

      return retVal;
   }
}

// modified Card class from Assignment 2
// -----------------------------------------------------------------------------
class Card
{
   // static public constants
   private static final char DEFAULT_VAL = 'A';
   private static final Suit DEFAULT_SUIT = Suit.spades;

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
