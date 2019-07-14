/** 
 * Returns the qualification to access a field of the given class node from within the given tree path (which MUST be within the class node scope).
 */
private static String getExplicitQualification(TreePath path,ClassTree tree,VisitorState state){
  for (  Tree node : path) {
    if (node.equals(tree)) {
      break;
    }
    if (node instanceof ClassTree) {
      if (ASTHelpers.getSymbol(node).isSubClass(ASTHelpers.getSymbol(tree),state.getTypes())) {
        return "super.";
      }
      return tree.getSimpleName() + ".this.";
    }
  }
  return "this.";
}
