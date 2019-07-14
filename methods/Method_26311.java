/** 
 * Performs a best-effort search for the AST node of a field declaration. <p>It will only find fields declared in a lexically enclosing scope of the current location. Since double-checked locking should always be used on a private field, this should be reasonably effective.
 */
@Nullable private static JCTree findFieldDeclaration(TreePath path,VarSymbol var){
  for (TreePath curr=path; curr != null; curr=curr.getParentPath()) {
    Tree leaf=curr.getLeaf();
    if (!(leaf instanceof JCClassDecl)) {
      continue;
    }
    for (    JCTree tree : ((JCClassDecl)leaf).getMembers()) {
      if (Objects.equals(var,ASTHelpers.getSymbol(tree))) {
        return tree;
      }
    }
  }
  return null;
}
