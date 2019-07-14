@Override public Object visit(ASTMethodDeclaration node,Object data){
  if (node.isAbstract() || node.isFinal() || node.isNative() || node.isSynchronized()) {
    return super.visit(node,data);
  }
  if (CLONE.equals(node.getMethodName()) && node.isPublic() && !this.hasArguments(node) && this.isMethodType(node,OBJECT) && this.isMethodThrowingType(node,exceptions)) {
    return super.visit(node,data);
  }
  ASTBlock block=node.getBlock();
  if (block == null) {
    return super.visit(node,data);
  }
  if (block.jjtGetNumChildren() != 1 || block.findDescendantsOfType(ASTStatement.class).size() != 1) {
    return super.visit(node,data);
  }
  Node statement=block.jjtGetChild(0).jjtGetChild(0);
  if (statement.jjtGetChild(0).jjtGetNumChildren() == 0) {
    return data;
  }
  Node statementGrandChild=statement.jjtGetChild(0).jjtGetChild(0);
  ASTPrimaryExpression primaryExpression;
  if (statementGrandChild instanceof ASTPrimaryExpression) {
    primaryExpression=(ASTPrimaryExpression)statementGrandChild;
  }
 else {
    List<ASTPrimaryExpression> primaryExpressions=findFirstDegreeChildrenOfType(statementGrandChild,ASTPrimaryExpression.class);
    if (primaryExpressions.size() != 1) {
      return super.visit(node,data);
    }
    primaryExpression=primaryExpressions.get(0);
  }
  ASTPrimaryPrefix primaryPrefix=findFirstDegreeChildrenOfType(primaryExpression,ASTPrimaryPrefix.class).get(0);
  if (!primaryPrefix.usesSuperModifier()) {
    return super.visit(node,data);
  }
  List<ASTPrimarySuffix> primarySuffixList=findFirstDegreeChildrenOfType(primaryExpression,ASTPrimarySuffix.class);
  if (primarySuffixList.size() != 2) {
    return super.visit(node,data);
  }
  ASTMethodDeclarator methodDeclarator=findFirstDegreeChildrenOfType(node,ASTMethodDeclarator.class).get(0);
  ASTPrimarySuffix primarySuffix=primarySuffixList.get(0);
  if (!primarySuffix.hasImageEqualTo(methodDeclarator.getImage())) {
    return super.visit(node,data);
  }
  primarySuffix=primarySuffixList.get(1);
  ASTArguments arguments=(ASTArguments)primarySuffix.jjtGetChild(0);
  ASTFormalParameters formalParameters=(ASTFormalParameters)methodDeclarator.jjtGetChild(0);
  if (formalParameters.jjtGetNumChildren() != arguments.jjtGetNumChildren()) {
    return super.visit(node,data);
  }
  if (!ignoreAnnotations) {
    ASTClassOrInterfaceBodyDeclaration parent=(ASTClassOrInterfaceBodyDeclaration)node.jjtGetParent();
    for (int i=0; i < parent.jjtGetNumChildren(); i++) {
      Node n=parent.jjtGetChild(i);
      if (n instanceof ASTAnnotation) {
        if (n.jjtGetChild(0) instanceof ASTMarkerAnnotation) {
          if ("Override".equals(((ASTName)n.jjtGetChild(0).jjtGetChild(0)).getImage())) {
            continue;
          }
        }
        return super.visit(node,data);
      }
    }
  }
  if (arguments.jjtGetNumChildren() == 0) {
    addViolation(data,node,getMessage());
  }
 else {
    ASTArgumentList argumentList=(ASTArgumentList)arguments.jjtGetChild(0);
    for (int i=0; i < argumentList.jjtGetNumChildren(); i++) {
      Node expressionChild=argumentList.jjtGetChild(i).jjtGetChild(0);
      if (!(expressionChild instanceof ASTPrimaryExpression) || expressionChild.jjtGetNumChildren() != 1) {
        return super.visit(node,data);
      }
      ASTPrimaryExpression argumentPrimaryExpression=(ASTPrimaryExpression)expressionChild;
      ASTPrimaryPrefix argumentPrimaryPrefix=(ASTPrimaryPrefix)argumentPrimaryExpression.jjtGetChild(0);
      if (argumentPrimaryPrefix.jjtGetNumChildren() == 0) {
        return super.visit(node,data);
      }
      Node argumentPrimaryPrefixChild=argumentPrimaryPrefix.jjtGetChild(0);
      if (!(argumentPrimaryPrefixChild instanceof ASTName)) {
        return super.visit(node,data);
      }
      if (formalParameters.jjtGetNumChildren() < i + 1) {
        return super.visit(node,data);
      }
      ASTName argumentName=(ASTName)argumentPrimaryPrefixChild;
      ASTFormalParameter formalParameter=(ASTFormalParameter)formalParameters.jjtGetChild(i);
      ASTVariableDeclaratorId variableId=findFirstDegreeChildrenOfType(formalParameter,ASTVariableDeclaratorId.class).get(0);
      if (!argumentName.hasImageEqualTo(variableId.getImage())) {
        return super.visit(node,data);
      }
    }
    addViolation(data,node,getMessage());
  }
  return super.visit(node,data);
}
