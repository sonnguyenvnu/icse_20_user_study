/** 
 * Ignore all annotations, until anything, that is not an annotation and return this node
 * @param child the node from where to start the search
 * @return the first child or the first child after annotations
 */
private Node skipAnnotations(Node child){
  Node nextChild=child.jjtGetChild(0);
  for (int j=0; j < child.jjtGetNumChildren(); j++) {
    if (!(child.jjtGetChild(j) instanceof ASTAnnotation)) {
      nextChild=child.jjtGetChild(j);
      break;
    }
  }
  return nextChild;
}
