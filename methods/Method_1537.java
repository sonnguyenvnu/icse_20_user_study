/** 
 * Determines if the supplied value is 'reusable'. This is called during  {@link #release(Object)}, and determines if the value can be added to the freelists of the pool (for future reuse), or must be released right away. Subclasses can override this to provide custom implementations
 * @param value the value to test for reusability
 * @return true if the value is reusable
 */
protected boolean isReusable(V value){
  Preconditions.checkNotNull(value);
  return true;
}
