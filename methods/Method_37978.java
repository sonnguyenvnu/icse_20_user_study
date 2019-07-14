/** 
 * Utility method to take a string and convert it to normal Java variable name capitalization.  This normally means converting the first character from upper case to lower case, but in the (unusual) special case when there is more than one character and both the first and second characters are upper case, we leave it alone. <p> Thus "FooBah" becomes "fooBah" and "X" becomes "x", but "URL" stays as "URL".
 * @param name The string to be decapitalized.
 * @return The decapitalized version of the string.
 */
public static String decapitalize(final String name){
  if (name.length() == 0) {
    return name;
  }
  if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
    return name;
  }
  char[] chars=name.toCharArray();
  char c=chars[0];
  char modifiedChar=Character.toLowerCase(c);
  if (modifiedChar == c) {
    return name;
  }
  chars[0]=modifiedChar;
  return new String(chars);
}
