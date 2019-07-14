protected void fireTreeModelEvent(TreeModelEvent e){
  for (  TreeModelListener listener : listeners) {
    listener.treeNodesChanged(e);
  }
}
