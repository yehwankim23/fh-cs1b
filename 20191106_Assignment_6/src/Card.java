
// -----------------------------------------------------------------------------
// Card class from Assignment 2 (modified)
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
