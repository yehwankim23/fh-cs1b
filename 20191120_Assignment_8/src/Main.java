// Assignment 8  - Seven Segment Displays on Consoles
public class Main
{
   public static void main(String[] args)
   {
      // -----------------------------------------------------------------------
      // Test of Phase 1
      /*
      System.out.println("SevenSegmentImage()\n");
      SevenSegmentImage ssi1 = new SevenSegmentImage();
      ssi1.turnOnCellsForSegment('A');
      ssi1.display();
      ssi1.turnOnCellsForSegment('B');
      ssi1.display();
      ssi1.turnOnCellsForSegment('C');
      ssi1.display();
      ssi1.turnOnCellsForSegment('D');
      ssi1.display();
      System.out.println("setSize(7, 9)\n");
      ssi1.setSize(7, 9);
      ssi1.turnOnCellsForSegment('E');
      ssi1.display();
      ssi1.turnOnCellsForSegment('F');
      ssi1.display();
      ssi1.turnOnCellsForSegment('G');
      ssi1.display();

      System.out.println("SevenSegmentImage(4, 4)\n");
      SevenSegmentImage ssi2 = new SevenSegmentImage(4, 4);
      ssi2.turnOnCellsForSegment('a');
      ssi2.display();
      ssi2.turnOnCellsForSegment('b');
      ssi2.display();
      ssi2.turnOnCellsForSegment('c');
      ssi2.display();
      ssi2.turnOnCellsForSegment('d');
      ssi2.display();
      System.out.println("setSize(10, 10)\n");
      ssi2.setSize(10, 10);
      ssi2.turnOnCellsForSegment('e');
      ssi2.display();
      ssi2.turnOnCellsForSegment('f');
      ssi2.display();
      ssi2.turnOnCellsForSegment('g');
      ssi2.display();

      SevenSegmentImage ssi3 = null;
      System.out.println("clone()\n");
      try
      {
         ssi3 = ssi2.clone();
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }
      ssi3.display();
      System.out.println("clearImage()\n");
      ssi3.clearImage();
      ssi3.display();
      System.out.println("setSize(10, 11)\n");
      ssi3.setSize(10, 11);
      ssi3.display();
      System.out.println("turnOnCellsForSegment('x')\n");
      ssi3.turnOnCellsForSegment('x');
      ssi3.display();
      System.out.println("turnOnCellsForSegment('3')\n");
      ssi3.turnOnCellsForSegment('3');
      ssi3.display();
       */

      // -----------------------------------------------------------------------
      // Test of Phase 2
      
      System.out.println("SevenSegmentDisplay()\n");
      SevenSegmentDisplay ssd1 = new SevenSegmentDisplay();
      int i;

      System.out.println("setSize(7, 9)\n");
      ssd1.setSize(7, 9);
      for (i = 0; i < 16; i++)
      {
         ssd1.eval(i);
         ssd1.loadConsoleImage();
         System.out.println("consoleDisplay()\n");
         ssd1.consoleDisplay();
      }

      SevenSegmentDisplay ssd2 = null;
      System.out.println("clone()\n");
      try
      {
         ssd2 = ssd1.clone();
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      for (i = 5; i < 21; i += 4)
      {
         System.out.println("setSize(" + i + ", " + (2 * i + 1) + ")\n");
         ssd2.setSize(i, 2 * i + 1);
         ssd2.eval(5);
         ssd2.loadConsoleImage();
         System.out.println("consoleDisplay()\n");
         ssd2.consoleDisplay();
      }
   }
}

// -----------------------------------------------------------------------------
/* Run of Test of Phase 1

SevenSegmentImage()

*****
     
     
     
     

*****
    *
    *
     
     

*****
    *
    *
    *
    *

*****
    *
    *
    *
*****

setSize(7, 9)

       
       
       
       
*      
*      
*      
*      
*      

*      
*      
*      
*      
*      
*      
*      
*      
*      

*      
*      
*      
*      
*******
*      
*      
*      
*      

SevenSegmentImage(4, 4)

*****
     
     
     
     

*****
    *
    *
     
     

*****
    *
    *
    *
    *

*****
    *
    *
    *
*****

setSize(10, 10)

*****
    *
*   *
*   *
*****

*****
*   *
*   *
*   *
*****

*****
*   *
*****
*   *
*****

clone()

*****
*   *
*****
*   *
*****

clearImage()

     
     
     
     
     

setSize(10, 11)

          
          
          
          
          
          
          
          
          
          
          

turnOnCellsForSegment('x')

          
          
          
          
          
          
          
          
          
          
          

turnOnCellsForSegment('3')

          
          
          
          
          
          
          
          
          
          
          

 */

// -----------------------------------------------------------------------------
/* Run of Test of Phase 2

SevenSegmentDisplay()

setSize(7, 9)

consoleDisplay()

*******
*     *
*     *
*     *
*     *
*     *
*     *
*     *
*******

consoleDisplay()

      *
      *
      *
      *
      *
      *
      *
      *
      *

consoleDisplay()

*******
      *
      *
      *
*******
*      
*      
*      
*******

consoleDisplay()

*******
      *
      *
      *
*******
      *
      *
      *
*******

consoleDisplay()

*     *
*     *
*     *
*     *
*******
      *
      *
      *
      *

consoleDisplay()

*******
*      
*      
*      
*******
      *
      *
      *
*******

consoleDisplay()

*******
*      
*      
*      
*******
*     *
*     *
*     *
*******

consoleDisplay()

*******
      *
      *
      *
      *
      *
      *
      *
      *

consoleDisplay()

*******
*     *
*     *
*     *
*******
*     *
*     *
*     *
*******

consoleDisplay()

*******
*     *
*     *
*     *
*******
      *
      *
      *
*******

consoleDisplay()

*******
*     *
*     *
*     *
*******
*     *
*     *
*     *
*     *

consoleDisplay()

*      
*      
*      
*      
*******
*     *
*     *
*     *
*******

consoleDisplay()

*******
*      
*      
*      
*      
*      
*      
*      
*******

consoleDisplay()

      *
      *
      *
      *
*******
*     *
*     *
*     *
*******

consoleDisplay()

*******
*      
*      
*      
*******
*      
*      
*      
*******

consoleDisplay()

*******
*      
*      
*      
*******
*      
*      
*      
*      

clone()

setSize(5, 11)

consoleDisplay()

*****
*    
*    
*    
*    
*****
    *
    *
    *
    *
*****

setSize(9, 19)

consoleDisplay()

*********
*        
*        
*        
*        
*        
*        
*        
*        
*********
        *
        *
        *
        *
        *
        *
        *
        *
*********

setSize(13, 27)

consoleDisplay()

*************
*            
*            
*            
*            
*            
*            
*            
*            
*            
*            
*            
*            
*************
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
            *
*************

setSize(17, 35)

consoleDisplay()

*****************
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*                
*****************
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
                *
*****************

 */
