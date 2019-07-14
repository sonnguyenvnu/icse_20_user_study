private ASTPrimaryExpression findNullCompareExpression(ASTEqualityExpression equalityExpression){
  List<ASTPrimaryExpression> primaryExpressions=equalityExpression.findDescendantsOfType(ASTPrimaryExpression.class);
  for (  ASTPrimaryExpression primaryExpression : primaryExpressions) {
    List<ASTPrimaryPrefix> primaryPrefixes=primaryExpression.findDescendantsOfType(ASTPrimaryPrefix.class);
    for (    ASTPrimaryPrefix primaryPrefix : primaryPrefixes) {
      if (primaryPrefix.hasDescendantOfType(ASTName.class)) {
        return primaryExpression;
      }
    }
  }
  return null;
}
