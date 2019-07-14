/** 
 * Ensure proper singleton serialization
 */
private Object readResolve(){
  return getInstance(iType);
}
