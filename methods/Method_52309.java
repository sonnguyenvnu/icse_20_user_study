/** 
 * Find the names of variables, methods and array arguments in a PrimaryExpression.
 */
private void findExpressionNames(Node nullCompareVariable,List<String> results){
  for (int i=0; i < nullCompareVariable.jjtGetNumChildren(); i++) {
    Node child=nullCompareVariable.jjtGetChild(i);
    if (child instanceof ASTName) {
      results.add(((ASTName)child).getImage());
    }
 else     if (child instanceof ASTLiteral) {
      String literalImage=((ASTLiteral)child).getImage();
      if (literalImage != null) {
        results.add(literalImage);
      }
    }
 else     if (child instanceof ASTPrimarySuffix) {
      String name=((ASTPrimarySuffix)child).getImage();
      if (StringUtils.isNotBlank(name)) {
        results.add(name);
      }
    }
 else     if (child instanceof ASTClassOrInterfaceType) {
      String name=((ASTClassOrInterfaceType)child).getImage();
      results.add(name);
    }
    if (child.jjtGetNumChildren() > 0) {
      findExpressionNames(child,results);
    }
  }
}
