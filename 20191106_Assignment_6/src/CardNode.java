
// -----------------------------------------------------------------------------
// Derived Class: CardNode
class CardNode extends Node
{
   // additional data for subclass
   private Card card;

   // constructor
   public CardNode(Card card)
   {
      super();
      this.card = card;
   }

   // accessor
   public Card getCard()
   {
      return card;
   }

   // stringizer
   public String toString()
   {
      return card.toString();
   }
}
