/** 
 * Sets a long click listener on this host.
 * @param listener The listener to set on this host.
 */
void setComponentLongClickListener(ComponentLongClickListener listener){
  mOnLongClickListener=listener;
  this.setOnLongClickListener(listener);
}
