
// Assignment 7 - Deep Memory Seven Segment Displays
public class Main
{
   public static void main(String[] args)
   {
      // -----------------------------------------------------------------------
      // Act 1: Class BooleanFunc
      /*
      BooleanFunc segA, segB, segC = null, segD, segE;

      int even[] =
      { 0, 2, 4, 6, 8, 10, 12, 14 };
      int odd[] =
      { 1, 3, 5, 7, 9, 11, 13, 15 };
      int low[] =
      { 0, 1, 2, 3, 4, 5, 6, 7, 16 };
      int high[] =
      { 8, 9, 10, 11, 12, 13, 14, 15 };
      int error[] =
      { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

      int input;

      segA = new BooleanFunc();
      segB = new BooleanFunc(16);
      try
      {
         segC = (BooleanFunc) segB.clone();
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }
      segD = new BooleanFunc(16, false);
      segE = new BooleanFunc(65537, true);

      segA.setTruthTableUsingTrue(even);
      segB.setTruthTableUsingFalse(odd);
      if (!segC.setTruthTableUsingFalse(error))
      {
         segC.setTruthTableUsingFalse(odd);
      }
      segD.setTruthTableUsingTrue(low);
      segE.setTruthTableUsingFalse(high);

      System.out.println(
            "segA(x) = " + segA.getState() + "\nsegB(x) = " + segB.getState()
                  + "\nsegC(x) = " + segC.getState() + "\nsegD(x) = "
                  + segD.getState() + "\nsegE(x) = " + segE.getState() + "\n");

      for (input = 0; input < 17; input++)
      {
         segA.eval(input);
         segB.eval(input);
         segC.eval(input);
         segD.eval(input);
         segE.eval(input);
         System.out.println("segA(" + input + ") = " + segA.getState()
               + "\nsegB(" + input + ") = " + segB.getState() + "\nsegC("
               + input + ") = " + segC.getState() + "\nsegD(" + input + ") = "
               + segD.getState() + "\nsegE(" + input + ") = " + segE.getState()
               + "\n");
      }
      */
      // -----------------------------------------------------------------------
      // Act 2: Class MultiSegmentLogic
      /*
      MultiSegmentLogic msl1 = new MultiSegmentLogic();
      MultiSegmentLogic msl2 = null;
      try
      {
         msl2 = (MultiSegmentLogic) msl1.clone();
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      BooleanFunc segA, segB;

      int one[] =
      { 1, 3 };
      int two[] =
      { 2, 3 };

      int input;

      if (!msl2.setNumSegs(-1))
      {
         msl2.setNumSegs(2);
      }

      segA = new BooleanFunc(4);
      segB = new BooleanFunc(4);

      segA.setTruthTableUsingTrue(one);
      segB.setTruthTableUsingTrue(two);

      try
      {
         msl2.setSegment(0, segA);
         msl2.setSegment(1, segB);
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      for (input = 0; input < 4; input++)
      {
         msl2.eval(input);
         System.out.println();
      }
      */
      // -----------------------------------------------------------------------
      // Act 3: Derived Class SevenSegmentLogic

      SevenSegmentLogic ssl1, ssl2 = null;

      int input, k;

      ssl1 = new SevenSegmentLogic();

      try
      {
         ssl2 = (SevenSegmentLogic) ssl1.clone();
      } catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      for (input = 0; input < 16; input++)
      {
         ssl2.eval(input);
         System.out.print(input + " | ");
         for (k = 0; k < 7; k++)
         {
            System.out.print(ssl2.getValOfSeg(k) + " | ");
         }
         System.out.println("\n");
      }
   }
}

//-----------------------------------------------------------------------------
/* Run of Act 1

segA(x) = false
segB(x) = false
segC(x) = false
segD(x) = false
segE(x) = false

segA(0) = true
segB(0) = true
segC(0) = true
segD(0) = true
segE(0) = true

segA(1) = false
segB(1) = false
segC(1) = false
segD(1) = true
segE(1) = true

segA(2) = true
segB(2) = true
segC(2) = true
segD(2) = true
segE(2) = true

segA(3) = false
segB(3) = false
segC(3) = false
segD(3) = true
segE(3) = true

segA(4) = true
segB(4) = true
segC(4) = true
segD(4) = true
segE(4) = true

segA(5) = false
segB(5) = false
segC(5) = false
segD(5) = true
segE(5) = true

segA(6) = true
segB(6) = true
segC(6) = true
segD(6) = true
segE(6) = true

segA(7) = false
segB(7) = false
segC(7) = false
segD(7) = true
segE(7) = true

segA(8) = true
segB(8) = true
segC(8) = true
segD(8) = false
segE(8) = false

segA(9) = false
segB(9) = false
segC(9) = false
segD(9) = false
segE(9) = false

segA(10) = true
segB(10) = true
segC(10) = true
segD(10) = false
segE(10) = false

segA(11) = false
segB(11) = false
segC(11) = false
segD(11) = false
segE(11) = false

segA(12) = true
segB(12) = true
segC(12) = true
segD(12) = false
segE(12) = false

segA(13) = false
segB(13) = false
segC(13) = false
segD(13) = false
segE(13) = false

segA(14) = true
segB(14) = true
segC(14) = true
segD(14) = false
segE(14) = false

segA(15) = false
segB(15) = false
segC(15) = false
segD(15) = false
segE(15) = false

segA(16) = false
segB(16) = false
segC(16) = false
segD(16) = false
segE(16) = true

*/

//-----------------------------------------------------------------------------
/* Run of Act 2 (edit MultiSegmentLogic.eval() to show in console)

false
false

true
false

false
true

true
true

*/

//-----------------------------------------------------------------------------
/* Run of Act 3

0 | true | true | true | true | true | true | false | 

1 | false | true | true | false | false | false | false | 

2 | true | true | false | true | true | false | true | 

3 | true | true | true | true | false | false | true | 

4 | false | true | true | false | false | true | true | 

5 | true | false | true | true | false | true | true | 

6 | true | false | true | true | true | true | true | 

7 | true | true | true | false | false | false | false | 

8 | true | true | true | true | true | true | true | 

9 | true | true | true | true | false | true | true | 

10 | true | true | true | false | true | true | true | 

11 | false | false | true | true | true | true | true | 

12 | true | false | false | true | true | true | false | 

13 | false | true | true | true | true | false | true | 

14 | true | false | false | true | true | true | true | 

15 | true | false | false | false | true | true | true | 

*/
