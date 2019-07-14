private boolean hasMoreChildren(){
  PathElement e=(PathElement)stack.getLastLeaf().getUserObject();
  return e.currentChild + 1 < e.node.getChildren().size();
}
