private void addCurrentChild(){
  if (currentPath.isBranch()) {
    PathElement last=(PathElement)stack.getLastLeaf().getUserObject();
    DataFlowNode inode=currentPath.getLast();
    if (inode.getChildren().size() > last.currentChild) {
      DataFlowNode child=inode.getChildren().get(last.currentChild);
      this.currentPath.addLast(child);
    }
  }
 else {
    DataFlowNode inode=currentPath.getLast();
    DataFlowNode child=inode.getChildren().get(0);
    this.currentPath.addLast(child);
  }
}
