/** 
 * Create a String from a char array with zero-copy (if available), using reflection to access a package-protected constructor of String.
 */
public static String wrapCharArray(char[] c){
  if (c == null)   return null;
  String s=null;
  if (stringConstructor != null) {
    try {
      s=stringConstructor.newInstance(0,c.length,c);
    }
 catch (    Exception e) {
    }
  }
  return s == null ? new String(c) : s;
}
