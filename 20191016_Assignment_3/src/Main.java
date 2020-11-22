import java.util.Scanner;

// Assignment 3 - Cellular Automata
public class Main
{
   public static void main(String[] args)
   {
      // Test of Option A: 1D Cellular Automata

      final int NUM_RULES = 4;
      final int NUM_GENS = 50;

      int rule = -1;

      Automaton automaton = new Automaton(rule);

      Scanner scanner = new Scanner(System.in);

      for (int i = 0; i < NUM_RULES; i++)
      {
         boolean isValid = false;

         while (!isValid)
         {
            System.out.print("Enter Rule (" + Automaton.MIN_RULE + " - "
                  + Automaton.MAX_RULE + "): ");
            int tempRule;

            try
            {
               tempRule = Integer.parseInt(scanner.next());

               if (tempRule < Automaton.MIN_RULE
                     || tempRule > Automaton.MAX_RULE)
               {
                  System.out.println("Error! Input out of range.\n");
               } else
               {
                  rule = tempRule;
                  isValid = true;
               }
            } catch (NumberFormatException error)
            {
               System.out.println("Error! Non-numeric input.\n");
            }
         }

         automaton.setRule(rule);
         automaton.resetFirstGen();

         System.out.println("   start");

         for (int j = 0; j < NUM_GENS; j++)
         {
            System.out.println(automaton.toStringCurrentGen());
            automaton.propagateNewGeneration();
         }

         System.out.println("   end\n");
      }

      scanner.close();
   }
}

class Automaton
{
   // static class finals
   public final static int RULES_SIZE = 8;
   public final static int BITS_IN_RULE_SIZE = (int) (Math.log(RULES_SIZE)
         / Math.log(2));
   public final static int MIN_DISPLAY_WIDTH = 21;
   public final static int MAX_DISPLAY_WIDTH = 121;
   public final static int DFLT_DISPLAY_WIDTH = 79;
   public final static int MIN_RULE = 0;
   public final static int MAX_RULE = (int) Math.pow(2, RULES_SIZE) - 1;
   public final static int DFLT_RULE = 1;
   public final static String ON_STR = "*";
   public final static String OFF_STR = " ";

   // instance members
   private String thisGen;
   private int displayWidth = DFLT_DISPLAY_WIDTH;
   private String extremeBit;
   private boolean rule[] = new boolean[RULES_SIZE];

   // client callable methods
   // constructor
   public Automaton(int rule)
   {
      if (!setRule(rule))
      {
         setRule(DFLT_RULE);
      }

      if (!setDisplayWidth(displayWidth))
      {
         setDisplayWidth(DFLT_DISPLAY_WIDTH);
      }

      resetFirstGen();
   }

   // stringizer
   public String toStringCurrentGen()
   {
      String retVal = ""; // return value
      int i; // for loop counter
      int sideLength;

      if (thisGen.length() <= displayWidth)
      {
         // add extreme bits
         sideLength = (displayWidth - thisGen.length()) / 2;

         for (i = 0; i < sideLength; i++)
         {
            retVal += extremeBit;
         }

         retVal += thisGen;

         for (i = 0; i < sideLength; i++)
         {
            retVal += extremeBit;
         }
      } else if (thisGen.length() > displayWidth)
      {
         // remove excess bits
         sideLength = (thisGen.length() - displayWidth) / 2;

         retVal += thisGen.substring(sideLength, thisGen.length() - sideLength);
      }

      return retVal;
   }

   // mutators
   public boolean setRule(int newRule)
   {
      boolean retVal; // return value
      int i; // for loop counter
      String binary; // newRule to binary
      char value; // binary value

      if (newRule < MIN_RULE || newRule > MAX_RULE)
      {
         // newRule is out of range
         retVal = false;
      } else
      {
         // set initial values to false
         for (i = 0; i < rule.length; i++)
         {
            rule[i] = false;
         }

         binary = Integer.toBinaryString(newRule);

         // set values according to rule
         for (i = 0; i < binary.length(); i++)
         {
            value = binary.charAt(binary.length() - 1 - i);

            if (value == '0')
            {
               rule[i] = false;
            } else if (value == '1')
            {
               rule[i] = true;
            }
         }

         retVal = true;
      }

      return retVal;
   }

   public boolean setDisplayWidth(int width)
   {
      boolean retVal; // return value

      if (width < MIN_DISPLAY_WIDTH || width > MAX_DISPLAY_WIDTH
            || width % 2 == 0)
      {
         // width is out of range or even
         retVal = false;
      } else
      {
         displayWidth = width;

         retVal = true;
      }

      return retVal;
   }

   public void resetFirstGen()
   {
      thisGen = ON_STR;
      extremeBit = OFF_STR;
   }

