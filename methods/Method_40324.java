/** 
 * Returns  {@code true} if this is the last/bottom component of the chain,or if the name is unqualified (i.e. has only one component, no dots).
 */
public boolean isBottom(){
  return next == null;
}
