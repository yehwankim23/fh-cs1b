import java.util.*;

// Assignment 4 - Encryption Keys and Inheritance

// provided support classes ----------------------------------------------------
// not modified. re-indented and re-formatted
// IntPair allows public, no filtering; classes that use it will protect it
class IntPair
{
   public long firstInt;
   public long secondInt;

   // constructors
   IntPair()
   {
      firstInt = secondInt = 0;
   }

   IntPair(long frst, long scnd)
   {
      firstInt = frst;
      secondInt = scnd;
   }

   public String toString()
   {
      return "(" + firstInt + ", " + secondInt + ")";
   }
};

// EncryptionSupport contains only static methods for client use 
// method names should be fairly descriptive except inverseOfAModM(), which
// you can take as a black-box (see description of assignment)
class EncryptionSupport
{
   static private Random randObject = new Random(System.currentTimeMillis());

   public static boolean isPrime(long x)
   {
      long k, loopLim;

      if (x < 2)
         return false;
      if (x < 4)
         return true;
      if (x % 2 == 0 || x % 3 == 0)
         return false;

      // now use the fact the all primes of form 6k +/- 1
      loopLim = (long) Math.sqrt(x);
      for (k = 5; k <= loopLim; k += 6)
      {
         if (x % k == 0 || x % (k + 2) == 0)
            return false;
      }
      return true;
   }

   public static long inverseOfAModM(long a, long m)
   {
      // uses extended euclidean algorithm giving as + mt = gcd(m, a),
      // with gcd(m, a) = 1, and s, t discovered. s = 1/a, and t ignored

      // NOTE: this only provides an inverse if there is one, namely, if
      // a is relatively prime to m. otherwise the value it returns is
      // not the inverse. in our case, we are passing e to a which is
      // relatively prime to phi, so it always works.

      long s, t, r, sPrev, tPrev, rPrev, temp, q, inverse;

      // initialize working variables
      s = 0;
      t = 1;
      r = m;
      sPrev = 1;
      tPrev = 0;
      rPrev = a;

      while (r != 0)
      {
         q = rPrev / r;

         temp = r;
         r = rPrev - q * r;
         rPrev = temp;

         temp = s;
         s = sPrev - q * s;
         sPrev = temp;

         temp = t;
         t = tPrev - q * t;
         tPrev = temp;
      }

      inverse = sPrev % m;
      if (inverse < 0)
         inverse += m;
      return inverse;
   }

   public static long getSmallRandomPrime()
   {
      int index;
      long lowPrimes[] =
      { 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163,
            167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233,
            239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311,
            313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389,
            397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
            467, 479, 487, 491, 499, 503, 509, 521, 523, 541 };
      long arraySize = lowPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int) (randObject.nextDouble() * arraySize);

      return lowPrimes[index];
   }

   public static long getMedSizedRandomPrime()
   {
      int index;
      long medPrimes[] =
      { 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617,
            619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
            709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797,
            809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881,
            883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977,
            983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051,
            1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123,
            1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217,
            1223, };
      long arraySize = medPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int) (randObject.nextDouble() * arraySize);

      return medPrimes[index];
   }
}