   public void propagateNewGeneration()
   {
      boolean extremeRule = false;
      String copyGen = extremeBit + extremeBit + thisGen + extremeBit
            + extremeBit; // modified copy of thisGen
      int i, j, index; // for loop counters and related index
      String substring; // substring of copyGen
      int ruleNum;

      // new extremeBit
      if (extremeBit.equals(OFF_STR))
      {
         extremeRule = rule[0];
      } else if (extremeBit.equals(ON_STR))
      {
         extremeRule = rule[RULES_SIZE - 1];
      }

      if (extremeRule)
      {
         extremeBit = ON_STR;
      } else
      {
         extremeBit = OFF_STR;
      }

      // new thisGen
      thisGen = "";

      for (i = 0; i < copyGen.length() - 2; i++)
      {
         ruleNum = 0;

         for (j = 0; j < BITS_IN_RULE_SIZE; j++)
         {
            index = i + j;
            substring = copyGen.substring(index, index + 1);

            if (substring.equals(ON_STR))
            {
               ruleNum += 1;
            }

            if (j < BITS_IN_RULE_SIZE - 1)
            {
               ruleNum *= 2;
            }
         }

         if (rule[ruleNum])
         {
            thisGen += ON_STR;
         } else
         {
            thisGen += OFF_STR;
         }
      }
   }
}

