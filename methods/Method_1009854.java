/** 
 * Refresh the current read marker event id and make all the checks again
 */
private void refresh(){
  Log.d(LOG_TAG,"refresh");
  updateReadMarkerValue();
  updateJumpToBanner();
  checkUnreadMessage();
}
