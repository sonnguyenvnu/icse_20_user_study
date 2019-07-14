private String findLambdaScopeNameSegment(ASTLambdaExpression node){
  Node parent=node.jjtGetParent();
  while (parent != null && !(parent instanceof ASTFieldDeclaration) && !(parent instanceof ASTEnumConstant) && !(parent instanceof ASTInitializer) && !(parent instanceof MethodLikeNode)) {
    parent=parent.jjtGetParent();
  }
  if (parent == null) {
    throw new IllegalStateException("The enclosing scope must exist.");
  }
  if (parent instanceof ASTInitializer) {
    return ((ASTInitializer)parent).isStatic() ? "static" : "new";
  }
 else   if (parent instanceof ASTConstructorDeclaration) {
    return "new";
  }
 else   if (parent instanceof ASTLambdaExpression) {
    return "null";
  }
 else   if (parent instanceof ASTEnumConstant) {
    return "static";
  }
 else   if (parent instanceof ASTFieldDeclaration) {
    ASTFieldDeclaration field=(ASTFieldDeclaration)parent;
    if (field.isStatic() || field.isInterfaceMember()) {
      return "static";
    }
    if (innermostEnclosingTypeName.peek().isAnonymousClass()) {
      return "";
    }
 else     if (innermostEnclosingTypeName.peek().isLocalClass()) {
      return classNames.head();
    }
 else {
      return "new";
    }
  }
 else {
    return ((ASTMethodDeclaration)parent).getMethodName();
  }
}
