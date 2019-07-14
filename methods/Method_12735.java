/** 
 * Check if a started service with the specified component exists in the registry
 * @param component
 * @return
 */
public boolean isServiceAvailable(ComponentName component){
  return this.mServices.containsKey(component);
}
