/** 
 * Send a Unicode code point to the screen.
 * @param codePoint The code point of the character to display
 */
private void emitCodePoint(int codePoint){
  mLastEmittedCodePoint=codePoint;
  if (mUseLineDrawingUsesG0 ? mUseLineDrawingG0 : mUseLineDrawingG1) {
switch (codePoint) {
case '_':
      codePoint=' ';
    break;
case '`':
  codePoint='?';
break;
case '0':
codePoint='?';
break;
case 'a':
codePoint='?';
break;
case 'b':
codePoint='?';
break;
case 'c':
codePoint='?';
break;
case 'd':
codePoint='\r';
break;
case 'e':
codePoint='?';
break;
case 'f':
codePoint='°';
break;
case 'g':
codePoint='±';
break;
case 'h':
codePoint='\n';
break;
case 'i':
codePoint='?';
break;
case 'j':
codePoint='?';
break;
case 'k':
codePoint='?';
break;
case 'l':
codePoint='?';
break;
case 'm':
codePoint='?';
break;
case 'n':
codePoint='?';
break;
case 'o':
codePoint='?';
break;
case 'p':
codePoint='?';
break;
case 'q':
codePoint='?';
break;
case 'r':
codePoint='?';
break;
case 's':
codePoint='?';
break;
case 't':
codePoint='?';
break;
case 'u':
codePoint='?';
break;
case 'v':
codePoint='?';
break;
case 'w':
codePoint='?';
break;
case 'x':
codePoint='?';
break;
case 'y':
codePoint='?';
break;
case 'z':
codePoint='?';
break;
case '{':
codePoint='?';
break;
case '|':
codePoint='?';
break;
case '}':
codePoint='£';
break;
case '~':
codePoint='·';
break;
}
}
final boolean autoWrap=isDecsetInternalBitSet(DECSET_BIT_AUTOWRAP);
final int displayWidth=WcWidth.width(codePoint);
final boolean cursorInLastColumn=mCursorCol == mRightMargin - 1;
if (autoWrap) {
if (cursorInLastColumn && ((mAboutToAutoWrap && displayWidth == 1) || displayWidth == 2)) {
mScreen.setLineWrap(mCursorRow);
mCursorCol=mLeftMargin;
if (mCursorRow + 1 < mBottomMargin) {
mCursorRow++;
}
 else {
scrollDownOneLine();
}
}
}
 else if (cursorInLastColumn && displayWidth == 2) {
return;
}
if (mInsertMode && displayWidth > 0) {
int destCol=mCursorCol + displayWidth;
if (destCol < mRightMargin) mScreen.blockCopy(mCursorCol,mCursorRow,mRightMargin - destCol,1,destCol,mCursorRow);
}
int offsetDueToCombiningChar=((displayWidth <= 0 && mCursorCol > 0 && !mAboutToAutoWrap) ? 1 : 0);
mScreen.setChar(mCursorCol - offsetDueToCombiningChar,mCursorRow,codePoint,getStyle());
if (autoWrap && displayWidth > 0) mAboutToAutoWrap=(mCursorCol == mRightMargin - displayWidth);
mCursorCol=Math.min(mCursorCol + displayWidth,mRightMargin - 1);
}
