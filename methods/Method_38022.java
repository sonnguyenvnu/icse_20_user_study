/** 
 * Reverse a string.
 */
public static String reverse(final String s){
  StringBuilder result=new StringBuilder(s.length());
  for (int i=s.length() - 1; i >= 0; i--) {
    result.append(s.charAt(i));
  }
  return result.toString();
}
