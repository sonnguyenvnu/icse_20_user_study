/** 
 * Sets a click listener on this host.
 * @param listener The listener to set on this host.
 */
void setComponentClickListener(ComponentClickListener listener){
  mOnClickListener=listener;
  this.setOnClickListener(listener);
}
