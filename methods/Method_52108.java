private boolean hasClone(ASTReturnStatement ret,String varName){
  List<ASTPrimaryExpression> expressions=ret.findDescendantsOfType(ASTPrimaryExpression.class);
  for (  ASTPrimaryExpression e : expressions) {
    if (e.jjtGetChild(0) instanceof ASTPrimaryPrefix && e.jjtGetNumChildren() == 2 && e.jjtGetChild(1) instanceof ASTPrimarySuffix && ((ASTPrimarySuffix)e.jjtGetChild(1)).isArguments() && ((ASTPrimarySuffix)e.jjtGetChild(1)).getArgumentCount() == 0) {
      ASTName name=e.getFirstDescendantOfType(ASTName.class);
      if (name != null && name.hasImageEqualTo(varName + ".clone")) {
        return true;
      }
    }
  }
  return false;
}
