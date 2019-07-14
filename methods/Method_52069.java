/** 
 * Gets the image of the ASTName node found by {@link Node#getFirstDescendantOfType(Class)} if it is the greatgrandchildof the given node. E.g. <pre> n = Expression || StatementExpression PrimaryExpression PrimaryPrefix Name </pre>
 * @param n the node to search
 * @return the image of the first ASTName or <code>null</code>
 */
protected String getFirstNameImage(Node n){
  ASTName name=n.getFirstDescendantOfType(ASTName.class);
  if (name != null && name.getNthParent(3) == n) {
    return name.getImage();
  }
  return null;
}
