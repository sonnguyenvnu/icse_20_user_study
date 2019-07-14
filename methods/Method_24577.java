/** 
 * Increments pos, sets EOF if needed, and returns the new chars[pos] or zero if out-of-bounds. Sets lastNonWhitespace if chars[pos] isn't whitespace. Does nothing and returns zero if already at EOF.
 */
private char nextChar(){
  if (EOF)   return 0;
  pos++;
  if (pos >= chars.length - 1)   EOF=true;
  if (pos >= chars.length)   return 0;
  char retVal=chars[pos];
  if (!Character.isWhitespace(retVal))   lastNonWhitespace=retVal;
  return retVal;
}
