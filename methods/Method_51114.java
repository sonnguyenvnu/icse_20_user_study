@Override public void reverseParentPathsTo(DataFlowNode destination){
  while (!parents.isEmpty()) {
    DataFlowNode parent=parents.get(0);
    parent.removePathToChild(this);
    parent.addPathToChild(destination);
  }
}
