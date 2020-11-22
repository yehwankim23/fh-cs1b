
// -----------------------------------------------------------------------------
// Base Class: Queue
class Queue
{
   // pointers
   private Node head, tail;

   // constructor
   public Queue()
   {
      tail = new Node();
      tail.next = head = new Node();
   }

   public void add(Node newNode)
   {
      // emergency return
      if (newNode == null)
      {
         return;
      }

      tail.next.next = newNode;
      tail.next = newNode;
   }

   public Node remove() throws QueueEmptyException
   {
      // throw a QueueEmptyException exception if the queue is empty
      if (head.next == null)
      {
         throw new QueueEmptyException();
      }

      Node tempNode = null;

      tempNode = head.next;
      head.next = head.next.next;

      return tempNode;
   }

   // stringizer
   public String toString()
   {
      String tempString = "";
      Node tempNode;

      for (tempNode = head.next; tempNode != null; tempNode = tempNode.next)
      {
         tempString += tempNode.toString() + "\n";
      }

      return tempString;
   }
}
