/** 
 * @param runnable
 */
protected void loadAfterCorrect(){
  if (isCurrentUserCorrect() == false) {
    Log.e(TAG,"loadAfterCorrect  isCurrentUserCorrect() == false >> return;");
    return;
  }
  if (onDataChangedListener != null) {
    onDataChangedListener.onDataChanged();
  }
}
