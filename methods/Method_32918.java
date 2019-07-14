/** 
 * Note that the column may end of second half of wide character. 
 */
public int findStartOfColumn(int column){
  if (column == mColumns)   return getSpaceUsed();
  int currentColumn=0;
  int currentCharIndex=0;
  while (true) {
    int newCharIndex=currentCharIndex;
    char c=mText[newCharIndex++];
    boolean isHigh=Character.isHighSurrogate(c);
    int codePoint=isHigh ? Character.toCodePoint(c,mText[newCharIndex++]) : c;
    int wcwidth=WcWidth.width(codePoint);
    if (wcwidth > 0) {
      currentColumn+=wcwidth;
      if (currentColumn == column) {
        while (newCharIndex < mSpaceUsed) {
          if (Character.isHighSurrogate(mText[newCharIndex])) {
            if (WcWidth.width(Character.toCodePoint(mText[newCharIndex],mText[newCharIndex + 1])) <= 0) {
              newCharIndex+=2;
            }
 else {
              break;
            }
          }
 else           if (WcWidth.width(mText[newCharIndex]) <= 0) {
            newCharIndex++;
          }
 else {
            break;
          }
        }
        return newCharIndex;
      }
 else       if (currentColumn > column) {
        return currentCharIndex;
      }
    }
    currentCharIndex=newCharIndex;
  }
}
