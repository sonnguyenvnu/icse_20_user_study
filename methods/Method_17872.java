/** 
 * Sets a focus change listener on this host.
 * @param listener The listener to set on this host.
 */
void setComponentFocusChangeListener(ComponentFocusChangeListener listener){
  mOnFocusChangeListener=listener;
  this.setOnFocusChangeListener(listener);
}
