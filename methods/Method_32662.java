/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Seconds.seconds(getValue());
}
