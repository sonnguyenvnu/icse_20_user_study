/** 
 * Sets a touch listener on this host.
 * @param listener The listener to set on this host.
 */
void setComponentTouchListener(ComponentTouchListener listener){
  mOnTouchListener=listener;
  setOnTouchListener(listener);
}
