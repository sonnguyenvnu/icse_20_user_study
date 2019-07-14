boolean forLoopVariable(VariableTree tree,TreePath path){
  Tree parent=path.getParentPath().getLeaf();
  if (!(parent instanceof ForLoopTree)) {
    return false;
  }
  ForLoopTree forLoop=(ForLoopTree)parent;
  return forLoop.getInitializer().contains(tree);
}
