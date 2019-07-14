/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Hours.hours(getValue());
}
