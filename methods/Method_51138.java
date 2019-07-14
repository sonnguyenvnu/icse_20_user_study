private void addNewPseudoPathElement(DefaultMutableTreeNode level,DataFlowNode ref){
  addNode(level,new PathElement(currentPath.getLast(),ref));
}
