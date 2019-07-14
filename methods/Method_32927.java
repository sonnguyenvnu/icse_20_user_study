/** 
 * Notify the  {@link #mChangeCallback} that the screen has changed. 
 */
protected void notifyScreenUpdate(){
  mChangeCallback.onTextChanged(this);
}
