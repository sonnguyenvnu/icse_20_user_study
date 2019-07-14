/** 
 * @deprecated use {@link #isThreadSafeType(boolean,Set,Type)} instead. 
 */
@Deprecated public Violation isThreadSafeType(Set<String> containerTypeParameters,Type type){
  return isThreadSafeType(true,containerTypeParameters,type);
}
