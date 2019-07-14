/** 
 * Resolves singletons.
 * @return the singleton instance
 */
private Object readResolve(){
  return Months.months(getValue());
}
