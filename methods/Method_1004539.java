/** 
 * Parses and formats the given char sequence, potentially removing some data to make the output easier to understand. <p> Equivalent to <code>eval(in).getApproximately()</code>
 * @param in the char sequence, may not be <code>null</code>
 * @return a formatted String, never <code>null</code>
 */
public static String approximately(CharSequence in){
  return eval(in).getApproximately();
}
