/** 
 * Set the callbacks for lifecycle notifications.
 * @param callbacks The callbacks for lifecycle notifications. (default: none)
 */
public void setCallbacks(Callback... callbacks){
  this.callbacks.clear();
  this.callbacks.addAll(Arrays.asList(callbacks));
}