public class Main
{
   public static void main(String[] args)
   {
      // Test of Option A: InternetUser, Communicator, and Two Helper Classes --
      // Test of The Base Class: InternetUser
      System.out.println("Test of The Base Class: InternetUser\n");
      InternetUser user1 = new InternetUser(); // default
      InternetUser user2 = new InternetUser("one", "1.1.1.1"); // valid
      InternetUser user3 = new InternetUser("", "2.222.22222.2222222"); // invalid

      System.out.println("After construction:\n");
      System.out.println(user1.toString());
      System.out.println(user2.toString());
      System.out.println(user3.toString());

      user1.setName("three");
      user1.setIp("3.3.3.3");
      user2.setName("four");
      user2.setIp("4.4.4.4");
      user3.setName("five");
      user3.setIp("5.5.5.5");

      System.out.println("\nAfter mutation:\n");
      System.out.println(user1.toString());
      System.out.println(user2.toString());
      System.out.println(user3.toString());

      // Test of The Derived Class: Communicator
      System.out.println("\nTest of The Derived Class: Communicator\n");
      Communicator com1 = new Communicator(); // default
      Communicator com2 = new Communicator(23, 29); // firstPrime, secondPrime
      Communicator com3 = new Communicator("six", "6.6.6.6"); // name, ip
      Communicator com4 = new Communicator("seven", "7.7.7.7", 31, 37); // valid
      Communicator com5 = new Communicator("", "8.888.88888.8888888", 88, 8888); // invalid

      System.out.println("After construction:\n");
      System.out.println(com1.toString());
      System.out.println(com2.toString());
      System.out.println(com3.toString());
      System.out.println(com4.toString());
      System.out.println(com5.toString());

      com1.setName("nine");
      com1.setIp("9.9.9.9");
      com1.setBoth(41, 43);
      com2.setName("ten");
      com2.setIp("10.10.10.10");
      com2.setBoth(47, 53);
      com3.setName("eleven");
      com3.setIp("11.11.11.11");
      com3.setBoth(61, 67);
      com4.setName("twelve");
      com4.setIp("12.12.12.12");
      com4.setBoth(71, 73);
      com5.setName("thirteen");
      com5.setIp("13.13.13.13");
      com5.setBoth(79, 83);

      System.out.println("\nAfter mutation:\n");
      System.out.println(com1.toString());
      System.out.println(com2.toString());
      System.out.println(com3.toString());
      System.out.println(com4.toString());
      System.out.println(com5.toString());

      System.out.println("\nAccessors (com5):\n");
      System.out.println("Name: " + com5.getName());
      System.out.println("IP Address: " + com5.getIp());
      System.out.println("Public Key: " + com5.getPublicKey());
      System.out.println("Private Key: " + com5.getPrivateKey());
   }
}

/* Run of Test of Option A: InternetUser, Communicator, and Two Helper Classes -

Test of The Base Class: InternetUser

After construction:

Name: (undefined)
IP Address: 0.0.0.0

Name: one
IP Address: 1.1.1.1

Name: (undefined)
IP Address: 0.0.0.0


After mutation:

Name: three
IP Address: 3.3.3.3

Name: four
IP Address: 4.4.4.4

Name: five
IP Address: 5.5.5.5


Test of The Derived Class: Communicator

After construction:

Name: (undefined)
IP Address: 0.0.0.0
firstPrime, secondPrime, n, phi, e, d: 0, 0, 0, 0, 0, 0
Public Key: null
Private Key: null

Name: (undefined)
IP Address: 0.0.0.0
firstPrime, secondPrime, n, phi, e, d: 23, 29, 667, 616, 431, 303
Public Key: (431, 667)
Private Key: (303, 667)

Name: six
IP Address: 6.6.6.6
firstPrime, secondPrime, n, phi, e, d: 0, 0, 0, 0, 0, 0
Public Key: null
Private Key: null

Name: seven
IP Address: 7.7.7.7
firstPrime, secondPrime, n, phi, e, d: 31, 37, 1147, 1080, 19, 739
Public Key: (19, 1147)
Private Key: (739, 1147)

Name: (undefined)
IP Address: 0.0.0.0
firstPrime, secondPrime, n, phi, e, d: 0, 0, 0, 0, 0, 0
Public Key: null
Private Key: null


After mutation:

Name: nine
IP Address: 9.9.9.9
firstPrime, secondPrime, n, phi, e, d: 41, 43, 1763, 1680, 107, 1523
Public Key: (107, 1763)
Private Key: (1523, 1763)

Name: ten
IP Address: 10.10.10.10
firstPrime, secondPrime, n, phi, e, d: 47, 53, 2491, 2392, 181, 1533
Public Key: (181, 2491)
Private Key: (1533, 2491)

Name: eleven
IP Address: 11.11.11.11
firstPrime, secondPrime, n, phi, e, d: 61, 67, 4087, 3960, 59, 2819
Public Key: (59, 4087)
Private Key: (2819, 4087)

Name: twelve
IP Address: 12.12.12.12
firstPrime, secondPrime, n, phi, e, d: 71, 73, 5183, 5040, 211, 1051
Public Key: (211, 5183)
Private Key: (1051, 5183)

Name: thirteen
IP Address: 13.13.13.13
firstPrime, secondPrime, n, phi, e, d: 79, 83, 6557, 6396, 157, 937
Public Key: (157, 6557)
Private Key: (937, 6557)


Accessors (com5):

Name: thirteen
IP Address: 13.13.13.13
Public Key: (157, 6557)
Private Key: (937, 6557)

End of Run ------------------------------------------------------------------ */
