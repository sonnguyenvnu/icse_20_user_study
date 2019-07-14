/** 
 * Encountering a character in the  {@link #ESC} state. 
 */
private void doEsc(int b){
switch (b) {
case '#':
    continueSequence(ESC_POUND);
  break;
case '(':
continueSequence(ESC_SELECT_LEFT_PAREN);
break;
case ')':
continueSequence(ESC_SELECT_RIGHT_PAREN);
break;
case '6':
if (mCursorCol > mLeftMargin) {
mCursorCol--;
}
 else {
int rows=mBottomMargin - mTopMargin;
mScreen.blockCopy(mLeftMargin,mTopMargin,mRightMargin - mLeftMargin - 1,rows,mLeftMargin + 1,mTopMargin);
mScreen.blockSet(mLeftMargin,mTopMargin,1,rows,' ',TextStyle.encode(mForeColor,mBackColor,0));
}
break;
case '7':
saveCursor();
break;
case '8':
restoreCursor();
break;
case '9':
if (mCursorCol < mRightMargin - 1) {
mCursorCol++;
}
 else {
int rows=mBottomMargin - mTopMargin;
mScreen.blockCopy(mLeftMargin + 1,mTopMargin,mRightMargin - mLeftMargin - 1,rows,mLeftMargin,mTopMargin);
mScreen.blockSet(mRightMargin - 1,mTopMargin,1,rows,' ',TextStyle.encode(mForeColor,mBackColor,0));
}
break;
case 'c':
reset();
blockClear(0,0,mColumns,mRows);
setCursorPosition(0,0);
break;
case 'D':
doLinefeed();
break;
case 'E':
setCursorCol(isDecsetInternalBitSet(DECSET_BIT_ORIGIN_MODE) ? mLeftMargin : 0);
doLinefeed();
break;
case 'F':
setCursorRowCol(0,mBottomMargin - 1);
break;
case 'H':
mTabStop[mCursorCol]=true;
break;
case 'M':
if (mCursorRow <= mTopMargin) {
mScreen.blockCopy(0,mTopMargin,mColumns,mBottomMargin - (mTopMargin + 1),0,mTopMargin + 1);
blockClear(0,mTopMargin,mColumns);
}
 else {
mCursorRow--;
}
break;
case 'N':
case '0':
break;
case 'P':
mOSCOrDeviceControlArgs.setLength(0);
continueSequence(ESC_P);
break;
case '[':
continueSequence(ESC_CSI);
break;
case '=':
setDecsetinternalBit(DECSET_BIT_APPLICATION_KEYPAD,true);
break;
case ']':
mOSCOrDeviceControlArgs.setLength(0);
continueSequence(ESC_OSC);
break;
case '>':
setDecsetinternalBit(DECSET_BIT_APPLICATION_KEYPAD,false);
break;
default :
unknownSequence(b);
break;
}
}
