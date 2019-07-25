/** 
 * Wrap a java.lang.String.
 * @param x the string
 * @return the object
 */
@Ignore public static String wrap(java.lang.String x){
  return new String(x.toCharArray());
}
