/** 
 * Adds a string to this token map.
 * @param string The string to add.
 * @param tokenType The type of token the string is.
 */
public void put(final String string,final int tokenType){
  if (isIgnoringCase()) {
    put(string.toLowerCase().toCharArray(),tokenType);
  }
 else {
    put(string.toCharArray(),tokenType);
  }
}
