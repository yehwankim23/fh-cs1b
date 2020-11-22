// The Derived Class: Communicator ---------------------------------------------
class Communicator extends InternetUser
{
   // private members
   private IntPair publicKey, privateKey;
   private int firstPrime, secondPrime, n, phi, e, d;

   // static class constants
   public final long ERROR_FLAG_NUM = 0;
   private final long MAX_PQ = (long) Math.sqrt(Long.MAX_VALUE);

   // public methods
   public Communicator()
   {
      super();
   }

   public Communicator(long firstPrime, long secondPrime)
   {
      this(DEFAULT_NAME, DEFAULT_IP, firstPrime, secondPrime);
   }

   public Communicator(String name, String ip)
   {
      super(name, ip);
   }

   public Communicator(String name, String ip, long firstPrime,
         long secondPrime)
   {
      super(name, ip);

      if (!setBoth(firstPrime, secondPrime))
      {
         setBoth(ERROR_FLAG_NUM, ERROR_FLAG_NUM);
      }
   }

   // mutator
   public boolean setBoth(long firstPrime, long secondPrime)
   {
      boolean retVal = false; // return value

      if (firstPrime != secondPrime && firstPrime <= MAX_PQ
            && secondPrime <= MAX_PQ && EncryptionSupport.isPrime(firstPrime)
            && EncryptionSupport.isPrime(secondPrime))
      {
         this.firstPrime = (int) firstPrime;
         this.secondPrime = (int) secondPrime;

         computeBothEncrKeys();

         retVal = true;
      }

      return retVal;
   }

   // accessors
   public IntPair getPublicKey()
   {
      return publicKey;
   }

   public IntPair getPrivateKey()
   {
      return privateKey;
   }

   // stringizer
   public String toString()
   {
      String retVal; // return value

      retVal = super.toString() + "firstPrime, secondPrime, n, phi, e, d: "
            + firstPrime + ", " + secondPrime + ", " + n + ", " + phi + ", " + e
            + ", " + d + "\nPublic Key: " + publicKey + "\nPrivate Key: "
            + privateKey + "\n";

      return retVal;
   }

   // private methods
   private boolean computeBothEncrKeys()
   {
      boolean loop = true; // while loop
      int i = 0; // counter
      int random; // random prime number

      n = firstPrime * secondPrime;
      phi = (firstPrime - 1) * (secondPrime - 1);

      while (loop)
      {
         if (i == 1000)
         {
            return false; // have tried 1000 times and failed
         }

         i++;
         random = (int) EncryptionSupport.getSmallRandomPrime();

         if (random < phi && phi % random != 0)
         {
            e = random;
            loop = false;
         }
      }

      d = (int) EncryptionSupport.inverseOfAModM(e, phi);
      publicKey = new IntPair(e, n);
      privateKey = new IntPair(d, n);

      return true;
   }
}
