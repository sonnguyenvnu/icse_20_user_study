/** 
 * DECSC save cursor - http://www.vt100.net/docs/vt510-rm/DECSC . See  {@link #restoreCursor()}. 
 */
private void saveCursor(){
  SavedScreenState state=(mScreen == mMainBuffer) ? mSavedStateMain : mSavedStateAlt;
  state.mSavedCursorRow=mCursorRow;
  state.mSavedCursorCol=mCursorCol;
  state.mSavedEffect=mEffect;
  state.mSavedForeColor=mForeColor;
  state.mSavedBackColor=mBackColor;
  state.mSavedDecFlags=mCurrentDecSetFlags;
  state.mUseLineDrawingG0=mUseLineDrawingG0;
  state.mUseLineDrawingG1=mUseLineDrawingG1;
  state.mUseLineDrawingUsesG0=mUseLineDrawingUsesG0;
}
