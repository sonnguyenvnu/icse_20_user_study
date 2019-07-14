/** 
 * Locates the start of the word at the specified position. Moved from TextUtilities.java [fry 121210].
 * @param line The text
 * @param pos The position
 */
public static int findWordStart(String line,int pos,String noWordSep){
  char ch=line.charAt(pos - 1);
  if (noWordSep == null) {
    noWordSep="";
  }
  boolean selectNoLetter=!Character.isLetterOrDigit(ch) && noWordSep.indexOf(ch) == -1;
  int wordStart=0;
  for (int i=pos - 1; i >= 0; i--) {
    ch=line.charAt(i);
    if (selectNoLetter ^ (!Character.isLetterOrDigit(ch) && noWordSep.indexOf(ch) == -1)) {
      wordStart=i + 1;
      break;
    }
  }
  return wordStart;
}
