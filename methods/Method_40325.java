/** 
 * Returns  {@code true} if this qname represents a simple, non-dotted modulename such as "os", "random" or "foo".
 */
public boolean isUnqualified(){
  return isTop() && isBottom();
}
