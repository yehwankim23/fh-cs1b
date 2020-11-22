// -----------------------------------------------------------------------------
// Class SevenSegmentDisplay
// outline provided by instructor
class SevenSegmentDisplay implements Cloneable
{
   private SevenSegmentImage theImage;
   private SevenSegmentLogic theDisplay;

   public SevenSegmentDisplay()
   {
      theImage = new SevenSegmentImage();
      theDisplay = new SevenSegmentLogic();
   }

   public SevenSegmentDisplay(int width, int height)
   {
      this();
      if (!setSize(width, height))
      {
         setSize(SevenSegmentImage.MIN_WIDTH, SevenSegmentImage.MIN_HEIGHT);
      }
   }

   public boolean setSize(int width, int height)
   {
      return theImage.setSize(width, height);
   }

   public void loadConsoleImage()
   {
      int i; // for loop counter
      theImage.clearImage();
      for (i = 0; i < theDisplay.segs.length; i++)
      {
         if (theDisplay.getValOfSeg(i))
         {
            theImage.turnOnCellsForSegment((char) (i + 97));
         }
      }
   }

   public void consoleDisplay()
   {
      theImage.display();
   }

   public void eval(int input)
   {
      theDisplay.eval(input);
   }

   public SevenSegmentDisplay clone() throws CloneNotSupportedException
   {
      SevenSegmentDisplay newObject = (SevenSegmentDisplay) super.clone();
      return newObject;
   }
}
