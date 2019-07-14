/** 
 * Returns true if this node is a try-with-resources, in which case it has a ResourceSpecification child node.
 */
public boolean isTryWithResources(){
  return getFirstChildOfType(ASTResourceSpecification.class) != null;
}
