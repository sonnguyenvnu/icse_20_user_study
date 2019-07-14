/** 
 * Process byte while in the  {@link #ESC_CSI_QUESTIONMARK} escape state. 
 */
private void doCsiQuestionMark(int b){
switch (b) {
case 'J':
case 'K':
    mAboutToAutoWrap=false;
  int fillChar=' ';
int startCol=-1;
int startRow=-1;
int endCol=-1;
int endRow=-1;
boolean justRow=(b == 'K');
switch (getArg0(0)) {
case 0:
startCol=mCursorCol;
startRow=mCursorRow;
endCol=mColumns;
endRow=justRow ? (mCursorRow + 1) : mRows;
break;
case 1:
startCol=0;
startRow=justRow ? mCursorRow : 0;
endCol=mCursorCol + 1;
endRow=mCursorRow + 1;
break;
case 2:
startCol=0;
startRow=justRow ? mCursorRow : 0;
endCol=mColumns;
endRow=justRow ? (mCursorRow + 1) : mRows;
break;
default :
unknownSequence(b);
break;
}
long style=getStyle();
for (int row=startRow; row < endRow; row++) {
for (int col=startCol; col < endCol; col++) {
if ((TextStyle.decodeEffect(mScreen.getStyleAt(row,col)) & TextStyle.CHARACTER_ATTRIBUTE_PROTECTED) == 0) mScreen.setChar(col,row,fillChar,style);
}
}
break;
case 'h':
case 'l':
if (mArgIndex >= mArgs.length) mArgIndex=mArgs.length - 1;
for (int i=0; i <= mArgIndex; i++) doDecSetOrReset(b == 'h',mArgs[i]);
break;
case 'n':
switch (getArg0(-1)) {
case 6:
mSession.write(String.format(Locale.US,"\033[?%d;%d;1R",mCursorRow + 1,mCursorCol + 1));
break;
default :
finishSequence();
return;
}
break;
case 'r':
case 's':
if (mArgIndex >= mArgs.length) mArgIndex=mArgs.length - 1;
for (int i=0; i <= mArgIndex; i++) {
int externalBit=mArgs[i];
int internalBit=mapDecSetBitToInternalBit(externalBit);
if (internalBit == -1) {
Log.w(EmulatorDebug.LOG_TAG,"Ignoring request to save/recall decset bit=" + externalBit);
}
 else {
if (b == 's') {
mSavedDecSetFlags|=internalBit;
}
 else {
doDecSetOrReset((mSavedDecSetFlags & internalBit) != 0,externalBit);
}
}
}
break;
case '$':
continueSequence(ESC_CSI_QUESTIONMARK_ARG_DOLLAR);
return;
default :
parseArg(b);
}
}
