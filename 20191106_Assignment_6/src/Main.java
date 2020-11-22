
// Assignment 6 - Queues Using Inheritance
public class Main
{
   public static void main(String[] args)
   {
      // -----------------------------------------------------------------------
      // Phase 1: Base Classes Node and Queue

      // no main()

      // -----------------------------------------------------------------------
      // Phase 2: Intermediate Step - Check Your Base Classes
      /*
      Queue queue = new Queue();
      Node node;
      int i;

      for (i = 0; i < 5; i++)
      {
         node = new Node();
         queue.add(node);
      }

      System.out.println("Queue.toString(): \n");
      System.out.println(queue.toString());

      System.out.println("Node.toString(): \n");
      try
      {
         while ((node = queue.remove()) != null)
         {
            System.out.println(node.toString());
         }
      } catch (Exception e)
      {
         //
      }
      */
      // -----------------------------------------------------------------------
      // Phase 3: Derived Classes CardNode and CardQueue

      CardQueue cardQueue = new CardQueue();
      CardNode cardNode, cardNode1, cardNode2, cardNode3, cardNode4, cardNode5;
      Card card1, card2, card3, card4, card5;

      card1 = new Card('A', Card.Suit.spades);
      card2 = new Card('5', Card.Suit.hearts);
      card3 = new Card('9', Card.Suit.clubs);
      card4 = new Card('K', Card.Suit.diamonds);
      card5 = new Card();

      cardNode1 = new CardNode(card1);
      cardNode2 = new CardNode(card2);
      cardNode3 = new CardNode(card3);
      cardNode4 = new CardNode(card4);
      cardNode5 = new CardNode(card5);

      cardQueue.add(cardNode1);
      cardQueue.add(cardNode2);
      cardQueue.add(cardNode3);
      cardQueue.add(cardNode4);
      cardQueue.add(cardNode5);

      System.out.println("CardQueue.toString(): \n");
      System.out.println(cardQueue.toString());

      System.out.println("CardNode.toString(): \n");
      try
      {
         while ((cardNode = (CardNode) cardQueue.remove()) != null)
         {
            System.out.println(cardNode.toString());
         }
      } catch (Exception e)
      {
         //
      }
   }
}

//------------------------------------------------------------------------------
/* Run of Phase 2

Queue.toString(): 

(generic node) 
(generic node) 
(generic node) 
(generic node) 
(generic node) 

Node.toString(): 

(generic node) 
(generic node) 
(generic node) 
(generic node) 
(generic node) 

 */

//------------------------------------------------------------------------------
/* Run of Phase 3

CardQueue.toString(): 

A of spades
5 of hearts
9 of clubs
K of diamonds
A of spades

CardNode.toString(): 

A of spades
5 of hearts
9 of clubs
K of diamonds
A of spades

 */
