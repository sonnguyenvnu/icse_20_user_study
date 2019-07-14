@Override public Description matchBinary(BinaryTree tree,VisitorState state){
  TreePath path=state.getPath().getParentPath();
  while (path != null && path.getLeaf() instanceof ExpressionTree) {
    if (path.getLeaf() instanceof BinaryTree) {
      return NO_MATCH;
    }
    path=path.getParentPath();
  }
  try {
    tree.accept(CONSTANT_VISITOR,null);
    return NO_MATCH;
  }
 catch (  ArithmeticException e) {
    Description.Builder description=buildDescription(tree);
    Fix longFix=longFix(tree,state);
    if (longFix != null) {
      description.addFix(longFix);
    }
    return description.build();
  }
}
