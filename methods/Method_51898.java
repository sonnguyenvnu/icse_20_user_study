/** 
 * Returns true if this node is a wildcard argument (bounded or not).
 */
public boolean isWildcard(){
  return getTypeNode() == null;
}
