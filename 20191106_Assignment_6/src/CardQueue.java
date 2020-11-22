
// -----------------------------------------------------------------------------
// Derived Class: CardQueue
class CardQueue extends Queue
{
   public void addCard(Card newCard)
   {
      // emergency return
      if (newCard == null)
      {
         return;
      }

      CardNode cardNode = new CardNode(newCard);
      super.add(cardNode);
   }

   public CardNode removeCard()
   {
      CardNode cardNode = (CardNode) remove();

      return cardNode;
   }
}
