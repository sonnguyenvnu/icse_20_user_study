private static boolean isInPrivateScope(VisitorState state){
  TreePath treePath=state.getPath();
  do {
    Tree currentLeaf=treePath.getLeaf();
    if (currentLeaf instanceof ClassTree) {
      ClassTree currentClassTree=(ClassTree)currentLeaf;
      if (currentClassTree.getModifiers().getFlags().contains(PRIVATE)) {
        return true;
      }
    }
    treePath=treePath.getParentPath();
  }
 while (treePath != null);
  return false;
}
