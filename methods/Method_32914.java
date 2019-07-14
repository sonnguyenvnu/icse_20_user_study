/** 
 * Set the cursor mode, but limit it to margins if  {@link #DECSET_BIT_ORIGIN_MODE} is enabled. 
 */
private void setCursorColRespectingOriginMode(int col){
  setCursorPosition(col,mCursorRow);
}