/* Run of Test of Option A: 1D Cellular Automata -------------------------------

Enter Rule (0 - 255): 3
   start
                                       *                                       
***************************************  **************************************
                                        *                                      
****************************************  *************************************
                                         *                                     
*****************************************  ************************************
                                          *                                    
******************************************  ***********************************
                                           *                                   
*******************************************  **********************************
                                            *                                  
********************************************  *********************************
                                             *                                 
*********************************************  ********************************
                                              *                                
**********************************************  *******************************
                                               *                               
***********************************************  ******************************
                                                *                              
************************************************  *****************************
                                                 *                             
*************************************************  ****************************
                                                  *                            
**************************************************  ***************************
                                                   *                           
***************************************************  **************************
                                                    *                          
****************************************************  *************************
                                                     *                         
*****************************************************  ************************
                                                      *                        
******************************************************  ***********************
                                                       *                       
*******************************************************  **********************
                                                        *                      
********************************************************  *********************
                                                         *                     
*********************************************************  ********************
                                                          *                    
**********************************************************  *******************
                                                           *                   
***********************************************************  ******************
                                                            *                  
************************************************************  *****************
                                                             *                 
*************************************************************  ****************
                                                              *                
**************************************************************  ***************
                                                               *               
***************************************************************  **************
   end

Enter Rule (0 - 255): 126
   start
                                       *                                       
                                      ***                                      
                                     ** **                                     
                                    *******                                    
                                   **     **                                   
                                  ****   ****                                  
                                 **  ** **  **                                 
                                ***************                                
                               **             **                               
                              ****           ****                              
                             **  **         **  **                             
                            ********       ********                            
                           **      **     **      **                           
                          ****    ****   ****    ****                          
                         **  **  **  ** **  **  **  **                         
                        *******************************                        
                       **                             **                       
                      ****                           ****                      
                     **  **                         **  **                     
                    ********                       ********                    
                   **      **                     **      **                   
                  ****    ****                   ****    ****                  
                 **  **  **  **                 **  **  **  **                 
                ****************               ****************                
               **              **             **              **               
              ****            ****           ****            ****              
             **  **          **  **         **  **          **  **             
            ********        ********       ********        ********            
           **      **      **      **     **      **      **      **           
          ****    ****    ****    ****   ****    ****    ****    ****          
         **  **  **  **  **  **  **  ** **  **  **  **  **  **  **  **         
        ***************************************************************        
       **                                                             **       
      ****                                                           ****      
     **  **                                                         **  **     
    ********                                                       ********    
   **      **                                                     **      **   
  ****    ****                                                   ****    ****  
 **  **  **  **                                                 **  **  **  ** 
****************                                               ****************
*              **                                             **              *
**            ****                                           ****            **
 **          **  **                                         **  **          ** 
****        ********                                       ********        ****
   **      **      **                                     **      **      **   
  ****    ****    ****                                   ****    ****    ****  
 **  **  **  **  **  **                                 **  **  **  **  **  ** 
************************                               ************************
                       **                             **                       
                      ****                           ****                      
   end

Enter Rule (0 - 255): 131
   start
                                       *                                       
***************************************  **************************************
**************************************  * *************************************
*************************************  *   ************************************
************************************  *  ** ***********************************
***********************************  *  *    **********************************
**********************************  *  *  *** *********************************
*********************************  *  *  * *   ********************************
********************************  *  *  *    ** *******************************
*******************************  *  *  *  ***    ******************************
******************************  *  *  *  * *  *** *****************************
*****************************  *  *  *  *    * *   ****************************
****************************  *  *  *  *  ***    ** ***************************
***************************  *  *  *  *  * *  ***    **************************
**************************  *  *  *  *  *    * *  *** *************************
*************************  *  *  *  *  *  ***    * *   ************************
************************  *  *  *  *  *  * *  ***    ** ***********************
***********************  *  *  *  *  *  *    * *  ***    **********************
**********************  *  *  *  *  *  *  ***    * *  *** *********************
*********************  *  *  *  *  *  *  * *  ***    * *   ********************
********************  *  *  *  *  *  *  *    * *  ***    ** *******************
*******************  *  *  *  *  *  *  *  ***    * *  ***    ******************
******************  *  *  *  *  *  *  *  * *  ***    * *  *** *****************
*****************  *  *  *  *  *  *  *  *    * *  ***    * *   ****************
****************  *  *  *  *  *  *  *  *  ***    * *  ***    ** ***************
***************  *  *  *  *  *  *  *  *  * *  ***    * *  ***    **************
**************  *  *  *  *  *  *  *  *  *    * *  ***    * *  *** *************
*************  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *   ************
************  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    ** ***********
***********  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    **********
**********  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  *** *********
*********  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *   ********
********  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    ** *******
*******  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    ******
******  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  *** *****
*****  *  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    * *   ****
****  *  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    ** ***
***  *  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  ***    **
**  *  *  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    * *  *** *
*  *  *  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    * *   
  *  *  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  ***    **
 *  *  *  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    * *  ***  
*  *  *  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    * *  *
  *  *  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  ***    * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    * *  ***  
*  *  *  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    * *  *
  *  *  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  ***    * 
 *  *  *  *  *  *  *  *  *  *  *  *  *  *    * *  ***    * *  ***    * *  ***  
*  *  *  *  *  *  *  *  *  *  *  *  *  *  ***    * *  ***    * *  ***    * *  *
  *  *  *  *  *  *  *  *  *  *  *  *  *  * *  ***    * *  ***    * *  ***    * 
   end

Enter Rule (0 - 255): 30
   start
                                       *                                       
                                      ***                                      
                                     **  *                                     
                                    ** ****                                    
                                   **  *   *                                   
                                  ** **** ***                                  
                                 **  *    *  *                                 
                                ** ****  ******                                
                               **  *   ***     *                               
                              ** **** **  *   ***                              
                             **  *    * **** **  *                             
                            ** ****  ** *    * ****                            
                           **  *   ***  **  ** *   *                           
                          ** **** **  *** ***  ** ***                          
                         **  *    * ***   *  ***  *  *                         
                        ** ****  ** *  * *****  *******                        
                       **  *   ***  **** *    ***      *                       
                      ** **** **  ***    **  **  *    ***                      
                     **  *    * ***  *  ** *** ****  **  *                     
                    ** ****  ** *  ******  *   *   *** ****                    
                   **  *   ***  ****     **** *** **   *   *                   
                  ** **** **  ***   *   **    *   * * *** ***                  
                 **  *    * ***  * *** ** *  *** ** * *   *  *                 
                ** ****  ** *  *** *   *  ****   *  * ** ******                
               **  *   ***  ****   ** *****   * ***** *  *     *               
              ** **** **  ***   * **  *    * ** *     *****   ***              
             **  *    * ***  * ** * ****  ** *  **   **    * **  *             
            ** ****  ** *  *** *  * *   ***  **** * ** *  ** * ****            
           **  *   ***  ****   **** ** **  ***    * *  ****  * *   *           
          ** **** **  ***   * **    *  * ***  *  ** ****   *** ** ***          
         **  *    * ***  * ** * *  ***** *  ******  *   * **   *  *  *         
        ** ****  ** *  *** *  * ****     ****     **** ** * * *********        
       **  *   ***  ****   **** *   *   **   *   **    *  * * *        *       
      ** **** **  ***   * **    ** *** ** * *** ** *  ***** * **      ***      
     **  *    * ***  * ** * *  **  *   *  * *   *  ****     * * *    **  *     
    ** ****  ** *  *** *  * **** **** ***** ** *****   *   ** * **  ** ****    
   **  *   ***  ****   **** *    *    *     *  *    * *** **  * * ***  *   *   
  ** **** **  ***   * **    **  ***  ***   ******  ** *   * *** * *  **** ***  
 **  *    * ***  * ** * *  ** ***  ***  * **     ***  ** ** *   * ****    *  * 
** ****  ** *  *** *  * ****  *  ***  *** * *   **  ***  *  ** ** *   *  ******
*  *   ***  ****   **** *   ******  ***   * ** ** ***  ******  *  ** *****     
 **** **  ***   * **    ** **     ***  * ** *  *  *  ***     ******  *    *   *
 *    * ***  * ** * *  **  * *   **  *** *  **********  *   **     ****  *** **
***  ** *  *** *  * **** *** ** ** ***   ****         **** ** *   **   ***   * 
   ***  ****   **** *    *   *  *  *  * **   *       **    *  ** ** * **  * ** 
* **  ***   * **    **  *** *********** * * ***     ** *  *****  *  * * *** *  
  * ***  * ** * *  ** ***   *           * * *  *   **  ****    ****** * *   ***
 ** *  *** *  * ****  *  * ***         ** * ***** ** ***   *  **      * ** **  
**  ****   **** *   ****** *  *       **  * *     *  *  * ***** *    ** *  * * 
  ***   * **    ** **      *****     ** *** **   ******** *     **  **  **** * 
   end

End of Run ------------------------------------------------------------------ */
