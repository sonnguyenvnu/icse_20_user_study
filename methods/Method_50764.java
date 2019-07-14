/** 
 * Traverses all new declarations to find PageReferences
 * @param node
 * @param data
 */
private void checkNewObjects(ASTNewObjectExpression node,Object data){
  ASTMethod method=node.getFirstParentOfType(ASTMethod.class);
  if (method != null && Helper.isTestMethodOrClass(method)) {
    return;
  }
  if (node.getType().equalsIgnoreCase(PAGEREFERENCE)) {
    getObjectValue(node,data);
  }
}
