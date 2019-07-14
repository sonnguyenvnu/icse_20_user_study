/** 
 * Gets the name of the variable returned. Some examples: <br> for this.foo returns foo <br> for foo returns foo <br> for foo.bar returns foo.bar
 * @param ret a return statement to evaluate
 * @return the name of the variable associated or <code>null</code> if itcannot be detected
 */
protected final String getReturnedVariableName(ASTReturnStatement ret){
  if (hasTernaryCondition(ret) && hasTernaryNullCheck(ret)) {
    return ret.getFirstDescendantOfType(ASTConditionalExpression.class).jjtGetChild(0).getFirstDescendantOfType(ASTName.class).getImage();
  }
  final ASTName n=ret.getFirstDescendantOfType(ASTName.class);
  if (n != null) {
    return n.getImage();
  }
  final ASTPrimarySuffix ps=ret.getFirstDescendantOfType(ASTPrimarySuffix.class);
  if (ps != null) {
    return ps.getImage();
  }
  return null;
}
