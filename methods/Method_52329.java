/** 
 * Checks whether the given node contains a qualified name, consisting of one ASTPrimaryPrefix and one or more ASTPrimarySuffix nodes.
 * @param node the node
 * @return <code>true</code> if it is a qualified name
 */
private boolean isPartOfQualifiedName(Node node){
  return node.jjtGetChild(0) instanceof ASTPrimaryPrefix && !node.findChildrenOfType(ASTPrimarySuffix.class).isEmpty();
}
