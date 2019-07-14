public String getName(){
  if (node instanceof ASTConstructorDeclaration) {
    return this.getEnclosingScope(ClassScope.class).getClassName();
  }
  return node.jjtGetChild(1).getImage();
}
