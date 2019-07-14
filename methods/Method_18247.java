/** 
 * Returns true if this KeyHandler has already recorded a component with the given key. 
 */
public boolean hasKey(String key){
  return mKnownGlobalKeys.contains(key);
}
