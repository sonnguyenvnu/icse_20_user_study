/** 
 * Check TOKEN data defined in RFC2616.
 * @param ch checking data
 * @return true when ch is TOKEN, false when ch is not TOKEN
 */
protected static boolean isTokenCharacter(int ch){
  if ((ch < 33) || (ch > 126)) {
    return false;
  }
switch (ch) {
case '"':
case '(':
case ')':
case ',':
case '/':
case ':':
case ';':
case '<':
case '=':
case '>':
case '?':
case '@':
case '[':
case '\\':
case ']':
case '{':
case '}':
    return false;
}
return true;
}
