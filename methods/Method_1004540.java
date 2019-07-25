/** 
 * Formats the given time delta, preserving all data. <p> Equivalent to <code>new HumanTime(l).getApproximately()</code>
 * @param l the time delta
 * @return a formatted String, never <code>null</code>
 */
public static String approximately(long l){
  return new HumanTime(l).getApproximately();
}
