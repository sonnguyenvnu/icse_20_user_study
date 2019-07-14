public String getFileName(DefaultMutableTreeNode node){
  return ((TreeNodeUserObject)node.getUserObject()).getOriginalName();
}
