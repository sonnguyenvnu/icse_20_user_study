/** 
 * Creates a new class scope for an AST node. The scope on top of the stack is set as the parent of the new scope, which is then also stored on the scope stack.
 * @param node the AST node for which the scope has to be created.
 * @throws java.util.EmptyStackException if the scope stack is empty.
 */
private void createClassScope(JavaNode node){
  Scope s=((JavaNode)node.jjtGetParent()).getScope();
  ClassNameDeclaration classNameDeclaration=new ClassNameDeclaration(node);
  s.addDeclaration(classNameDeclaration);
  if (node instanceof ASTClassOrInterfaceBody) {
    addScope(new ClassScope(classNameDeclaration),node);
  }
 else {
    addScope(new ClassScope(node.getImage(),classNameDeclaration),node);
  }
}
