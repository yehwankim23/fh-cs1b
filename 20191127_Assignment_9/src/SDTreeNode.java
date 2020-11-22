// -----------------------------------------------------------------------------
// Class SDTreeNode
// template provided by instructor
class SDTreeNode<E>
{
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected SDTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected SDTreeNode<E> myRoot; // needed to test for certain error
   protected boolean deleted; // true if the node has been removed from the tree

   public SDTreeNode(E d, SDTreeNode<E> sb, SDTreeNode<E> chld,
         SDTreeNode<E> prv, boolean dltd)
   {
      firstChild = chld;
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = dltd;
   }

   public SDTreeNode()
   {
      this(null, null, null, null, false);
   }

   public E getData()
   {
      return data;
   }

   // for use only by SDtree (default access)
   protected SDTreeNode(E d, SDTreeNode<E> sb, SDTreeNode<E> chld,
         SDTreeNode<E> prv, SDTreeNode<E> root, boolean dltd)
   {
      this(d, sb, chld, prv, dltd);
      myRoot = root;
   }
}
