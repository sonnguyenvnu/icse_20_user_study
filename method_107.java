/** 
 * Tells if the token looks like a long option.
 * @param token
 */
private boolean _XXXXX_(final String token){
  if (!token.startsWith("-") || token.length() == 1) {
    return false;
  }
  final int pos=token.indexOf("=");
  final String t=pos == -1 ? token : token.substring(0,pos);
  if (!getMatchingLongOptions(t).isEmpty()) {
    return true;
  }
 else   if (getLongPrefix(token) != null && !token.startsWith("--")) {
    return true;
  }
  return false;
}