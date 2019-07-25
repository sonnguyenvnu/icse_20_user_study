/** 
 * Validates the legal characters of a percent-encoded string that represents a URI component type.
 * @param s the encoded string.
 * @param t the URI compontent type identifying the legal characters.
 * @param template true if the encoded string contains URI template variables
 * @throws IllegalArgumentException if the encoded string contains illegalcharacters.
 */
public static void validate(String s,Type t,boolean template) throws IllegalArgumentException {
  int i=_valid(s,t,template);
  if (i > -1) {
    throw new IllegalArgumentException("The string '" + s + "' for the URI component " + t + " contains an invalid character, '" + s.charAt(i) + "', at index " + i);
  }
}
