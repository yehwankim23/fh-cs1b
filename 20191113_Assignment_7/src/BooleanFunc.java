
// -----------------------------------------------------------------------------
// Class BooleanFunc
class BooleanFunc implements Cloneable
{
   // static members
   public static int MAX_TABLE_FOR_CLASS = 65536; // 16 binary inputs
   public static int DEFAULT_TABLE_SIZE = 16; // 4 binary inputs

   // instance members
   private int tableSize;
   private boolean[] truthTable;
   private boolean evalReturnIfError = false;
   private boolean state = false;

   // instance methods
   // constructors
   public BooleanFunc()
   {
      this(DEFAULT_TABLE_SIZE);
   }

   public BooleanFunc(int tableSize)
   {
      if (!setTableSize(tableSize))
      {
         setTableSize(DEFAULT_TABLE_SIZE);
      }
      truthTable = new boolean[this.tableSize];
   }

   public BooleanFunc(int tableSize, boolean evalReturnIfError)
   {
      this(tableSize);
      this.evalReturnIfError = evalReturnIfError;
   }

   // mutators
   public boolean setTruthTableUsingTrue(int[] inputsThatProduceTrue)
   {
      return setTruthTable(inputsThatProduceTrue, true);
   }

   public boolean setTruthTableUsingFalse(int[] inputsThatProduceFalse)
   {
      return setTruthTable(inputsThatProduceFalse, false);
   }

   public boolean eval(int input)
   {
      if (tableSize <= input)
      {
         state = evalReturnIfError;
      } else
      {
         state = truthTable[input];
      }
      return state;
   }

   // accessor
   public boolean getState()
   {
      return state;
   }

   // deep memory method
   protected BooleanFunc clone() throws CloneNotSupportedException
   {
      int i; // for loop counter
      BooleanFunc newObject = (BooleanFunc) super.clone();
      newObject.truthTable = new boolean[this.tableSize];
      for (i = 0; i < newObject.tableSize; i++)
      {
         newObject.truthTable[i] = this.truthTable[i];
      }
      return newObject;
   }

   // helper methods
   private boolean setTableSize(int tableSize)
   {
      boolean retVal = false; // return value
      if (0 < tableSize && tableSize <= MAX_TABLE_FOR_CLASS)
      {
         retVal = true;
         this.tableSize = tableSize;
      }
      return retVal;
   }

   private boolean setTruthTable(int[] inputs, boolean value)
   {
      boolean retVal = false; // return value
      int i; // for loop counter
      if (inputs.length <= truthTable.length)
      {
         retVal = true;
         for (i = 0; i < truthTable.length; i++)
         {
            truthTable[i] = !value;
         }
         for (i = 0; i < inputs.length; i++)
         {
            if (inputs[i] < truthTable.length)
            {
               truthTable[inputs[i]] = value;
            }
         }
      }
      return retVal;
   }
}
