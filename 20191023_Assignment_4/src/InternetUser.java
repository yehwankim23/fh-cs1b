// The Base Class: InternetUser ------------------------------------------------
class InternetUser
{
   // public static class constants
   final public static int MAX_NAME_LEN = 74;
   final public static int MIN_NAME_LEN = 1;
   final public static int MAX_IP4_LEN = 15; // xxx.xxx.xxx.xxx
   final public static int MIN_IP4_LEN = 7; // x.x.x.x
   final public static String DEFAULT_NAME = "(undefined)";
   final public static String DEFAULT_IP = "0.0.0.0";

   // private member data
   private String name;
   private String ip;

   // public methods
   // constructors
   public InternetUser()
   {
      this(DEFAULT_NAME, DEFAULT_IP);
   }

   public InternetUser(String name, String ip)
   {
      setName(name);
      setIp(ip);
   }

   // mutators
   public void setName(String name)
   {
      if (isValidName(name))
      {
         this.name = name;
      } else
      {
         this.name = DEFAULT_NAME;
      }
   }

   public void setIp(String ip)
   {
      if (isValidIp(ip))
      {
         this.ip = ip;
      } else
      {
         this.ip = DEFAULT_IP;
      }
   }

   // accessors
   public String getName()
   {
      return name;
   }

   public String getIp()
   {
      return ip;
   }

   // stringizer
   public String toString()
   {
      String retVal; // return value

      retVal = "Name: " + name + "\nIP Address: " + ip + "\n";

      return retVal;
   }

   // private methods
   // validation helpers
   private static boolean isValidName(String name)
   {
      boolean retVal = false; // return value

      if (name.length() >= MIN_NAME_LEN && name.length() <= MAX_NAME_LEN)
      {
         retVal = true;
      }

      return retVal;
   }

   private static boolean isValidIp(String ip)
   {
      boolean retVal = false; // return value

      if (ip.length() >= MIN_IP4_LEN && ip.length() <= MAX_IP4_LEN)
      {
         retVal = true;
      }

      return retVal;
   }
}
