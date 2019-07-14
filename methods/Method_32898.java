/** 
 * DECRS restore cursor - http://www.vt100.net/docs/vt510-rm/DECRC. See  {@link #saveCursor()}. 
 */
private void restoreCursor(){
  SavedScreenState state=(mScreen == mMainBuffer) ? mSavedStateMain : mSavedStateAlt;
  setCursorRowCol(state.mSavedCursorRow,state.mSavedCursorCol);
  mEffect=state.mSavedEffect;
  mForeColor=state.mSavedForeColor;
  mBackColor=state.mSavedBackColor;
  int mask=(DECSET_BIT_AUTOWRAP | DECSET_BIT_ORIGIN_MODE);
  mCurrentDecSetFlags=(mCurrentDecSetFlags & ~mask) | (state.mSavedDecFlags & mask);
  mUseLineDrawingG0=state.mUseLineDrawingG0;
  mUseLineDrawingG1=state.mUseLineDrawingG1;
  mUseLineDrawingUsesG0=state.mUseLineDrawingUsesG0;
}
