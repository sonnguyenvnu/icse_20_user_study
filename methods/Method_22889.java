/** 
 * Updates update count in the UI. Called on EDT.
 * @param count number of available updates
 */
public void setUpdatesAvailable(int count){
  footer.setUpdateCount(count);
}
