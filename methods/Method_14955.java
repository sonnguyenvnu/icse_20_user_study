/** 
 */
protected void invalidate(){
  if (isRunning() == false) {
    isDataChanged=true;
    Log.w(TAG,"onDataChanged  isRunning() == false >> return;");
    return;
  }
  isDataChanged=false;
  setCurrentUser();
  loadAfterCorrect();
}
