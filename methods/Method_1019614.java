/** 
 * Adds a string to this token map.  The char array passed-in will be used as the actual data for the token, so it may well be modified (such as lower-casing it if <code>ignoreCase</code> is <code>true</code>).  This shouldn't be an issue though as this method is only called from the public <code>put</code> method, which allocates a new char array.
 * @param string The string to add.
 * @param tokenType The type of token the string is.
 */
private void put(char[] string,int tokenType){
  int hashCode=getHashCode(string,0,string.length);
  addTokenToBucket(hashCode,new TokenMapToken(string,tokenType));
}
