/** 
 * Returns if the class has a known deep copy strategy.
 * @param clazz the class of the object being copied
 * @return if the class has a known deep copy strategy
 */
protected boolean canDeeplyCopy(Class<?> clazz){
  return deepCopyStrategies.containsKey(clazz);
}
