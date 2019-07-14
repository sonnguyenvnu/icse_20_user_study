public void doDecSetOrReset(boolean setting,int externalBit){
  int internalBit=mapDecSetBitToInternalBit(externalBit);
  if (internalBit != -1) {
    setDecsetinternalBit(internalBit,setting);
  }
switch (externalBit) {
case 1:
    break;
case 3:
  mLeftMargin=mTopMargin=0;
mBottomMargin=mRows;
mRightMargin=mColumns;
setDecsetinternalBit(DECSET_BIT_LEFTRIGHT_MARGIN_MODE,false);
blockClear(0,0,mColumns,mRows);
setCursorRowCol(0,0);
break;
case 4:
break;
case 5:
break;
case 6:
if (setting) setCursorPosition(0,0);
break;
case 7:
case 8:
case 9:
case 12:
case 25:
case 40:
case 45:
case 66:
break;
case 69:
if (!setting) {
mLeftMargin=0;
mRightMargin=mColumns;
}
break;
case 1000:
case 1001:
case 1002:
case 1003:
case 1004:
case 1005:
case 1006:
case 1015:
case 1034:
break;
case 1048:
if (setting) saveCursor();
 else restoreCursor();
break;
case 47:
case 1047:
case 1049:
{
TerminalBuffer newScreen=setting ? mAltBuffer : mMainBuffer;
if (newScreen != mScreen) {
boolean resized=!(newScreen.mColumns == mColumns && newScreen.mScreenRows == mRows);
if (setting) saveCursor();
mScreen=newScreen;
if (!setting) {
int col=mSavedStateMain.mSavedCursorCol;
int row=mSavedStateMain.mSavedCursorRow;
restoreCursor();
if (resized) {
mCursorCol=col;
mCursorRow=row;
}
}
if (resized) resizeScreen();
if (newScreen == mAltBuffer) newScreen.blockSet(0,0,mColumns,mRows,' ',getStyle());
}
break;
}
case 2004:
break;
default :
unknownParameter(externalBit);
break;
}
}
