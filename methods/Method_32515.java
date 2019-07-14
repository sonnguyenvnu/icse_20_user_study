/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Minutes.minutes(getValue());
}
