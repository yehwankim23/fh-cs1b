// -----------------------------------------------------------------------------
// Class SevenSegmentImage
// outline provided by instructor
class SevenSegmentImage implements Cloneable
{
   // static members
   public static final int MIN_HEIGHT = 5;
   public static final int MIN_WIDTH = 5;
   public static final int MAX_HEIGHT = 65;
   public static final int MAX_WIDTH = 41;
   public static final String DRAW_CHAR = "*";
   public static final String BLANK_CHAR = " ";

   // instance members
   private boolean[][] data;
   private int topRow, midRow, bottomRow, leftCol, rightCol;

   // instance methods
   // constructors
   public SevenSegmentImage()
   {
      this(MIN_WIDTH, MIN_HEIGHT);
   }

   public SevenSegmentImage(int width, int height)
   {
      if (!setSize(width, height))
      {
         setSize(MIN_WIDTH, MIN_HEIGHT);
      }
   }

   public void clearImage()
   {
      int i, j; // for loop counters
      for (i = 0; i < data.length; i++)
      {
         for (j = 0; j < data[i].length; j++)
         {
            data[i][j] = false;
         }
      }
   }

   public boolean turnOnCellsForSegment(char segment)
   {
      boolean retVal = false; // return value
      char lowercase = Character.toLowerCase(segment); // to lower case
      if ('a' <= lowercase && lowercase <= 'g')
      {
         switch (lowercase)
         {
         case 'a':
            drawHorizontal(topRow);
            break;
         case 'b':
            drawVertical(rightCol, topRow, midRow);
            break;
         case 'c':
            drawVertical(rightCol, midRow, bottomRow);
            break;
         case 'd':
            drawHorizontal(bottomRow);
            break;
         case 'e':
            drawVertical(leftCol, midRow, bottomRow);
            break;
         case 'f':
            drawVertical(leftCol, topRow, midRow);
            break;
         case 'g':
            drawHorizontal(midRow);
            break;
         default:
            System.out
            .println("Error: SevenSegmentImage.turnOnCellsForSegment()");
            break;
         }
      }
      return retVal;
   }

   public boolean setSize(int width, int height)
   {
      boolean retVal = false; // return value
      if (validateSize(width, height))
      {
         topRow = 1;
         midRow = height / 2 + 1;
         bottomRow = height;
         leftCol = 1;
         rightCol = width;
         allocateCleanArray();
         clearImage();
         retVal = true;
      }
      return retVal;
   }

   public void display()
   {
      int i, j; // for loop counters
      String value;
      for (i = 0; i < data.length; i++)
      {
         for (j = 0; j < data[i].length; j++)
         {
            value = BLANK_CHAR;
            if (data[i][j] == true)
            {
               value = DRAW_CHAR;
            }
            System.out.print(value);
         }
         System.out.println();
      }
      System.out.println();
   }

   // deep copy required
   public SevenSegmentImage clone() throws CloneNotSupportedException
   {
      int i, j; // for loop counters
      SevenSegmentImage newObject = (SevenSegmentImage) super.clone();
      newObject.data = new boolean[this.bottomRow][this.rightCol];
      for (i = 0; i < newObject.data.length; i++)
      {
         for (j = 0; j < newObject.data[i].length; j++)
         {
            newObject.data[i][j] = this.data[i][j];
         }
      }
      return newObject;
   }

   private boolean validateSize(int width, int height)
   {
      boolean retVal = false; // return value
      if (MIN_WIDTH <= width && width <= MAX_WIDTH && MIN_HEIGHT <= height
            && height <= MAX_HEIGHT && height % 2 == 1)
      {
         retVal = true;
      }
      return retVal;
   }

   private void allocateCleanArray()
   {
      data = new boolean[bottomRow][rightCol];
   }

   // helpers - not required, but used by instructor
   void drawHorizontal(int row)
   {
      int i; // for loop counter
      for (i = 0; i < data[row - 1].length; i++)
      {
         data[row - 1][i] = true;
      }
   }

   void drawVertical(int col, int startRow, int stopRow)
   {
      int i; // for loop counter
      for (i = startRow - 1; i < stopRow; i++)
      {
         data[i][col - 1] = true;
      }
   }
}
