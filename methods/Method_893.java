/** 
 * annotation is sibling of classOrInterface declaration or method declaration
 * @param node transactional annotation
 * @param clz  classOrInterface declaration or method declaration
 * @param < T >  generic
 * @return sibling node
 */
private <T>T getSiblingForType(ASTAnnotation node,Class<T> clz){
  Node parent=node.jjtGetParent();
  int num=parent.jjtGetNumChildren();
  for (int i=0; i < num; i++) {
    Node child=parent.jjtGetChild(i);
    if (clz.isAssignableFrom(child.getClass())) {
      return clz.cast(child);
    }
    if (!(child instanceof ASTAnnotation)) {
      return null;
    }
  }
  return null;
}
