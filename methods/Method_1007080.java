/** 
 * Regular expression pattern matching.
 * @param regex A regular expression to match this lazy string.
 * @return True if this string mathches the given regular expression, otherwise False.
 */
public boolean matches(final String regex){
  return Pattern.matches(regex,this);
}
