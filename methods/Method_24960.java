/** 
 * Are we inside a string? (TODO: ignore comments in the code)
 * @param pos position in the code
 * @param code the code
 * @return
 */
static private boolean isInsideString(int pos,String code){
  int quoteNum=0;
  for (int c=pos; c >= 0 && code.charAt(c) != '\n'; c--) {
    if (code.charAt(c) == '"') {
      quoteNum++;
    }
  }
  if (quoteNum % 2 == 1) {
    return true;
  }
  return false;
}
