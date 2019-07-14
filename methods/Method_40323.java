/** 
 * Returns  {@code true} if this is the first/top component of the chain,or if the name is unqualified (i.e. has only one component, no dots).
 */
public boolean isTop(){
  return getPrevious() == null;
}
