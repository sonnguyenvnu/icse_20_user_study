/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Years.years(getValue());
}
