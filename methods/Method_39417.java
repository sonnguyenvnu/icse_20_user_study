/** 
 * Returns number of destroyable beans that have been registered.
 */
protected int totalRegisteredDestroyableBeans(){
  if (destroyableBeans == null) {
    return 0;
  }
  return destroyableBeans.size();
}
