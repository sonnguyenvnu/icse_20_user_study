/** 
 * Check TEXT data defined in RFC2616.
 * @param ch checking data
 * @return true when ch is TEXT, false when ch is not TEXT
 */
protected static boolean isText(int ch){
  if (((ch >= 32) && (ch <= 126)) || ((ch >= 128) && (ch <= 255))) {
    return true;
  }
switch (ch) {
case '\t':
case '\n':
case '\r':
    return true;
}
return false;
}
