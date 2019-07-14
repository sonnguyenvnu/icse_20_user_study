/** 
 * Locates the end of the word at the specified position. Moved from TextUtilities.java [fry 121210].
 * @param line The text
 * @param pos The position
 */
public static int findWordEnd(String line,int pos,String noWordSep){
  char ch=line.charAt(pos);
  if (noWordSep == null) {
    noWordSep="";
  }
  boolean selectNoLetter=!Character.isLetterOrDigit(ch) && noWordSep.indexOf(ch) == -1;
  int wordEnd=line.length();
  for (int i=pos; i < line.length(); i++) {
    ch=line.charAt(i);
    if (selectNoLetter ^ (!Character.isLetterOrDigit(ch) && noWordSep.indexOf(ch) == -1)) {
      wordEnd=i;
      break;
    }
  }
  return wordEnd;
}
