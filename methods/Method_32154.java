/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Days.days(getValue());
}
