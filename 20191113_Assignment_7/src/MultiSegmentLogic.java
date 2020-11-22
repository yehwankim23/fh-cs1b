
// -----------------------------------------------------------------------------
// Class MultiSegmentLogic
class MultiSegmentLogic implements Cloneable
{
   // instance members
   protected int numSegs;
   protected BooleanFunc[] segs;

   // instance methods
   // constructors
   public MultiSegmentLogic()
   {
      this(0); // literal is okay
   }

   public MultiSegmentLogic(int numSeg)
   {
      if (!setNumSegs(numSeg))
      {
         setNumSegs(0); // literal is okay
      }
   }

   // mutators
   public boolean setNumSegs(int numSegs)
   {
      boolean retVal = false; // return value
      if (0 <= numSegs)
      {
         retVal = true;
         this.numSegs = numSegs;
         init();
      }
      return retVal;
   }

   public boolean setSegment(int segNum, BooleanFunc funcForThisSeg)
         throws CloneNotSupportedException
   {
      boolean retVal = false; // return value
      if (0 <= segNum && segNum < numSegs)
      {
         retVal = true;
         segs[segNum] = (BooleanFunc) funcForThisSeg.clone();
      }
      return retVal;
   }

   public void eval(int input)
   {
      int i; // for loop counter
      for (i = 0; i < segs.length; i++)
      {
         segs[i].eval(input); // Act 2 - println() this
      }
   }

   // deep memory method
   protected MultiSegmentLogic clone() throws CloneNotSupportedException
   {
      int i; // for loop counter
      MultiSegmentLogic newObject = (MultiSegmentLogic) super.clone();
      newObject.segs = new BooleanFunc[this.numSegs];
      for (i = 0; i < newObject.numSegs; i++)
      {
         newObject.segs[i] = this.segs[i];
      }
      return newObject;
   }

   // helper method
   private void init()
   {
      int i; // for loop counter
      segs = new BooleanFunc[numSegs];
      for (i = 0; i < segs.length; i++)
      {
         segs[i] = new BooleanFunc();
      }
   }
}
