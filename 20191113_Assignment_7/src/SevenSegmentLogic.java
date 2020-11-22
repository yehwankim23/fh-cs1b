
// -----------------------------------------------------------------------------
// Class SevenSegmentLogic
class SevenSegmentLogic extends MultiSegmentLogic
{
   // instance methods
   public SevenSegmentLogic()
   {
      super();
      setNumSegs(7); // literal is okay
      load();
   }

   public boolean getValOfSeg(int seg)
   {
      boolean retVal = false; // return value
      if (0 <= seg && seg < numSegs)
      {
         retVal = segs[seg].getState();
      }
      return retVal;
   }

   // helper method
   private void load()
   {
      BooleanFunc segA, segB, segC, segD, segE, segF, segG;
      int a[] =
      { 1, 4, 11, 13 };
      int b[] =
      { 5, 6, 11, 12, 14, 15 };
      int c[] =
      { 2, 12, 14, 15 };
      int d[] =
      { 1, 4, 7, 10, 15 };
      int e[] =
      { 1, 3, 4, 5, 7, 9 };
      int f[] =
      { 1, 2, 3, 7, 13 };
      int g[] =
      { 0, 1, 7, 12 };
      segA = new BooleanFunc();
      segB = new BooleanFunc();
      segC = new BooleanFunc();
      segD = new BooleanFunc();
      segE = new BooleanFunc();
      segF = new BooleanFunc();
      segG = new BooleanFunc();
      segA.setTruthTableUsingFalse(a);
      segB.setTruthTableUsingFalse(b);
      segC.setTruthTableUsingFalse(c);
      segD.setTruthTableUsingFalse(d);
      segE.setTruthTableUsingFalse(e);
      segF.setTruthTableUsingFalse(f);
      segG.setTruthTableUsingFalse(g);
      try
      {
         setSegment(0, segA);
         setSegment(1, segB);
         setSegment(2, segC);
         setSegment(3, segD);
         setSegment(4, segE);
         setSegment(5, segF);
         setSegment(6, segG);
      } catch (CloneNotSupportedException ex)
      {
         ex.printStackTrace();
      }
   }
}
