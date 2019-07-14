/** 
 * <p>Returns either the passed in String, or if the String is  {@code null}, an empty String ("").</p> <pre> StringUtils.defaultString(null)  = "" StringUtils.defaultString("")    = "" StringUtils.defaultString("bat") = "bat" </pre>
 * @param str the String to check, may be null
 * @return the passed in String, or the empty String if itwas  {@code null}
 * @see String#valueOf(Object)
 */
public static String defaultString(final Object str){
  return toString(str,EMPTY);
}
