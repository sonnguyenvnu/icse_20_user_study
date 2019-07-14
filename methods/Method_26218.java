/** 
 * Determines whether the expression contains a reference to one of the enclosing method's parameters. <p>TODO(eaftan): Extract this to ASTHelpers.
 * @param path the path to the current tree node
 * @param tree the node to compare against the parameters
 * @return whether the argument is a parameter to the enclosing method
 */
private static boolean hasMethodParameter(TreePath path,ExpressionTree tree){
  Set<Symbol> symbols=new HashSet<>();
  for (  IdentifierTree ident : getVariableUses(tree)) {
    Symbol sym=ASTHelpers.getSymbol(ident);
    if (sym.isLocal()) {
      symbols.add(sym);
    }
  }
  while (path != null && !(path.getLeaf() instanceof MethodTree)) {
    path=path.getParentPath();
  }
  if (path == null) {
    throw new IllegalStateException("Should have an enclosing method declaration");
  }
  MethodTree methodDecl=(MethodTree)path.getLeaf();
  for (  VariableTree param : methodDecl.getParameters()) {
    if (symbols.contains(ASTHelpers.getSymbol(param))) {
      return true;
    }
  }
  return false;
}
