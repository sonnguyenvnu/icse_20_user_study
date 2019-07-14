/** 
 * NOTE: The sourceX2 is exclusive. 
 */
public void copyInterval(TerminalRow line,int sourceX1,int sourceX2,int destinationX){
  mHasNonOneWidthOrSurrogateChars|=line.mHasNonOneWidthOrSurrogateChars;
  final int x1=line.findStartOfColumn(sourceX1);
  final int x2=line.findStartOfColumn(sourceX2);
  boolean startingFromSecondHalfOfWideChar=(sourceX1 > 0 && line.wideDisplayCharacterStartingAt(sourceX1 - 1));
  final char[] sourceChars=(this == line) ? Arrays.copyOf(line.mText,line.mText.length) : line.mText;
  int latestNonCombiningWidth=0;
  for (int i=x1; i < x2; i++) {
    char sourceChar=sourceChars[i];
    int codePoint=Character.isHighSurrogate(sourceChar) ? Character.toCodePoint(sourceChar,sourceChars[++i]) : sourceChar;
    if (startingFromSecondHalfOfWideChar) {
      codePoint=' ';
      startingFromSecondHalfOfWideChar=false;
    }
    int w=WcWidth.width(codePoint);
    if (w > 0) {
      destinationX+=latestNonCombiningWidth;
      sourceX1+=latestNonCombiningWidth;
      latestNonCombiningWidth=w;
    }
    setChar(destinationX,codePoint,line.getStyle(sourceX1));
  }
}
