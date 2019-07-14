/** 
 * Used for debugging. Logs the internal representation of children to default logger.
 */
private void logChildren(){
  Log.d(TAG,"internal representation of views on the screen");
  for (int i=0; i < getChildCount(); i++) {
    View child=getChildAt(i);
    Log.d(TAG,"item " + getPosition(child) + ", coord:" + mOrientationHelper.getDecoratedStart(child));
  }
  Log.d(TAG,"==============");
}
