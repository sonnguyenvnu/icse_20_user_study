private boolean wideDisplayCharacterStartingAt(int column){
  for (int currentCharIndex=0, currentColumn=0; currentCharIndex < mSpaceUsed; ) {
    char c=mText[currentCharIndex++];
    int codePoint=Character.isHighSurrogate(c) ? Character.toCodePoint(c,mText[currentCharIndex++]) : c;
    int wcwidth=WcWidth.width(codePoint);
    if (wcwidth > 0) {
      if (currentColumn == column && wcwidth == 2)       return true;
      currentColumn+=wcwidth;
      if (currentColumn > column)       return false;
    }
  }
  return false;
}
